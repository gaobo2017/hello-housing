<div id="_memberbalancelog" class="easyui-dialog popBox" title="会员余额变化日志" style="width:800px;height:400px;" data-options="resizable:true,closable:true,closed:true,cache: false,modal: true">
	<div data-options="region:'center'" border="false" style="width:783px;height:358px;">
		<table id="memberbalancelogdataGrid" class="easyui-datagrid" 
				data-options="rownumbers:true
							,singleSelect:true
							,autoRowHeight:false
							,fitColumns:false
							,striped:true
							,pagination:true
							,pageSize:'${pageSize}'
							,fit:true
	    					,onLoadSuccess:dataGridLoadSuccess
	    					,method:'get'">
			<thead>
				<tr>
					<th field="memberName" width="150" align="left" halign="center">用户名</th>  
		            <th field="moneyBefore" width="100" align="left" halign="center">变化之前的余额</th>  
		            <th field="moneyAfter" width="100" align="left" halign="center">变化之后的余额</th>  
		            <th field="money" width="100" align="left" halign="center">变化金额</th>  
		            <th field="state" width="80" align="left" halign="center" formatter="balanceStateFormat">变化原因</th>  
		            <th field="remark" width="200" align="left" halign="center">备注</th>  
		            <th field="createTime" width="200" align="left" halign="center">升级时间</th>  
					<th field="optName" width="150" align="left" halign="center">操作者</th>  
				</tr>
			</thead>
		</table>
	</div>
</div>
