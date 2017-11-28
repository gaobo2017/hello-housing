<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">
$(function(){
	$("#back").click(function(){
	 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/code";
		});
	$("#add").click(function(){
			if($("#addForm").form('validate')){
		 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/code/create")
	  				 .attr("method", "POST")
	  				 .submit();
	  		}
		});
	<#if message??>$.messager.progress('close');alert('${message}');</#if>
})
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">字典新增<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/code">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>CODE DIV: </label>
							<input type="text" id="codeDiv" name="codeDiv" value="${(code.codeDiv)!''}" class="easyui-validatebox" missingMessage="CODE DIV必须填写，输入3到20个字符" data-options="required:true,validType:'length[3,20]'"/>
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>CODE CD: </label>
							<input type="text" id="codeCd" name="codeCd" value="${(code.codeCd)!''}" class="easyui-numberbox" missingMessage="CODE CD必须填写，输入0到9999之间的数字" data-options="min:0,max:9999,required:true"/>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>CODE名称: </label>
							<input type="text" id="codeText" name="codeText" value="${(code.codeText)!''}" class="easyui-validatebox" missingMessage="CODE名称必须填写，输入2到20个字符" data-options="required:true,validType:'length[2,20]'"/>
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>顺序位: </label>
							<input type="text" id="sortOrder" name="sortOrder" value="${(code.sortOrder)!''}" class="easyui-numberbox" missingMessage="顺序位必须填写，输入0到100之间的数字" data-options="min:0,max:100,required:true"/>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>是否使用: </label>
							<@cont.radio id="useYn" value="${(code.useYn)!''}" codeDiv="USE_YN" />
						</p>
					</div>

				</dd>
			</dl>
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>帮助</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item">帮助信息。</label>
					</div>
				</dd>
			</dl>
			
			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="新增"/>
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />