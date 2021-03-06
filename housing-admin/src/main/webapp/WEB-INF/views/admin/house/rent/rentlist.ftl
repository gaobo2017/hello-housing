<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/rent/manage"/>
<#include "housecss.ftl"/>

<script language="javascript">
	var codeBox;
	var code2Box;
	$(function() {
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("HOUSE_PAY_WAY","HOUSE_LEASE_STATE")})');
			
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
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/rent/manage/add";
		});
		
		$("#btn-gridEdit").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if(!selected) {
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			
		    if(selected.status !=1) {
				$.messager.alert('提示','非租期内，请不要修改。');
				return;
			}
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/rent/manage/edit?id="+selected.id;
		});
	});

	function getPayWay(value, row, index) {
		var box = codeBox["HOUSE_PAY_WAY"][value];
 		return box;
	}
	
	function getStatus(value, row, index) {
		var box = codeBox["HOUSE_LEASE_STATE"][value];
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
        
        if(row.status==1){//租期内
            html += "<a href='javascript:;' onclick='editRetrunRent("+row.id+
                    ")'>退租</a>";
        } 
        
        return html;
    }

	function editRetrunRent(id){
  // window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/house/manage/edit?id="+selected.id;
            window.open("${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/rent/manage/cancellease?id="+id);
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
		租房列表一览 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
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
						<label class="lab-item">租房编号 :</label> <input type="text"
							class="txt" id="q_id" name="q_id" value="${q_id!''}" />
					</p>
					
					<p class="p4 p-item">
						<label class="lab-item">缴费方式 :</label> <@cont.select id="q_pay_way"
						codeDiv="HOUSE_PAY_WAY" name="q_pay_way" style="width:100px"/>
					</p>
					<p class="p4 p-item">
						<label class="lab-item">租赁状态 :</label> <@cont.select id="q_lease_state"
						codeDiv="HOUSE_LEASE_STATE" name="q_lease_state" style="width:100px"/>
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
				<th field="id" width="100" align="center">租房编号</th>
				
				<th field="houseId" hidden="hidden"></th>
				<th field="roomCodeNo" width="100" align="center" formatter="proTitle">房源编号</th>
				<th field="houseName" width="100" align="center">房源名称</th>
				<th field="payWay" width="90" align="center" formatter="getPayWay">交租方式</th>
				
				
				<th field="rent" width="100" align="center">月租</th>
				<th field="allRent" width="100" align="center">合同租金总额</th>
			    <th field="agencyFee" width="100" align="center">中介费</th>
				<th field="cashPledge" width="100" align="center">押金</th>
				
				<th field="leaseStartTime" width="120" align="center">租赁开始时间</th>
				<th field="leaseEndTime" width="120" align="center">租赁结束时间</th>
				<th field="contract" width="100" align="center">租赁合同号</th>
   
				<th field="dayRentCost" width="100" align="center">日租成本</th>
				<th field="dayRentIncome" width="100" align="center">日租收入</th>
				<th field="grossProfit" width="100" align="center">单笔毛利润</th>
				
				<th field="status" width="90" align="center" formatter="getStatus">租赁状态</th>
				<th field="finalLeaveTime" width="100" align="center">实际退房日期</th>
				<th field="returnRentCost" width="100" align="center">退租返款</th>
				<th field="rentIncomeAgain" width="100" align="center">物损赔偿金额</th>
				<th field="remark" width="100" align="center">备注</th>				
				<th field="createTime" width="100" align="center">创建时间</th>
				<th field="updateTime" width="100" align="center">更新时间</th>
				
				<th field="handler" width="90" align="center" formatter="handler">操作</th>
				
				<th field="seller" width="100" align="center">销售员</th>
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
