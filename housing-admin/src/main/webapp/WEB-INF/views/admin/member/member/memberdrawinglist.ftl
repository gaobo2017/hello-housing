<#include "/admin/commons/_detailheader.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
	var stateBox;
	$(function(){
		//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			stateBox = eval('(${initJSCodeContainer("MEMBER_DRAWING_STATE")})');
		</#noescape>
		
		$('#a-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());  
		});
		$('#a-gridAuditing').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			var rows1 = $('#dataGrid').datagrid('getSelections');
			var array1 = new Array();
			var error = false;
			$.each(rows1, function(index, item){
				if (item.state != 1) {
					error = true;
					return
				}
				array1.push(item.id);
			});
			if (error) {
				$.messager.alert('提示','请选择未审核的申请。');
				return;
			}
			var ids = array1.join(",");
			$.messager.confirm('Confirm', '确定审核通过吗？', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/drawmoney/auditing",
						dataType: "json",
					    data: "ids=" + ids,
					    cache:false,
						success:function(data, textStatus){
							if (data.success) {
								$('#dataGrid').datagrid('reload');
						    } else {
						    	$.messager.alert('提示',data.message);
						    	$('#dataGrid').datagrid('reload');
						    }
							$.messager.progress('close');
						}
					});
			    }
			});
		});
		
		// 拒绝
		$('#a-gridReject').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			if (selected.state != 1) {
				$.messager.alert('提示','请选择待审核的申请。');
				return;
			}
			$.messager.confirm('Confirm', '确定拒绝该申请吗？', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/drawmoney/reject",
						dataType: "json",
					    data: "id=" + selected.id,
					    cache:false,
						success:function(data, textStatus){
							if (data.success) {
								$('#dataGrid').datagrid('reload');
						    } else {
						    	$.messager.alert('提示',data.message);
						    	$('#dataGrid').datagrid('reload');
						    }
							$.messager.progress('close');
						}
					});
			    }
			});
		});
		
		// 已打款
		$('#a-gridPaid').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			if (selected.state != 2) {
				$.messager.alert('提示','请选择通过审核的申请。');
				return;
			}
			$.messager.confirm('Confirm', '确定已经向用户支付了提现金额了吗？', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/drawmoney/paid",
						dataType: "json",
					    data: "id=" + selected.id,
					    cache:false,
						success:function(data, textStatus){
							if (data.success) {
								$('#dataGrid').datagrid('reload');
						    } else {
						    	$.messager.alert('提示',data.message);
						    	$('#dataGrid').datagrid('reload');
						    }
							$.messager.progress('close');
						}
					});
			    }
			});
		});
	})
	
	function stateFormat(value,row,index){
		return stateBox["MEMBER_DRAWING_STATE"][value];
	}
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">提现申请列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
		<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p3 p-item">
					<label class="lab-item">用户名 :</label>
					<input type="text" class="txt" id="q_memberName" name="q_memberName" value="${q_memberName!''}"/>
				</p>
				<p class="p6 p-item">
					<label class="lab-item">申请时间 :</label>
					<input id="q_startTime" name="q_startTime" style="width:160px;" value="${q_startTime!''}" type="text" class="Wdate {required:true}" onFocus="WdatePicker({readOnly:true,startDate:'%y-%M-{%d+1} 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'q_endTime\')}'})" data-options="required:true"/>
					~
					<input id="q_endTime" name="q_endTime" style="width:160px;" value="${q_endTime!''}" type="text" class="Wdate {required:true}" onFocus="WdatePicker({readOnly:true,startDate:'%y-%M-{%d+1} 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'q_startTime\') || \'%y-%M-{%d+1} 00:00:00\'}'})" data-options="required:true"/>
				</p>
				<p class="p3 p-item">
					<label class="lab-item">银行名称 :</label>
					<input type="text" class="txt" id="q_bank" name="q_bank" value="${q_bank!''}"/>
				</p>
				<p class="p3 p-item">
					<label class="lab-item">申请状态 :</label>
					<@cont.select id="q_state" codeDiv="MEMBER_DRAWING_STATE" style="width:80px"/>
				</p>
				<p class="p3 p-item">
					<label class="lab-item">失败原因 :</label>
					<input type="text" class="txt" id="q_failReason" name="q_failReason" value="${q_failReason!''}"/>
				</p>
			</div>
		</form>
		</div>
	</div>
</div>

<#--2.datagrid----------------->
<div data-options="region:'center'" border="false">
	<table id="dataGrid" class="easyui-datagrid" 
			data-options="rownumbers:true
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:false
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/member/drawmoney/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<!-- <th field="ck" checkbox="true"></th> -->
				<th field="memberName" width="150" align="left" halign="center">用户名</th>  
	            <th field="code" width="100" align="left" halign="center">提现编号</th>  
	            <th field="money" width="100" align="left" halign="center">提现金额</th>  
	            <th field="createTime" width="150" align="left" halign="center">申请时间</th>  
	            <th field="auditingTime" width="150" align="left" halign="center">审核时间</th>  
	            <th field="handleTime" width="150" align="left" halign="center">处理时间</th>  
	            <th field="bank" width="150" align="left" halign="center">收款银行</th>  
	            <th field="bankCode" width="200" align="left" halign="center">收款账号</th>  
	            <th field="state" width="60" align="left" halign="center" formatter="stateFormat">状态</th>  
	            <th field="failReason" width="200" align="left" halign="center">失败原因</th>  
	            <th field="optName" width="60" align="left" halign="center">处理人</th>  
			</tr>
		</thead>
	</table>
	
	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/member/drawmoney/auditing">
		<a id="a-gridAuditing" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">通过</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/drawmoney/reject">
		<a id="a-gridReject" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">拒绝</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/drawmoney/paid">
		<a id="a-gridPaid" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">已打款</a>
		</@shiro.hasPermission>
	<div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />