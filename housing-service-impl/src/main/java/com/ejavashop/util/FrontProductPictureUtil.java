package com.ejavashop.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductPicture;
import com.ejavashop.service.product.IFrontProductPictureService;

/**
 * 处理商品图片相关的类
 *                       
 * @Filename: FrontProductPictureUtil.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Service(value = "frontProductPictureUtil")
public class FrontProductPictureUtil {

    private static Logger               log = LogManager.getLogger(FrontProductPictureUtil.class);

    @Resource(name = "frontProductPictureService")
    private IFrontProductPictureService frontProductPictureService;

    /**
     * 根据商品ID获取商品图片(大图)
     * @param productId 商品ID
     * @return
     */
    public List<String> getByProductIds(Integer productId) {
        List<String> list = new ArrayList<String>();
        ServiceResult<List<ProductPicture>> result = frontProductPictureService
            .getByProductIds(productId);
        if (!result.getSuccess()) {
            log.error(result.getMessage());
            return list;
        }
        List<ProductPicture> listPicture = result.getResult();

        for (ProductPicture productPicture : listPicture) {
            list.add(productPicture.getImagePath());
        }
        return list;
    }

    /**
     * 根据商品ID获取商品的主图（大图）
     * @param productId
     * @return
     */
    public String getproductLead(Integer productId) {
        ServiceResult<ProductPicture> result = frontProductPictureService.getproductLead(productId);
        if (!result.getSuccess()) {
            log.error(result.getMessage());
            return "";
        }
        ProductPicture productPicture = result.getResult();
        if (productPicture == null) {
            return "";
        }
        return productPicture.getImagePath();
    }

    /**
     * 根据商品ID获取商品的主图（中图）
     * @param productId
     * @return
     */
    public String getproductLeadMiddle(Integer productId) {
        ServiceResult<ProductPicture> result = frontProductPictureService.getproductLead(productId);
        if (result.getResult() == null || !result.getSuccess()) {
            log.error(result.getMessage());
            return "";
        }
        ProductPicture productPicture = result.getResult();
        if (productPicture == null) {
            return "";
        }
        String pathPicture = productPicture.getImagePath();
        if (null == pathPicture || "".equals(pathPicture)) {
            return "";
        }

        return newFileName(pathPicture, ConstantsEJS.PRODUCT_MIDDLE);

        //        return "/resources/front/img/1.jpg";
    }

    /**
     * 根据商品ID获取商品的主图（小图）
     * @param productId
     * @return
     */
    public String getproductLeadLittle(Integer productId) {
        ServiceResult<ProductPicture> result = frontProductPictureService.getproductLead(productId);
        if (result.getResult() == null || !result.getSuccess()) {
            log.error(result.getMessage());
            return "";
        }
        ProductPicture productPicture = result.getResult();
        if (productPicture == null) {
            return "";
        }
        String pathPicture = productPicture.getImagePath();
        if (null == pathPicture || "".equals(pathPicture)) {
            return "";
        }

        return newFileName(pathPicture, ConstantsEJS.PRODUCT_LITTLE);
    }

    /**
     * 生成新的文件名称，如果目录不存在，则生成目录
     * @param oldName 旧的名称
     * @param pathName 在最后目录下添加 little或者middle
     * @return
     */
    public static String newFileName(String oldName, String pathName) {
        String[] strings = oldName.split("/");
        StringBuilder sb = new StringBuilder();
        int count = strings.length;
        for (int i = 0; i < count; i++) {
            sb.append(strings[i]);
            if ((i + 2) == count) {
                sb.append("/");
                sb.append(pathName);
                sb.append("/");
                File newFilePathFile = new File(sb.toString());
                if (!newFilePathFile.exists()) {
                    newFilePathFile.mkdirs();
                }
            } else if ((i + 1) != count) {
                sb.append("/");
            }
        }
        return sb.toString();
    }

}
