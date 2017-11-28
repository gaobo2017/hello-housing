<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/cate/"/>
<script language="javascript">
	$(function() {
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("SELLER_CATE_STATE")})');
    </#noescape>
		$("#btn_audit").click(function() {
			var selectedCode = $('#dataGrid').datagrid('getSelected');
			if (!selectedCode) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}

            if(selectedCode.status != 1){
                $.messager.alert('提示', '只有 提交审核 状态的才可以审核。');
                return;
            }
		     $.messager.confirm('确认', '确定审核通过该商家分类申请吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"get",
                        url: "${currentBaseUrl}auditPass",
                        dataType: "json",
                        data: {
                        	id:selectedCode.id,
                        	type:2
                        },
                        cache:false,
                        success:function(e){
                            $('#dataGrid').datagrid('reload');
                            $.messager.progress('close');
                        }
                    });
                }
            });
		});
        $("#btn_audit1").click(function() {
			var selectedCode = $('#dataGrid').datagrid('getSelected');
			if (!selectedCode) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}

            if(selectedCode.status != 1){
                $.messager.alert('提示', '只有 提交审核 状态的才可以审核。');
                return;
            }
		     $.messager.confirm('确认', '确定审核不通过该商家分类申请吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"get",
                        url: "${currentBaseUrl}auditPass",
                        dataType: "json",
                        data: {
                        	id:selectedCode.id,
                        	type:3
                        },
                        cache:false,
                        success:function(e){
                            $('#dataGrid').datagrid('reload');
                            $.messager.progress('close');
                        }
                    });
                }
            });
		});

        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
	})


    function statusFormat(value,row,index){
        return codeBox["SELLER_CATE_STATE"][value];
    }
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		商品分类申请列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">分类名称 :</label> <input type="text"
							class="txt" id="q_name" name="q_name" value="${q_name!''}" />
					</p>
                    <p class="p4 p-item">
                        <label class="lab-item">状态 :</label>
                    <@cont.select id="q_state" name="q_state" value="${(q_state)!''}" codeDiv="SELLER_CATE_STATE" mode="1"/>
                    </p>
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
				<th field="pathName" width="60" align="left" halign="center">分类名称</th>
				<th field="sellerName" width="60" align="center">商家名称</th>
				<th field="seller" width="60" align="center">申请人</th>
				<th field="createTime" width="60" align="center">申请时间</th>
				<th field="status" width="60" align="center" formatter="statusFormat">审核状态</th>
				<th field="scaling" width="30" align="center">分佣比例(%)</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/product/cate/auditPass">
        <a id="btn_audit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">审核通过</a>
        <a id="btn_audit1" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">审核不通过</a>
        </@shiro.hasPermission>
    <div>

    </div>

	<#include "/admin/commons/_detailfooter.ftl" />