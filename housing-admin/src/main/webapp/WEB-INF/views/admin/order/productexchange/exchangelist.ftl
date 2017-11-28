<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/productExchange"/>

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
			codeBox = eval('(${initJSCodeContainer("MEM_PROD_EXCHG_STATE")})');
		</#noescape>

		// 查询按钮
		$('#btn-search').click(function() {
			$('#dataGrid').datagrid('reload', queryParamsHandler());
		});

	});

	function getState(value, row, index) {
		var box = codeBox["MEM_PROD_EXCHG_STATE"][value];
		return box;
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		换货申请列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">订单号:</label> <input type="text"
							class="txt" id="q_companyName" name="q_companyName"
							value="${q_companyName!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">换货状态 :</label> <@cont.select
						id="q_state" codeDiv="MEM_PROD_EXCHG_STATE"
						value="${q_state!''}" name="q_state"
						style="width:100px"/>
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
				<th field="orderSn" width="120" align="center">订单号</th>
				<th field="productName" width="120" align="center">商品名称</th>
				<th field="memberName" width="120" align="center">用户名</th>
				<th field="question" width="120" align="center">问题描述</th>
				<th field="state" width="120" align="center" formatter="getState">退货状态</th>
				<th field="createTime" width="90" align="center">创建时间</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-search"
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">查询</a>
	</div>
</div>

<div id="newstypeWin">
	<form id="newstypeForm" method="post">

		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
	</form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
