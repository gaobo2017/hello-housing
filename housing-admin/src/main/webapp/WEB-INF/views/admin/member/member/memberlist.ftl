<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">
	var yesNoBox;
	var gradeBox;
	var sourceBox;
	var genderBox;
	var statusBox;
	var optTypeBox;
	var balanceStateBox;
	var addressStateBox;
	$(function(){
		//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			yesNoBox = eval('(${initJSCodeContainer("YES_NO")})');
			gradeBox = eval('(${initJSCodeContainer("MEMBER_GRADE")})');
			sourceBox = eval('(${initJSCodeContainer("MEMBER_SOURCE")})');
			genderBox = eval('(${initJSCodeContainer("GENDER")})');
			statusBox = eval('(${initJSCodeContainer("MEMBER_STATUS")})');
			optTypeBox = eval('(${initJSCodeContainer("MEMBER_VAL_OPT_TYPE")})');
			balanceStateBox = eval('(${initJSCodeContainer("MEMBER_BALANCE_STATE")})');
			addressStateBox = eval('(${initJSCodeContainer("MEMBER_ADDRESS_STATE")})');
		</#noescape>
		// 查询按钮
		$('#a-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});

		// 积分经验值操作
		$("#a-gridOptIntegral").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			$("#valuereset").click();
			$("#lbl_value_memberId").html(selected.id);
			$("#lbl_value_memberName").html(selected.name);
			$("#value_memberId").val(selected.id);
			$("#value_memberName").val(selected.name);
			$('#_membervalueopt').dialog('open');
	 	});

		// 经验值积分值操作界面确定按钮
		$("#valueOptOk").click(function(){
			var type = $("#type").val();
			if (type == null || type == "") {
				$.messager.alert('提示','操作类型不能为空。');
				return;
			}
			var optType = $("#optType").val();
			if (optType == null || optType == "") {
				$.messager.alert('提示','具体操作不能为空。');
				return;
			}
			if($("#valueOptForm").form('validate')){
				$.ajax({
					type:"POST",
					url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/member/valueopt",
					dataType: "json",
					data: $('#valueOptForm').serialize(),// + "&" + getCSRFTokenParam(),
					cache:false,
					success:function(data, textStatus){
						if (data.success) {
							$.messager.alert('提示','修改成功。');
							$('#_membervalueopt').dialog('close');
							$('#dataGrid').datagrid('reload',queryParamsHandler());
							return;
						} else {
							$.messager.alert("提示",data.message);
							//refrushCSRFToken(data.csrfToken);
						}
					}
				});
	  		}
		});

		// 经验值积分值操作界面取消按钮
		$("#valueOptCancel").click(function(){
			$('#_membervalueopt').dialog('close');
		});

		// 升级日志按钮
		$("#a-gridUpLog").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			$("#memberuplogdataGrid").datagrid({url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/member/uploglist?memberId='+selected.id});
			$('#_memberuplog').dialog('open');
	 	});

	 	// 经验值变更日志按钮
		$("#a-gridGradeLog").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			$("#membergradelogdataGrid").datagrid({url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/member/grdIntloglist?memberId='+selected.id+'&type=1'});
			$('#_membergradelog').dialog('open');
	 	});

	 	// 积分值变更日志按钮
		$("#a-gridIntegralLog").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			$("#memberintegrallogdataGrid").datagrid({url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/member/grdIntloglist?memberId='+selected.id+'&type=2'});
			$('#_memberintegrallog').dialog('open');
	 	});

	 	// 余额变更日志按钮
		$("#a-gridBalanceLog").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			$("#memberbalancelogdataGrid").datagrid({url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/member/balanceloglist?memberId='+selected.id});
			$('#_memberbalancelog').dialog('open');
	 	});

	 	// 收货地址按钮
		$("#a-gridAddress").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			$("#memberaddressdataGrid").datagrid({url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/member/addresslist?memberId='+selected.id});
			$('#_memberaddress').dialog('open');
	 	});

	 	// 余额操作
		$("#a-gridOptBalance").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			$("#balancereset").click();
			$("#lbl_balance_memberId").html(selected.id);
			$("#lbl_balance_memberName").html(selected.name);
			$("#lbl_balance_balance").html(selected.balance);
			$("#balance_memberId").val(selected.id);
			$("#balance_memberName").val(selected.name);
			$('#_memberbalanceopt').dialog('open');
	 	});

		// 余额操作界面确定按钮
		$("#balanceOptOk").click(function(){
			var state = $("#state").val();
			if (state == null || state == "") {
				$.messager.alert('提示','具体操作不能为空。');
				return;
			}
			if($("#balanceOptForm").form('validate')){
				$.ajax({
					type:"POST",
					url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/member/balanceopt",
					dataType: "json",
					data: $('#balanceOptForm').serialize(),// + "&" + getCSRFTokenParam(),
					cache:false,
					success:function(data, textStatus){
						if (data.success) {
							$.messager.alert('提示','修改成功。');
							$('#_memberbalanceopt').dialog('close');
							$('#dataGrid').datagrid('reload',queryParamsHandler());
							return;
						} else {
							$.messager.alert("提示",data.message);
							//refrushCSRFToken(data.csrfToken);
						}
					}
				});
	  		}
		});

		// 余额操作界面取消按钮
		$("#balanceOptCancel").click(function(){
			$('#_memberbalanceopt').dialog('close');
		});
	})

	function yesNoFormat(value,row,index){
		return yesNoBox["YES_NO"][value];
	}
	function gradeFormat(value,row,index){
		return gradeBox["MEMBER_GRADE"][value];
	}
	function sourceFormat(value,row,index){
		return sourceBox["MEMBER_SOURCE"][value];
	}
	function genderFormat(value,row,index){
		return genderBox["GENDER"][value];
	}
	function statusFormat(value,row,index){
		return statusBox["MEMBER_STATUS"][value];
	}
	function optTypeFormat(value,row,index){
		return optTypeBox["MEMBER_VAL_OPT_TYPE"][value];
	}
	function balanceStateFormat(value,row,index){
		return balanceStateBox["MEMBER_BALANCE_STATE"][value];
	}
	function addressStateFormat(value,row,index){
		return addressStateBox["MEMBER_ADDRESS_STATE"][value];
	}

