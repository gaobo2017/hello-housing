<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/typeattr1/"/>
<script language="javascript">
    var codeBox;
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("ATTR_TYPE")})');
    </#noescape>

        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
        $("#a-gridAdd").click(function(){
            window.location.href="${currentBaseUrl}add";
        });
        $("#a-gridEdit").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            window.location.href="${currentBaseUrl}edit?id="+selectedCode.id;
        });

        $("#a-gridRemove").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selectedCode.state == 0){
                $.messager.alert('提示','已经删除。');
                return;
            }

            var selectedIndex = $('#dataGrid').datagrid('getRowIndex', selectedCode);
            $.messager.confirm('Confirm', '确定删除吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${currentBaseUrl}del",
                        dataType: "json",
                        data: "id="+selectedCode.id+"&"+getCSRFTokenParam(),
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                //$('#dataGrid').datagrid('deleteRow',selectedIndex);
                                $('#dataGrid').datagrid('reload',queryParamsHandler());
                            }else{
                                $.messager.alert('提示', data.message);
                            }
                            refrushCSRFToken(data.csrfToken);
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });
    })

    function typeFormat(value,row,index){
        return codeBox["ATTR_TYPE"][value];
    }

</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">商品类型属性列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">属性名称 :</label>
                        <input type="text" class="txt" id="q_name" name="q_name" value="${q_name!''}"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">属性类型 :</label>
                        <@cont.select id="q_type" value="${(norm.type)!''}" codeDiv="ATTR_TYPE" mode="1"/>
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
                ,url:'${currentBaseUrl}/list'
                ,queryParams:queryParamsHandler()
                ,onLoadSuccess:dataGridLoadSuccess
                ,method:'get'">
        <thead>
        <tr>
            <th field="name" width="60" align="center">属性名称</th>
            <th field="productTypeName" width="60" align="center">类型名称</th>
            <th field="type" width="60" align="center" formatter="typeFormat">属性类型</th>
            <th field="value" width="150" align="left" halign="center">属性值</th>
            <th field="createId" width="60" align="center">创建人</th>
            <th field="createTime" width="60" align="center">创建时间</th>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
        <#noescape>
			${buttons!}
		</#noescape>
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <div>
        </div>

<#include "/admin/commons/_detailfooter.ftl" />