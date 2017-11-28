<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">
	var codeBox;
	$(function(){
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("USE_YN")})');
		</#noescape>
		
		$('#a-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());  
		});
		$("#a-gridAdd").click(function(){
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/grouptype/add";
		});
		$("#a-gridEdit").click(function(){
			var selectedCode = $('#dataGrid').datagrid('getSelected');
			if(!selectedCode){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			if(selectedCode.state == 1) {
				$.messager.alert('提示','已经使用中的团购分类的不能修改。');
				return;
			}
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/grouptype/edit?id="+selectedCode.id;
		});	
		
		$("#a-gridDel").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
			if(selectedCode.state == 1) {
				$.messager.alert('提示','已经使用中的团购分类的不能删除。');
				return;
			}
            var selectedIndex = $('#dataGrid').datagrid('getRowIndex', selectedCode);
            $.messager.confirm('提示', '确定删除吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/grouptype/del",
                        dataType: "json",
                        data: "id="+selectedCode.id,
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('deleteRow',selectedIndex);
                            }else{
                                $.messager.alert('提示', data.message);
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
        
        $("#a-gridAuditYes").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selectedCode.state == 1) {
				$.messager.alert('提示','团购分类已经启用，请勿重复操作。');
				return;
			}
			
            var selectedIndex = $('#dataGrid').datagrid('getRowIndex', selectedCode);
            $.messager.confirm('提示', '确定要启用吗，启用之后商家可以在此分类下申请商品，C端客户可以查看到此分类？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/grouptype/auditYes",
                        dataType: "json",
                        data: "id="+selectedCode.id,
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('reload');
                            }else{
                                $.messager.alert('提示', data.message);
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
        
         $("#a-gridAuditNo").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selectedCode.state == 0) {
				$.messager.alert('提示','团购分类已经停用，请勿重复操作。');
				return;
			}
			
            var selectedIndex = $('#dataGrid').datagrid('getRowIndex', selectedCode);
            $.messager.confirm('提示', '确定要停用吗，停用之后商家不可以在此分类下申请商品，C端客户不可以查看到此分类和此分类下的商品？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/grouptype/auditNo",
                        dataType: "json",
                        data: "id="+selectedCode.id,
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('reload');
                            }else{
                                $.messager.alert('提示', data.message);
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
        
	})
	
	function useYnFormat(value,row,index){
		return codeBox["USE_YN"][value];
	}
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">团购分类列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
		<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p12 p-item">
					<label class="lab-item">团购分类名称 :</label>
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
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/grouptype/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="name" width="150" align="center">团购分类名称</th>  
				<th field="sort" width="150" align="center">排序</th>  
				<th field="state" width="150" align="center" formatter="useYnFormat">状态</th>  
	            <th field="createName" width="150" align="center">添加人</th>  
	            <th field="createTime" width="150" align="center">添加时间</th>
	            <th field="updateName" width="150" align="center">最后修改人</th>  
	            <th field="updateTime" width="150" align="center">修改更新时间</th>  
			</tr>
		</thead>
	</table>
	
	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/promotion/grouptype/add">
		<a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/grouptype/edit">
		<a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/grouptype/auditYes">
		<a id="a-gridAuditYes" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">启动</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/grouptype/auditNo">
		<a id="a-gridAuditNo" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">停用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/grouptype/del">
		<a id="a-gridDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />