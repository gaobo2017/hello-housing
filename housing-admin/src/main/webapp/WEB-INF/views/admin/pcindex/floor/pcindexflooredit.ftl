<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){


	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floor";
	});
	$("#edit").click(function(){

		if($("#addForm").form('validate')){
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floor/update")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">编辑首页楼层<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floor">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<input type="hidden" id="id" name="id" value="${(pcIndexFloor.id)!''}">
					<input type="hidden" id="status" name="status" value="${(pcIndexFloor.status)!''}">
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>楼层名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="name" name="name" value="${(pcIndexFloor.name)!''}" data-options="required:true,validType:'length[0,20]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>排序号：</label>
							<!-- <input class="easyui-numberbox w280" id="orderNo" name="orderNo" value="${(pcIndexFloor.orderNo)!''}" data-options="required:true,max:99" > -->
							<input class="easyui-numberbox w280" type="text" id="orderNo" name="orderNo" value="${(pcIndexFloor.orderNo)!''}" data-options="required:true,max:99" >
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
							<label class="lab-item"><font class="red">*</font>主图：</label>
							<input type="file" id="masterImageFile" name="masterImageFile"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" />
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								图片最佳像素（或保持该比例）：宽329，高400
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<img alt="图片" style="width: 329px;height: 400px;"
											src="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(pcIndexFloor.masterImage)!''}">
						</p>
						<input type="hidden" id="masterImage" name="masterImage" value="${(pcIndexFloor.masterImage)!''}">
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>主图链接地址：</label>
							<input class="easyui-validatebox txt w280" type="text" id="masterLinkUrl" name="masterLinkUrl" value="${(pcIndexFloor.masterLinkUrl)!''}" data-options="required:true,validType:'length[0,200]'" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								填写完整的地址
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">顶图：</label>
							<input type="file" id="advImageFile" name="advImageFile"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" />
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								图片最佳像素（或保持该比例）：宽1210，高100
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<img alt="图片" style="width: 605;height: 50px;"
											src="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(pcIndexFloor.advImage)!''}">
						</p>
						<input type="hidden" id="advImage" name="advImage" value="${(pcIndexFloor.advImage)!''}">
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">顶图链接地址：</label>
							<input class="easyui-validatebox txt w280" type="text" id="advLinkUrl" name="advLinkUrl" value="${(pcIndexFloor.advLinkUrl)!''}" data-options="validType:'length[0,200]'" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								填写完整的地址
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">备注：</label>
							<textarea name="remark" rows="4" cols="60" id="remark" class="{maxlength:100}" >${(pcIndexFloor.remark)!''}</textarea>
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