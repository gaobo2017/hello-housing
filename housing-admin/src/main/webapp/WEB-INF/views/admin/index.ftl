<#include "/admin/commons/_detailheader.ftl" />

<style>
.panel a:hover {
	background-color: #D1EEEE;
}

.s_hover {
	background-color: #D1EEEE;
}
</style>

<script type="text/javascript">
	  function addTab(name, url) {
	  	var t = $('#tab');
	  	if(t.tabs('exists', name)) {
	  		t.tabs('select', name);
	  	} else {
	  		var content ='<iframe scrolling="auto" frameborder="0" src="'+url+'" style="width:100%; height:100%"></iframe>';
		  	t.tabs('add',{
				title: name,
				selected: true,
				closable: true,
				content: content
			});
	  	}
	  }
	  
	$(function(){
		/* var first = $("#aa > .panel > .ra_div >a:first");
		first.addClass('s_hover');
		eval(first.attr('onclick')); */
		
		$(".panel a").click(function() {
			$(this).addClass('s_hover');
			$(this).siblings().removeClass('s_hover');
		});
			
	$('#tab').tabs({
		tools:[{
			text : '刷新',
			iconCls:'icon-reload',
			handler:function(){
				var currTab = $('#tab').tabs('getSelected'); 
		        var url = $(currTab.panel('options').content).attr('src');    
		        var content ='<iframe scrolling="auto" frameborder="0" src="'+url+'" style="width:100%; height:100%"></iframe>';
		        $('#tab').tabs('update',{
		            tab:currTab,
		            options:{
		                content: content
		            }
		        });
			}
		},{
			text : '关闭',
			iconCls:'icon-cross',
			handler:function(){
				var tab = $('#tab').tabs('getSelected');
				var index = $('#tab').tabs('getTabIndex',tab);
				$('#tab').tabs('close',index);
			}
		}]
	});
	

	$(".tabs-header").bind('contextmenu',function(e){
		e.preventDefault();
		$('#rcmenu').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
	});
	$("#closecur").bind("click",function(){
		var tab = $('#tab').tabs('getSelected');
		var index = $('#tab').tabs('getTabIndex',tab);
		$('#tab').tabs('close',index);
	});
	$("#closeall").bind("click",function(){
		var tablist = $('#tab').tabs('tabs');
		for(var i=tablist.length-1;i>=0;i--){
			$('#tab').tabs('close',i);
		}
	});
	$("#closeother").bind("click",function(){
		var tablist = $('#tab').tabs('tabs');
		var tab = $('#tab').tabs('getSelected');
		var index = $('#tab').tabs('getTabIndex',tab);
		for(var i=tablist.length-1;i>index;i--){
			$('#tab').tabs('close',i);
		}
		var num = index-1;
		for(var i=num;i>=0;i--){
			$('#tab').tabs('close',0);
		}
	});
	$("#closeright").bind("click",function(){
		var tablist = $('#tab').tabs('tabs');
		var tab = $('#tab').tabs('getSelected');
		var index = $('#tab').tabs('getTabIndex',tab);
		for(var i=tablist.length-1;i>index;i--){
			$('#tab').tabs('close',i);
		}
	});
	$("#closeleft").bind("click",function(){
		var tab = $('#tab').tabs('getSelected');
		var index = $('#tab').tabs('getTabIndex',tab);
		var num = index-1;
		for(var i=0;i<=num;i++){
			$('#tab').tabs('close',0);
		}
	});
	
    $("#refresh").click(function(){
        var currTab = $('#tab').tabs('getSelected');  
        var url = $(currTab.panel('options').content).attr('src');   
        var content ='<iframe scrolling="auto" frameborder="0" src="'+url+'" style="width:100%; height:100%"></iframe>';
        $('#tab').tabs('update',{
            tab:currTab,
            options:{
                content: content
            }
        });
    });
    
    $("#refreshAll").click(function(){
    	var tablist = $('#tab').tabs('tabs');
		for(var i=tablist.length-1;i>=0;i--){
			var currTab = tablist[i];
	        var url = $(currTab.panel('options').content).attr('src'); 
	        var content ='<iframe scrolling="auto" frameborder="0" src="'+url+'" style="width:100%; height:100%"></iframe>';
	        $('#tab').tabs('update',{
	            tab:currTab,
	            options:{
	                content: content
	            }
	        });
		}
    });
});
	 
</script>
	  
    <div data-options="region:'north',split:false" style=" background-color:#dfe8f7; padding:10px 0; height:50px;">
       <h2 style="font-size: 24px; font-family:'微软雅黑';text-indent:7px; margin:0px; letter-spacing:2px; font-weight:normal; line-height:22px;">ejavashop B2B2C 多商家管理系统平台端</h2>
       <span style="float:right; height:16px; margin-top:-17px; margin-right:55px;">欢迎：${SESSION_ADMIN_USER.name}</span>
       <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/exit" style="float:right; height:16px; margin-top:-17px; padding-right:22px;" title="退出系统"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/images/log-out.png" border="0"/></a>
    </div>
    
    <div data-options="region:'west',title:'系统菜单',split:false" style="width:150px;">
    	<#include "/admin/commons/_west.ftl" />
    </div>
    
    <div data-options="region:'center',split:false">
   	    <div id="tab" class="easyui-tabs" data-options="fit:true,border:false">
		    <!--
		    <div class="tabs-header" title="首页">
		    </div>
		    -->
	    </div>
    </div>
    
<div id="rcmenu" class="easyui-menu" style="">
	<div id="refresh">
		刷新
	</div>
	<div id="refreshAll">
		刷新全部
	</div>
	<div class="menu-sep"></div>
	<div id="closecur">
		关闭
	</div>
	<div id="closeall">
		关闭全部
	</div>
	<div id="closeother">
		关闭其他
	</div>
	<div class="menu-sep"></div>
	<div id="closeright">
		关闭右侧标签页
	</div>
	<div id="closeleft">
		关闭左侧标签页
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />