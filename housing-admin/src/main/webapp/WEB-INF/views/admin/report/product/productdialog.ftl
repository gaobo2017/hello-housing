<script type="text/javascript">
function proTitle(value,row,index){
    return "<font style='color:blue;cursor:pointer' title='"+
            value+"' onclick='openwin("+row.id+")'>"+value+"</font>";
}

function stateFormat(value,row,index){
    return codeBox["PRODUCT_STATE"][value];
}

function isTopFormat(value,row,index){
    return codeBox["PRODUCT_IS_TOP"][value];
}

function openwin(id){
    window.open("${(domainUrlUtil.EJS_FRONT_URL)!}/product/"+ id +".html?preview=1");
}

function dataGridLoadSuccess(){
    $("table.datagrid-btable tr td .datagrid-cell font").hover(function() {
        var index = $(this).parents(".datagrid-row").attr("datagrid-row-index");
        var height = Number(index)*25+20;
        if($("#proinfo").length < 1)
            $("<div id='proinfo' class='proInfo'>点击商品名称去商城查看此商品</div>").appendTo($(this));
        $(".proInfo").animate({opacity: "show", top: height}, 300);
    }, function() {
        $("#proinfo").remove();
    });
}
<#noescape>
var codeBox = eval('(${initJSCodeContainer("PRODUCT_STATE","PRODUCT_IS_TOP")})');
</#noescape>

function submitProForm(){
	$('#productDataGrid').datagrid('reload',queryParamsHandler());
}
</script>

<div id="productDialog" class="easyui-dialog popBox" title="商品列表"
	style="width: 980px; height: 421px;"
	data-options="resizable:true,closable:true,closed:true,cache: false"
	buttons="#dlg-buttons-award-act">

	<div data-options="region:'north'" border="false" style="margin:0 auto;">
		<div id="searchbox" class="head-seachbox">
			<div class="w-p99 marauto searchCont">
				<form class="form-search" action="doForm" method="post"
					id="queryForm" name="queryForm">
					<div class="fluidbox">
						<p class="p4 p-item">
						<label class="lab-item-cho">商家
						</label>
						<select id="sel_seller" name="q_sellerId" onchange="submitProForm();">
							<option value="">请选择</option>
	                        <#if sellers??>
	                        	<#list sellers as seller>
									<option value="${(seller.id)!}" 
									<#if byseller??&&byseller==1&&sel_seller==seller.id>selected</#if>>
									${(seller.sellerName)!}</option>
								</#list>
							</#if>
						</select>
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div  data-options="fit:true,region:'center'"  border="false" style="height: 89%">
			<table id="productDataGrid" class="easyui-datagrid"
				data-options="
							rownumbers:true,
							singleSelect : true,
							pagination:true,
							fit:true,
							fitColumns:true,
							url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/list?q_state=6',
							onLoadSuccess:dataGridLoadSuccess,
							method:'get'">
				<thead>
					 <tr>
			            <th field="name1" width="400" align="left" halign="center" formatter="proTitle">商品名称</th>
			            <th field="seller" width="120" align="center">商家</th>
			            <th field="productCateName" width="100" align="center">商品分类</th>
			            <th field="productBrandName" width="90" align="center">商品品牌</th>
			            <th field="actualSales" width="70" align="center">销量</th>
			            <th field="state" width="60" align="center" formatter="stateFormat">状态</th>
			        </tr>
				</thead>
			</table>
	</div>
	
	<div id="dlg-buttons-award-act" style="text-align: right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="submit()">确定</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#productDialog').dialog('close')">取消</a>
	</div>
</div>

<script language="javascript">
	function submit() {
		var selectedRow = $("#productDataGrid").datagrid('getSelected');
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