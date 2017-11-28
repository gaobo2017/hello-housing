<#import "/admin/commons/_macro_controller.ftl" as cont/>
<style>
 .p3 img{
 	margin-left: 55px;
    max-width: 300px;
  }
</style>

<script type="text/javascript">
var codeBox;
function submitProForm(){
	$('#auditGrid').datagrid('reload',queryParamsHandler());
}

$(function(){
	<#noescape>
	codeBox = eval('(${initJSCodeContainer("FLASH_PRODUCT_STATUS")})');
	</#noescape>
	
	$("#a-audit-pass").click(function(){
		var obj = this;
		var sel = $('#auditGrid').datagrid('getSelected');
			if (!sel) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			if (sel.status!=1) {
				$.messager.alert('提示', '当前状态不可操作');
				return;
			}
			var actFlashSaleProductId = sel.id;
			var auditOpinion = '';
			
			var params = "actFlashSaleProductId=" + actFlashSaleProductId;
			params += "&auditOpinion=" + auditOpinion;
			params += "&status=2";
			params += "&actFlashSaleId=" + $("#actFlashSaleId").val();
			
			$.messager.confirm('提示:', '确定审核通过该条申请信息吗？', function(event) {
				if (event) {
					$.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash/doaudit",
	                    dataType: "json",
	                    data: params,
	                    cache:false,
	                    success:function(data, textStatus){
	                        if (data.success) {
	                            $.messager.alert('提示', "审核成功！");
	                            $('#auditGrid').datagrid('reload');
	                        }else{
	                            $.messager.alert('提示', data.message);
	                        }
	                        $.messager.progress('close');
	                    }
	                });
				} 
			});
		});

		$("#a-audit-back").click(function() {
			
			var sel = $('#auditGrid').datagrid('getSelected');
			if (!sel) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			if (sel.status!=1) {
				$.messager.alert('提示', '当前状态不可操作');
				return;
			}
			var actFlashSaleProductId = sel.id;
			
			$.messager.confirm('提示:', '确定驳回该条申请信息吗？', function(event) {
				if (event) {
					$.messager.prompt('提示:', '请输入审核意见', function(r) {
						var params = "actFlashSaleProductId=" + actFlashSaleProductId;
						params += "&auditOpinion=" + r;
						params += "&status=3";
						params += "&actFlashSaleId=" + $("#actFlashSaleId").val();
						//可不输入
						$.messager.progress({text:"提交中..."});
			            $.ajax({
			                type:"POST",
			                url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash/doaudit",
			                dataType: "json",
			                data: params,
			                cache:false,
			                success:function(data, textStatus){
			                    if (data.success) {
			                        $.messager.alert('提示', "操作成功！");
			                        $('#auditGrid').datagrid('reload');
			                    }else{
			                        $.messager.alert('提示', data.message);
			                    }
			                    $.messager.progress('close');
			                }
			            });
					})
				} 
			});
		});
		
		$("#a-audit-sort").click(function() {
			var sel = $('#auditGrid').datagrid('getSelected');
			if (!sel) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			$.messager.prompt('提示:', '请输入排序号', function(r) {
				if (r) {
					$.ajax({
						url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash/sort?id='+sel.id+'&sort='+r,
						success:function(data){
							var msg = (data.success)?"修改成功":data.message;
							$.messager.show({
								title:'提示',
								msg:msg,
								showType:'show'
							});
							 $('#auditGrid').datagrid('reload');
						}
					});
				}
			})
		});

	});

	function stateFormat(value, row, index) {
		var box = codeBox["FLASH_PRODUCT_STATUS"][value];
		return box;
	}
</script>

<div id="audit-searchbar" data-options="region:'north'" style="margin: 0 auto;height: 12%" border="false">
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
		<form class="form-search" onsubmit="return false;" method="get" id="auditForm" name="queryForm">
				<input type="hidden" id="actFlashSaleId" name="id" value="${(actFlashSale.id)!''}">
				<div class="fluidbox">
					<p class="p3 p-item">
						<label class="lab-item">活动名称：</label>
						<label>${(actFlashSale.actFlashSaleName)!''}</label>
					</p>
					<p class="p3 p-item">
						<label class="lab-item">活动日期：</label>
						<label>${(actFlashSale.actDate?string('yyyy-MM-dd'))!''}</label>
					</p>
					<p class="p3 p-item">
						<label class="lab-item">活动状态：</label>
						<@cont.select disabled="disabled" id="status" value="${(actFlashSale.status)!''}" codeDiv="FLASH_SALE_STATUS" style="width:100px" mode="1"/>
					</p>
					<p class="p3 p-item">
						<label class="lab-item"><font class="red">*</font>应用渠道：</label>
						<@cont.select disabled="disabled" id="channel" value="${(actFlashSale.channel)!''}" codeDiv="CHANNEL" style="width:100px" mode="1"/>
					</p>
				</div>
				<div class="fluidbox">
					<p class="p3 p-item">
						<label class="lab-item">作废原因：</label>
						<label>${(actFlashSale.auditOpinion)!''}</label>
					</p>
					<p class="p3 p-item">
						<label class="lab-item">申请规则：</label>
						<label>${(actFlashSale.auditRule)!''}</label>
					</p>
					<p class="p3 p-item">
						<label class="lab-item">活动描述：</label>
						<label>${(actFlashSale.remark)!''}</label>
					</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false" style="height:88%">
	<table id="auditGrid" class="easyui-datagrid"
			data-options="singleSelect:true
						,fitColumns:true
						,collapsible:true
						,toolbar:'#audit-gts'
						,striped:true
						,pagination:false
						,fit:true
    					,url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash/product/list?actFlashSaleId=${actFlashSale.id}'
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
			    <th field="actTime" width="110"  align="center">秒杀时间段</th>
			    <th field="productName" width="400"  halign="center">商品</th>
			    <th field="sellerName" width="200"  align="center">申请商家</th>
	            <th field="price" width="120" align="center">价格</th>
	            <th field="mallPcPrice" width="120" align="center">原价</th>
	            <th field="stock" width="120" align="center">库存</th>
	            <th field="actualSales" width="120" align="center">销量</th>
	            <th field="status" width="100" align="center" formatter="stateFormat">状态</th>
	            <th field="sort" width="100" align="center">排序</th>
			</tr>
		</thead>
	</table>
	<div id="audit-gts">
		<@shiro.hasPermission name="/admin/promotion/flash/doaudit">
		<a id="a-audit-pass" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">审核通过</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/flash/doaudit">
		<a id="a-audit-back" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-back" plain="true">驳回</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/flash/sort">
		<a id="a-audit-sort" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-filter" plain="true">修改排序</a>
		</@shiro.hasPermission>
	</div>
</div>

<script language="javascript">
	function submit() {
		var selectedRow = $("#auditGrid").datagrid('getSelected');
		if (!selectedRow) {
			$.messager.alert('提示', '请选择操作行。');
			return;
		}
		var callbackfunc = eval('productCallBack');
		callbackfunc(selectedRow);
		$("#productDialog").dialog('close');
		$("#proid").change();
	}
</script>