<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/cost/manage"/>
<#include "housecss.ftl"/>

<script language="javascript">
	var codeBox;
	var code2Box;
	$(function() {
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("HOUSE_SOLD_STATE")})');
			code2Box = eval('(${initJSCodeContainer("HOUSE_USED_STATE")})');
		</#noescape>
		

		
		$('#btn_del').click(function(){
	 		var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		if (selected.isSold == 1) {
				$.messager.alert('提示','只能删除未出租的房源。');
				return;
			}
	 		$.messager.confirm('确认', '确定删除该房源吗?删除后,此操作不可撤销', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					
					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/delete?id=2",
						success:function(data, textStatus){
							if (data.success) {
								$.messager.alert('提示','删除成功。');
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
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		$("#btn-gridAdd").click(function(){
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/house/manage/add";
		});
		
		$("#btn-gridEdit").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			if (selected.isSold == 1) {
				$.messager.alert('提示','只能修改房 未出租的房源。');
				return;
			}
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/house/manage/edit?id="+selected.id;
		});
	});

	function getSoldState(value, row, index) {
		var box = codeBox["HOUSE_SOLD_STATE"][value];
 		return box;
	}
	
		function getUsedState(value, row, index) {
		var box2 = code2Box["HOUSE_USED_STATE"][value];
 		return box2;
	}
	
	function openwin(id){
  // window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/house/manage/edit?id="+selected.id;
            window.open("${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/house/manage/edit?id="+id);
    }

   
    function proTitle(value,row,index){
        return "<font style='color:blue;cursor:pointer' title='"+
                value+"' onclick='openwin("+row.id+")'>"+value+"</font>";
    }
    
    
    
    
</script>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
	border="false">
	<h2 class="h2-title">
		成本管理列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">房源编号 :</label> <input type="text"
							class="txt" id="q_roomCodeNo" name="q_roomCodeNo" value="${q_roomCodeNo!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">房源名称 :</label> <input type="text"
							class="txt" id="q_houseName" name="q_houseName" value="${q_houseName!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">出租状态 :</label> <@cont.select id="q_sold_state"
						codeDiv="HOUSE_SOLD_STATE" name="q_sold_state" style="width:100px"/>
					</p>
					<p class="p4 p-item">
						<label class="lab-item">房源状态 :</label> <@cont.select id="q_used_state"
						codeDiv="HOUSE_USED_STATE" name="q_used_state" style="width:100px"/>
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
    					,method:'post'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				
				<th field="houseId" hidden="hidden"></th>
				<th field="roomCodeNo" width="100" align="center" formatter="proTitle">房源编号</th>
				<th field="houseName" width="100" align="center">房源名称</th>
				<th field="isSold" width="90" align="center" formatter="getSoldState">出租状态</th>
				<th field="status" width="90" align="center" formatter="getUsedState">房子状态</th>
				<th field="renovationCostSum" width="100" align="center">装修成本</th>
				<th field="otherCostSum" width="100" align="center">其他成本</th>
				<th field="dayRentCost" width="100" align="center">每天房租成本</th>
				<th field="vacancyDays" width="120" align="center">空闲总天数</th>
				<th field="vacancyDay" width="120" align="center">最近一次空闲天数</th>
				<th field="vacancyFeeSumt" width="100" align="center">空闲费用总计</th>
				
				<th field="allCostSum" width="100" align="center">成本总计</th>
				<th field="monthlyRent" width="100" align="center">月租</th>
				
				
				<th field="contractStartTime" width="100" align="center">合同开始时间</th>
				<th field="contractEndTime" width="100" align="center">合同结束时间</th>	
				<th field="lastSoldTime" width="100" align="center">最近租出时间</th>
				<th field="pricesSum" width="100" align="center">房源总价</th>
				
				<th field="gainTime" width="100" align="center">收房时间</th>
				
				<th field="operationName" width="100" align="center">登记人名字</th>
								
			</tr>
		</thead>
	</table>

	<div id="gridTools">

		<@shiro.hasPermission name="/admin/cost/manage/add">
		<a id="btn-gridAdd" href="/admin/cost/manage/add" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/house/manage/edit">
		<a id="btn-gridEdit" href="/admin/house/manage/edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/house/manage/del">
		<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
		
		<a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<div></div>
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
