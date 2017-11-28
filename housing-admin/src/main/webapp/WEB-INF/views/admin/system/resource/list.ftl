<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/resource"/>
<script language="javascript">
	var codeBox;
	$(function() {
		//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("RESOURCE_STATUS")})');
		</#noescape>

		$('#a-gridSearch').click(function() {
			$('#dataGrid').datagrid('reload', queryParamsHandler());
		});
		
		$("#a-gridAdd").click(function() {
			var selected = $('#dataGrid').treegrid('getSelected');
			var id = "";
			if(selected)
				id = selected.id;
			else
				id="1";
			$("#addResources").window({
				width : 1100,
				height : 480,
				href : "${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/resource/addWin?id="+id,
				title : "资源添加",
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		});
		
		$("#a-gridEdit").click(function() {
			var selected = $('#dataGrid').treegrid('getSelected');
			$("#addResources").window({
				width : 1100,
				height : 480,
				href : "${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/resource/editWin?id="+selected.id,
				title : "资源编辑",
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		});

		$("#a-gridRemove").click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			if (selected.children.length > 0 || selected.state == 'closed') {
				$.messager.alert('提示', '该节点下有子节点,不允许删除。');
				return;
			}
			$.messager.confirm('确认', '确定删除此资源吗？此操作不可撤消', function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});
					$.ajax({
						url : "${currentBaseUrl}/del?id=" + selected.id,
						cache : false,
						success : function(e) {
							$('#dataGrid').treegrid('reload');
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
	})

	function typeFormat(value, row, index) {
		return value == 1 ? "菜单" : value == 0 ? "根节点" : "按钮";
	}
	
	function scopeFormat(value, row, index) {
		return value == 1 ? "商家" : "平台";
	}

	function statusFormat(value, row, index) {
		return codeBox["RESOURCE_STATUS"][value];
	}

	function afterDataGridLoaded() {
	}

	function dataGridLoadFail() {
		alert("服务器异常");
	}

	function dataGridLoadSuccess(row, data) {
		$('#dataGrid').treegrid('expand',1);
	}

	function cc(row) {
	}

	function bl(row, param) {
		if (!row) {
			param.id = 0;
		}
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		资源列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont" style="display: none">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">类型名称 :</label> <input type="text"
							class="txt" id="q_name" name="q_name" value="${q_name!''}" />
					</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false">
	<table id="dataGrid" class="easyui-treegrid"
		data-options="rownumbers:false
						,singleSelect:true
						,autoRowHeight:true
						,animate:true
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:false
						,pageSize:'2'
						,fit:true
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,onLoadError:dataGridLoadFail
						,onBeforeExpand:cc
						,treeField:'content'
						,idField:'id'
    					,method:'get'
						,onBeforeLoad:bl">
		<thead>
			<tr>
				<th field="content" width="120" align="left" halign="center">资源名称</th>
				<th field="url" width="120" align="left" halign="center">资源链接</th>
				<th field="type" width="60" align="center" formatter="typeFormat">资源类型</th>
				<th field="scope" width="60" align="center" formatter="scopeFormat">应用范围</th>
				<th field="status" width="60" align="center" formatter="statusFormat">状态</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<#noescape>
			${buttons!}
		</#noescape>
		<@shiro.hasPermission name="/admin/system/resource/addWin">
		<a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/resource/editWin">
		<a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/resource/del">
		<a id="a-gridRemove" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>

	<div class="wrapper" id="addResources">
		
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />
