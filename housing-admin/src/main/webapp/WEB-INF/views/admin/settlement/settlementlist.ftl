<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/settlement"/>

<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>

<style>
#newstypeTree img {
	max-width: 390px;
	max-height: 290px;
}
</style>

<script language="javascript">
	var statusBox;
	var otherTypeBox;
	$(function() {

		<#noescape>
			statusBox = eval('(${initJSCodeContainer("SETTLEMENT_STATUS")})');
			otherTypeBox = eval('(${initJSCodeContainer("SETTLE_OTHER_TYPE")})');
		</#noescape>

		$("#btn-detail").click(function(){
			var selectedCode = $('#dataGrid').datagrid('getSelected');
			if(!selectedCode){
				$.messager.alert('提示','请选择操作行。');
				return;
			}	
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/settlement/detail?id="+selectedCode.id;
		});	
		
		// 查询按钮
		$('#btn-search').click(function() {
			$('#dataGrid').datagrid('reload', queryParamsHandler());
		});

		<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	});

	function settlementStatus(value, row, index) {
		var box = statusBox["SETTLEMENT_STATUS"][value];
		return box;
	}
	
	function otherType(value, row, index) {
		var box = otherTypeBox["SETTLE_OTHER_TYPE"][value];
		return box;
	}
	
	
</script>

<div id="devWin"></div>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		结算账单列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<form class="form-search" action="doForm" method="post" id="queryForm"
			name="queryForm">
			<div class="fluidbox">
				<p class="p4 p-item">
					<label class="lab-item">结算周期:</label> <input type="text" class="txt"
						id="q_settleCycle" name="q_settleCycle" value="${q_settleCycle!''}" />
				</p>
				<p class="p4 p-item">
					<label class="lab-item">结算状态 :</label> <@cont.select id="q_status"
					codeDiv="SETTLEMENT_STATUS" value="${q_status!''}" name="q_status" style="width:100px"/>
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
				<th field="settleCycle" width="60" align="center">结算周期</th>
				<th field="sellerName" width="120" align="center">店铺</th>
				<th field="moneyOrder" width="60" align="center">订单总额</th>
				<th field="moneyPaidBalance" width="60" align="center">余额支付总额</th>
				<th field="moneyPaidReality" width="60" align="center">现金支付总额</th>
				<th field="moneyIntegral" width="60" align="center">积分转换总额</th>
				<th field="integral" width="60" align="center">积分总额</th>
				<th field="moneyBack" width="60" align="center">退款总额</th>
				<th field="moneyIntegralBack" width="80" align="center">退会积分金额总额</th>
				<th field="moneyOther" width="60" align="center">其他金额</th>
				<th field="moneyOtherType" width="60" align="center" formatter="otherType">其他金额类型</th>
				<th field="moneyOtherReason" width="60" align="center">其他金额理由</th>
				<th field="commision" width="60" align="center">佣金总额</th>
				<th field="payable" width="60" align="center">系统计算总额</th>
				<th field="status" width="70" align="center" formatter="settlementStatus">结算状态</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/settlement/detail">
		<a id="btn-detail" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">详情</a>
		</@shiro.hasPermission>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />
