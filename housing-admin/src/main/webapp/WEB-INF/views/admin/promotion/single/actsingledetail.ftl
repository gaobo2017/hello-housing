<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">

$(function(){

	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/single";
	});
	
	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">单品立减详情<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/single">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动名称：</label>
							<input disabled class="easyui-validatebox txt w280" type="text" id="actSingleName" name="actSingleName" value="${(actSingle.actSingleName)!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动类型：</label>
							<@cont.select disabled="disabled" id="type" value="${(actSingle.type)!''}" codeDiv="ACT_SINGLE_TYPE" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>优惠额/折扣：</label>
							<input disabled class="easyui-numberbox txt w280" type="text" id="discount" name="discount" value="${(actSingle.discount)!''}" data-options="required:true,precision:2" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								活动类型为减免金额时为金额（如10为减免10元），折扣类型时为折扣（如0.90为打九折）。
								</font>
							</label>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动商品：</label> 
							<#if productList??>
								<#list  productList as product>
									<#if product_index != 0 >
									<br/>
									<label class="lab-item">&nbsp;</label>
									</#if>
									<input type="hidden" id="ids" name="ids" value="${(product.id)!''}" />
									商品名称：
									<input type="text" id="productName" name="productName" readonly="readonly" style="background:#eee;color:#777;" value="${(product.name1)!''}" class="txt w250" />
									&nbsp;&nbsp;商城价：
									<input type="text" id="mallPcPrice" name="mallPcPrice" readonly="readonly" style="background:#eee;color:#777;" value="${(product.mallPcPrice)!''}" class="txt w50" />
									&nbsp;&nbsp;移动端价：
									<input type="text" id="malMobilePrice" name="malMobilePrice" readonly="readonly" style="background:#eee;color:#777;" value="${(product.malMobilePrice)!''}" class="txt w50" />
								</#list>
							</#if>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动时间：</label>
							<input disabled class="easyui-validatebox txt w200" type="text" id="startTime" name="startTime" value="${(actSingle.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}" data-options="required:true,validType:'length[0,100]'" >
							~
							<input disabled class="easyui-validatebox txt w200" type="text" id="endTime" name="endTime" value="${(actSingle.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>应用渠道：</label>
							<@cont.select disabled="disabled" id="channel" value="${(actSingle.channel)!''}" codeDiv="CHANNEL" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>状态：</label>
							<@cont.select disabled="disabled" id="status" value="${(actSingle.status)!''}" codeDiv="ACT_STATUS" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">活动描述：</label>
							<textarea disabled name="remark" rows="4" cols="60" id="remark" class="{maxlength:255}" >${(actSingle.remark)!''}</textarea>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">审核意见：</label>
							<textarea disabled name="auditOpinion" rows="4" cols="60" id="auditOpinion" class="{maxlength:255}" >${(actSingle.auditOpinion)!''}</textarea>
						</p>
					</div>
					<br/>
					
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />