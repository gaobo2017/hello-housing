<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/courierCompany"/>

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

<script language="javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/lodop/LodopFuncs.js"></script>
<script language="javascript" type="text/javascript"> 
    var LODOP; //声明为全局变量       
	function myBlankDesign() {       
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
		LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='http://s1.sinaimg.cn/middle/721e77e5t99431b026bd0&690'>");
		LODOP.PRINT_DESIGN();
	};
</script> 

<script language="javascript">
	var codeBox;
	var disBox;
	$(function() {
		
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("TRANSPORT_MODEL")})');
			disBox = eval('(${initJSCodeContainer("DISABLE_STATE")})');
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
		
		$('#btn-courier-company').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			location.href = '${currentBaseUrl}/editCourierCompany?id='+selected.id;
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
			$.messager.confirm('确认', '确定删除该物流公司吗?此操作不可撤销', function(r) {
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

	function showimg(href) {
		$("#newstypeTree").html(
				"<img src='${domainUrlUtil.EJS_URL_RESOURCES}/"+href+"' alt='企业logo'>");
		$("#newstypeWin").window('open');
	}
	
	function getState(value, row, index) {
		var box = disBox["DISABLE_STATE"][value];
		return box;
	}
	
	function getType(value, row, index) {
		var box = codeBox["TRANSPORT_MODEL"][value];
		return box;
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		物流公司列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">物流公司名称:</label> <input type="text"
							class="txt" id="q_companyName" name="q_companyName"
							value="${queryMap['q_companyName']!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">快递类型 :</label> <@cont.select id="q_companyType"
						codeDiv="TRANSPORT_MODEL" value="${queryMap['q_companyType']!''}" 
						name="q_companyType" style="width:100px"/>
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
				<th field="companyName" width="120" align="center">物流公司名称</th>
				<th field="companyMark" width="120" align="center">物流公司代码</th>
				<th field="seq" width="120" align="center">排序号</th>
				<th field="companyType" width="120" align="center" formatter="getType">快递类型</th>
				<th field="state" width="50" align="center" formatter="getState">状态</th>
				<th field="createTime" width="90" align="center">创建时间</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<a id="btn-courier-company" href="javascript:void(0)" target="_blank" class="easyui-linkbutton" iconCls="icon-edit" plain="true">设置打印单</a>
		<@shiro.hasPermission name="/admin/operate/courierCompany/add">
		<a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/operate/courierCompany/edit">
		<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/operate/courierCompany/del">
		<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>
</div>

<div id="newstypeWin">
	<form id="newstypeForm" method="post">

		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
	</form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
