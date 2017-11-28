<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/type/"/>
<script language="javascript">
    var codeBox;
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("NORM_TYPE")})');
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
            $.messager.confirm('确认', '确定删除吗？', function(r){
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
        return codeBox["NORM_TYPE"][value];
    }

</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">商品类型列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">类型名称 :</label>
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
                ,url:'${currentBaseUrl}/list'
                ,queryParams:queryParamsHandler()
                ,onLoadSuccess:dataGridLoadSuccess
                ,method:'get'">
        <thead>
        <tr>
            <th field="name" width="60" align="center">类型名称</th>
            <th field="normNames" width="120" align="center">关联规格</th>
            <th field="brandNames" width="150" align="left" halign="center">关联品牌</th>
            <th field="createUser" width="60" align="center">创建人</th>
            <th field="createTime" width="120" align="center">创建时间</th>
            <th field="sort" width="30" align="center">排序</th>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/product/type/add">
        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/type/edit">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/type/del">
        <a id="a-gridRemove" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</@shiro.hasPermission>
        
        <div>
        </div>

<#include "/admin/commons/_detailfooter.ftl" />