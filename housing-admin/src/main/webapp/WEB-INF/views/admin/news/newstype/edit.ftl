<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/newstype"/>

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

		//初始化分配资源对话框
		$("#newstypeWin").window({
			width : 400,
			height : 500,
			title : "选择文章分类",
			closed : true,
			shadow : false,
			collapsible : false,
			minimizable : false,
			maximizable : false
		});

	})

	function closeWin() {
		$('#newstypeWin').window('close');
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			<#if obj??>
				编辑文章分类
			<#else>
				新增文章分类
			</#if>
			 <span class="s-poar"> <a class="a-back"
				href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			name="addForm" enctype="multipart/form-data"
			action="${currentBaseUrl}/doAdd"> 
			
			<#if obj??>
			<input type="hidden" value="${(obj.id)!''}" name="id"> 
			<input type="hidden" value="${(obj.image)!''}" name="image">
			</#if>
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
<!-- 						<p class="p6 p-item"> -->
<!-- 							<label class="lab-item"><font class="red">*</font>上级分类: </label> -->
<!-- 							<input type="text" id="parentPath" name="parentPath" -->
<!-- 								value="${(obj.parentPath)!''}" -->
<!-- 								class="txt w200 easyui-validatebox" -->
<!-- 								missingMessage="请选择上级分类" -->
<!-- 								data-options="required:true" /> <input -->
<!-- 								type="hidden" name="pid" id="pid" /> -->
								
<!-- 						</p> -->
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>分类名称: </label>
							<input type="text" id="name" name="name" value="${(obj.name)!''}"
								class="txt w200 easyui-validatebox"
								missingMessage="分类名称必填，2-20个字符"
								data-options="required:true,validType:'length[2,20]'" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>排序号: </label> 
							<input type="text" id="sort" name="sort" value="${(obj.sort)!''}" class="txt w200 easyui-numberbox" data-options="min:1,max:200,required:true" missingMessage="顺序位必填，1-200" />
						</p>
					</div>

<!-- 					<div class="fluidbox"> -->
<!-- 						<p class="p6 p-item"> -->
<!-- 							<label class="lab-item">分类图片: </label> <#if obj??> <img -->
<!-- 								alt="分类图片" -->
<!-- 								src="${domainUrlUtil.EJS_URL_RESOURCES}/${(obj.image)!''}"> -->
<!-- 							</#if> <input type="file" id="imagepic" name="imagepic" -->
<!-- 								class="txt w240 easyui-validatebox" /> -->
<!-- 						</p> -->
<!-- 					</div> -->

				</dd>
			</dl>

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>帮助
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">系统添加文章时会选择该分类</label>
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


<!-- 上级分类对话窗 -->
<div id="newstypeWin">
	<form id="newstypeForm" method="post">
		<div style="margin-top: 5px; border-bottom: 2px solid #C9DDFF;">
			<label class="lab-item">文章分类: </label><span id="roleNameSpan"></span><br />
		</div>

		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto;"></ul>
	</form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
