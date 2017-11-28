<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/courierCompany"/>

<style>
.dl-group p img {
	max-width: 120px;
	float: left;
}

.formbox-a .lab-item {
	float: left;
	width: 120px;
	text-align: right;
	margin-right: 3px;
	display: inline;
	padding-top: 5px;
}
</style>

<script language="javascript">
	$(function() {

		$("#back").click(function() {
			location.href = '${currentBaseUrl}';
		});

		$("#add").click(function() {
			if ($('#addForm').form('validate')) {
				$('#addForm').submit();
			}
		});

	})

	function closeWin() {
		$('#newstypeWin').window('close');
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			<#if obj??> 编辑物流公司 <#else> 新增物流公司 </#if> <span class="s-poar">
				<a class="a-back" href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			name="addForm" enctype="multipart/form-data"
			action="${currentBaseUrl}/doAdd"> <input type="hidden"
				value="${(obj.id)!''}" name="id"> <input type="hidden"
				value="${(obj.image)!''}" name="image">

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>物流公司名称:
							</label> <input type="text" id="companyName" name="companyName"
								value="${(obj.companyName)!''}"
								class="txt w200 easyui-validatebox" missingMessage="请输入快递公司名称"
								data-options="required:true,validType:'length[2,200]'" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item">物流公司代码: </label> <input type="text"
								id="companyMark" name="companyMark"
								value="${(obj.companyMark)!''}" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>排序号: </label> <input
								type="text" id="seq" name="seq" value="${(obj.seq)!''}"
								class="txt w200 easyui-numberbox"
								missingMessage="请输入排序号,排序号越小显示越靠前"
								data-options="required:true,min:0" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>快递类型: </label>
							<input type="radio" id="mail" name="companyType" value="1"<#if
							obj??&&obj.companyType=='1'>checked="checked"</#if>/>平邮 <input
								type="radio" id="ems" name="companyType" value="2"<#if
							obj??&&obj.companyType=='2'>checked="checked"</#if>/>EMS <input
								type="radio" id="exp" name="companyType" value="3"<#if
							!obj??|| obj??&&obj.companyType=='3'>checked="checked"</#if>/>快递
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>状态: </label>
							<@cont.select id="state" mode="-1" codeDiv="DISABLE_STATE"
							name="state" class="txt" value="${(obj.state)!''}"
							style="width:200px;"/>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">模板图片：</label>
							<input type="file" id="imageFile" name="imageFile"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" />
							<#if (obj.imagePath)?? >
							<img alt="图片" style="width: 300px;height: 120px;"
											src="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(obj.imagePath)!''}">
							</#if>
						</p>
						<input type="hidden" id="imagePath" name="imagePath" value="${(obj.imagePath)!''}">
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">模板内容: </label>
							<textarea name="content" id="content" cols="90" rows="3">${(obj.content)!""}</textarea>
						</p>
					</div>
				</dd>
			</dl>

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>帮助
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
							请添加物流公司信息,填写完毕后点击提交 </label>
					</div>
				</dd>
			</dl>

			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="提交" /> <input
					type="button" id="back" class="btn" value="返回" />
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />
