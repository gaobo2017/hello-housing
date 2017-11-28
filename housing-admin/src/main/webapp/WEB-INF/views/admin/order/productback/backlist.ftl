<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/productBack"/>

<style>
#newstypeTree img {
	max-width: 390px;
	max-height: 290px;
}
</style>

<script language="javascript">
	var backCodeBox;
	var monCodeBox;
	$(function() {

		<#noescape>
			backCodeBox = eval('(${initJSCodeContainer("MEM_PB_STATE_RETURN")})');
			monCodeBox = eval('(${initJSCodeContainer("MEM_PB_STATE_MONEY")})');
		</#noescape>

		$('#btn_backMoney').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			var state = selected.stateReturn;
			if(state != 3){
				$.messager.alert('提示', '只有申请状态是已经收货的申请才能退款！');
				return;
			}
			var stateMoney = selected.stateMoney;
			if(stateMoney != 1){
				$.messager.alert('提示', '该申请已经退款，请勿重复操作！');
				return;
			}
			var msg = "用户货款将由系统退还到用户的账户中，确认要退款吗？";
			$.messager.confirm('确认', msg, function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});
					$.ajax({
						type:"POST",
						url : "${currentBaseUrl}/backmoney",
						dataType: "json",
						data: {id:selected.id},
						cache:false,
						success:function(data, textStatus){
							if (data.success) {
								$.messager.alert('提示','退款成功。');
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
		$('#btn-search').click(function() {
			$('#dataGrid').datagrid('reload', queryParamsHandler());
		});

	});

	function proBackState(value, row, index) {
		var box = backCodeBox["MEM_PB_STATE_RETURN"][value];
		return box;
	}
	
	function proMonState(value, row, index) {
		var box = monCodeBox["MEM_PB_STATE_MONEY"][value];
		return box;
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		退货申请列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
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
						<label class="lab-item">退货状态 :</label> <@cont.select
						id="q_stateReturn" codeDiv="MEM_PB_STATE_RETURN"
						value="${q_stateReturn!''}" name="q_stateReturn"
						style="width:100px"/>
					</p>
					<p class="p4 p-item">
						<label class="lab-item">退款状态 :</label> <@cont.select
						id="q_stateMoney" codeDiv="MEM_PB_STATE_MONEY"
						value="${q_stateMoney!''}" name="q_stateMoney"
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
						,fitColumns:false
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
				<th field="orderMoney" hidden="hidden"></th>
				<th field="orderSn" width="150" align="center">订单号</th>
				<th field="productName" width="150" align="center">商品名称</th>
				<th field="memberName" width="120" align="center">用户名</th>
				<th field="question" width="150" align="center">问题描述</th>
				<th field="backMoney" width="80" align="center">退款金额</th>
				<th field="backIntegral" width="60" align="center">退回积分</th>
				<th field="backCouponSn" width="120" align="center">退回优惠券</th>
				<th field="stateReturn" width="100" align="center" formatter="proBackState">退货状态</th>
				<th field="stateMoney" width="100" align="center" formatter="proMonState">退款状态</th>
				<th field="createTime" width="150" align="center">创建时间</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/admin/order/productBack/backmoney">
		<a id="btn_backMoney" href="/admin/order/productBack/backmoney" class="easyui-linkbutton" iconCls="icon-edit" plain="true">退款到账户</a>
		</@shiro.hasPermission>
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
