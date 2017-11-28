<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/brand/"/>
<script language="javascript">
    var codeBox;
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("USE_YN", "SELLER_STATE")})');
    </#noescape>

        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
        $("#a-gridCommit").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selectedCode.state != 1){
                $.messager.alert('提示','只有 提交审核 的才可以审核。');
                return;
            }
            window.location.href="${currentBaseUrl}edit?id="+selectedCode.id + "&type=1";
        });
    })

    function useYnFormat(value,row,index){
        return codeBox["USE_YN"][value];
    }

    function imgFormat(value,row,index){
        var hrefVal = "${domainUrlUtil.EJS_IMAGE_RESOURCES}" + value;
        return "<a href='"+hrefVal+"' target='_blank'>"+hrefVal+"</a>";
    }
    function stateFormat(value,row,index){
        return codeBox["SELLER_STATE"][value];
    }

</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">待审核品牌列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">品牌名称 :</label>
                        <input type="text" class="txt" id="q_name" name="q_name" value="${q_name!''}"/>
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
    					,url:'${currentBaseUrl}/todolist'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
        <thead>
        <tr>
            <th field="name" width="60" align="center">品牌名称</th>
            <th field="nameFirst" width="40" align="center">首字母</th>
            <th field="image" width="180" align="center" formatter="imgFormat">图片地址</th>
            <th field="state" width="60" align="center" formatter="stateFormat">状态</th>
            <th field="createUser" width="80" align="center">创建人</th>
            <th field="createTime" width="170" align="center">创建时间</th>
            <th field="updateUser" width="80" align="center">修改人</th>
            <th field="updateTime" width="170" align="center">修改时间</th>
        </tr>
        </thead>
    </table>

    <div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/product/brand/auditing">
		<a id="a-gridCommit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">审核</a>
		</@shiro.hasPermission>
    <div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />