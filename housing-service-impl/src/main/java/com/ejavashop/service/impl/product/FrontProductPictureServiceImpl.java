package com.ejavashop.service.impl.product;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductPicture;
import com.ejavashop.model.product.FrontProductPictureModel;
import com.ejavashop.service.product.IFrontProductPictureService;

@Service(value = "frontProductPictureService")
public class FrontProductPictureServiceImpl implements IFrontProductPictureService {
    private static Logger            log = LogManager
        .getLogger(FrontProductPictureServiceImpl.class);

    @Resource
    private FrontProductPictureModel frontProductPictureModel;

    /**
    * 根据id取得商品对应图片表
    * @param  productPictureId
    * @return
    */
    @Override
    public ServiceResult<ProductPicture> getProductPictureById(Integer productPictureId) {
        ServiceResult<ProductPicture> result = new ServiceResult<ProductPicture>();
        try {
            result.setResult(frontProductPictureModel.getProductPictureById(productPictureId));
        } catch (Exception e) {
            log.error("[FrontProductPictureServiceImpl][getProductPictureById]根据id["
                      + productPictureId + "]取得商品对应图片表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + productPictureId + "]取得商品对应图片表时出现未知异常");
        }
        return result;
    }

    /**
     * 根据id取得商品对应图片表
     * @param  productId
     * @return
     */
    @Override
    public ServiceResult<List<ProductPicture>> getByProductIds(Integer productId) {
        ServiceResult<List<ProductPicture>> result = new ServiceResult<List<ProductPicture>>();
        try {
            result.setResult(frontProductPictureModel.getByProductIds(productId));
        } catch (Exception e) {
            log.error("[FrontProductPictureServiceImpl][getByProductIds]根据商品id[" + productId
                      + "]取得商品对应图片表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据商品id[" + productId + "]取得商品对应图片表时出现未知异常");
        }
        return result;
    }

    /**
     * 根据商品ID获取商品的主图
     * @param productId
     * @return
     */
    @Override
    public ServiceResult<ProductPicture> getproductLead(Integer productId) {
        ServiceResult<ProductPicture> result = new ServiceResult<ProductPicture>();
        try {
            result.setResult(frontProductPictureModel.getproductLead(productId));
        } catch (Exception e) {
            log.error("[FrontProductPictureServiceImpl][getproductLead]根据商品id[" + productId
                      + "]取得商品对应图片表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据商品id[" + productId + "]取得商品对应图片表时出现未知异常");
        }
        return result;
    }

}