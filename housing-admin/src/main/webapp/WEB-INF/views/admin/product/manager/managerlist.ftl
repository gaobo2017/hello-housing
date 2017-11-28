<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/manager/"/>
<script language="javascript">
    var codeBox,managerId;
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("MANAGE_STATE")})');
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
            //提交审核的品牌不允许在编辑
            if ( 1 == selectedCode.state){
                $.messager.alert('提示','该品牌已经提交审核，不允许在进行编辑。');
                return;
            }
            if(2 == selectedCode.state){
                $.messager.alert('提示','显示中的品牌，不允许在进行编辑。');
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

            //提交审核、显示中的品牌不允许删除
            if (1 == selectedCode.state){
                $.messager.alert('提示','该品牌已经提交审核，不允许删除。');
                return;
            }
            if(2 == selectedCode.state){
                $.messager.alert('提示','显示中的品牌，不允许删除。');
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
                                $('#dataGrid').datagrid('deleteRow',selectedIndex);
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

        //提交审核
        $('#a-gridCommit').click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selectedCode.state != 1){
                $.messager.alert('提示','数据有误，该状态不可以审核。');
                return;
            }
            managerId = selectedCode.id;
            $('#auditDlg').dialog('open');
        });

        //停用
        $('#a-gridStop').click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selectedCode.state == 4){
                $.messager.alert('提示','该分类已经停用。');
                return;
            }
            managerId = selectedCode.id;
            $('#stopDlg').dialog('open');
        });
    })

    function auditCallBack(data){
        $.ajax({
            type:"POST",
            url: "${currentBaseUrl}auditing",
            dataType: "json",
            data: "id="+managerId+"&state=" + data + "&" +getCSRFTokenParam(),
            cache:false,
            success:function(data, textStatus){
                if (!data.success) {
                    $.messager.alert('提示', data.message);
                }
                refrushCSRFToken(data.csrfToken);
                $.messager.progress('close');
                $('#dataGrid').datagrid('reload',queryParamsHandler());
            }
        });
    }

    function stopCallBack(data){
        $.ajax({
            type:"POST",
            url: "${currentBaseUrl}stop",
            dataType: "json",
            data: "id="+managerId+"&stopReason="+data+"&"+getCSRFTokenParam(),
            cache:false,
            success:function(data, textStatus){
                if (!data.success) {
                    $.messager.alert('提示', data.message);
                }
                refrushCSRFToken(data.csrfToken);
                $.messager.progress('close');
                $('#dataGrid').datagrid('reload',queryParamsHandler());
            }
        });
    }

    function stateFormat(value,row,index){
        return codeBox["MANAGE_STATE"][value];
    }
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">申请分类 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">状态 :</label>
                        <@cont.select id="q_state" name="q_state" value="${(brand.state)!''}" codeDiv="MANAGE_STATE" mode="1"/>
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
            <th field="productCateName" width="150" align="center">商品分类</th>
            <th field="state" width="110" align="center" formatter="stateFormat">状态</th>
            <th field="createUser" width="110" align="center">申请人</th>
            <th field="createTime" width="170" align="center">申请时间</th>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridCommit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">审核</a>
        <a id="a-gridStop" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">停用</a>
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
    <div>
</div>
<div style="display: none">
<#include "auditdialog.ftl" />
</div>
<div style="display: none">
<#include "stopdialog.ftl" />
</div>

<#include "/admin/commons/_detailfooter.ftl" />