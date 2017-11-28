<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">
$(function(){
	$("#oldPassword").val("");
	$("#newPassword").val("");
	$("#newPasswordCfm").val("");
	
	$("#edit").click(function(){
		if($("#addForm").form('validate')){
			$.ajax({
				type:"POST",
				url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/adminuser/editpwd/update",
				dataType: "json",
				data: $('#addForm').serialize(),
				cache:false,
				success:function(data, textStatus){
					if (data.success) {
						$.messager.alert('提示','修改成功。');
						$("#oldPassword").val("");
						$("#newPassword").val("");
						$("#newPasswordCfm").val("");
						return;
					} else {
						$.messager.alert("提示",data.message);
					}
				}
			});
		}
	});

	<#if message??>$.messager.progress('close');alert('${message}');</#if>
})
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">修改密码</h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
                    
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>旧密码: </label>
                            <input type="password" id="oldPassword" name="oldPassword" class="txt w200 easyui-validatebox" data-options="required:true,validType:'length[6,8]'"/>
                        </p>
                    </div>
                    
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>新密码: </label>
                            <input type="password" id="newPassword" name="newPassword" class="txt w200 easyui-validatebox" data-options="required:true,validType:'length[6,8]'"/>
                        </p>
                    </div>
                    
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>新密码确认: </label>
                            <input type="password" id="newPasswordCfm" name="newPasswordCfm" class="txt w200 easyui-validatebox" data-options="required:true,validType:'length[6,8]'"/>
                        </p>
                    </div>
                    
				</dd>
			</dl>
			
			<!-- <dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>帮助</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label></label>
						</p>
					</div>
				</dd>
			</dl> -->
			
			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="edit" class="btn" value="确定"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />