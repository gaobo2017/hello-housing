<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/promotion/actgroup"/>

<script language="javascript">
	var activityStateBox;
	var stateBox;
	var channelBox;
	var executeStateBox;

	$(function(){
		//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			activityStateBox = eval('(${initJSCodeContainer("BIDDING_A_STATE")})');
			stateBox = eval('(${initJSCodeContainer("BIDDING_STATE")})');
			channelBox = eval('(${initJSCodeContainer("CHANNEL")})');
			executeStateBox = eval('(${initJSCodeContainer("YES_NO")})');
		</#noescape>
		// 查询按钮
		$('#a-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});

		$("#a-gridAdd").click(function(){
	 		window.location.href="${(currentBaseUrl)!}/add";
		});
		
		$("#a-gridLook").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		window.location.href="${(currentBaseUrl)!}/look?id="+selected.id;
		});
		
		$("#a-gridEdit").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		window.location.href="${(currentBaseUrl)!}/edit?id="+selected.id;
		});
		
		
		$("#a-gridAuditYes").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择操作行。');
	            return;
	        }
	        if(selected.state != 2) {
	        	$.messager.alert('提示','只有提交审核才能进行此操作。');
	            return;
	        }
	        if(selected.activityState == 3) {
	        	$.messager.alert('提示','活动结束不能进行操作');
	            return;
	        }

	        $.messager.confirm('提示', '确定审核通过吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${(currentBaseUrl)!}/auditYes",
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
	    
	    
	    $("#a-gridAuditNo").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	        if(selected.state != 2) {
	        	$.messager.alert('提示','只有提交审核才能进行此操作。');
	            return;
	        }
	        if(selected.activityState == 3) {
	        	$.messager.alert('提示','活动结束不能进行操作');
	            return;
	        }
	        
			$("#valuereset").click();
			$('#_activitybiddingaudit').dialog('open');
	 	});

		$("#auditRejectOk").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	        if(selected.state != 2) {
	        	$.messager.alert('提示','只有提交审核才能进行此操作。');
	            return;
	        }
	        var auditOpinion = $("#auditOpinion").val();
	        if (auditOpinion == null || auditOpinion == "") {
	        	$.messager.alert('提示','请填写审核意见。');
	            return;
	        }
			if($("#activitybiddingauditForm").form('validate')){
				$.ajax({
                    type:"POST",
                    url: "${(currentBaseUrl)!}/auditNo",
                    dataType: "json",
                    data: "id="+selected.id + "&auditOpinion=" + auditOpinion,
                    cache:false,
                    success:function(data, textStatus){
                        if (data.success) {
                            $('#dataGrid').datagrid('reload',queryParamsHandler());
                            $('#_activitybiddingaudit').dialog('close');
                        }else{
                            $.messager.alert('提示', data.message);
                        }
                        $.messager.progress('close');
                    }
                });
	  		}
		});

		$("#auditRejectCancel").click(function(){
			$('#_activitybiddingaudit').dialog('close');
		});
	    
	   
	    
	    $("#a-gridReleaseFinal").click(function(){
	        var selected = $('#dataGrid').datagrid('getSelected');
	        if(!selected){
	            $.messager.alert('提示','请选择操作行。');
	            return;
	        }

	        $.messager.confirm('提示', '确定要结束吗？活动一旦结束，操作是不可逆的。', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${(currentBaseUrl)!}/releaseFinal",
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
	    
	});
	
	function activityStateFormat(value,row,index){
		return activityStateBox["BIDDING_A_STATE"][value];
	}
	function stateFormat(value,row,index){
		return stateBox["BIDDING_STATE"][value];
	}
	function channelFormat(value,row,index){
		return channelBox["CHANNEL"][value];
	}
	function executeStateFormat(value,row,index){
		return executeStateBox["YES_NO"][value];
	}

</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">团购管理 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
		<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p4 p-item">
	                <label class="lab-item">团购分类 :</label>
	                <select name="q_type">
	                	<option value="">请选择</option>
						<#if actGroupTypes ??>
							<#list actGroupTypes as actGroupType>
					  			<option value="${actGroupType.id}">${actGroupType.name}</option>
					  		</#list>
					  	</#if>
					</select>
	            </p>
				<p class="p4 p-item">
	                <label class="lab-item">活动状态 :</label>
	                <@cont.select id="q_activityState" name="q_activityState" codeDiv="BIDDING_A_STATE" mode="1"/>
	            </p>
				<p class="p4 p-item">
	                <label class="lab-item">审核状态 :</label>
	                <@cont.select id="q_state" name="q_state" codeDiv="BIDDING_STATE" mode="1"/>
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
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
	            <th field="productName" width="300" align="left" halign="center">商品名称</th>  
	            <th field="name" width="300" align="left" halign="center">促销内容</th>  
	            <th field="typeName" width="100" align="left" halign="center">分类名称</th>  
	            <th field="sellerName" width="150" align="left" halign="center">所属商家</th> 
	            <th field="saleNum" width="100" align="left" halign="center">销量</th>  
	            <th field="virtualSaleNum" width="100" align="left" halign="center">虚拟销量</th>  
	            <th field="marketPrice" width="100" align="left" halign="center">原价或市场价</th>  
	            <th field="price" width="100" align="left" halign="center">团购价</th>  
	            <th field="stock" width="100" align="left" halign="center">库存</th>  
	            <th field="channel" width="100" align="left" halign="center" formatter="channelFormat">渠道</th>  
	            <th field="startTime" width="150" align="left" halign="center">活动开始时间</th>  
	            <th field="endTime" width="150" align="left" halign="center">活动结束时间</th>  
	            <th field="sortNum" width="150" align="left" halign="center">排序</th>  
	            <th field="isBest" width="150" align="left" halign="center" formatter="executeStateFormat">是否推荐</th>  
	            <th field="activityState" width="150" align="left" formatter="activityStateFormat">活动状态</th>  
	            <th field="state" width="150" align="left" formatter="stateFormat">审核状态</th>  
	            <th field="createTime" width="150" align="left">创建时间</th>
	            <th field="auditOpinion" width="150" align="left">审核意见</th>
			</tr>
		</thead>
	</table>

	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/promotion/actgroup/look">
			<a id="a-gridLook" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">详情</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/actgroup/edit">
			<a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/actgroup/auditYes">
			<a id="a-gridAuditYes" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">审核通过</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/actgroup/auditNo">
			<a id="a-gridAuditNo" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">审核驳回</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/actgroup/releaseFinal">
			<a id="a-gridReleaseFinal" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">结束</a>
		</@shiro.hasPermission>
	</div>
</div>


<#include "/admin/commons/_detailfooter.ftl" />

<#----审核失败输入框-------------->
<#include "/admin/promotion/group/groupaudit.ftl" />