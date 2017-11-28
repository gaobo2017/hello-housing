<div id="brandDlg" class="easyui-dialog popBox" title="商品品牌列表"
	style="width: 980px; height: 520px;"
	data-options="resizable:true,closable:true,closed:true,cache: false,modal: true"
	buttons="#dlg-buttons-brand">

	<div class="easyui-layout" data-options="fit:true">
		<table id="brandDataGrid" class="easyui-datagrid"
			data-options="
						rownumbers:true,
						autoRowHeight:false,
						striped : true,
						singleSelect : false,
						fit:true,
						fitColumns:true,
						url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/brand/list_no_page',
						method:'get'">
			<thead>
				<tr>
					<th field="ck" checkbox="true"></th>
					<th field="name" width="150" align="center">品牌名称</th>
					<th field="createTime" width="150" align="center">创建时间</th>
					<th field="sort" width="150" align="center">排序</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="dlg-buttons-brand" style="text-align: right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="brandSubmit()">确定</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#brandDlg').dialog('close')">取消</a>
	</div>
</div>

<script language="javascript">
	function brandSubmit() {
		var selectedRow = $("#brandDataGrid").datagrid('getSelections');
		if (selectedRow == null) {
			$.messager.alert('友情提示', '请至少选择一个对象');
			return false;
		}
		var callbackfunc = eval('brandCallBack');
		callbackfunc(selectedRow);
		$("#brandDlg").dialog('close');
	}
</script>