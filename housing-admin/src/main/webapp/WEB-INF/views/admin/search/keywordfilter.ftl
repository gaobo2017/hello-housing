<#include "/admin/commons/_detailheader.ftl" /> 
<script>
	<#if message??>
		$.messager.alert('提示','${(message)!}');
	</#if>
</script>
<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
				敏感词过滤设置
			<span class="s-poar">
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" action="${domainUrlUtil.EJS_URL_RESOURCES}/admin/search/updateKeywordfilter"> 
			
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>敏感词过滤
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>敏感词过滤: </label>
							<@cont.radio id="keywordFilter" value="${(searchSetting.keywordFilter)!''}" codeDiv="SEARCH_KEYWORDFILTER" />
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
						<label class="lab-item" style="width: 100%; text-align: left;">启用敏感词过滤之后，输入敏感词将搜索不到任何结果</label>
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