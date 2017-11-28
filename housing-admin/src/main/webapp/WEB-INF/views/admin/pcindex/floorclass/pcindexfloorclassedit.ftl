<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){


	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorclass";
	});
	$("#edit").click(function(){
		var floorId = $("#floorId").val();
    	if (floorId == null || floorId == "") {
    		$.messager.alert('提示','请选择楼层');
    		return false;
    	}

		if($("#addForm").form('validate')){
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorclass/update")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">编辑首页楼层分类<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorclass">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<input type="hidden" id="id" name="id" value="${(pcIndexFloorClass.id)!''}">
					<input type="hidden" id="status" name="status" value="${(pcIndexFloorClass.status)!''}">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>楼层：</label>
							<select name="floorId" id="floorId" value="${(pcIndexFloorClass.floorId)!''}" data-options="required:true" disabled="disabled">
		                    	<option value="">--请选择--</option>
		                        <#if floors?? && floors?size &gt; 0>
		                        	<#list floors as floor>
										<option value="${(floor.id)!}" <#if pcIndexFloorClass?? && pcIndexFloorClass.floorId?? && pcIndexFloorClass.floorId == floor.id>selected="true"</#if>>${(floor.name)!}</option>
									</#list>
								</#if>
						    </select>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="name" name="name" value="${(pcIndexFloorClass.name)!''}" data-options="required:true,validType:'length[0,20]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>排序号：</label>
							<input class="easyui-numberbox w280" type="text" id="orderNo" name="orderNo" value="${(pcIndexFloorClass.orderNo)!''}" data-options="required:true,max:99" >
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
						<p class="p12 p-item">
							<label class="lab-item">备注：</label>
							<textarea name="remark" rows="4" cols="60" id="remark" class="{maxlength:100}" >${(pcIndexFloorClass.remark)!''}</textarea>
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



<#include "/admin/commons/_detailfooter.ftl" />