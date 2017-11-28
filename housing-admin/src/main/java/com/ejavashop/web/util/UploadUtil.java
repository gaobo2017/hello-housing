package com.ejavashop.web.util;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;

/**
 * 上传图片工具类，上传到服务器
 * 
 * @Filename: UploadUtil.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public class UploadUtil {
    private static UploadUtil   uploadUtil;
    private static final String BOUNDARY     = UUID.randomUUID().toString(); // 边界标识 随机生成
    private static final String PREFIX       = "--";
    private static final String LINE_END     = "\r\n";
    private static final String CONTENT_TYPE = "multipart/form-data";       // 内容类型

    /**
     * 获得上传成功后的路径
     */
    private static String       result       = null;

    private UploadUtil() {

    }

    /**
     * 单例模式获取上传工具类
     * @return
     */
    public static UploadUtil getInstance() {
        result = null;
        if (null == uploadUtil) {
            uploadUtil = new UploadUtil();
        }
        return uploadUtil;
    }

    private int                 readTimeOut                = 30 * 1000;               // 读取超时 30s
    private int                 connectTimeout             = 60 * 1000;               // 超时时间 1分钟

    /***
     * 请求使用多长时间
     */
    private static int          requestTime                = 0;

    private static final String CHARSET                    = "utf-8";                 // 设置编码

    /**
     * UEDITOR 上传图片
     */
    private static final String UTLPATH_IMAGE_UEDITOR      = DomainUrlUtil.EJS_IMAGE_RESOURCES
                                                             + "/imageUtilUmeditor";

    /**
     * 品牌 上传图片
     */
    private static final String BRAND_IMAGE_PATH           = DomainUrlUtil.EJS_IMAGE_RESOURCES
                                                             + "/imageUtilBrand";

    /**
     * 广告上传图片
     */
    public static final String  ADV_IMAGE_PATH             = DomainUrlUtil.EJS_IMAGE_RESOURCES
                                                             + "/imageUtilAdv";

    /**
     * 移动端首页上传图片
     */
    public static final String  M_INDEX_IMAGE_PATH         = DomainUrlUtil.EJS_IMAGE_RESOURCES
                                                             + "/imageMobileUtilAdv";


    /**
     * 通用图片上传
     */
    public static final String  COMMON_IMAGE_UPLOAD_PATH   = DomainUrlUtil.EJS_IMAGE_RESOURCES
                                                             + "/commonImageUploadServlet";
    
    /**
     * 上传至图片服务器，不指定图片宽高，不剪裁图片
     * @param file
     * @param request
     * @param param
     * @return
     */
    public String uploadFile2ImageServer(String file, HttpServletRequest request,
                                         Map<String, String> param) {
        return uploadFile2ImageServer(file, request, null, null, param);
    }

    /**
     * 上传图片到图片服务器，根据指定宽高剪裁图片
     * @param file
     * @param request
     * @param width
     * @param height
     * @param params
     * @return
     */
    public String uploadFile2ImageServer(String file, HttpServletRequest request, String width,
                                         String height, Map<String, String> params) {
        if (width != null && height != null) {
            params.put("pic_width", width);
            params.put("pic_height", height);
        }
        uploadFile(getTempFile(request, file), "pic", COMMON_IMAGE_UPLOAD_PATH, params);
        if (result != null && !"".equals(result)) {
            return result.replaceAll("\\\\", "/");
        }
        return null;

    }
    
    /**
     * UEditor 上传图片
     * @param file 需要上传的图片
     * @param request
     * @return 图片服务器图片的URL
     */
    public String uEditorUploaderImage(File file, HttpServletRequest request) {
        String fileKey = "pic";
        Map<String, String> params = new HashMap<String, String>();
        params.put(ConstantsEJS.SELLER_ID, "1");

        uploadFile(file, fileKey, UTLPATH_IMAGE_UEDITOR, params);
        return result;
    }

    /**
     * 上传文件到服务器本地
     * @param request
     * @param formFile 表单的值 -->页面input 提交的file
     * @param savePath 保存路径
     * @return
     */
    public Map<String, Object> uploadFile2LocServer(HttpServletRequest request, String formFile,
                                                    String savePath) {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //表单文件流
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(formFile);

        //扩展名
        String extend = file.getOriginalFilename()
            .substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();

        //保存路径：服务器项目根目录
        String saveFilePath = request.getServletContext().getRealPath("/") + savePath;

        //统一所有路径符号
        saveFilePath = saveFilePath.replaceAll("\\\\", "/");

        //返回信息集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //随机生成文件名
            String saveFileName = UUID.randomUUID().toString() + "." + extend;

            if ((file != null) && (!file.isEmpty())) {
                //文件大小
                float fileSize = Float.valueOf(file.getSize()).floatValue();

                File path = new File(saveFilePath);
                if (!path.exists()) {
                    //创建虚拟目录
                    path.mkdirs();
                }
                //保存文件
                saveFile2Loca(file, saveFilePath, saveFileName);

                //如果是图片，则存储其大小信息
                if (isImg(extend)) {
                    File img = new File(saveFilePath + "/" + saveFileName);
                    BufferedImage bis = ImageIO.read(img);
                    int w = bis.getWidth();
                    int h = bis.getHeight();
                    map.put("width", Integer.valueOf(w));
                    map.put("height", Integer.valueOf(h));
                }
                map.put("mime", extend);
                map.put("fileName", saveFileName);
                map.put("fileSize", Float.valueOf(fileSize));
                map.put("oldName", file.getOriginalFilename());
                map.put("fileAbsolutePath", saveFilePath + "/" + saveFileName);
                map.put("filePath", savePath + "/" + saveFileName);
            } else {
                map.put("width", Integer.valueOf(0));
                map.put("height", Integer.valueOf(0));
                map.put("mime", "");
                map.put("fileName", "");
                map.put("fileSize", Float.valueOf(0.0F));
                map.put("oldName", "");
                map.put("fileAbsolutePath", "");
                map.put("filePath", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 保存文件到服务器本地
     * @param file
     * @param saveFilePath
     * @param saveFileName
     */
    private void saveFile2Loca(CommonsMultipartFile file, String saveFilePath, String saveFileName) {
        InputStream is = null;
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new FileOutputStream(saveFilePath + File.separator
                                                            + saveFileName));
            is = file.getInputStream();
            int size = Integer.valueOf((int) file.getSize());
            byte[] buffer = new byte[size];
            while (is.read(buffer) > 0) {
                out.write(buffer);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 是否是图片
     * @param extend
     * @return
     */
    public static boolean isImg(String extend) {
        boolean ret = false;
        List<String> list = new ArrayList<String>();
        list.add("jpg");
        list.add("jpeg");
        list.add("bmp");
        list.add("gif");
        list.add("png");
        list.add("tif");
        for (String s : list) {
            if (s.equals(extend)) {
                ret = true;
            }
        }
        return ret;
    }

    //    /**
    //     * 上传图片到图片服务器
    //     * @param file
    //     * @param request
    //     * @param width
    //     * @param height
    //     * @param param
    //     * @return
    //     */
    //    public String uploadFile2ImageServer(String file, HttpServletRequest request, String width,
    //                                         String height, String param) {
    //        Map<String, String> params = new HashMap<String, String>();
    //        params.put("adv", param == null ? "advPos" : param);
    //        if (width != null && height != null) {
    //            params.put("pic_width", width);
    //            params.put("pic_height", height);
    //        }
    //        uploadFile(getTempFile(request, file), "pic", ADV_IMAGE_PATH, params);
    //        if (result != null && !"".equals(result)) {
    //            return result.replaceAll("\\\\", "/");
    //        }
    //        return null;
    //    }
    //
    //    public String uploadAdv2ImageServer(String file, HttpServletRequest request, String param) {
    //        return uploadFile2ImageServer(file, request, null, null, param);
    //    }

    /**
     * 上传图片到图片服务器
     * @param file
     * @param request
     * @param width
     * @param height
     * @param param
     * @return
     */
    public String advUploadFile2ImageServer(String file, HttpServletRequest request) {
        //        Map<String, String> params = new HashMap<String, String>();
        //        params.put("adv", param == null ? "advPos" : param);
        //        if (width != null && height != null) {
        //            params.put("pic_width", width);
        //            params.put("pic_height", height);
        //        }
        advUploaderImage(getTempFile(request, file), request, true);
        if (result != null && !"".equals(result)) {
            return result.replaceAll("\\\\", "/");
        }
        return null;
    }

    /**
     * 从表单文件流中读出文件
     * @param request
     * @param file
     * @return
     */
    private File getTempFile(HttpServletRequest request, String file) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //表单文件流
        CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest.getFile(file);
        File tmpFile = null;
        try {
            if (null != multipartFile && multipartFile.getSize() > 0) {
                //扩展名
                String extend = multipartFile.getOriginalFilename()
                    .substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1)
                    .toLowerCase();
                //随机生成文件名
                String saveFileName = UUID.randomUUID().toString() + "." + extend;
                //TODO上传图片修改
                tmpFile = new File(buildImgPath(request) + "/" + saveFileName);
                if (!multipartFile.isEmpty()) {
                    byte[] bytes = multipartFile.getBytes();
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                        tmpFile));
                    stream.write(bytes);
                    stream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmpFile;
    }

    /**
     * 构建图片上传路径
     * @param request
     * @return
     */
    @SuppressWarnings("deprecation")
    private String buildImgPath(HttpServletRequest request) {
        String path = "upload";
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        path += "/" + formater.format(new Date());
        path = request.getRealPath(path);
        File dir = new File(path);
        if (!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    //    /**
    //     * 商品 上传图片
    //     * @param file 需要上传的图片
    //     * @param request
    //     * @return 图片服务器图片的URL
    //     */
    //    public String productUploaderImage(File file, HttpServletRequest request) {
    //        String fileKey = "pic";
    //        Map<String, String> params = new HashMap<String, String>();
    //        params.put(ConstantsJM.SELLER_ID, WebSellerSession.getAdminUser(request).getId() + "");
    //
    //        uploadFile(file, fileKey, PRODUCT_IMAGE_PATH, params);
    //        return result;
    //    }

    /**
     * 品牌上传
     * @param file 上图的图片
     * @param request 请求的路径
     * @param flag:
     *          <li>true:平台上传；</li>
     *          <li>false:商家上传；</li>
     * @return
     */
    public String brandUploaderImage(File file, HttpServletRequest request, boolean flag) {
        String fileKey = "pic";
        Map<String, String> params = new HashMap<String, String>();
        if (flag) {
            params.put(ConstantsEJS.BRAND, ConstantsEJS.BRAND);
        } else {
            //params.put(ConstantsJM.SELLER_ID, WebSellerSession.getAdminUser(request).getId() + "");
        }
        uploadFile(file, fileKey, BRAND_IMAGE_PATH, params);
        return result;
    }

    /**
     * 品广告上传
     * @param file 上图的图片
     * @param request 请求的路径
     * @param flag:
     *          <li>true:平台上传；</li>
     *          <li>false:商家上传；</li>
     * @return
     */
    public String advUploaderImage(File file, HttpServletRequest request, boolean flag) {
        String fileKey = "pic";
        Map<String, String> params = new HashMap<String, String>();
        if (flag) {
            params.put(ConstantsEJS.AD_FRONT, ConstantsEJS.AD_FRONT);
        } else {
            //params.put(ConstantsJM.SELLER_ID, WebSellerSession.getAdminUser(request).getId() + "");
        }
        uploadFile(file, fileKey, ADV_IMAGE_PATH, params);
        return result;
    }

    /**
     * 上传文件
     * @param file 需要上传的文件
     * @param fileKey 在网页上<input type=file name=xxx/> xxx就是这里的fileKey
     * @param RequestURL 请求的URL
     * @param param 传递参数
     */
    public void uploadFile(File file, String fileKey, String RequestURL, Map<String, String> param) {
        if (file == null || (!file.exists())) {
            return;
        }

        requestTime = 0;

        long requestTime = System.currentTimeMillis();
        long responseTime = 0;

        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(readTimeOut);
            conn.setConnectTimeout(connectTimeout);
            conn.setDoInput(true); // 允许输入流
            conn.setDoOutput(true); // 允许输出流
            conn.setUseCaches(false); // 不允许使用缓存
            conn.setRequestMethod("POST"); // 请求方式
            conn.setRequestProperty("Charset", CHARSET); // 设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
            //			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            /**
             * 当文件不为空，把文件包装并且上传
             */
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            StringBuffer sb = null;
            String params = "";

            /***
             * 以下是用于上传参数
             */
            if (param != null && param.size() > 0) {
                Iterator<String> it = param.keySet().iterator();
                while (it.hasNext()) {
                    sb = null;
                    sb = new StringBuffer();
                    String key = it.next();
                    String value = param.get(key);
                    sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
                    sb.append("Content-Disposition: form-data; name=\"").append(key).append("\"")
                        .append(LINE_END).append(LINE_END);
                    sb.append(value).append(LINE_END);
                    params = sb.toString();
                    dos.write(params.getBytes());
                    //					dos.flush();
                }
            }

            sb = null;
            params = null;
            sb = new StringBuffer();

            sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
            sb.append("Content-Disposition:form-data; name=\"" + fileKey + "\"; filename=\""
                      + file.getName() + "\"" + LINE_END);
            sb.append("Content-Type:image/pjpeg" + LINE_END); // 这里配置的Content-type很重要的 ，用于服务器端辨别文件的类型的
            sb.append(LINE_END);
            params = sb.toString();
            sb = null;

            dos.write(params.getBytes());
            /**上传文件*/
            InputStream is = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                dos.write(bytes, 0, len);
            }
            is.close();

            dos.write(LINE_END.getBytes());
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
            dos.write(end_data);
            dos.flush();

            /**
             * 获取响应码 200=成功 当响应成功，获取响应的流
             */
            int res = conn.getResponseCode();
            responseTime = System.currentTimeMillis();
            requestTime = (int) ((responseTime - requestTime) / 1000);
            if (res == 200) {
                InputStream input = conn.getInputStream();
                StringBuffer sb1 = new StringBuffer();
                int ss;
                while ((ss = input.read()) != -1) {
                    sb1.append((char) ss);
                }
                result = sb1.toString();
            }
            file.delete();
            return;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public int getReadTimeOut() {
        return readTimeOut;
    }

    public void setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    /**
     * 获取上传使用的时间
     * @return
     */
    public static int getRequestTime() {
        return requestTime;
    }

    public String getResult() {
        return result;
    }

    /**
     * 移动端首页上传图片
     * @param file
     * @param request
     * @return
     */
    public String mIndexUploadFile2ImageServer(String file, HttpServletRequest request) {
        mIndexUploaderImage(getTempFile(request, file), request);
        if (result != null && !"".equals(result)) {
            return result.replaceAll("\\\\", "/");
        }
        return null;
    }

    /**
     * @param file 上图的图片
     * @param request 请求的路径
     * @return
     */
    public String mIndexUploaderImage(File file, HttpServletRequest request) {
        String fileKey = "pic";
        Map<String, String> params = new HashMap<String, String>();
        params.put(ConstantsEJS.AD_MOBILE, ConstantsEJS.AD_MOBILE);
        uploadFile(file, fileKey, M_INDEX_IMAGE_PATH, params);
        return result;
    }

}
