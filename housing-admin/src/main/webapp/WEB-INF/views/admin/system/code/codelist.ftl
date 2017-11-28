<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">
	var codeBox;
	$(function(){
		//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("USE_YN")})');
		</#noescape>
		
		$('#a-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());  
		});
		$("#a-gridAdd").click(function(){
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/system/code/add";
		});
		$("#a-gridEdit").click(function(){
			var selectedCode = $('#dataGrid').datagrid('getSelected');
			if(!selectedCode){
				$.messager.alert('提示','请选择操作行。');
				return;
			}	
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/system/code/edit?codeDiv="+selectedCode.codeDiv+"&codeCd="+selectedCode.codeCd;
		});	
	})
	
	function useYnFormat(value,row,index){
		return codeBox["USE_YN"][value];
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
					<label class="lab-item">Code_Div :</label>
					<input type="text" class="txt" id="q_codeDiv" name="q_codeDiv" value="${q_codeDiv!''}"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">Code_Text :</label>
					<input type="text" class="txt" id="q_codeCd" name="q_codeCd" value="${q_codeCd!''}"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">是否使用 :</label>
					<@cont.select id="q_useYn" codeDiv="USE_YN" style="width:80px"/>
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
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/system/code/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="codeDiv" width="150" align="center">CODE DIV</th>  
	            <th field="codeCd" width="100" align="center">CODE CD</th>  
	            <th field="codeText" width="150" align="center">CODE TEXT</th>  
	            <th field="sortOrder" width="60" align="right">顺序</th>  
	            <th field="useYn" width="100" align="center" formatter="useYnFormat">是否使用</th>  
	            <th field="createUser" width="110" align="center">创建人</th>  
	            <th field="createTime" width="170" align="center">创建时间</th>  
	            <th field="updateUser" width="110" align="center">修改人</th>  
	            <th field="updateTime" width="170" align="center">修改时间</th>  
			</tr>
		</thead>
	</table>
	
	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/system/code/add">
		<a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/code/edit">
		<a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
	</div>

<#include "/admin/commons/_detailfooter.ftl" />