</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">字典列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
		<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p4 p-item">
					<label class="lab-item">用户名 :</label>
					<input type="text" class="txt" id="q_name" name="q_name" value="${q_name!''}"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">会员等级 :</label>
					<@cont.select id="q_grade" codeDiv="MEMBER_GRADE" style="width:80px"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">邮箱 :</label>
					<input type="text" class="txt" id="q_email" name="q_email" value="${q_email!''}"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">手机号 :</label>
					<input type="text" class="txt" id="q_mobile" name="q_mobile" value="${q_mobile!''}"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">会员来源 :</label>
					<@cont.select id="q_source" codeDiv="MEMBER_SOURCE" style="width:80px"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">使用状态 :</label>
					<@cont.select id="q_status" codeDiv="MEMBER_STATUS" style="width:80px"/>
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
						,collapsible:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/member/member/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="name" width="150" align="left" halign="center">用户名</th>  
	            <th field="grade" width="80" align="left" halign="center" formatter="gradeFormat">等级</th>  
	            <th field="gradeValue" width="50" align="left" halign="center">经验值</th>  
	            <th field="integral" width="50" align="left" halign="center">积分</th>  
	            <th field="registerTime" width="150" align="left" halign="center">注册时间</th>  
	            <th field="lastLoginTime" width="150" align="left" halign="center">最后登录时间</th>  
	            <!-- <th field="lastAddressId" width="60" align="left" halign="center">上次使用的地址</th>   -->
	            <th field="gender" width="50" align="left" halign="center" formatter="genderFormat">性别</th>  
	            <th field="birthday" width="100" align="left" halign="center">生日</th>  
	            <th field="qq" width="100" align="left" halign="center">QQ</th>  
	            <th field="email" width="110" align="left" halign="center">邮箱</th>  
	            <th field="mobile" width="110" align="left" halign="center">手机号</th>  
	            <th field="phone" width="100" align="left" halign="center">电话</th>  
	            <th field="source" width="50" align="left" halign="center" formatter="sourceFormat">来源</th>  
	            <th field="balance" width="100" align="left" halign="center">账户余额</th>  
	            <th field="isEmailVerify" width="50" align="left" halign="center" formatter="yesNoFormat">邮箱验证</th>  
	            <th field="isSmsVerify" width="50" align="left" halign="center" formatter="yesNoFormat">手机验证</th>  
	            <th field="canReceiveSms" width="50" align="left" halign="center" formatter="yesNoFormat">接受短信</th>  
	            <th field="canReceiveEmail" width="50" align="left" halign="center" formatter="yesNoFormat">接收邮件</th>  
	            <th field="status" width="70" align="left" halign="center" formatter="statusFormat">使用状态</th>  
			</tr>
		</thead>
	</table>

	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/member/member/valueopt">
		<a id="a-gridOptIntegral" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">经验积分管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/member/balanceopt">
		<a id="a-gridOptBalance" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">余额管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/member/uploglist">
		<a id="a-gridUpLog" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">升级日志</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/member/grdIntloglist">
		<a id="a-gridGradeLog" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">经验值日志</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/member/grdIntloglist">
		<a id="a-gridIntegralLog" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">积分值日志</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/member/balanceloglist">
		<a id="a-gridBalanceLog" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">余额日志</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/member/addresslist">
		<a id="a-gridAddress" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">收货地址</a>
		</@shiro.hasPermission>
	</div>
</div>
<#----会员经验值积分变更-------------->
<#include "/admin/member/member/membervalueopt.ftl" />
<#----会员余额变更-------------->
<#include "/admin/member/member/memberbalanceopt.ftl" />
<#----会员等级升级日志-------------->
<#include "/admin/member/member/memberuplog.ftl" />
<#----会员经验值变更日志-------------->
<#include "/admin/member/member/membergradelog.ftl" />
<#----会员积分值变更日志-------------->
<#include "/admin/member/member/memberintegrallog.ftl" />
<#----会员余额变更日志-------------->
<#include "/admin/member/member/memberbalancelog.ftl" />
<#----会员收货地址-------------->
<#include "/admin/member/member/memberaddress.ftl" />

<#include "/admin/commons/_detailfooter.ftl" />