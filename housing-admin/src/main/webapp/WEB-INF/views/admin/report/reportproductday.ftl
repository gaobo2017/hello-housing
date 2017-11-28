<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/productday"/>
<script type="text/javascript" src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
    $(function(){
        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
    })

</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">商品每日销量报表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p6 p-item">
                    	<label class="lab-item">查询时间 :</label>
                        <input type="text" id="q_startTime" name="q_startTime"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00'})" class="txt w180"/>
                        -
                        <input type="text" id="q_endTime" name="q_endTime"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59'})" class="txt w180"/>
                    </p>
                    <p class="p6 p-item">
                    	<label class="lab-item">店铺 :</label>
                        <select name="q_sellerId" id="q_sellerId">
	                    	<option value="">请选择</option>
	                        <#if sellers??>
	                        	<#list sellers as seller>
									<option value="${(seller.id)!}">${(seller.sellerName)!}</option>
								</#list>
							</#if>
					    </select>
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
						,fit:true
						,showFooter:true
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
        <thead>
        <tr>
            <th field="orderDay" width="100" align="center">日期</th>
            <th field="productName" width="200" align="center">商品名称</th>
            <th field="sellerName" width="150" align="center">店铺名称</th>
            <th field="moneyAmount" width="100" align="center">金额</th>
            <th field="number" width="50" align="center">数量</th>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
    </div>

<#include "/admin/commons/_detailfooter.ftl" />