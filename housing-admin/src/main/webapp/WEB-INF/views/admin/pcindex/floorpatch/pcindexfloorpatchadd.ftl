<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){


	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorpatch";
	});
	$("#add").click(function(){
		var floorId = $("#floorId").val();
    	if (floorId == null || floorId == "") {
    		$.messager.alert('提示','请选择楼层');
    		return false;
    	}
		
		if($("#addForm").form('validate')){
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorpatch/create")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">添加楼层碎屑<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorpatch">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>楼层：</label>
							<select name="floorId" id="floorId" value="${(pcIndexFloorPatch.floorId)!''}" data-options="required:true">
		                    	<option value="">--请选择--</option>
		                        <#if floors?? && floors?size &gt; 0>
		                        	<#list floors as floor>
										<option value="${(floor.id)!}" <#if pcIndexFloorPatch?? && pcIndexFloorPatch.floorId?? && pcIndexFloorPatch.floorId == floor.id>selected="true"</#if>>${(floor.name)!}</option>
									</#list>
								</#if>
						    </select>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>显示文字：</label>
							<input class="easyui-validatebox txt w280" type="text" id="title" name="title" value="${(pcIndexFloorPatch.title)!''}" data-options="required:true,validType:'length[0,20]'" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								显示在页面的内容，请认真填写
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>链接地址：</label>
							<input class="easyui-validatebox txt w280" type="text" id="linkUrl" name="linkUrl" value="${(pcIndexFloorPatch.linkUrl)!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								请填写完整的地址
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>排序号：</label>
							<input class="easyui-numberbox w280" type="text" id="orderNo" name="orderNo" value="${(pcIndexFloorPatch.orderNo)!''}" data-options="required:true,max:99" >
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
							<textarea name="remark" rows="4" cols="60" id="remark" class="{maxlength:100}" >${(pcIndexFloorPatch.remark)!''}</textarea>
						</p>
					</div>
					<br/>
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="新增" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>



<#include "/admin/commons/_detailfooter.ftl" />