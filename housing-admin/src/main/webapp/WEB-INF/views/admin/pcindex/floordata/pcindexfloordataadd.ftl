<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">

$(function(){


	$("#back").click(function(){
		backTo();
	});
	$("#add").click(function(){
		
		var floorClassId = $("#floorClassId").val();
    	if (floorClassId == null || floorClassId == "") {
    		$.messager.alert('提示','请选择楼层分类');
    		return false;
    	}
    	
		var dataType = $("#dataType").val();
		if (dataType == null || dataType == "") {
    		$.messager.alert('提示','请选择数据类型');
    		return false;
    	} else if (dataType == 1) {
        	var refId = $("#refId").val();
        	if (refId == null || refId == "") {
        		$.messager.alert('提示','请选择商品');
        		return false;
        	}
        } else if (dataType == 2) {
        	var title = $("#title").val();
        	if (title == null || title == "") {
        		$.messager.alert('提示','请填写图片标题');
        		return false;
        	}
        	var linkUrl = $("#linkUrl").val();
        	if (linkUrl == null || linkUrl == "") {
        		$.messager.alert('提示','请填写链接地址');
        		return false;
        	}
        	var imageFile = $("#imageFile").val();
        	if (imageFile == null || imageFile == "") {
        		$.messager.alert('提示','请选择图片');
        		return false;
        	}
        }
		
		if($("#addForm").form('validate')){
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floordata/create")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});

	$('#pro').click(function(){
		$('#goodsDialog').dialog('open');
		$('#gd_dataGrid').datagrid('unselectAll');
	});
	
	
	$("#dataType").change(function(){
        var dataType = $(this).val();
        $("#dataPrdDiv").hide();
        $("#dataLinkDiv").hide();
        if (dataType == 1) {
        	$("#dataPrdDiv").show();
        } else if (dataType == 2) {
        	$("#dataLinkDiv").show();
        }
        
    });
	
	
	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
});

	function backTo() {
		var fromClass = ${(fromClass)!'0'};
		if (fromClass == "1") {
			window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorclass";
		} else {
			window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floordata";
		}
	}

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">添加楼层数据<span class="s-poar"><a class="a-back" href="javaScript:;" onclick="backTo()">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<input type="hidden" id="fromClass" name="fromClass" value="${(fromClass)!0}">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>楼层：</label>
							<select name="floorClassId" id="floorClassId" value="${(pcIndexFloorData.floorClassId)!''}" data-options="required:true">
		                    	<option value="">--请选择--</option>
		                        <#if floorClasss?? && floorClasss?size &gt; 0>
		                        	<#list floorClasss as floorClass>
		                        		<#if fromClass?? && floorClassId?? && fromClass == "1" >
		                        		<option value="${(floorClass.id)!}" <#if floorClassId == floorClass.id >selected="true"</#if>>${(floorClass.name)!}</option>
		                        		<#else>
										<option value="${(floorClass.id)!}" <#if pcIndexFloorData?? && pcIndexFloorData.floorClassId?? && pcIndexFloorData.floorClassId == floorClass.id>selected="true"</#if>>${(floorClass.name)!}</option>
										</#if>
									</#list>
								</#if>
						    </select>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>排序号：</label>
							<input class="easyui-numberbox w280" id="orderNo" name="orderNo" value="${(pcIndexFloorData.orderNo)!''}" data-options="required:true,max:99" >
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
							<label class="lab-item"><font class="red">*</font>数据类型: </label>
							<@cont.select id="dataType" value="${(pcIndexFloorData.dataType)!''}" codeDiv="DATA_TYPE" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div id="dataPrdDiv" style="display:none;">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>商品: </label> 
							<input type="button" value="选择商品" id="pro"/>
							<input type="text" id="productName" name="productName" value="${(pcIndexFloorData.product.name1)!''}" readonly="readonly">
							<input type="hidden" id="refId" name="refId" value="${(pcIndexFloorData.refId)!''}">
						</p>
					</div>
					<br/>
					</div>
					
					<div id="dataLinkDiv" style="display:none;">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>图片标题：</label>
							<input class="easyui-validatebox txt w280" type="text" id="title" name="title" value="${(pcIndexFloorData.title)!''}" data-options="validType:'length[0,100]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>链接地址：</label>
							<input class="easyui-validatebox txt w280" type="text" id="linkUrl" name="linkUrl" value="${(pcIndexFloorData.linkUrl)!''}" data-options="validType:'length[0,100]'" >
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
							<label class="lab-item"><font class="red">*</font>图片：</label>
							<input type="file" id="imageFile" name="imageFile"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" />
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								图片最佳像素（或保持该比例）：宽185，高247
								</font>
							</label>
						</p>
					</div>
					<br/>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">备注：</label>
							<textarea name="remark" rows="4" cols="60" id="remark" class="{maxlength:100}" >${(pcIndexFloorData.remark)!''}</textarea>
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

<div style="display: none">
<#include "goodsDialog.ftl"/>
</div>

<#include "/admin/commons/_detailfooter.ftl" />