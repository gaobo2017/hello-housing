<#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/role"/>

<script type="text/javascript">
	$(function(){
		$("#resourceTree").tree({
			method: "get",
			checkbox: true,
			cascadeCheck: false,
			onLoadSuccess:function(){
				$("#resourceTree").tree('expandAll');
			},
			url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/resource/roleResTree?roleId=${id}"
		});
		
		$('#saveRuleRes').click(function() {
			var checked = $("#resourceTree").tree('getChecked');
			
			$(this).find('.l-btn-text').html('提交中...');
			$(this).attr('disabled',true);
			
			var resids = new Array();
			$.each(checked,function(idx,e){
				resids.push(e.id);
			});
			$("#resIds").val(resids.toString());
			$("#roleResForm").form('submit', {
				url : "${currentBaseUrl}/saveRoleRes",
				success : function(e) {
					$(this).find('.l-btn-text').html('保存');
					$(this).attr('disabled',false);
					$.messager.progress('close');
					$.messager.show({
						title : '提示',
						msg : e,
						showType : 'show'
					});
					closeWin();
				}
			});
		});
		
	});
	
	function closeWin(){
		$("#allotResourceWin,window.parent.document").window("close");
	}
</script>

<form id="roleResForm" method="post">
	<div style="margin-top: 5px;">
		<label class="lab-item">角色名称: </label><span id="roleNameSpan">${rolesName!}</span><br />
		<label style="color: red; font-weight: bold; margin-left: 15px;">新分配的功能及菜单需要重新登录后才能生效
		</label> <input id="resIds" type="hidden" name="resIds"> <input
			id="roleId" type="hidden" name="roleId" value="${id!}">
	</div>

	<ul id="resourceTree"
		style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;">
		<div style="padding: 12px 140px;text-align: center;">数据加载中...</div>
	</ul>
	<div>
		<p class="p-item p-btn">
			<a id="saveRuleRes" class="easyui-linkbutton" iconCls="icon-save">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-delete"
				onclick="closeWin();">关闭</a>
		</p>
	</div>
</form>