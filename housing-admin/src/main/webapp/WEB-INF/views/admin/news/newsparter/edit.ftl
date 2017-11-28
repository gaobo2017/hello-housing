<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/newsParter"/>

<style>
.dl-group p img {
	max-width: 120px;
	float: left;
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
			<#if obj??> 编辑合作伙伴信息 <#else> 新增合作伙伴 </#if> <span class="s-poar">
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
							<label class="lab-item"><font class="red">*</font>名称: </label> <input
								type="text" id="name" name="name"
								value="${(obj.name)!''}"
								class="txt w200 easyui-validatebox" missingMessage="请输入合作伙伴名称"
								data-options="required:true" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>状态: </label>
							<@cont.select id="status" mode="-1"
							codeDiv="NEWS_PARTNER_STATUS" value="${(obj.status)!''}" name="status" class="txt"
							style="width:200px;"/>
						</p>
					</div>

					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>企业logo:
							</label> <#if obj??> <img alt=""
								src="${domainUrlUtil.EJS_URL_RESOURCES}/${(obj.image)!''}">
							</#if> <input type="file" id="imagepic" name="imagepic"
								class="txt w240 easyui-validatebox" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>排序号: </label> 
							<input type="text" id="sort" name="sort" value="${(obj.sort)!''}" class="txt w200 easyui-numberbox" data-options="min:1,max:200,required:true" missingMessage="顺序位必填，1-200" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">链接: </label>
							<textarea type="text" id="url" name="url"
								value="${(obj.url)!''}" class="txt w200 easyui-validatebox"
								cols="50" rows="1" style="width: 240px;">${(obj.url)!''}</textarea>
						<div class="tooltip tooltip-right"
							style="left: 34%; margin-top: 10px; display: block; color: #C93; background-color: #FFC; border-color: #DBB46F;">
							<div class="tooltip-content">链接URL应以http://开头</div>
							<div class="tooltip-arrow-outer"
								style="border-right-color: rgb(204, 153, 51);"></div>
							<div class="tooltip-arrow"
								style="border-right-color: rgb(255, 255, 204);"></div>
						</div>
						</p>
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
