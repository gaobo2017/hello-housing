<#include "/admin/commons/_detailheader.ftl" />

<style>
#newstypeTree img {
	max-width: 390px;
	max-height: 290px;
}
</style>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

	var codeBox;
	$(function(){
	//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("FLASH_SALE_STATUS","CHANNEL")})');
		</#noescape>

		$("#a-gridAdd").click(function(){
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/flash/add";
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
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/flash/edit?actFlashSaleId="+selected.id;
		});
		
		$("#a-gridDel").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selectedCode.status != 1) {
            	$.messager.alert('提示','只能删除新建状态的活动');
                return;
            }

            $.messager.confirm('提示', '确定删除吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash/delete",
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
		
		$("#a-gridRecruitStart").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择操作行。');
	            return;
	        }
	        if(selected.status != 1) {
	        	$.messager.alert('提示','只能对新建状态的活动发起商品征集');
	            return;
	        }

	        $.messager.confirm('提示', '确定开始征集活动商品吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash/recruitstart",
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
		
		$("#a-gridRecruitEnd").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择操作行。');
	            return;
	        }
	        if(selected.status != 2) {
	        	$.messager.alert('提示','只能对正在征集商品的活动进行停止征集操作');
	            return;
	        }

	        $.messager.confirm('提示', '确定结束征集活动商品吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash/recruitend",
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

		// 作废
		$("#a-gridAbolish").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	        if(selected.status != 3) {
	        	$.messager.alert('提示','只能对已经结束征集商品的活动进行作废操作');
	            return;
	        }
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/flash/abolish?actFlashSaleId="+selected.id;
	    });

		// 审核商品
		$("#a-gridAudit").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	        if(selected.status != 3) {
	        	$.messager.alert('提示','只能对已经结束征集商品的活动进行审核操作');
	            return;
	        }
	    	$("#auditWin").dialog({
	    		 title: '活动商品审核',
	             iconCls:"icon-accept",
	             width: 1100,
	             height: 568,
	             modal: true,
	             href: "${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/flash/audit?actFlashSaleId="+selected.id
	    	});
		});
		
		//修改排序
		$("#a-pro-sort").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	    	$("#auditWin").dialog({
	    		 title: '修改活动商品排序',
	             iconCls:"icon-accept",
	             width: 1100,
	             height: 568,
	             modal: true,
	             href: "${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/flash/toProductSort?actFlashSaleId="+selected.id
	    	});
		});
		
		// 上架
		$("#a-gridUp").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择操作行。');
	            return;
	        }
	        if(selected.status == 5) {
	        	$.messager.alert('提示','已经是上架状态，请勿重复操作');
	            return;
	        }
	        if(selected.status != 3) {
	        	$.messager.alert('提示','只能上架已经结束征集商品的活动');
	            return;
	        }

	        $.messager.confirm('提示', '确定上架该活动吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash/up",
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

		// 下架
	    $("#a-gridDown").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择操作行。');
	            return;
	        }
	        if(selected.status == 6) {
	        	$.messager.alert('提示','已经是下架状态，请勿重复操作');
	            return;
	        }
	        if(selected.status != 5) {
	        	$.messager.alert('提示','只能对上架状态的活动进行下架');
	            return;
	        }

	        $.messager.confirm('提示', '确定下架该活动吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash/down",
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

		// 详情
		$("#a-gridDetail").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/flash/detail?actFlashSaleId="+selected.id;
	    });
		
	    
	    $("#a-gridView").click(function(){
	 		window.open("${(domainUrlUtil.EJS_FRONT_URL)!}/previewindex.html");
		});
        
	    $("#newstypeWin").window({
			width : 666,
			height : 420,
			title : "楼层片",
			closed : true,
			shadow : false,
			modal : true,
			collapsible : false,
			minimizable : false,
			maximizable : false
		});
	    
		<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	})

	function statusFormat(value,row,index){
		return codeBox["FLASH_SALE_STATUS"][value];
	}

	function channelFormat(value,row,index){
		return codeBox["CHANNEL"][value];
	}
	
	function imageFormat(value, row, index) {
		return "<a class='newstype_view' onclick='showimg($(this).attr(\"imgpath\"));' href='javascript:;' imgpath='"
				+ value + "'>点击查看</a>";
	}

	function showimg(href) {
		$("#newstypeTree").html("<img src='${domainUrlUtil.EJS_IMAGE_RESOURCES}/"+href+"' >");
		$("#newstypeWin").window('open');
	}

</script>

<div id="searchbar" data-options="region:'north'" style="margin: 0 auto;" border="false">
	<div id="searchbox" class="head-seachbox">
		<h2 class="h2-title">限时抢购活动列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
		<div class="w-p99 marauto searchCont">
		<form class="form-search" onsubmit="return false;" action="" method="get" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p4 p-item"><label class="lab-item">活动名称：</label><input type="text" class="txt" id="q_actFlashSaleName" name="q_actFlashSaleName" value="${q_actFlashSaleName!''}"/></p>
				<p class="p4 p-item"><label class="lab-item">渠道：</label><@cont.select id="q_channel" value="${(q_channel)!''}" codeDiv="CHANNEL" style="width:80px" /></p>
				<p class="p4 p-item"><label class="lab-item">状态：</label><@cont.select id="q_status" value="${(q_status)!''}" codeDiv="FLASH_SALE_STATUS" style="width:80px" /></p>
				<p class="p4 p-item">
					<label class="lab-item">日期：</label>
                    <input type="text" id="q_actDate" name="q_actDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="txt w180"/>
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
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/flash/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="actFlashSaleName" width="100" align="left" halign="center">活动名称</th>
				<th field="actDate" width="150" align="center" halign="center">活动日期</th>
				<th field="pcImage" width="80" align="center" halign="center" formatter="imageFormat">PC图片</th>
				<th field="mobileImage" width="80" align="center" halign="center" formatter="imageFormat">移动图片</th>
				<th field="channel" width="80" align="center" halign="center" formatter="channelFormat">应用渠道</th>
				<th field="status" width="80" align="center" halign="center" formatter="statusFormat">活动状态</th>
				<th field="auditRule" width="150" align="left" halign="center">申请规则</th>
				<th field="remark" width="150" align="left" halign="center">活动描述</th>
				<th field="auditOpinion" width="100" align="left" halign="center">作废原因</th>
	            <th field="updateUserName" width="100" align="center">最后修改人</th>
	            <th field="updateTime" width="150" align="center">最后修改时间</th>
			</tr>
		</thead>
	</table>
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/promotion/flash/add">
		<a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/flash/edit">
		<a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/flash/delete">
		<a id="a-gridDel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/flash/recruitstart">
		<a id="a-gridRecruitStart" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">征集商品</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/flash/recruitend">
		<a id="a-gridRecruitEnd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">征集结束</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/flash/abolish">
		<a id="a-gridAbolish" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">作废</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/flash/audit">
		<a id="a-gridAudit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">审核商品</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/flash/up">
		<a id="a-gridUp" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">上架</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/flash/down">
		<a id="a-gridDown" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">下架</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/flash/toProductSort">
		<a id="a-pro-sort" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改排序</a>
		</@shiro.hasPermission>
		<a id="a-gridDetail" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-car_enter" plain="true">详情</a>
<!-- 		<a id="a-gridView" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-filter" plain="true">预览</a> -->
	</div>
</div>

<div id="newstypeWin">
	<form id="newstypeForm" method="post">
		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
	</form>
</div>

<div id="auditWin"></div>

<#include "/admin/commons/_detailfooter.ftl" />