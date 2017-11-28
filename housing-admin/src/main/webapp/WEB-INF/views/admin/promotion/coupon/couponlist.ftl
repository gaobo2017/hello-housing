<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">

	var codeBox;
	$(function(){
	//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("ACT_STATUS","CHANNEL","COUPON_TYPE")})');
		</#noescape>

		$("#a-gridSearch").click(function(){
	 		$('#dataGrid').datagrid('load',queryParamsHandler());
		});
		$("#a-gridAudit").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
            if(selected.status != 2) {
            	$.messager.alert('提示','非提交审核的优惠券不能执行审核操作。');
                return;
            }
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/coupon/audit?couponId="+selected.id;
		});

		$("#a-gridUserDetail").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
            if(selected.status != 5 && selected.status != 6) {
            	$.messager.alert('提示','没有上架过的优惠券没有发放记录。');
                return;
            }
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/couponuser?couponId="+selected.id;
		});
        
		<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	})

	function statusFormat(value,row,index){
		return codeBox["ACT_STATUS"][value];
	}

	function channelFormat(value,row,index){
		return codeBox["CHANNEL"][value];
	}

	function typeFormat(value,row,index){
		return codeBox["COUPON_TYPE"][value];
	}


</script>



<div id="searchbar" data-options="region:'north'" style="margin: 0 auto;" border="false">
	<div id="searchbox" class="head-seachbox">
		<h2 class="h2-title">优惠券列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
		<div class="w-p99 marauto searchCont">
		<form class="form-search" onsubmit="return false;" action="" method="get" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p3 p-item"><label class="lab-item">优惠券名称：</label><input type="text" class="txt" id="q_couponName" name="q_couponName" value="${q_couponName!''}"/></p>
				<p class="p3 p-item"><label class="lab-item">状态：</label><@cont.select id="q_status" value="${(q_status)!''}" codeDiv="ACT_STATUS" style="width:80px" /></p>
				<p class="p3 p-item"><label class="lab-item">渠道：</label><@cont.select id="q_channel" value="${(q_channel)!''}" codeDiv="CHANNEL" style="width:80px" /></p>
				<p class="p3 p-item">
					<label class="lab-item">发放日期：</label>
                    <input type="text" id="q_sendTime" name="q_sendTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:dd'})" class="txt w180"/>
                </p>
                <p class="p3 p-item">
					<label class="lab-item">使用日期：</label>
                    <input type="text" id="q_useTime" name="q_useTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:dd'})" class="txt w180"/>
                </p>
                <p class="p3 p-item">
                	<label class="lab-item">店铺 :</label>
                    <select name="q_sellerId" id="q_sellerId">
	                 	<option value="">请选择</option>
						<#if sellers??>
	                     	<#list sellers as seller>
							<option value="${(seller.id)!}">${(seller.sellerName)!}</option>
							</#list>
						</#if>
	    			</select>
                </p>
			</div>
		</form>
		</div>
	</div>
</div>


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
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/coupon/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="sellerName" width="100" align="left" halign="center">店铺名称</th>
				<th field="couponName" width="150" align="left" halign="center">优惠券名称</th>
				<th field="prefix" width="80" align="center" halign="center">优惠券前缀</th>
				<th field="couponValue" width="70" align="right" halign="center">优惠券面值</th>
				<th field="minAmount" width="100" align="right" halign="center">适用最低订单金额</th>
				<th field="sendStartTime" width="150" align="center" halign="center">发放开始时间</th>
				<th field="sendEndTime" width="150" align="center" halign="center">发放结束时间</th>
				<th field="useStartTime" width="150" align="center" halign="center">使用起始时间</th>
				<th field="useEndTime" width="150" align="center" halign="center">使用截止时间</th>
				<th field="personLimitNum" width="100" align="right" halign="center">会员限制数量</th>
				<th field="totalLimitNum" width="80" align="right" halign="center">总数量</th>
				<th field="receivedNum" width="100" align="right" halign="center">已发放数量</th>
				<th field="type" width="80" align="center" halign="center" formatter="typeFormat">优惠券类型</th>
				<th field="channel" width="80" align="center" halign="center" formatter="channelFormat">应用渠道</th>
				<th field="status" width="60" align="center" halign="center" formatter="statusFormat">状态</th>
				<th field="remark" width="150" align="left" halign="center">优惠券描述</th>
				<th field="auditOpinion" width="100" align="left" halign="center">审核意见</th>
				<th field="updateUserName" width="100" align="center" halign="center">最后修改人</th>
				<th field="updateTime" width="150" align="center" halign="center">最后修改时间</th>
			</tr>
		</thead>
	</table>
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		
		<@shiro.hasPermission name="/admin/promotion/coupon/audit">
		<a id="a-gridAudit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">审核</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/couponuser">
		<a id="a-gridUserDetail" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" plain="true">发放详情</a>
		</@shiro.hasPermission>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />