<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/complaint"/>

<style>
#newstypeTree img {
	max-width: 390px;
	max-height: 290px;
}
</style>

<script language="javascript">
	var codeBox;
	$(function() {
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("SELLER_COMPLAINT")})');
		</#noescape>

		$('#btn_audit').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
            var state = selected.state;
            if(state != 1 && state !=4){
                $.messager.alert('提示',"状态只有是:买家投诉待审核或者卖家申诉待审核,平台才可以处理");
                return;
            }
			location.href = '${currentBaseUrl}/auditPage?id=' + selected.id;
		});

		// 查询按钮
		$('#btn-search').click(function() {
			$('#dataGrid').datagrid('reload', queryParamsHandler());
		});


		$("#newstypeWin").window({
			width : 666,
			height : 420,
			title : "举证图片",
			closed : true,
			shadow : false,
			modal : true,
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
		$("#newstypeTree")
				.html(
						"<img src='${domainUrlUtil.EJS_IMAGE_RESOURCES}/"+href+"' alt='举证图片'>");
		$("#newstypeWin").window('open');
	}

	function getState(value, row, index) {
		var box = codeBox["SELLER_COMPLAINT"][value];
		return box;
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		投诉列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">订单号 :</label> <input type="text"
							class="txt" id="q_name" name="q_name"
							value="${queryMap['q_name']!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">状态 :</label><@cont.select id="q_state"
						codeDiv="SELLER_COMPLAINT" value="${queryMap['q_state']!''}"
						name="q_state" style="width:100px"/>
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
				<th field="userName" width="50" align="center">投诉人账户名</th>
				<th field="orderSn" width="50" align="center">订单号</th>
				<th field="source" width="40" align="center">投诉来源</th>
				<th field="content" width="150" align="left" halign="center">投诉内容</th>
				<th field="sellerName" width="50" align="center">被投诉商家</th>
				<th field="image" width="40" align="center" formatter="imageFormat">举证图片</th>
				<th field="complaintTimeStr" width="50" align="center">投诉时间</th>
				<th field="state" width="50" align="center" formatter="getState">投诉状态</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/admin/order/complaint/auditPage">
		<a id="btn_audit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">处理</a>
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
