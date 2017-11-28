<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){


	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/recommend";
	});
	$("#edit").click(function(){

		if($("#addForm").form('validate')){
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/recommend/update")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});
	
	$('#pro').click(function(){
		$('#goodsDialog').dialog('open');
		$('#gd_dataGrid').datagrid('unselectAll');
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">编辑推荐商品<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/recommend">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<input type="hidden" id="id" name="id" value="${(pcRecommend.id)!''}">
					<input type="hidden" id="status" name="status" value="${(pcRecommend.status)!''}">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>推荐类型: </label>
							<@cont.select id="recommendType" value="${(pcRecommend.recommendType)!''}" codeDiv="RECOMMEND_TYPE" style="width:150px" mode="0"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>商品: </label> 
							<input type="button" value="选择商品" id="pro"/>
							<input class="w280" type="text" id="productName" name="productName" value="${(pcRecommend.product.name1)!''}" readonly="readonly">
							<input type="hidden" id="productId" name="productId" value="${(pcRecommend.productId)!''}">
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>排序号：</label>
							<input class="easyui-numberbox w280" type="text" id="orderNo" name="orderNo" value="${(pcRecommend.orderNo)!''}" data-options="required:true,max:99" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								序号越小越靠前显示
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<label class="lab-item"><font class="red">*</font>推荐时间：</label>
						
						<input type="text" id="startTime" name="startTime"
								class="txt w200 easyui-validatebox" missingMessage="开始时间必填"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"
								value="${(pcRecommend.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
						~
						<input type="text" id="endTime" name="endTime"
								class="txt w200 easyui-validatebox" missingMessage="结束时间必填"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});"
								value="${(pcRecommend.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">备注：</label>
							<textarea name="remark" rows="4" cols="60" id="remark" class="{maxlength:100}" >${(pcRecommend.remark)!''}</textarea>
						</p>
					</div>
					<br/>
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="edit" class="btn" value="修改" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>

<div style="display: none">
<#include "goodsDialog.ftl"/>
</div>

<#include "/admin/commons/_detailfooter.ftl" />