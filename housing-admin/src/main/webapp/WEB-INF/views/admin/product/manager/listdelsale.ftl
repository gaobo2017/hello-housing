<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product"/>
<#include "incproductcss.ftl"/>
<#include "incproductjs.ftl"/>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">已删除商品列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox"><!-- 不分隔 -->
                    <p class="p4 p-item">
                        <label class="lab-item">商品名称 :</label>
                        <input type="text" class="txt" id="q_name1" name="q_name1" value="${q_name!''}"/>
                    </p>
                    <#--<p class="p4 p-item">-->
                        <#--<label class="lab-item">状态 :</label>-->
                        <#--<@cont.select id="q_state" name="q_state" value="${(brand.state)!''}" codeDiv="PRODUCT_STATE" mode="1"/>-->
                    <#--</p>-->
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
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
						,view: detailview
						,detailFormatter:detailFormatter
						,onExpandRow:onExpandRow
    					,url:'${currentBaseUrl}/list?q_state=${q_state!''}'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
        <thead>
        <tr>
            <th field="name1" width="300" align="left" halign="center">商品名称</th>
            <th field="seller" width="120" align="center">商家</th>
            <th field="productCateName" width="100" align="center">商品分类</th>
            <th field="productBrandName" width="90" align="center">商品品牌</th>
            <th field="productCode" width="90" align="center">商品编码</th>
            <th field="costPrice" width="70" align="center">成本价</th>
            <th field="protectedPrice" width="70" align="center">保护价</th>
            <th field="productStock" width="70" align="center">库存</th>
            <th field="actualSales" width="70" align="center">销量</th>
            <th field="isTop" width="90" align="center" formatter="isTopFormat">是否推荐</th>
            <th field="createTime" width="150" align="center">创建时间</th>
            <th field="upTime" width="150" align="center">上架时间</th>
            <th field="sellerCateName" width="90" align="center">店铺分类</th>
            <th field="state" width="70" align="center" formatter="stateFormat">状态</th>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
    </div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />