package com.ejavashop.web.controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.product.IProductGoodsService;
import com.ejavashop.service.product.IProductPictureService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;

/**
 * 商品品牌
 */
@Controller
@RequestMapping(value = "admin/product")
public class ProductController extends BaseController {
    @Resource
    private IProductService        productService;
    @Resource
    private IProductPictureService productPicService;
    @Resource
    private IMemberService         memberService;
    @Resource
    private IProductGoodsService   productGoodsService;
    @Resource
    private ISellerService         sellerService;

    private String                 baseUrl = "admin/product/manager/";
    private Logger                 log     = Logger.getLogger(this.getClass());

    @RequestMapping(value = "waitSale", method = { RequestMethod.GET })
    public String waitSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        dataMap.put("q_state", "1,2,3,4,7");//1、刚创建；2、提交审核；3、审核通过；4、申请驳回；7、下架
        return baseUrl + "listwaitsale";
    }

    @RequestMapping(value = "onSale", method = { RequestMethod.GET })
    public String onSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        dataMap.put("q_state", "6");//6、上架；
        return baseUrl + "listonsale";
    }

    @RequestMapping(value = "delSale", method = { RequestMethod.GET })
    public String delSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        dataMap.put("q_state", "5");//5、商品删除；
        return baseUrl + "listdelsale";
    }

    /**
     * 商品列表
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> list(HttpServletRequest request,
                                                            Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        if (!StringUtil.isEmpty(request.getParameter("q_state1"))) {
            queryMap.put("q_state", request.getParameter("q_state1"));
        }
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<Product>> serviceResult = productService.pageProduct(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        log.debug("jsonResult size=" + jsonResult.getTotal());
        return jsonResult;
    }

    /**
     * 商品列表无分页
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "listnopage", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> listNoPage(HttpServletRequest request,
                                                                  Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        ServiceResult<List<Product>> serviceResult = productService.pageProduct(queryMap, null);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();
        jsonResult.setRows(serviceResult.getResult());
        return jsonResult;
    }

    /**
     * 根据商品ID查询货品
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list_goods", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductGoods>> listGoods(Integer productId,
                                                                      HttpServletRequest request,
                                                                      Map<String, Object> dataMap) {
        ServiceResult<List<ProductGoods>> serviceResult = productGoodsService
            .getGoodSByProductId(productId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductGoods>> jsonResult = new HttpJsonResult<List<ProductGoods>>();
        jsonResult.setRows(serviceResult.getResult());
        return jsonResult;
    }

    @RequestMapping(value = "auditProduct", method = { RequestMethod.GET })
    public void auditProduct(HttpServletRequest request, HttpServletResponse response, Integer id,
                             Integer type) {
        response.setContentType("text/plain;charset=utf-8");
        String msg = "操作成功!";
        PrintWriter pw = null;
        try {
            if (id == null || id == 0)
                throw new BusinessException("请选择要操作的商品");
            if (type == null)
                throw new BusinessException("未知操作");
            switch (type) {
                case 3:
                    msg += "该商品将允许在商城上架";
                    break;
                case 4:
                    msg += "该商品将不允许再次上架";
                    break;
                case 5:
                    msg += "该商品将被冻结,并强制下架";
                    break;
                case 7:
                    msg += "该商品将强制下架";
                    break;
                default:
                    msg = "操作失败,请稍后重试";
                    throw new BusinessException("操作失败,请稍后重试");
            }
            productService.updateProductState(id, type);

            pw = response.getWriter();
        } catch (Exception e) {
            log.error("[admin][ProductController] auditProduct exception", e);
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    /**
     * 跳转到单品页
     * @param productId
     * @return
     */
    @RequestMapping(value = "/toDetail.html", method = { RequestMethod.GET })
    public String toDetail(@RequestParam(value = "productId", required = true) Integer productId) {
        String url = DomainUrlUtil.getEJS_FRONT_URL() + "/front/toDetail.html?productId="
                     + productId;
        return "redirect:" + url;
    }

    @RequestMapping(value = "recommond", method = { RequestMethod.GET })
    public void recommond(HttpServletRequest request, HttpServletResponse response, Integer id,
                          Boolean isRec) {
        response.setContentType("text/plain;charset=utf-8");
        String msg = "";
        PrintWriter pw = null;

        try {
            if (isRec == null)
                throw new BusinessException("未知操作");

            Integer isTop = ConstantsEJS.PRODUCT_IS_TOP_1;
            if (isRec) {
                isTop = ConstantsEJS.PRODUCT_IS_TOP_2;
                msg = "推荐成功";
            } else {
                isTop = ConstantsEJS.PRODUCT_IS_TOP_1;
                msg = "取消推荐成功";
            }
            ServiceResult<Boolean> sr = productService.updateProductRecommend(id, isTop);
            if (!sr.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(sr.getCode())) {
                    throw new RuntimeException(sr.getMessage());
                } else {
                    throw new BusinessException(sr.getMessage());
                }
            }
            pw = response.getWriter();
        } catch (IOException e) {
            log.error("[admin][ProductController] recommond exception", e);
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public void del(HttpServletRequest request, HttpServletResponse response, Integer id) {
        response.setContentType("text/plain;charset=utf-8");
        String msg = "删除成功";
        PrintWriter pw = null;

        try {
            ServiceResult<Boolean> sr = productService.updateProductState(id, Product.STATE_5);
            if (!sr.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(sr.getCode())) {
                    throw new RuntimeException(sr.getMessage());
                } else {
                    throw new BusinessException(sr.getMessage());
                }
            }
            pw = response.getWriter();
        } catch (IOException e) {
            log.error("[admin][ProductController] del exception", e);
            msg = e.getMessage();
        }
        pw.print(msg);
    }
}
