<#include "/admin/commons/_detailheader.ftl" /> 
<script>
	<#if message??>
		$.messager.alert('提示','${(message)!}');
	</#if>
</script>
	
<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
				关键词设置
			<span class="s-poar">
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" action="${domainUrlUtil.EJS_URL_RESOURCES}/admin/search/updateKeyword"> 

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>关键字
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>关键字: </label>
							<input type="text" id="keyword" name="keyword" value="${(searchSetting.keyword)!''}"
								class="txt w500 easyui-validatebox"
								missingMessage="关键字必填，2-30个字符"
								data-options="required:true,validType:'length[2,30]'" />
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
						<label class="lab-item" style="width: 100%; text-align: left;">关键字用英文逗号（,）隔开</label>
					</div>
				</dd>
			</dl>

			<p class="p-item p-btn">
				<input type="submit" id="add" class="btn" value="提交" />
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />
