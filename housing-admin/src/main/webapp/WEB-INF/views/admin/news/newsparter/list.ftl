<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/newsParter"/>

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
	var codeBox;
	$(function() {
		
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("NEWS_PARTNER_STATUS")})');
		</#noescape>
		
		$('#btn_add').click(function() {
			location.href = '${currentBaseUrl}/add';
		});
		
		$('#btn_edit').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			location.href = '${currentBaseUrl}/edit?id='+selected.id;
		});
		
		// 查询按钮
		$('#btn-search').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		$('#btn_del').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			$.messager.confirm('确认', '确定删除该合作伙伴吗?此操作不可撤销', function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});
					$.ajax({
						url : '${currentBaseUrl}/del?id=' + selected.id,
						success : function() {
							$('#dataGrid').datagrid('reload');
							$.messager.progress('close');
						}
					});
				}
			});

		});

		$("#newstypeWin").window({
			width : 400,
			height : 300,
			title : "企业logo",
			closed : true,
			shadow : false,
			collapsible : false,
			minimizable : false,
			maximizable : false
		});
	});

	function imageFormat(value, row, index) {
		return "<a class='newstype_view' onclick='showimg($(this).attr(\"imgpath\"));' href='javascript:;' imgpath='"
				+ value + "'>点击查看</a>";
	}

	function showURL(value, row, index) {
		if (value)
			return "<a href='"+value+"' target='_blank'><font color='0227A9'>"
					+ value + "</font></a>";
		else
			return "--";
	}
	
	function showimg(href) {
		$("#newstypeTree").html(
				"<img src='${domainUrlUtil.EJS_URL_RESOURCES}/"+href+"' alt='企业logo'>");
		$("#newstypeWin").window('open');
	}
	
	function getState(value, row, index) {
		var box = codeBox["NEWS_PARTNER_STATUS"][value];
		return box;
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		合作伙伴列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">合作伙伴名称 :</label> <input type="text"
							class="txt" id="q_name" name="q_name"
							value="${queryMap['q_name']!''}" />
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
				<th field="name" width="120" align="center">名称</th>
				<th field="image" width="80" align="center" formatter="imageFormat">logo</th>
				<th field="url" width="120" align="left" halign="center" formatter="showURL">链接</th>
				<th field="status" width="50" align="center" formatter="getState">是否可见</th>
				<th field="sort" width="50" align="center">排序</th>
				<th field="createTime" width="90" align="center">创建时间</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/admin/system/newsParter/add">
		<a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/newsParter/edit">
		<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/newsParter/del">
		<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
		<a id="btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
</div>

<div id="newstypeWin">
	<form id="newstypeForm" method="post">

		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
	</form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
