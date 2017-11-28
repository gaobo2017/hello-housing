<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){


	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash";
	});

	$("#abolish").click(function(){
		
		if($("#addForm").form('validate')){
			var auditOpinion = $("#auditOpinion").val();
			if (auditOpinion == null || auditOpinion == "") {
				$.messager.alert('提示','请填写作废原因。');
				return;
			}
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash/doabolish")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});
	
	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">作废限时抢购<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<input type="hidden" id="id" name="id" value="${(actFlashSale.id)!''}">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">活动名称：</label>
							<label>${(actFlashSale.actFlashSaleName)!''}</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">活动日期：</label>
							<label>${(actFlashSale.actDate?string('yyyy-MM-dd'))!''}</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">活动状态：</label>
							<@cont.select disabled="disabled" id="status" value="${(actFlashSale.status)!''}" codeDiv="FLASH_SALE_STATUS" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>应用渠道：</label>
							<@cont.select disabled="disabled" id="channel" value="${(actFlashSale.channel)!''}" codeDiv="CHANNEL" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">PC图片：</label>
							<#if actFlashSale.pcImage?? >
								<img alt="图片" style="width: 192px;height: 45px;"
												src="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(actFlashSale.pcImage)!''}">
							</#if>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								用于PC端活动页展示。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">移动图片：</label>
							<#if actFlashSale.mobileImage?? >
								<img alt="图片" style="width: 144px;height: 70px;"
												src="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(actFlashSale.mobileImage)!''}">
							</#if>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								用于移动端活动页展示。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">申请规则：</label>
							<label>${(actFlashSale.auditRule)!''}</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">活动描述：</label>
							<label>${(actFlashSale.remark)!''}</label>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>抢购阶段：</label> 
						</p>
					</div>
					<#if stageList??>
						<#list  stageList as stage>
							<div class="fluidbox addItemT">
								<p class="p12 p-item">
									<label class="lab-item">${(stage.startTime)!''}点 ~ ${(stage.endTime)!''}点：</label>
									<label class="">${(stage.remark)!''}</label>
									<input type="hidden" value="${stage.id}" id="stageIdHidden"/>
								</p>
								<#if stage.productList?? && stage.productList?size &gt; 0 >
									<#list stage.productList as stageProduct>
									<p class="p12 p-item">
										<label class="lab-item">&nbsp;</label>
										商品：<input class="easyui-validatebox txt w280" type="text" id="productName" name="productName" value="${(stageProduct.product.name1)!''}" disabled="true">
										价格：<input class="easyui-numberbox w80" type="text" id="price" name="price" value="${(stageProduct.price)!''}" disabled="true">
										原价：<input class="easyui-numberbox w80" type="text" id="pcPrice" name="pcPrice" value="${(stageProduct.product.mallPcPrice)!''}" disabled="true">
										库存：<input class="easyui-numberbox w80" type="text" id="stock" name="stock" value="${(stageProduct.stock)!''}" disabled="true">
										销量：<input class="easyui-numberbox w80" type="text" id="actualSales" name="actualSales" value="${(stageProduct.actualSales)!''}" disabled="true">
									</p>
									<br/>
									<p class="p12 p-item">
										<label class="lab-item">&nbsp;</label>
										状态：<@cont.select disabled="disabled" id="status" value="${(stageProduct.status)!''}" codeDiv="FLASH_PRODUCT_STATUS" style="width:100px" mode="1"/>
										审核意见：<input class="easyui-validatebox w280" type="text" id="auditOpinion" name="auditOpinion" value="${(stageProduct.auditOpinion)!''}" disabled="true">
									</p>
									</#list>
								</#if>
							</div>
						</#list>
					</#if>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>作废原因：</label>
							<textarea name="auditOpinion" rows="4" cols="100" id="auditOpinion" class="{maxlength:500}" >${(actFlashSale.auditOpinion)!''}</textarea>
						</p>
					</div>
					<br/>
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="abolish" class="btn" value="作废"/>
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />