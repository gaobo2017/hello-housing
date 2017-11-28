<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product"/>

<style>
	.datagrid-btable td a{
		color:blue;
	}
	
	.proInfo{
		background: #C0E0E9 none repeat scroll 0% 0%;
	    background-image: none;
	    background-repeat: repeat;
	    background-attachment: scroll;
	    background-position: 0% 0%;
	    background-clip: border-box;
	    background-origin: padding-box;
	    background-size: auto auto;
		line-height: 33px;
		height: 35px;
		position: absolute;
		top: 25px;
		left: 275px;
		text-align: center;
		font-style: normal;
		z-index: 20;
		display: none;
		border-radius: 5px;
		font-size: 17px;
		color: #137455;
		padding: 0 10px;
	}
</style>

<script language="javascript">
    var codeBox;
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("PRODUCT_STATE","PRODUCT_IS_TOP")})');
    </#noescape>

        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });

        //商城显示
        $('#a-gridCommit').click(function(){
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.state!=2){
                $.messager.alert('提示','该商品不是可审核状态。');
                return;
            }
        	$.messager.confirm('确认', '确定审核通过该商品吗？', function(r){
    			if (r){
    				$.messager.progress({text:"提交中..."});
    				$.ajax({
    					type:"GET",
    				    url: "${currentBaseUrl}/auditProduct?id="+selected.id+"&type=3",
    					success:function(e){
    						$('#dataGrid').datagrid('reload');
    						$.messager.progress('close');
    						$.messager.show({
    							title : '提示',
    							msg : e,
    							showType : 'show'
    						});
    					}
    				});
    		    }
    		});
        });
        
        //申请驳回
        $('#a_pro_back').click(function(){
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.state!=2){
                $.messager.alert('提示','该商品没有提交审核申请');
                return;
            }
          	$.messager.confirm('确认', '确定驳回该商品申请吗？该商品将无法上架', function(r){
    			if (r){
    				$.messager.progress({text:"提交中..."});
    				$.ajax({
    					type:"GET",
    				    url: "${currentBaseUrl}/auditProduct?id="+selected.id+"&type=4",
    					success:function(e){
    						$('#dataGrid').datagrid('reload');
    						$.messager.progress('close');
    						$.messager.show({
    							title : '提示',
    							msg : e,
    							showType : 'show'
    						});
    					}
    				});
    		    }
    		});
        });
        
        //删除商品
        $('#a_pro_del').click(function(){
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.state==5){
                $.messager.alert('提示','该商品已被删除。');
                return;
            }
          	$.messager.confirm('确认', '确定删除该商品吗？删除后,该商品将被强制下架', function(r){
    			if (r){
    				$.messager.progress({text:"提交中..."});
    				$.ajax({
    					type:"GET",
    				    url: "${currentBaseUrl}/auditProduct?id="+selected.id+"&type=5",
    					success:function(e){
    						$('#dataGrid').datagrid('reload');
    						$.messager.progress('close');
    						$.messager.show({
    							title : '提示',
    							msg : e,
    							showType : 'show'
    						});
    					}
    				});
    		    }
    		});
        });
        
        
    });

    function stateFormat(value,row,index){
        return codeBox["PRODUCT_STATE"][value];
    }
    
    function isTopFormat(value,row,index){
        return codeBox["PRODUCT_IS_TOP"][value];
    }
    
    function sellerIsTopFormat(value,row,index){
        return codeBox["PRODUCT_IS_TOP"][value];
    }
    
    function openwin(id){
    	window.open("${(domainUrlUtil.EJS_FRONT_URL)!}/product/"+id+".html","_blank");
    }
    
    //操作
    function handler(value,row,index){
    	var html ="";
    	if(row.isTop==1){
    		html += "<a href='javascript:;' onclick='recommond("+row.id+
    				",true,"+row.state+")'>推荐</a>&nbsp;&nbsp;<a href='javascript:;' onclick='del("+
    						row.id+")'>删除";
    	} else{
    		html += "<a href='javascript:;' onclick='recommond("+row.id+
			",false,"+row.state+")'>取消推荐</a>&nbsp;&nbsp;<a href='javascript:;' onclick='del("+
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
        $.messager.confirm('确认', '确定删除该商品吗？删除此商品后,商家店铺的该商品将被迫下架', function(r){
            if (r){
                $.messager.progress({text:"提交中..."});
                $.ajax({
                    type:"get",
                    url: "${currentBaseUrl}/del?id="+id,
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
    
    function getLength(str){  
        var realLength = 0;  
        for (var i = 0; i < str.length; i++){  
            charCode = str.charCodeAt(i);  
            if (charCode >= 0 && charCode <= 128)   
            realLength += 1;  
            else   
            realLength += 2;  
        }  
        return realLength;  
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
    
    function proTitle(value,row,index){
    	return "<font style='color:blue;cursor:pointer' title='"+
			value+"' onclick='openwin("+row.id+")'>"+value+"</font>";
    }
</script>



<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">商品列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox"><!-- 不分隔 -->
                    <p class="p4 p-item">
                        <label class="lab-item">商品名称 :</label>
                        <input type="text" class="txt" id="q_name1" name="q_name1" value="${q_name!''}"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">状态 :</label>
                        <@cont.select id="q_state" name="q_state" value="${(brand.state)!''}" codeDiv="PRODUCT_STATE" mode="1"/>
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
            <th field="ck" checkbox="true"></th>
            <th field="name1" width="190" align="left" halign="center" formatter="proTitle">商品名称</th>
            <th field="productCateName" width="100" align="center">商品分类</th>
            <th field="productBrandName" width="90" align="center">商品品牌</th>
            <th field="mallPcPrice" width="70" align="center">商城价</th>
            <th field="isTop" width="90" align="center" formatter="isTopFormat">是否推荐</th>
            <th field="upTimeStr" width="90" align="center">上架时间</th>
            <th field="sellerCateName" width="90" align="center">店铺分类</th>
            <th field="state" width="70" align="center" formatter="stateFormat">状态</th>
            <th field="handler" width="90" align="center" formatter="handler">操作</th>
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