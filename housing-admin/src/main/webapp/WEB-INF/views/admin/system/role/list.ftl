<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/role"/>

<style>
#newstypeTree img {
	max-width: 390px;
	max-height: 290px;
}
</style>

<link rel="stylesheet"
	href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/colorbox/colorbox.css"
	type="text/css"></link>
<script type="text/javascript"
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/colorbox/jquery.colorbox-min.js"></script>

<script language="javascript">
	$(function() {

		$("#btn_add").click(function() {
			$("#roleid").val('');
			$("#rolesName").val('');
			$("#roleCode").val('');
			$("#roleCode").attr('disabled',false);
			$("#rolesName").attr('disabled',false);
			$("#content").val('');
			$("#addRole").window({
				width : 600,
				height : 323,
				title : "角色添加",
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
			$("#roleCode").validatebox({   
			    required: true,   
			    validType: ['length[1,20]','remote["${currentBaseUrl}/validateRole","roleCode"]'] 
			});
		});
		
		//分配权限
		$("#btn_res").click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			$("#allotResourceWin").window({
				width : 400,
				height : 510,
				title : "分配权限",
				href : "${currentBaseUrl}/role2Res?id="+selected.id+"&rolesName="+selected.rolesName,
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
			
		});
		
		$('#btn_edit').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			$("#roleid").val(selected.id);
			$("#rolesName").val(selected.rolesName);
			$("#roleCode").val(selected.roleCode);
			$("#rolesName").attr('disabled',true);
			$("#roleCode").attr('disabled',true);
			$("#rolesName").validatebox({});
			$("#roleCode").validatebox({});
			$("#content").val(selected.content);
			$("#addRole").window({
				width : 600,
				height : 323,
				title : "角色添加",
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		});

		// 查询按钮
		$('#btn-search').click(function() {
			$('#dataGrid').datagrid('reload', queryParamsHandler());
		});

		$('#btn_del').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			$.messager.confirm('确认', '确定删除该角色吗?此操作不可撤销', function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});
					$.ajax({
						url : '${currentBaseUrl}/del?id=' + selected.id,
						success : function(e) {
						  	$.messager.show({
								title:'提示',
								msg:e,
								showType:'show'
							});
							$('#dataGrid').datagrid('reload');
							$.messager.progress('close');
						}
					});
				}
			});
		});

		$("#addBtn").click(function(){
			var isValid = $("#addRoleForm").form('validate');
			if(isValid){
			$.messager.progress({
				text : "提交中..."
			});
			 $("#addRoleForm").form('submit',{
				 url:"${currentBaseUrl}/save",
			     success:function(e){
		    		closeW();
		    		$('#addRoleForm').form('clear');
		    		$.messager.progress('close');
		 		    $("#dataGrid").datagrid('reload');
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

	function closeW(){
	    $("#addRole").window("close");
	} 
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		角色列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">角色名称 :</label> <input type="text"
							class="txt" id="q_rolesName" name="q_rolesName"
							value="${queryMap['q_rolesName']!''}" />
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
				<th field="rolesName" width="120" align="center">角色名称</th>
				<th field="content" width="120" align="center">角色描述</th>
				<th field="createTime" width="90" align="center">创建时间</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/admin/system/role/add">
		<a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/role/edit">
		<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/role/del">
		<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/role/role2Res">
		<a id="btn_res" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">分配权限</a>
		</@shiro.hasPermission>
		<a id="btn-search"
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">查询</a>
	</div>

	<div id="addRole" class="wrapper">
		<div class="formbox-a">
			<form id="addRoleForm" method="post">
				<input type="hidden" id="roleid" name="id" value="0">
				<div class="form-contbox">
					<dl class="dl-group">

						<dd class="dd-group">
							<div class="fluidbox">
								<p class="p12 p-item">
									<label class="lab-item"><font class="red">*</font>角色名称:
									</label>
									<input class="txt w200 easyui-validatebox" type="text" id="rolesName"
										name="rolesName"
										data-options="required:true,validType:['length[1,30]']" />
									<span class="title_span">1-30个字符</span>
								</p>
							</div>
							<br />
							<div class="fluidbox">
								<p class="p12 p-item">
									<label class="lab-item"><font class="red">*</font>角色编码:
									</label>
									<input class="txt w200 easyui-validatebox" type="text" id="roleCode"
										name="roleCode"
										data-options="required:true,validType:['length[1,20]']" />
									<span class="title_span">1-20个字符,唯一</span>
								</p>
							</div>
							<br/>
							<div class="fluidbox">
								<p class="p12 p-item">
									<label class="lab-item"><font class="red">*</font>角色描述:
									</label> <input class="txt w200 easyui-validatebox" type="text" id="content"
										name="content" style="width: 300px;"
										data-options="required:true,validType:['length[1,200]']" />
								</p>
							</div>
							<br />

						</dd>
					</dl>

					<p class="p-item p-btn">
						<a class="easyui-linkbutton" iconCls="icon-save" id="addBtn">保存</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-delete" onclick="closeW()">关闭</a> 
					</p>
				</div>
			</form>
		</div>
	</div>
	
	<div id="allotResourceWin">
		
	</div>


</div>


<#include "/admin/commons/_detailfooter.ftl" />
