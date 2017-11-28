package com.ejavashop.web.controller.product;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.HttpJsonResultForAjax;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.product.ProductBrand;
import com.ejavashop.entity.seller.SellerApplyBrand;
import com.ejavashop.entity.system.Code;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.product.IProductBrandService;
import com.ejavashop.service.product.ISellerBrandService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebAdminSession;
import com.ejavashop.web.util.freemarkerutil.CodeManager;

/**
 * 商品品牌
 */
@Controller
@RequestMapping(value = "admin/product/brand")
public class BrandController extends BaseController {
    @Resource
    private IProductBrandService productBrandService;

    @Resource
    private ISellerBrandService  sellerApplyBrandService;

    /**
     * 默认，显示全部状态的品牌
     *
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/product/brand/brandlist";
    }

    @RequestMapping(value = "upload", method = { RequestMethod.GET })
    public String upload() {
        return "admin/product/brand/imagetest";
    }

    /**
     * 待审核品牌
     *
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "todo", method = { RequestMethod.GET })
    public String toDo(Map<String, Object> dataMap) throws Exception {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        dataMap.put("q_state", String.valueOf(SellerApplyBrand.Status.COMMIT.getValue()));
        return "admin/product/brand/todolist";
    }

    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductBrand>> list(HttpServletRequest request,
                                                                 Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("q_state", String.valueOf(ProductBrand.Status.SUCCESS.getValue()));//只显示审核通过的品牌
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ProductBrand>> serviceResult = productBrandService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductBrand>> jsonResult = new HttpJsonResult<List<ProductBrand>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 取出所有可用的品牌
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list_no_page", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductBrand>> list_no_page(HttpServletRequest request,
                                                                         Map<String, Object> dataMap) {
        ServiceResult<List<ProductBrand>> serviceResult = productBrandService.listNoPage();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductBrand>> jsonResult = new HttpJsonResult<List<ProductBrand>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(serviceResult.getResult().size());
        return jsonResult;
    }

    /**
     * 待审核列表
     *
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "todolist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SellerApplyBrand>> todoList(HttpServletRequest request,
                                                                         Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<SellerApplyBrand>> serviceResult = sellerApplyBrandService.page(
            queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<SellerApplyBrand>> jsonResult = new HttpJsonResult<List<SellerApplyBrand>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) {
        Code code = new Code();
        dataMap.put("code", code);
        return "admin/product/brand/brandadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> create(ProductBrand brand, MultipartHttpServletRequest request,
                                         Map<String, Object> dataMap) throws IOException {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }

        if (!StringUtil.is_alpha(brand.getNameFirst())) {
            jsonResult.setMessage("首字母输入错误，请重新输入");
            return jsonResult;
        }

        //上传品牌图片
        MultipartFile multipartFile = request.getFile("imageFile");
        if (null != multipartFile && multipartFile.getSize() > 0) {
            String originalFilename = multipartFile.getOriginalFilename();
            File tmpFile = new File(buildImgPath(request)
                                    + "/"
                                    + UUID.randomUUID()
                                    + originalFilename.substring(originalFilename.lastIndexOf("."),
                                        originalFilename.length()));
            if (!multipartFile.isEmpty()) {
                byte[] bytes = multipartFile.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(tmpFile));
                stream.write(bytes);
                stream.close();
            }

            //String url = DomainUrlUtil.getEJS_IMAGE_RESOURCES();
            String url = UploadUtil.getInstance().brandUploaderImage(tmpFile, request, true);
            brand.setImage(url);
            tmpFile.delete();
        }
        brand.setCreateId(user.getId());
        brand.setUpdateId(user.getId());
        brand.setState(ProductBrand.Status.SUCCESS.getValue());//平台维护品牌，默认审核通过
        brand.setNameFirst(brand.getNameFirst().toUpperCase());
        ServiceResult<Boolean> serviceResult = productBrandService.save(brand);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

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
                log.error("error", e);
            }
        }
        return path;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(@RequestParam(value = "id", required = true) Integer id,
                       @RequestParam(value = "type", required = true) Integer type,
                       Map<String, Object> dataMap) {
        String path = "";
        if (type == 0) {
            //平台品牌编辑
            ServiceResult<ProductBrand> serviceResult = productBrandService.getById(id);
            if (!serviceResult.getSuccess()) {
                throw new RuntimeException(serviceResult.getMessage());
            }
            dataMap.put("brand", serviceResult.getResult());
            path = "admin/product/brand/brandedit";
        } else if (type == 1) {
            //商家品牌审核查看
            ServiceResult<SellerApplyBrand> serviceResult = sellerApplyBrandService.getById(id);
            if (!serviceResult.getSuccess()) {
                throw new RuntimeException(serviceResult.getMessage());
            }
            SellerApplyBrand brand = serviceResult.getResult();
            dataMap.put("brand", brand);
            path = "admin/product/brand/brandaudit";
        }
        return path;
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> update(ProductBrand brand, MultipartHttpServletRequest request)
                                                                                                 throws IOException {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }

        if (!StringUtil.is_alpha(brand.getNameFirst())) {
            jsonResult.setMessage("首字母输入错误，请重新输入");
            return jsonResult;
        }

        MultipartFile multipartFile = request.getFile("imageFile");
        if (null != multipartFile && multipartFile.getSize() > 0) {
            String originalFilename = multipartFile.getOriginalFilename();
            File tmpFile = new File(buildImgPath(request)
                                    + "/"
                                    + UUID.randomUUID()
                                    + originalFilename.substring(originalFilename.lastIndexOf("."),
                                        originalFilename.length()));
            if (!multipartFile.isEmpty()) {
                byte[] bytes = multipartFile.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(tmpFile));
                stream.write(bytes);
                stream.close();
            }

            //String url = DomainUrlUtil.getEJS_IMAGE_RESOURCES();
            String url = UploadUtil.getInstance().brandUploaderImage(tmpFile, request, true);
            brand.setImage(url);
        }

        brand.setUpdateId(user.getId());
        brand.setNameFirst(brand.getNameFirst().toUpperCase());
        ServiceResult<Boolean> serviceResult = productBrandService.update(brand);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        CodeManager.init();
        return jsonResult;
    }

    @RequestMapping(value = "del", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> del(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        ServiceResult<Boolean> serviceResult = productBrandService.del(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 平台审核商家提交的品牌信息
     *
     * @param request
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "auditing", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> auditing(HttpServletRequest request,
                                                         @RequestParam("id") Integer id,
                                                         @RequestParam("status") Integer status) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        if (!SellerApplyBrand.Status.chkStatus(status)) {
            jsonResult.setMessage("无效的审核状态，请检查");
        }
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        ServiceResult<Boolean> serviceResult = productBrandService.audit(id, status, user.getId());
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 商家商品类型编辑页面初始化ajax调用
     *
     * @param request
     * @param ids
     * @return
     */
    @RequestMapping(value = "getByIds", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<Object> getByIds(HttpServletRequest request,
                                                         @RequestParam("ids") String ids) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        //多个id，构造sql的in部分
        ServiceResult<List<ProductBrand>> serviceResult = productBrandService.getByIds(ids);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        String str = "";
        for (ProductBrand brand : serviceResult.getResult()) {
            str += brand.getName() + ",";
        }
        if (str.length() > 0) {
            str = str.substring(0, str.lastIndexOf(","));
        }
        jsonResult.setRows(str);
        return jsonResult;
    }

}
