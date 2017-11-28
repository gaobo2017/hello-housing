<#include "/admin/commons/_detailheader.ftl" />

<style>
#newstypeTree img {
	max-width: 390px;
	max-height: 290px;
}
</style>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script language="javascript">

	var statusBox;
	var dataTypeBox;
	$(function(){
	//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			statusBox = eval('(${initJSCodeContainer("USE_PRE")})');
			dataTypeBox = eval('(${initJSCodeContainer("DATA_TYPE")})');
		</#noescape>

		$("#a-gridAdd").click(function(){
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/pcindex/floorclass/add";
		});
		$("#a-gridSearch").click(function(){
	 		$('#dataGrid').datagrid('load',queryParamsHandler());
		});
		$("#a-gridEdit").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/pcindex/floorclass/edit?pcIndexFloorClassId="+selected.id;
		});
		
		$("#a-gridDel").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selectedCode.status == 1) {
            	$.messager.alert('提示','使用中的楼层分类不能删除');
                return;
            }

            $.messager.confirm('提示', '删除楼层分类将会同时删除分类下的数据，确定删除吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorclass/delete",
                        dataType: "json",
                        data: "id="+selectedCode.id,
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('reload',queryParamsHandler());
                            }else{
                                $.messager.alert('提示', data.message);
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
		
		$("#a-gridUp").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择启用楼层分类。');
	            return;
	        }
	        if(selected.status == 1) {
	        	$.messager.alert('提示','已经是启用状态，请勿重复操作');
	            return;
	        }
	        if(selected.status == 3) {
	        	$.messager.alert('提示','不能从停用状态直接修改成使用状态');
	            return;
	        }

	        $.messager.confirm('提示', '确定启用吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorclass/up",
	                    dataType: "json",
	                    data: "id="+selected.id,
	                    cache:false,
	                    success:function(data, textStatus){
	                        if (data.success) {
	                            $('#dataGrid').datagrid('reload',queryParamsHandler());
	                        }else{
	                            $.messager.alert('提示', data.message);
	                        }
	                        $.messager.progress('close');
	                    }
	                });
	            }
	        });
	    });
	    
		$("#a-gridPre").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择预使用楼层分类。');
	            return;
	        }
	        if(selected.status == 2) {
	        	$.messager.alert('提示','已经是预使用状态，请勿重复操作');
	            return;
	        }

	        $.messager.confirm('提示', '确定预使用吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorclass/pre",
	                    dataType: "json",
	                    data: "id="+selected.id,
	                    cache:false,
	                    success:function(data, textStatus){
	                        if (data.success) {
	                            $('#dataGrid').datagrid('reload',queryParamsHandler());
	                        }else{
	                            $.messager.alert('提示', data.message);
	                        }
	                        $.messager.progress('close');
	                    }
	                });
	            }
	        });
	    });
	    
	    $("#a-gridDown").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择停用楼层分类。');
	            return;
	        }
	        if(selected.status == 3) {
	        	$.messager.alert('提示','已经是停用状态，请勿重复操作');
	            return;
	        }

	        $.messager.confirm('提示', '确定停用吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorclass/down",
	                    dataType: "json",
	                    data: "id="+selected.id,
	                    cache:false,
	                    success:function(data, textStatus){
	                        if (data.success) {
	                            $('#dataGrid').datagrid('reload',queryParamsHandler());
	                        }else{
	                            $.messager.alert('提示', data.message);
	                        }
	                        $.messager.progress('close');
	                    }
	                });
	            }
	        });
	    });

	    // 增加楼层数据
		$("#a-gridAddData").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/pcindex/floordata/add?floorClassId="+selected.id;
		});
	    
	    $("#a-gridView").click(function(){
	 		window.open("${(domainUrlUtil.EJS_FRONT_URL)!}/previewindex.html");
		});
        
		<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	})

	function statusFormat(value,row,index){
		return statusBox["USE_PRE"][value];
	}

	function detailFormatter(index,row){
        return '<div style="padding:2px"><table class="ddv"></table></div>';
    }
	
    function onExpandRow(index,row){
        var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
        ddv.datagrid({
           fitColumns:true,
           singleSelect:true,
           method:'get',
           url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floordata/listbyfloorclass?floorClassId='+row.id,
			loadMsg : '数据加载中...',
			height : 'auto',
			columns : [[{
				field : 'dataType',
				title : '数据类型',
				width : 70,
				align : 'center',
				halign : 'center',
				formatter : function(value, row, index) {
					return dataTypeBox["DATA_TYPE"][value];
				}
			}, {
				field : 'productName',
				title : '商品名称',
				width : 150,
				align : 'left',
				halign : 'center'
			}, {
				field : 'title',
				title : '图片标题',
				width : 100,
				align : 'center',
				halign : 'center'
			}, {
				field : 'linkUrl',
				title : '链接地址',
				width : 200,
				align : 'center',
				halign : 'center'
			}, {
				field : 'orderNo',
				title : '排序号',
				width : 50,
				align : 'center',
				halign : 'center'
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



<div id="searchbar" data-options="region:'north'" style="margin: 0 auto;" border="false">
	<div id="searchbox" class="head-seachbox">
		<h2 class="h2-title">PC端首页楼层分类列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
		<div class="w-p99 marauto searchCont">
		<form class="form-search" onsubmit="return false;" action="" method="get" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p4 p-item">
					<label class="lab-item">楼层：</label>
					<select name="q_floorId" id="q_floorId" value="${q_floorId!''}">
                    	<option value="">--全部--</option>
                        <#if floors?? && floors?size &gt; 0>
                        	<#list floors as floor>
								<option value="${(floor.id)!}">${(floor.name)!}</option>
							</#list>
						</#if>
				    </select>
				</p>
				<p class="p4 p-item">名称：<input type="text" class="txt" id="q_name" name="q_name" value="${q_name!''}"/></p>
				<p class="p4 p-item">状态：<@cont.select id="q_status" value="${(q_status)!''}" codeDiv="USE_PRE" style="width:80px" /></p>
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
						,view: detailview
						,detailFormatter:detailFormatter
						,onExpandRow:onExpandRow
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/pcindex/floorclass/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="name" width="100" align="left" halign="center">名称</th>
				<th field="floorName" width="100" align="left" halign="center">所属楼层</th>
	            <th field="orderNo" width="50" align="center" halign="center">排序号</th>
	            <th field="status" width="70" align="center" halign="center" formatter="statusFormat">使用状态</th>
	            <th field="remark" width="100" align="center" halign="center">备注</th>
	            <th field="updateUserName" width="100" align="center">最后修改人</th>
	            <th field="updateTime" width="150" align="center">最后修改时间</th>
			</tr>
		</thead>
	</table>
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/pcindex/floor/add">
		<a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floor/edit">
		<a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floor/delete">
		<a id="a-gridDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floor/up">
		<a id="a-gridUp" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">使用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floor/pre">
		<a id="a-gridPre" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">预使用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floor/down">
		<a id="a-gridDown" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">停用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floordata/add">
		<a id="a-gridAddData" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增数据</a>
		</@shiro.hasPermission>
		
		<a id="a-gridView" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-filter" plain="true">预览</a>
	</div>
</div>


<#include "/admin/commons/_detailfooter.ftl" />