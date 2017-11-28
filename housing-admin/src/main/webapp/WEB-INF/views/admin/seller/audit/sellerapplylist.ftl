<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/audit"/>
<script language="javascript">
	var codeBox;
	$(function() {
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("SELLER_APPLY_STATE")})');
		</#noescape>
		
		$('#btn_audit').click(function(){
	 		var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		window.location.href='${currentBaseUrl}/audit.html?id='+selected.id;
		});
		
		$('#btn_del').click(function(){
	 		var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		if (selected.state != 1 && selected.state != 4) {
				$.messager.alert('提示','只能删除提交申请和审核失败状态的商家申请。');
				return;
			}
	 		$.messager.confirm('确认', '确定删除该商家申请吗?删除后,该商家账号也会被删除,此操作不可撤销', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/delete?id="+selected.id+"&userId="+selected.userId,
						success:function(data, textStatus){
							if (data.success) {
								$.messager.alert('提示','删除成功。');
								$('#dataGrid').datagrid('reload',queryParamsHandler());
							} else {
								$.messager.alert("提示",data.message);
							}
							$.messager.progress('close');
						}
					});
			    }
			});
		});

		// 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		$("#btn-gridAdd").click(function(){
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/seller/audit/add";
		});
		
		$("#btn-gridEdit").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			if (selected.state != 1 && selected.state != 4) {
				$.messager.alert('提示','只能修改提交申请和审核失败状态的商家申请。');
				return;
			}
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/seller/audit/edit?sellerApplyId="+selected.id;
		});
	});

	function getState(value, row, index) {
		var box = codeBox["SELLER_APPLY_STATE"][value];
 		return box;
	}
</script>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
	border="false">
	<h2 class="h2-title">
		商家申请列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">税务登记号 :</label> <input type="text"
							class="txt" id="q_taxLicense" name="q_taxLicense" value="${q_taxLicense!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">状态 :</label> <@cont.select id="q_state"
						codeDiv="SELLER_APPLY_STATE" name="q_state" style="width:100px"/>
					</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false">
	<table id="dataGrid" class="easyui-datagrid"
		data-options="rownumbers:true
						,idField :'id'
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="userId" hidden="hidden"></th>
				<th field="name" width="100" align="center">用户名</th>
				<th field="company" width="120" align="center">公司名称</th>
				<th field="bussinessLicense" width="120" align="center">营业执照注册号</th>
				<th field="taxLicense" width="120" align="center">税务登记号</th>
				<th field="legalPerson" width="100" align="center">法定代表人</th>
				<th field="state" width="90" align="center" formatter="getState">状态</th>
				<th field="createTime" width="110" align="center">申请时间</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/admin/seller/audit/audit.html">
		<a id="btn_audit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">审核</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/seller/audit/add">
		<a id="btn-gridAdd" href="/admin/seller/audit/add" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/seller/audit/edit">
		<a id="btn-gridEdit" href="/admin/seller/audit/edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/seller/audit/delete">
		<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
		
		<a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<div></div>
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
