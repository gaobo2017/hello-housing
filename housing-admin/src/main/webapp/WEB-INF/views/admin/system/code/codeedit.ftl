<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">
$(function(){
	$("#back").click(function(){
	 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/code";
		});
	$("#edit").click(function(){
		if($("#editForm").form('validate')){
	 		$("#editForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/code/update")
  				 .attr("method", "POST")
  				 .submit();
		}
	});
	<#if message??>$.messager.progress('close');alert('${message}');</#if>
})
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">字典修改<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/code">返回</a></span></h2>
		
		<#--1.editForm----------------->
		<div class="form-contbox">
			
			<@form.form method="post" class="validForm" id="editForm" name="editForm">
			<input type="hidden" id="codeDiv" name="codeDiv" value="${(code.codeDiv)!''}" />
			<input type="hidden" id="codeCd" name="codeCd" value="${(code.codeCd)!''}" />
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">

					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">CODE DIV: </label>
							${(code.codeDiv)!''}
						</p>
						<p class="p6 p-item">
							<label class="lab-item">CODE CD: </label>
							${(code.codeCd)!''}
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>CODE名称: </label>
							<#-- <input type="text" id="codeText" name="codeText" value="${(code.codeText)!''}" class="txt w200 {required:true,maxlength:100}" data-options="required:true"/> -->
							<input type="text" id="codeText" name="codeText" value="${(code.codeText)!''}" class="easyui-validatebox" missingMessage="CODE名称必须填写，输入2到20个字符" data-options="required:true,validType:'length[2,20]'"/>
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>顺序位: </label>
							<#-- <input type="text" id="sortOrder" name="sortOrder" value="${(code.sortOrder)!''}" class="txt w50 {required:true,integer:true}" /> -->
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
				<dt class="dt-group"><span class="s-icon"></span>日志</dt>
				<dd class="dd-group">

					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">创建人： </label>
							${(code.createUser)!''}
						</p>
						<p class="p6 p-item">
							<label class="lab-item">创建时间：</label>
							<#if code.createTime??>${(code.createTime)?string("yyyy-MM-dd HH:mm:ss")}</#if>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">修改人： </label>
							${(code.updateUser)!''}
						</p>
						<p class="p6 p-item">
							<label class="lab-item">修改时间：</label>
							<#if code.updateTime??>${(code.updateTime)?string("yyyy-MM-dd HH:mm:ss")}</#if>
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
				<input type="button" id="edit" class="btn" value="保存"/>
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>

</div>

<#include "/admin/commons/_detailfooter.ftl" />