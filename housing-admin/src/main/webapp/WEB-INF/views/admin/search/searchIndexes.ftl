<#include "/admin/commons/_detailheader.ftl" /> 
<script>
$(function(){
	<#if message??>
		$.messager.alert('提示','${(message)!}');
	</#if>
	$("#add").click(function(){
		$.messager.confirm('提示', '确定索引要初始化吗？', function(r){
			if (r){
				$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/searchIndexes/operation")
	  				 .attr("method", "POST")
	  				 .submit();
			}
		});
	});
})
</script>
	
<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
				索引初始化
			<span class="s-poar">
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm">

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>说明
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
						索引初始化是清除solr里面所有的索引文件，下一次定时器从头开始执行建立索引。<br/>
						建议在必要时才使用此功能。
						</label>
					</div>
				</dd>
			</dl>

			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="索引初始化" />
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />
