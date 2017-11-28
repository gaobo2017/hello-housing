<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/news"/>

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
		$('#btn_add').click(function() {
			location.href = '${currentBaseUrl}/add';
		});

		$('#btn_edit').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			location.href = '${currentBaseUrl}/edit?id=' + selected.id;
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
			$.messager.confirm('确认', '确定删除该文章吗?此操作不可撤销', function(r) {
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
			width : 1000,
			height : 500,
			title : "新闻内容",
			closed : true,
			shadow : true,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			modal:true
		});
		
		$(".datagrid-body .datagrid-cell a").colorbox();
	});

	function showContent(value, row, index) {
		return "<a title='双击查看内容' class='newstype_view' onclick='showdetail("+
				index+");' href='javascript:;'><font color='276892'>"
			+value+"</font></a>";
	}

	function showdetail(idx) {
		var row = $('#dataGrid').datagrid('getData').rows[idx];
		$("#newstypeTree").html(row.content);
		$("#newstypeWin").window('open');
	}
	

	function showURL(value, row, index) {
		if (value)
			return "<a href='"+value+"' target='_blank'><font color='0227A9'>"
					+ value + "</font></a>";
		else
			return "--";
	}

	function formatBoolean(value, row, index) {
		return value == 1 ? "是" : "否";
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		文章列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">文章标题 :</label> <input type="text"
							class="txt" id="q_title" name="q_title"
							value="${queryMap['q_title']!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">文章内容:</label> <input type="text"
							class="txt" id="q_content" name="q_content" style="width:200px"
							value="${queryMap['q_content']!''}" />
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
				<th field="content" hidden="hidden"></th>
				<th field="typePath" width="60" align="center">所属分类</th>
				<th field="title" width="120" align="center" formatter="showContent">新闻标题</th>
				<th field="author" width="40" align="center">作者</th>
				<th field="isOut" width="60" align="center" formatter="formatBoolean">外部链接</th>
				<th field="outUrl" width="60" align="center" formatter="showURL">外部URL</th>
				<th field="status" width="40" align="center" formatter="formatBoolean">是否显示</th>
				<th field="isRecommend" width="40" align="center" formatter="formatBoolean">是否推荐</th>
				<th field="createTime" width="40" align="center">创建时间</th>
				<th field="updateTime" width="40" align="center">最后更新</th>
				<th field="sort" width="30" align="center">排序</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/admin/system/news/add">
		<a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/news/edit">
		<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/news/del">
		<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
		<a id="btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
</div>

<div id="newstypeWin">
	<form id="newstypeForm" method="post">

		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px;overflow: auto;"></ul>
	</form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
