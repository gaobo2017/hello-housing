<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/settlement"/>

<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script language="javascript">
var codeBox;
var paycodeBox;
var ivocodeBox;
var backCodeBox;
var monCodeBox;
$(function() {

	<#noescape>
		codeBox = eval('(${initJSCodeContainer("ORDERS_ORDER_STATE")})');
		paycodeBox = eval('(${initJSCodeContainer("ORDER_PAYMENT_STATUS")})');
		ivocodeBox = eval('(${initJSCodeContainer("ORDER_INVOICE_STATUS")})');
		backCodeBox = eval('(${initJSCodeContainer("MEM_PB_STATE_RETURN")})');
		monCodeBox = eval('(${initJSCodeContainer("MEM_PB_STATE_MONEY")})');
	</#noescape>

	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/settlement";
	});
	$("#settleStart").click(function(){

		var moneyOther = $("#moneyOther").val();
		if (isNaN(moneyOther)) {
			$.messager.alert('提示','请输入数字！');
			return;
		}
		moneyOther = parseFloat(moneyOther);
		if (moneyOther > 0 ) {
			var moneyOtherType = $("#moneyOtherType").val();
			if (moneyOtherType == null || moneyOtherType == "") {
				$.messager.alert('提示','请选择其他金额类型！');
				return;
			}
			var moneyOtherReason = $("#moneyOtherReason").val();
			if (moneyOtherReason == null || moneyOtherReason == "") {
				$.messager.alert('提示','请填写其他金额的产生理由！');
				return;
			}
		}

		$.messager.confirm('确认', '确定要发起结算吗？', function(r) {
			if (r) {
				$.messager.progress({
					text : "提交中..."
				});
				
				if($("#addForm").form('validate')){
			 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/settlement/settleStart")
		  				 .attr("method", "POST")
		  				 .submit();
		  		}
				$.messager.progress('close');
			}
		});
	});
	
	$("#btn-platformExplain").click(function(){
		var platformExplain = $("#platformExplain").val();
		if (platformExplain == null || platformExplain == "") {
			$.messager.alert('提示','请输入理由！');
			return;
		}
		
		var moneyOther = $("#moneyOther").val();
		if (isNaN(moneyOther)) {
			$.messager.alert('提示','请输入数字！');
			return;
		}
		moneyOther = parseFloat(moneyOther);
		if (moneyOther > 0 ) {
			var moneyOtherType = $("#moneyOtherType").val();
			if (moneyOtherType == null || moneyOtherType == "") {
				$.messager.alert('提示','请选择其他金额类型！');
				return;
			}
			var moneyOtherReason = $("#moneyOtherReason").val();
			if (moneyOtherReason == null || moneyOtherReason == "") {
				$.messager.alert('提示','请填写其他金额的产生理由！');
				return;
			}
		}

		if($("#addForm").form('validate')){
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/settlement/platformExplain")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});
	
	$("#checkOver").click(function(){
		$.messager.confirm('确认', '确定完成对该账单的核对吗？', function(r) {
			if (r) {
				$.messager.progress({
					text : "提交中..."
				});
				
				if($("#addForm").form('validate')){
			 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/settlement/checkOver")
		  				 .attr("method", "POST")
		  				 .submit();
		  		}
				$.messager.progress('close');
			}
		});
	});
	
	$("#payOver").click(function(){
		$.messager.confirm('确认', '确定已经向商家打款了吗？', function(r) {
			if (r) {
				$.messager.progress({
					text : "提交中..."
				});
				if($("#addForm").form('validate')){
			 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/settlement/payOver")
		  				 .attr("method", "POST")
		  				 .submit();
		  		}
				$.messager.progress('close');
			}
		});
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

	function getState(value, row, index) {
		var box = codeBox["ORDERS_ORDER_STATE"][value];
		return box;
	}
	
	function paymentStatus(value, row, index) {
		var box = paycodeBox["ORDER_PAYMENT_STATUS"][value];
		return box;
	}
	
	function invoiceStatus(value, row, index) {
		var box = ivocodeBox["ORDER_INVOICE_STATUS"][value];
		return box;
	}
	
	function detailFormatter(index,row){
        return '<div style="padding:2px"><table class="ddv"></table></div>';
    }
	
	function proBackState(value, row, index) {
		var box = backCodeBox["MEM_PB_STATE_RETURN"][value];
		return box;
	}
	
	function proMonState(value, row, index) {
		var box = monCodeBox["MEM_PB_STATE_MONEY"][value];
		return box;
	}
	
	function onExpandRow(index,row){
        var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
        ddv.datagrid({
           fitColumns:true,
           singleSelect:true,
           method:'get',
           url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/settlement/getSettlementOp?orderId='+row.id,
			loadMsg : '数据加载中...',
			height : 'auto',
			columns : [[{
				field : 'productName',
				title : '货品名称',
				width : 150,
				align : 'left',
				halign : 'center'
			}, {
				field : 'moneyPrice',
				title : '商品单价',
				width : 30,
				align : 'center'
			}, {
				field : 'number',
				title : '商品数量',
				width : 30,
				align : 'center'
			}, {
				field : 'moneyAmount',
				title : '网单金额',
				width : 30,
				align : 'center'
			}, {
				field : 'productCateName',
				title : '所属分类',
				width : 50,
				align : 'center'
			}, {
				field : 'scaling',
				title : '佣金比例',
				width : 30,
				align : 'center'
			}, {
				field : 'commision',
				title : '佣金',
				width : 30,
				align : 'center'
			}]],
			onResize : function() {
				$('#dataGrid').datagrid('fixDetailRowHeight',index);
			},
			onLoadSuccess : function() {
				setTimeout(function() {
					$('#dataGrid').datagrid('fixDetailRowHeight',index);
				}, 0);
			}
		});
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">账单详情<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/settlement">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<input type="hidden" id="id" name="id" value="${(settlement.id)!''}"/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">结算周期：</label>
							<label>${(settlement.settleCycle)!''}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">商家名称：</label>
							<label>${(settlement.sellerName)!''}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">订单总额：</label>
							<label>${(settlement.moneyOrder)!''}元</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">余额支付总额：</label>
							<label>${(settlement.moneyPaidBalance)!''}元</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">现金支付总额：</label>
							<label>${(settlement.moneyPaidReality)!''}元</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">积分转换总额：</label>
							<label>${(settlement.moneyIntegral)!''}元</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">积分总额：</label>
							<label>${(settlement.integral)!''}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">退货总额：</label>
							<label>${(settlement.moneyBack)!''}元</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								退货申请的退款时间在结算周期内的所有退货退款总额，退货申请表的退款金额和。
								</font>
							</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">退回积分总额：</label>
							<label>${(settlement.moneyIntegralBack)!''}元</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								退货申请的退款时间在结算周期内的所有退货退回积分金额总额，退货申请表的退回积分金额和。
								</font>
							</label>
						</p>
					</div>
					
					<!-- 结算状态：1、账单生成；2、平台审核通过；3、商家核对通过；4、商家核对质疑；5、对账完成；6、支付完成 -->
					<#if settlement.status == 1 || settlement.status == 4>
						<!-- 结算状态：1、账单生成；4、商家核对质疑，此时可以修改其他金额 -->
						<div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item">其他金额：</label>
								<!-- <label>${(settlement.moneyOther)!''}元</label> -->
								<input class="easyui-validatebox textbox w280" id="moneyOther" name="moneyOther" value="${(settlement.moneyOther)!''}" data-options="required:true,validType:'length[0,20]',novalidate:true" >
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label>
								<label>
									<font style="color: #808080">
									请填数字，可以带小数点；如果没有其他金额产生请填0。
									</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item">其他金额类型: </label>
								<@cont.select id="moneyOtherType" value="${(settlement.moneyOtherType)!''}" codeDiv="SETTLE_OTHER_TYPE" style="width:100px" mode="1"/>
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label>
								<label>
									<font style="color: #808080">
									与其他金额关联，支付增加表示应付金额会增加，支付扣减表示应付金额会扣除。
									</font>
								</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item">其他金额理由：</label>
								<!-- <label>${(settlement.moneyOtherReason)!''}元</label> -->
								<input class="easyui-validatebox textbox w280" id="moneyOtherReason" name="moneyOtherReason" value="${(settlement.moneyOtherReason)!''}" data-options="required:true,validType:'length[0,100]',novalidate:true" >
							</p>
							<p class="p12 p-item">
								<label class="lab-item">&nbsp;</label>
								<label>
									<font style="color: #808080">
									请简单描述其他金额的产生原因。
									</font>
								</label>
							</p>
						</div>
					</#if>
					<#if settlement.status == 2 || settlement.status == 3 || settlement.status == 5 || settlement.status == 6>
						<!-- 结算状态：2、平台审核通过；3、商家核对通过；5、对账完成；6、支付完成，此时不可以修改其他金额 -->
						<div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item">其他金额：</label>
								<label>${(settlement.moneyOther)!''}元</label>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item">其他金额类型: </label>
								<@cont.select id="moneyOtherType" value="${(settlement.moneyOtherType)!''}" codeDiv="SETTLE_OTHER_TYPE" disabled="disabled" style="width:100px" mode="1"/>
							</p>
						</div>
						<div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item">其他金额理由：</label>
								<label>${(settlement.moneyOtherReason)!''}</label>
							</p>
						</div>
					</#if>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">佣金总额：</label>
							<label>${(settlement.commision)!''}元</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">系统计算总额：</label>
							<label>${(settlement.payable)!''}元</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								系统计算总额=订单总额-退款总额-退回积分总额-佣金总额
								</font>
							</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">应支付总额：</label>
							<#if settlement.moneyOtherType?? && settlement.moneyOtherType == 1>
							<label>${(settlement.payable + settlement.moneyOther)!''}元</label>
							<#elseif settlement.moneyOtherType?? && settlement.moneyOtherType == 2>
							<label>${(settlement.payable - settlement.moneyOther)!''}元</label>
							<#else>
							<label>${(settlement.payable)!''}元</label>
							</#if>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								应付总额=系统计算总额+（或者-，由其他金额类型决定）其他金额
								</font>
							</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">状态: </label>
							<@cont.select id="status" value="${(settlement.status)!''}" codeDiv="SETTLEMENT_STATUS" disabled="disabled" style="width:100px" mode="1"/>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">商家质疑：</label>
							<label>${(settlement.sellerDoubt)!''}</label>
						</p>
					</div>
					
					<!-- 结算状态：1、账单生成；2、平台审核通过；3、商家核对通过；4、商家核对质疑；5、对账完成；6、支付完成 -->
					<#if settlement.status == 4>
						<!-- 结算状态：4、商家核对质疑，此时可以输入解释 -->
						<div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item">平台解释：</label>
								<textarea name="platformExplain" rows="4" cols="60" id="platformExplain" class="{maxlength:255}">${(settlement.platformExplain)!''}</textarea>
							</p>
						</div>
					</#if>
					<#if settlement.status == 1 || settlement.status == 2 || settlement.status == 3 || settlement.status == 5 || settlement.status == 6>
						<!-- 结算状态：1、账单生成；2、平台审核通过；3、商家核对通过；5、对账完成；6、支付完成，此时不可以输入解释 -->
						<div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item">平台解释：</label>
								<label>${(settlement.platformExplain)!''}</label>
							</p>
						</div>
					</#if>
					
				</dd>
			</dl>
			</@form.form>
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>订单列表</dt>
				<dd class="dd-group">
					<div data-options="region:'center'" border="false" style="width:100%;height:400px;">
						<table id="dataGrid" class="easyui-datagrid"
							data-options="rownumbers:true
											,idField :'id'
											,singleSelect:true
											,view: detailview
											,autoRowHeight:false
											,fitColumns:true
											,detailFormatter:detailFormatter
											,onExpandRow:onExpandRow
											,striped:true
											,pagination:true
											,pageSize:'20'
											,fit:true
					    					,url:'${currentBaseUrl}/orderlist?settlementId=${(settlement.id)!''}'
					    					,onLoadSuccess:dataGridLoadSuccess
					    					,method:'get'">
							<thead>
								<tr>
									<th field="id" hidden="hidden"></th>
									<th field="orderSn" width="120" align="left" halign="center">订单号</th>
									<th field="memberName" width="120" align="center">买家用户名</th>
									<th field="sellerName" width="120" align="center">店铺</th>
									<th field="moneyProduct" width="60" align="center">商品金额</th>
									<th field="moneyOrder" width="60" align="center">订单总金额</th>
									<th field="paymentStatus" width="70" align="center" formatter="paymentStatus">付款状态</th>
									<th field="orderState" width="70" align="center" formatter="getState">订单状态</th>
									<th field="invoiceStatus" width="70" align="center" formatter="invoiceStatus">发票状态</th>
									<th field="invoiceTitle" width="70" align="center">发票抬头</th>
									<th field="invoiceType" width="70" align="center">发票类型</th>
									<th field="paymentName" width="50" align="center">支付方式</th>
									<th field="createTime" width="110" align="center">创建时间</th>
									<th field="updateTime" width="110" align="center">修改时间</th>
								</tr>
							</thead>
						</table>
					</div>
				</dd>
			</dl>
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>退货列表</dt>
				<dd class="dd-group">
					<div data-options="region:'center'" border="false" style="width:100%;height:400px;">
						<table id="backDataGrid" class="easyui-datagrid"
							data-options="rownumbers:true
											,idField :'id'
											,singleSelect:true
											,autoRowHeight:false
											,fitColumns:true
											,striped:true
											,pagination:true
											,pageSize:'20'
											,fit:true
					    					,url:'${currentBaseUrl}/backlist?settlementId=${(settlement.id)!''}'
					    					,onLoadSuccess:dataGridLoadSuccess
					    					,method:'get'">
							<thead>
								<tr>
									<th field="id" hidden="hidden"></th>
									<th field="orderMoney" hidden="hidden"></th>
									<th field="orderSn" width="120" align="center">订单号</th>
									<th field="productName" width="120" align="center">商品名称</th>
									<th field="memberName" width="120" align="center">用户名</th>
									<th field="question" width="120" align="center">问题描述</th>
									<th field="backMoney" width="80" align="center">退款金额</th>
									<th field="backIntegral" width="80" align="center">退回积分</th>
									<th field="backIntegralMoney" width="80" align="center">退回积分金额</th>
									<th field="stateReturn" width="120" align="center" formatter="proBackState">退货状态</th>
									<th field="stateMoney" width="120" align="center" formatter="proMonState">退款状态</th>
									<th field="createTime" width="90" align="center">创建时间</th>
								</tr>
							</thead>
						</table>
					</div>
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<!-- 结算状态：1、账单生成；2、平台审核通过；3、商家核对通过；4、商家核对质疑；5、对账完成；6、支付完成 -->
				<#if settlement.status == 1>
					<input type="button" id="settleStart" class="btn" value="发起结算" />
				</#if>
				<#-- 
				<#if settlement.status == 2>
				</#if>
				-->
				<#if settlement.status == 3>
					<input type="button" id="checkOver" class="btn" value="对账完成" />
				</#if>
				<#if settlement.status == 4>
					<input type="button" id="btn-platformExplain" class="btn" value="回复商家质疑" />
				</#if>
				<#if settlement.status == 5>
					<input type="button" id="payOver" class="btn" value="支付完成" />
				</#if>
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			
		</div>
	</div>
</div>



<#include "/admin/commons/_detailfooter.ftl" />