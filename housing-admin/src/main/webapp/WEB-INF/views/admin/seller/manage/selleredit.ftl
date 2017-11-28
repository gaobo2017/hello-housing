<#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/manage"/>

<script>
	$("#addBtn").click(function() {
		var isValid = $("#addResourcesForm").form('validate');
		if (isValid) {
			$.messager.progress({
				text : "提交中..."
			});
			$("#editForm").form('submit', {
				url : "${currentBaseUrl}/update",
				success : function(e) {
					$.messager.progress('close');
					$.messager.show({
						title : '提示',
						msg : e,
						showType : 'show'
					});
					closeW();
					$('#dataGrid,window.parent.document').datagrid('reload');
				}
			});
		}
	});
	
	function closeW(){
		$("#editWin,window.parent.document").window("close");
	}
	
</script>

<div class="formbox-a">
	<form id="editForm" method="post">
		<div class="form-contbox">
			<dl class="dl-group">
				<input type="hidden" value="${seller.id}" name="id">

				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>商家申请账号: </label>
							<span id="resourcesNameSpan"> <input
								class="txt w200 easyui-validatebox" type="text" id="name"
								name="name" value="${seller.name!}"
								data-options="required:true,validType:['notkong','maxLength[40]']"
								class="txt w400" /> <span class="title_span">长度为1-40个字符</span>

							</span>
						</p>
					</div>
					<br />

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>店铺名: </label>
							<input class="txt w200 easyui-validatebox" type="text" id="sellerName"
								name="sellerName" value="${seller.sellerName!}"
								data-options="required:true,validType:['notkong','maxLength[100]']" />
							<span class="title_span">长度为1-100个字符</span>
						</p>
					</div>
					<br />
					
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