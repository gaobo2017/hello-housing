<#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/adminuser"/>

<script>
	$(function() {
		$("#addBtn").click(function() {
			var isValid = $("#adForm").form('validate');
			if (isValid) {
				$.messager.progress({
					text : "提交中..."
				});
				$("#adForm").form('submit', {
					url : "${currentBaseUrl}/save",
					success : function(e) {
						$.messager.progress('close');
						$.messager.show({
							title : '提示',
							msg : e,
							showType : 'show'
						});
						$('#dataGrid,window.parent.document').datagrid('reload');
						closeW();
					}
				});
			}
		});
		
	});
	
	function closeW(){
		$("#editWin,window.parent.document").window("close");
	}
	
</script>
<div class="formbox-a">
	<#if !admin??>
	<span style="color: #F00;float: left;margin-left: 20px;margin-top: 2px;">
		<img style="float: left;margin-right: 6px;" 
		src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/images/warning.jpg"/>
		新增的管理员账号初始密码为:123456,
		请告知用户登录后及时修改密码
	</span>
	</#if>

	<form id="adForm" method="post">
		<input type="hidden" name="id" value="${(admin.id)!}"/>
	
		<div class="form-contbox">
			<dl class="dl-group">

				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>账号: </label>
							<span id="nameSpan"> <input
								class="txt w200 easyui-validatebox" type="text" id="name"
								name="name" value="${(admin.name)!}"
								data-options="required:true,validType:'length[1,40]'"
								class="txt w400" /> <span class="title_span">登录账号名</span>
							</span>
						</p>
					</div>
					<#if admin??>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">密码: </label>
							<span id="pwdspan"> <input
								class="txt w200" type="text" id="password"
								name="password"
								class="txt w400" /> 
								
								<div class="tooltip tooltip-right"
									style="left: 66%;display: block; color: #C93; background-color: #FFC; border-color: #DBB46F;">
									<div class="tooltip-content">为空表示不修改</div>
									<div class="tooltip-arrow-outer"
										style="border-right-color: rgb(204, 153, 51);"></div>
									<div class="tooltip-arrow"
										style="border-right-color: rgb(255, 255, 204);"></div>
								</div>
							</span>
						</p>
					</div>
					</#if>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">电话: </label>
							<span id="telspan"> <input
								class="txt w200 easyui-validatebox" type="text" id="tel"
								name="tel" value="${(admin.tel)!}"
								data-options="validType:'length[1,20]'"
								class="txt w400" /> <span class="title_span">长度为1-20个字符</span>

							</span>
						</p>
					</div>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>角色: </label>
							<select id="roleId" class="txt w200 easyui-combobox" name="roleId"
								editable="false" data-options="required:true">
								<#list roles as role>
									<option value="${role.id}" <#if admin??&&admin.roleId==role.id>selected</#if>>${role.rolesName}</option>
								</#list>
							</select>
						</p>
					</div>

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>状态: </label>
							<select id="scope" class="txt w200 easyui-combobox" name="status"
								editable="false" data-options="required:true">
								<#list codeManager.codeMap['ADMIN_STATUS'] as code>
									<option value="${code.codeCd}" <#if admin??&&admin.status?string==code.codeCd>selected</#if>>${code.codeText!''}</option>
								</#list>
							</select>
						</p>
					</div>

				</dd>
			</dl>

			<p class="p-item p-btn">
				<a id="addBtn" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-delete" onclick="closeW()">关闭</a> <input
					type="hidden" id="rid" name="rid" value="0">
			</p>
		</div>
	</form>
</div>