<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">

	$(function(){

		$("#a-gridSearch").click(function(){
	 		$('#dataGrid').datagrid('load',queryParamsHandler());
		});
		
		$("#a-gridBack").click(function(){
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/coupon";
		});

		$("#a-gridDoExport").click(function(){
			$.messager.confirm('提示', '确定导出该优惠券的发放详情吗？', function(r){
	            if (r){
	            	$.fileDownload('${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/couponuser/doexport',{data:queryParamsHandler()});
	            }
	        });
		});
        
		<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	})


</script>



<div id="searchbar" data-options="region:'north'" style="margin: 0 auto;" border="false">
	<div id="searchbox" class="head-seachbox">
		<h2 class="h2-title">【${(coupon.couponName)!''}】发放详情列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
		<div class="w-p99 marauto searchCont">
		<form class="form-search" onsubmit="return false;" action="" method="get" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<input type="hidden" id="q_couponId" name="q_couponId" value="${(coupon.id)!''}"/>
				<p class="p6 p-item">
					<label class="lab-item">领取日期：</label>
                    <input type="text" id="q_receiveTimeStart" name="q_receiveTimeStart"
						class="txt w200" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'q_receiveTimeEnd\')}'});"
						value="" readonly="readonly">
					~
					<input type="text" id="q_receiveTimeEnd" name="q_receiveTimeEnd"
							class="txt w200"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'q_receiveTimeStart\')}'});"
							value="" readonly="readonly">
                </p>
                <p class="p6 p-item">
					<label class="lab-item">使用日期：</label>
                    <input type="text" id="q_useTimeStart" name="q_useTimeStart"
						class="txt w200" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'q_useTimeEnd\')}'});"
						value="" readonly="readonly">
					~
					<input type="text" id="q_useTimeEnd" name="q_useTimeEnd"
							class="txt w200"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'q_useTimeStart\')}'});"
							value="" readonly="readonly">
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
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/couponuser/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="sellerName" width="100" align="left" halign="center">店铺名称</th>
				<th field="memberName" width="100" align="left" halign="center">会员名称</th>
				<th field="couponSn" width="150" align="left" halign="center">序列号</th>
				<th field="canUse" width="100" align="center" halign="center">可使用次数</th>
				<th field="receiveTime" width="100" align="center" halign="center">领取时间</th>
				<th field="orderSn" width="100" align="left" halign="center">订单Sn</th>
				<th field="useTime" width="100" align="center" halign="center">使用时间</th>
			</tr>
		</thead>
	</table>
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<a id="a-gridBack" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-back" plain="true">返回优惠券列表</a>
		<a id="a-gridDoExport" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" plain="true">导出</a>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />