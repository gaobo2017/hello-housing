<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/manage"/>
<script language="javascript">
	var codeBox;
	$(function() {
		<#noescape>
		codeBox = eval('(${initJSCodeContainer("SELLER_AUDIT_STATE")})');
		</#noescape>
		
		// 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		$('#btn_edit').click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		$("#editWin").window({
				width : 600,
				height : 295,
				href : "${currentBaseUrl}/edit?id="+selected.id,
				title : "修改",
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		});
		
		// 冻结商家
		$('#btn_freeze').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		$.messager.confirm('确认', '确定停用该商家吗？停用后，该商家的店铺及所有商品将被冻结', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/freeze",
						dataType: "json",
					    data: "sellerId=" + selected.id,
					    cache:false,
						success:function(data, textStatus){
							if (data.success) {
								$('#dataGrid').datagrid('reload');
						    } else {
						    	$.messager.alert('提示',data.message);
						    	$('#dataGrid').datagrid('reload');
						    }
							$.messager.progress('close');
						}
					});
			    }
			});
		});
		
		// 解冻商家
		$('#btn_unfreeze').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		$.messager.confirm('确认', '确定解冻该商家吗？', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/unfreeze",
						dataType: "json",
					    data: "sellerId=" + selected.id,
					    cache:false,
						success:function(data, textStatus){
							if (data.success) {
								$('#dataGrid').datagrid('reload');
						    } else {
						    	$.messager.alert('提示',data.message);
						    	$('#dataGrid').datagrid('reload');
						    }
							$.messager.progress('close');
						}
					});
			    }
			});
		});
		
	});

	function getState(value, row, index) {
		var box = codeBox["SELLER_AUDIT_STATE"][value];
		return box;
	}
</script>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
	border="false">
	<h2 class="h2-title">
		商家列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">用户名 :</label> <input type="text"
							class="txt" id="q_name" name="q_name" value="${q_name!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">店铺名称 :</label> <input type="text"
							class="txt" id="q_sellerName" name="q_sellerName" value="${q_sellerName!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">状态 :</label> <@cont.select id="q_auditStatus"
						codeDiv="SELLER_AUDIT_STATE" name="q_auditStatus" style="width:100px"/>
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
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="name" width="120" align="center">申请注册账号名</th>
				<th field="memberName" width="120" align="center">申请人账号</th>
				<th field="sellerName" width="150" align="center">申请店铺名</th>
				<th field="sellerGrade" width="70" align="center">店铺等级</th>
				<th field="scoreService" width="70" align="center">服务评分</th>
				<th field="scoreDeliverGoods" width="70" align="center">发货评分</th>
				<th field="scoreDescription" width="70" align="center">描述评分</th>
				<th field="productNumber" width="70" align="center">商品数量</th>
				<th field="collectionNumber" width="70" align="center">被收藏数量</th>
				<th field="saleMoney" width="70" align="center">店铺总销售金额</th>
				<th field="orderCount" width="70" align="center">总订单量</th>
				<th field="orderCountOver" width="70" align="center">完成订单量</th>
				<th field="auditStatus" width="70" align="center" formatter="getState">状态</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/admin/seller/manage/freeze">
		<a id="btn_freeze" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">停用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/seller/manage/unfreeze">
		<a id="btn_unfreeze" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">启用</a>
		</@shiro.hasPermission>
		<a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
	
	<div class="wrapper" id="editWin">
		
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
