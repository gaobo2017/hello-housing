<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/costdetail/manage"/>
<#include "housecss.ftl"/>

<script language="javascript">
	var codeBox;
	var code2Box;
	$(function() {
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("HOUSE_COST_TYPE")})');
			
		</#noescape>
		

		
		$('#btn_del').click(function(){
	 		var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		
	 		$.messager.confirm('确认', '确定删除该成本明细吗?', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					
					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/delete?id="+selected.id,
						success:function(data, textStatus){
							if (data.success) {
								$.messager.alert('提示','删除成功。');
								$('#dataGrid').datagrid('reload',queryParamsHandler());
							} else {
								$.messager.alert("提示",data.message);
							}
							$.messager.progress('close');
						}
					});
					
			    }
			});
			
		});

		// 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		$("#btn-gridAdd").click(function(){
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/costdetail/manage/add";
		});
		
		$("#btn-gridEdit").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/costdetail/manage/edit?id="+selected.id;
		});
	});

	function getCostType(value, row, index) {
		var box = codeBox["HOUSE_COST_TYPE"][value];
 		return box;
	}
	
		
	
	function openwin(id){
  // window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/house/manage/edit?id="+selected.id;
            window.open("${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/house/manage/edit?id="+id);
    }

   
    function proTitle(value,row,index){
        return "<font style='color:blue;cursor:pointer' title='"+
                value+"' onclick='openwin("+row.houseId+")'>"+value+"</font>";
    }
    
    
   //操作
    function handler(value,row,index){
        var html ="";
        
        if(row.isTop==1){
            html += "<a href='javascript:;' onclick='recommond("+row.id+
                    ",true,"+row.state+")'>推荐</a>&nbsp;&nbsp;<a href='javascript:;' onclick='del("+
                    row.id+")'>删除";
        } else{
            html += "&nbsp;&nbsp;<a href='javascript:;' onclick='del("+
                    row.id+")'>删除";
        }
        html += "</a>";
        return html;
    }

    //推荐/取消推荐
    function recommond(id,isRec,status){
        if(status==5){
            $.messager.alert('提示','该商品已被删除');
            return;
        }
        try{
            $.ajax({
                url:'${currentBaseUrl}/recommond?id='+id+'&isRec='+isRec,
                success:function(e){
                    $.messager.show({
                        title:'提示',
                        msg:e,
                        showType:'show'
                    });
                    $('#dataGrid').datagrid('reload');
                }
            });
        } catch(e){
            throw new Error(e);
        }
    }

    function del(id){
    
        $.messager.confirm('确认', '确定删除该成本明细吗？', function(r){
            if (r){
                $.messager.progress({text:"提交中..."});
                $.ajax({
                    type:"get",
                    url: "${currentBaseUrl}/delete?id="+id,
                    success:function(e){
                     
                        $.messager.show({
                            title:'提示',
                            msg:e,
                            showType:'show'
                        });
                        $.messager.progress('close');
                        $('#dataGrid').datagrid('reload');
                    }
                });
            }
        });
    } 
    
</script>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
	border="false">
	<h2 class="h2-title">
		成本详情列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">房源编号 :</label> <input type="text"
							class="txt" id="q_roomCodeNo" name="q_roomCodeNo" value="${q_roomCodeNo!''}" />
					</p>
		
					<p class="p4 p-item">
						<label class="lab-item">成本编号 :</label> <input type="text"
							class="txt" id="q_id" name="q_id" value="${q_id!''}" />
					</p>
					
					<p class="p4 p-item">
						<label class="lab-item">费用类型 :</label> <@cont.select id="q_cost_type"
						codeDiv="HOUSE_COST_TYPE" name="q_cost_type" style="width:100px"/>
					</p>
					
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false">
	<table id="dataGrid" class="easyui-datagrid"
		data-options="rownumbers:true
						,idField :'id'
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
    					,method:'post'">
		<thead>
			<tr>
				<th field="id" width="100" align="center">成本编号</th>
				<th field="costId" hidden="hidden">总成本id</th>
				<th field="houseId" hidden="hidden"></th>
				<th field="roomCodeNo" width="100" align="center" formatter="proTitle">房源编号</th>
				<th field="houseName" width="100" align="center">房源名称</th>
				<th field="costType" width="90" align="center" formatter="getCostType">费用类型</th>
				
				<th field="money" width="100" align="center">发生金额</th>
				<th field="remark" width="100" align="center">备注</th>
				<th field="createTime" width="100" align="center">发生时间</th>
				<th field="handler" width="90" align="center" formatter="handler">操作</th>
				<th field="operationName" width="100" align="center">操作人</th>
								
			</tr>
		</thead>
	</table>

	<div id="gridTools">


		<@shiro.hasPermission name="/admin/costdetail/manage/edit">
		<a id="btn-gridEdit" href="/admin/house/manage/edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/costdetail/manage/delete2222">
		<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
		
		<a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<div></div>
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
