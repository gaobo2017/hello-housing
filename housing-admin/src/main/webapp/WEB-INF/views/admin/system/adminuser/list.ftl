<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/adminuser"/>
<script language="javascript">
	var codeBox;
	$(function() {
		<#noescape>
		codeBox = eval('(${initJSCodeContainer("ADMIN_STATUS")})');
		</#noescape>
		
		// 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		$('#btn_add').click(function(){
	 		$("#editWin").window({
				width : 600,
				height : 290,
				href : "${currentBaseUrl}/add",
				title : "新增管理员",
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		});
		
		$('#btn_edit').click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		$("#editWin").window({
				width : 600,
				height : 323,
				href : "${currentBaseUrl}/edit?id="+selected.id,
				title : "修改管理员信息",
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		});
		
		$('#btn_freeze').click(function(){
	 		var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		if(selected.status==2){
	 			$.messager.alert('提示','此账号已被冻结');
				return;
	 		}
	 		if(selected.status==3){
	 			$.messager.alert('提示','此账号已被删除');
				return;
	 		}
			$.messager.confirm('确认', '确定冻结该管理员账号吗？冻结后，该管理员将无法登录', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/freeze?id="+selected.id,
						success:function(e){
							$('#dataGrid').datagrid('reload');
							$.messager.progress('close');
							$.messager.show({
								title:'提示',
								msg:e,
								showType:'show'
							});
						}
					});
			    }
			});
		});
		
		$('#btn_del').click(function(){
	 		var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		if(selected.status==3){
	 			$.messager.alert('提示','此账号已被删除');
				return;
	 		}
			$.messager.confirm('确认', '确定删除该管理员账号吗？删除后，该管理员将无法登录,但所拥有的角色资源仍将保留', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/del?id="+selected.id,
						success:function(e){
							$('#dataGrid').datagrid('reload');
							$.messager.progress('close');
							$.messager.show({
								title:'提示',
								msg:e,
								showType:'show'
							});
						}
					});
			    }
			});
		});
		
	});

	function getState(value, row, index) {
		var box = codeBox["ADMIN_STATUS"][value];
		return box;
	}
</script>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
	border="false">
	<h2 class="h2-title">
		管理员列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">账号 :</label> <input type="text" placeholder="账号查找..."
							class="txt" id="q_name" name="q_name" value="${queryMap['q_name']!''}" />
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
				<th field="name" width="120" align="center">账号</th>
				<th field="roleName" width="120" align="center">角色</th>
				<th field="tel" width="120" align="center">电话</th>
				<th field="createTime" width="120" align="center">创建时间</th>
				<th field="status" width="70" align="center" formatter="getState">状态</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/admin/system/adminuser/add">
		<a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/adminuser/edit">
		<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/adminuser/freeze">
		<a id="btn_freeze" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">冻结</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/adminuser/del">
		<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
		<a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
	
	<div class="wrapper" id="editWin">
		
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
