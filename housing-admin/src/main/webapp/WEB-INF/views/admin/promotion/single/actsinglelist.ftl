<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">

	var codeBox;
	$(function(){
	//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("ACT_STATUS","CHANNEL","ACT_SINGLE_TYPE")})');
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
            	$.messager.alert('提示','非提交审核的活动不能执行审核操作。');
                return;
            }
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/single/audit?actSingleId="+selected.id;
		});
		$("#a-gridDetail").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/single/detail?actSingleId="+selected.id;
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
		return codeBox["ACT_SINGLE_TYPE"][value];
	}

</script>



<div id="searchbar" data-options="region:'north'" style="margin: 0 auto;" border="false">
	<div id="searchbox" class="head-seachbox">
		<h2 class="h2-title">单品立减列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
		<div class="w-p99 marauto searchCont">
		<form class="form-search" onsubmit="return false;" action="" method="get" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p3 p-item"><label class="lab-item">活动名称：</label><input type="text" class="txt" id="q_actSingleName" name="q_actSingleName" value="${q_actSingleName!''}"/></p>
				<p class="p3 p-item"><label class="lab-item">状态：</label><@cont.select id="q_status" value="${(q_status)!''}" codeDiv="ACT_STATUS" style="width:80px" /></p>
				<p class="p3 p-item"><label class="lab-item">类型：</label><@cont.select id="q_type" value="${(q_type)!''}" codeDiv="ACT_SINGLE_TYPE" style="width:80px" /></p>
				<p class="p3 p-item"><label class="lab-item">渠道：</label><@cont.select id="q_channel" value="${(q_channel)!''}" codeDiv="CHANNEL" style="width:80px" /></p>
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
				<p class="p3 p-item">
					<label class="lab-item">日期：</label>
                    <input type="text" id="q_time" name="q_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:dd'})" class="txt w180"/>
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
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/single/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="sellerName" width="100" align="left" halign="center">店铺名称</th>
				<th field="actSingleName" width="100" align="left" halign="center">活动名称</th>
				<th field="type" width="100" align="center" halign="center" formatter="typeFormat">活动类型</th>
				<th field="discount" width="100" align="right" halign="center">优惠额/折扣</th>
				<th field="startTime" width="150" align="center" halign="center">开始时间</th>
				<th field="endTime" width="150" align="center" halign="center">结束时间</th>
				<th field="channel" width="100" align="center" halign="center" formatter="channelFormat">应用渠道</th>
				<th field="status" width="100" align="center" halign="center" formatter="statusFormat">状态</th>
				<th field="remark" width="100" align="left" halign="center">活动描述</th>
				<th field="auditOpinion" width="100" align="left" halign="center">审核意见</th>
				<th field="updateUserName" width="100" align="center" halign="center">最后修改人</th>
				<th field="updateTime" width="150" align="center" halign="center">最后修改时间</th>
			</tr>
		</thead>
	</table>
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<a id="a-gridDetail" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-car_enter" plain="true">详情</a>
		
		<@shiro.hasPermission name="/admin/promotion/single/audit">
		<a id="a-gridAudit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">审核</a>
		</@shiro.hasPermission>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />