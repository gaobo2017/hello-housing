<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">
	$(function(){
		$('#a-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());  
		});
	})
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">搜索词历史记录列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
		<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p12 p-item">
					<label class="lab-item">搜索词 :</label>
					<input type="text" class="txt" id="q_keyword" name="q_keyword" value="${q_keyword!''}"/>
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
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/searchlogs/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="keyword" width="150" align="center">搜索词</th>  
				<th field="ip" width="150" align="center">IP地址</th>  
				<th field="siteCookie" width="200" align="center">cookie埋点</th>  
				<th field="memberId" width="100" align="center">用户ID</th>  
	            <th field="createTime" width="100" align="center">添加时间</th>
			</tr>
		</thead>
	</table>
	
	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />