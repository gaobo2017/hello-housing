<div id="normDialog" class="easyui-dialog popBox" title="商品规格列表"
	style="width: 980px; height: 520px;"
	data-options="resizable:true,closable:true,closed:true,cache: false,modal: true"
	buttons="#dlg-buttons-award-act">

	<div class="easyui-layout" data-options="fit:true">
			<table id="normDataGrid" class="easyui-datagrid"
				data-options="
							rownumbers:true,
							autoRowHeight:false,
							striped : true,
							singleSelect : false,
							fit:true,
							fitColumns:true,
							url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/norm/list_no_page',
							method:'get'">
				<thead>
					<tr>
						<th field="ck" checkbox="true"></th>
						<th field="name" width="150" align="center">规格名称</th>
						<th field="createTime" width="150" align="center">创建时间</th>
						<th field="sort" width="60" align="center">排序</th>
					</tr>
				</thead>
			</table>
	</div>
	
	<div id="dlg-buttons-award-act" style="text-align: right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="submit()">确定</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#normDialog').dialog('close')">取消</a>
	</div>
</div>

<script language="javascript">
	function submit() {
		var selectedRow = $("#normDataGrid").datagrid('getSelections');
		if (!selectedRow || selectedRow.length == 0) {
			$.messager.alert('友情提示', '请至少选择一个对象');
			return false;
		}
		var callbackfunc = eval('normCallBack');
		callbackfunc(selectedRow);
		$("#normDialog").dialog('close');
	}
</script>