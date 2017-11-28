<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<style>
      body{ font:14px/1.5 'Microsoft YaHei',"微软雅黑",Tahoma, Helvetica, Arial, sans-serif; margin:0; padding:0; border:0; background:#fff; color:#333;}
     body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,blockquote,p{padding:0; margin:0;}
     .tab1,.tab{
     	border-collapse: collapse;
     	margin: 20px auto auto;
     }
     .tab td{
       height: 40px;
     }
     .tab1 td,.tab1 th{
     	border:solid 1px #dedede;
     	height: 40px;
     	vertical-align: middle;
     	padding-left: 10px;
     	text-align: left;
     }
	</style>
	<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery.jqprint-0.3.js"></script>
    <script>
        $(function () {
            $('#print').click(function () {
                $("#printArea").jqprint();
            });
        });
    </script>
</head>
<input type="button" id="print" class="btn" value="打印"/>
<body id="printArea">
    <h2 style="text-align:center; margin:10px 0;">订单详情</h2>
	<table width="80%" cellpadding="0" cellspacing="0" class="tab">
		<tr>
			<td>订单编号：${(orders.orderSn)!""}</td>
			<td>订购时间：${(orders.createTime)?string("yyyy-MM-dd")!""}</td>
		</tr>
		<tr>
			<td>客户姓名：${(orders.name)!""}</td>
			<td>联系方式：${(orders.mobile)!""}</td>
		</tr>
		<tr>
			<td colspan="2">客户地址：${(orders.addressAll)!""} &nbsp; ${(orders.addressInfo)!""}</td>
		</tr>
	</table>

	<table width="80%" cellpadding="0" cellspacing="0" class="tab1">
	    <tr>
	    	<th>商品编号</th>
	    	<th>商品名称</th>
	    	<th>数量</th>
	    	<th>商品金额</th>
	    </tr>
	    <#if orders?? && orders.orderProductList?? && orders.orderProductList?size &gt; 0 >
	    	<#list orders.orderProductList as op>
				<tr>
					<td>${(op.productId)!""}</td>
					<td>${(op.productName)!""}</td>
					<td>${(op.number)!""}</td>
					<td>￥${(op.moneyAmount)!""}</td>
				</tr>
			</#list>
		</#if>
		
	</table>

	<table width="80%" cellpadding="0" cellspacing="0" class="tab1">
		<tr>
			<td>商品总金额：<span>${(orders.moneyProduct)?string("0.00")!""}元</span>&nbsp;+&nbsp;运费：<span>${(orders.moneyLogistics)?string("0.00")!""}元</span>&nbsp;-&nbsp;优惠：<span>${(orders.moneyDiscount)?string("0.00")!""}元</span>&nbsp;-&nbsp;余额：<span>${(orders.moneyPaidBalance)?string("0.00")!""}元</span>&nbsp;-&nbsp;积分：<span>${(orders.moneyIntegral)?string("0.00")!""}元</span></td>
		</tr>
	</table>
	<div style="margin-top:10px; text-align:right; width:80%; margin:20px auto auto">
		<#if orders?? && orders.isCodconfim?? && orders.isCodconfim == 1 >
			货到付款 &nbsp;&nbsp;
		</#if>
		订单支付金额：<span>￥${(orders.moneyOrder - orders.moneyPaidBalance - orders.moneyIntegral)?string("0.00")!""}</span>
	</div>
</body>
</html>