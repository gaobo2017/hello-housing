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

<div data-options="region:'center'" border="false" style="height:100%">
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
		<@shiro.hasPermission name="/admin/promotion/flash/sort">
		<a id="a-audit-sort" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-filter" plain="true">修改排序</a>
		</@shiro.hasPermission>
	</div>
</div>
