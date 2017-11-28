<#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/resource"/>

<script>
	$(function() {
		$("#addBtn").click(function() {
			var isValid = $("#addResourcesForm").form('validate');
			if (isValid) {
				var type = $('#type').combobox('getValue');
				if (type == "") {
					$.messager.alert('提示', '请选择资源类型。');
					return;
				}
				
				var scope = $('#scope').combobox('getValue');
				if (scope == "") {
					$.messager.alert('提示', '请选择应用范围。');
					return;
				}
				
				$.messager.progress({
					text : "提交中..."
				});
				$("#addResourcesForm").form('submit', {
					url : "${currentBaseUrl}/save",
					success : function(e) {
						$.messager.progress('close');
						$.messager.show({
							title : '提示',
							msg : e,
							showType : 'show'
						});
						$('#dataGrid,window.parent.document').treegrid('reload');
						closeW();
					}
				});
			}
		});
	});
	
	function closeW(){
		$("#addResources,window.parent.document").window("close");
	}
	
	function loadSuccess(){
		var t = $("#pid").combotree('tree');
	    var node = t.tree('getSelected');
	    if (node){
			t.tree('expandTo', node.target);
	    }
	}
</script>
<div class="formbox-a">
	<form id="addResourcesForm" method="post">
		<div class="form-contbox">
			<dl class="dl-group">

				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>资源名称: </label>
							<span id="resourcesNameSpan"> <input
								class="txt w200 easyui-validatebox" type="text" id="content"
								name="content"
								data-options="required:true,validType:'length[1,40]'"
								class="txt w400" /> <span class="title_span">长度为1-40个字符</span>

							</span>
						</p>
					</div>
					<br />

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>&nbsp;父&nbsp;资&nbsp;源:
							</label> 
							<input id="pid" class="txt w200 easyui-combotree" name="pid"
								value="${id}"
								data-options="
								url:'${currentBaseUrl}/resTree',
								method:'get',
								onLoadSuccess:loadSuccess,
								required:true" />
						</p>
					</div>
					<br />

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>资源链接: </label>
							<input class="txt w500 easyui-validatebox" type="text" id="url"
								name="url" 
								data-options="required:true,validType:'length[1,255]'" />
							<span class="title_span">长度为1-255个字符</span>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								填写完成该资源操作需要的所有链接地址，并用英文逗号分隔，不能有空格或其他特殊字符；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								菜单类型的资源链接的第一个链接必须填写打开菜单所指向资源的链接，如下/admin/order为打开列表页的链接，后两个为获取数据的链接；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								列表页面，如果显示列表数据需要3个链接，可如下填写[/admin/order,/admin/order/list,/admin/orderproduct/listbyorder]；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								新增编辑数据页面，需要填写打开页面链接、保存数据的链接，[/admin/resource/add,/admin/resource/create]；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								如果打开页面还会调用其他资源链接，请用逗号分隔添加在之后，如[/admin/resource/add,/admin/resource/create,/admin/resource/listparent]；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								如果是一级菜单，平台：admin_menu_模块名，商家：seller_menu_模块名，如admin_menu_order
								</font>
							</label>
						</p>
					</div>
					<br />

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>资源类型: </label>
							<select id="type" class="txt w200 easyui-combobox" name="type"
								editable="false" data-options="required:true">
								<option value="">请选择</option>
								<option value="2">按钮</option>
								<option value="1">菜单</option>
							</select>
						</p>
					</div>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>应用范围: </label>
							<select id="scope" class="txt w200 easyui-combobox" name="scope"
								editable="false" data-options="required:true">
								<option value="">请选择</option>
								<option value="2">平台</option>
								<option value="1">商家</option>
							</select>
						</p>
					</div>
					
					<#-- <!-- <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">资源ID: </label>
							<input class="txt w200 easyui-validatebox" type="text" id="resId"
								name="resId"
								data-options="required:false,validType:'length[0,20]'" />
							<div class="tooltip tooltip-right"
									style="margin-left: 360px; display: block; color: #C93; background-color: #FFC; border-color: #DBB46F;">
									<div class="tooltip-content">菜单或按钮的ID</div>
									<div class="tooltip-arrow-outer"
										style="border-right-color: rgb(204, 153, 51);"></div>
									<div class="tooltip-arrow"
										style="border-right-color: rgb(255, 255, 204);"></div>
							</div>
						</p>
					</div>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">资源图标: </label>
							<input class="txt w200 easyui-validatebox" type="text" id="resIcon"
								name="resIcon"
								data-options="required:false,validType:'length[0,20]'" />
								<div class="tooltip tooltip-right"
									style="margin-left: 360px; display: block; color: #C93; background-color: #FFC; border-color: #DBB46F;">
									<div class="tooltip-content">部分按钮需要指定资源图标</div>
									<div class="tooltip-arrow-outer"
										style="border-right-color: rgb(204, 153, 51);"></div>
									<div class="tooltip-arrow"
										style="border-right-color: rgb(255, 255, 204);"></div>
								</div>
						</p>
					</div> -->

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