<div id="_memberaddress" class="easyui-dialog popBox" title="会员收货地址" style="width:800px;height:400px;" data-options="resizable:true,closable:true,closed:true,cache: false,modal: true">
	<div data-options="region:'center'" border="false" style="width:783px;height:358px;">
		<table id="memberaddressdataGrid" class="easyui-datagrid" 
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
		            <!-- <th field="provinceId" width="100" align="left" halign="center">省</th>  
		            <th field="cityId" width="150" align="left" halign="center">市</th>  
		            <th field="areaId" width="150" align="left" halign="center">区</th>   -->
		            <th field="addAll" width="100" align="left" halign="center">省市区</th>  
		            <th field="addressInfo" width="200" align="left" halign="center">详细地址</th>  
		            <th field="mobile" width="100" align="left" halign="center">手机</th>  
		            <th field="phone" width="100" align="left" halign="center">固话</th>  
		            <th field="email" width="100" align="left" halign="center">邮箱</th>  
		            <th field="state" width="60" align="left" halign="center" formatter="addressStateFormat">是否默认</th>  
		            <th field="createTime" width="150" align="left" halign="center">创建时间</th>  
		            <th field="updateTime" width="150" align="left" halign="center">修改时间</th>  
				</tr>
			</thead>
		</table>
	</div>
</div>
