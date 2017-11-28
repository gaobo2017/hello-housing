<div id="_memberuplog" class="easyui-dialog popBox" title="会员等级升级日志" style="width:800px;height:400px;" data-options="resizable:true,closable:true,closed:true,cache: false,modal: true">
	<div data-options="region:'center'" border="false" style="width:783px;height:358px;">
		<table id="memberuplogdataGrid" class="easyui-datagrid" 
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
		            <th field="beforeExper" width="100" align="left" halign="center">升级之前的经验值</th>  
		            <th field="afterExper" width="100" align="left" halign="center">升级之后的经验值</th>  
		            <th field="beforeGrade" width="100" align="left" halign="center" formatter="gradeFormat">升级之前的等级</th>  
		            <th field="afterGrade" width="100" align="left" halign="center" formatter="gradeFormat">升级之后的等级</th>  
		            <th field="createTime" width="150" align="left" halign="center">升级时间</th>  
				</tr>
			</thead>
		</table>
	</div>
</div>
