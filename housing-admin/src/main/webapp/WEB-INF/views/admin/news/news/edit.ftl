<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/news"/>

<style>
.dd-group .fluidbox .lab-item {
	margin-top: 3px;
}
</style>

<link
	href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/umeditor/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8"
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/umeditor/umeditor.min.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/umeditor/lang/zh-cn/zh-cn.js"></script>

<script language="javascript">
	$(function() {
		
		$.extend($.fn.validatebox.methods, {
		    remove: function(jq, newposition){  
		        return jq.each(function(){  
		            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');
		        });  
		    },
		    reduce: function(jq, newposition){  
		        return jq.each(function(){  
		           var opt = $(this).data().validatebox.options;
		           $(this).addClass("validatebox-text").validatebox(opt);
		        });  
		    }   
		});
		
		<#if !obj??||obj??&&obj.isOut==0 >
			//去掉必填验证
			$('#outUrl').validatebox('remove');
		</#if>
		
		$("#back").click(function() {
			location.href = '${currentBaseUrl}';
		});
		
		<#if !obj??>
			$('#typePath').val(($('#typeId').find('option:selected').text()));
		</#if>
		
		$("#add").click(function() {
			$("#content").val(um.getContent());
			if ($('#addForm').form('validate')) {
				$('#addForm').submit();
			}
		});

		<#if obj??&&obj.isOut==1>
			$('#outer_url').show();
		</#if>
		
		//初始化分配资源对话框
		$("#newstypeWin").window({
			width : 400,
			height : 500,
			title : "选择文章分类",
			closed : true,
			shadow : false,
			collapsible : false,
			minimizable : false,
			maximizable : false
		});
		
	});

	function closeWin() {
		$('#newstypeWin').window('close');
	}
	
	function showOuter(){
		$('#outUrl').validatebox('reduce');
		$('#outer_url').show();
	}
	
	function outerHide(){
		$('#outUrl').validatebox('remove');
		$('#outer_url').hide();
	}
</script>

<div class="wrapper">
	<div class="formbox-a" style="overflow: auto; max-height: 600px">
		<h2 class="h2-title">
			<#if obj??> 编辑文章 <#else> 新增文章 </#if> <span class="s-poar"> <a
				class="a-back" href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			name="addForm" enctype="multipart/form-data"
			action="${currentBaseUrl}/doAdd"> <input type="hidden"
				value="${(obj.id)!''}" name="id"> <input type="hidden"
				id="content" value="${(obj.content)!''}" name="content">

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>文章分类: </label>
							<select class="txt w200 easyui-validatebox" id="typeId"
								name="typeId"
								onchange="$('#typePath').val(($(this).find('option:selected').text()));">
								<#list typelist as type>
								<option value="${type.id!''}"<#if
									obj??&&obj.typeId==type.id>selected="selected"</#if> >
									${type.name!''}</option> </#list>
							</select> <input type="hidden" id="typePath" name="typePath"
								value="${(obj.typePath)!''}">
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>文章标题: </label>
							<input type="text" id="title" name="title"
								value="${(obj.title)!''}" class="txt w200 easyui-validatebox"
								missingMessage="文章标题必填，2-20个字符"
								data-options="required:true,validType:'length[2,20]'" />
						</p>
					</div>

					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>是否显示: </label>
							<input id="status_y" name="status" class="rad" value="1"
								type="radio"<#if !obj??>checked=""</#if> <#if
							obj??&&obj.status==1> checked=""</#if> /> 是 <input id="status_n"
								name="status" class="rad" value="0"<#if
							obj??&&obj.status==0> checked=""</#if> type="radio" /> 否
						</p>
						<p class="p6p-item">
							<label class="lab-item"><font class="red">*</font>是否推荐: </label>
							<input id="ir_y" type="radio" name="isRecommend" class="rad"
								value="1"<#if obj??&&obj.isRecommend==1>
							checked=""</#if> /> 是 <input id="ir_n" type="radio"
								name="isRecommend" class="rad" value="0"<#if
							!obj??>checked=""</#if> <#if obj??&&obj.isRecommend==0>
							checked=""</#if> /> 否
						</p>
					</div>

					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>排序: </label> <input
								type="text" id="sort" name="sort" value="${(obj.sort)!''}"
								class="txt w200 easyui-numberbox" data-options="min:1,max:999,required:true" missingMessage="顺序位必填，1-999" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>是否外部链接:
							</label> <input id="outer_y" name="isOut" class="rad" value="1"
								type="radio" onclick="showOuter();"<#if
							obj??&&obj.isOut==1> checked=""</#if> /> 是 <input id="outer_n"
								name="isOut" class="rad" value="0"<#if
							obj??&&obj.isOut==0> checked=""</#if> <#if
							!obj??>checked=""</#if> type="radio" onclick="outerHide();" /> 否
						</p>
					</div>

					<div class="fluidbox" id="outer_url" style="display: none">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>外部链接URL:
							</label> <input type="text" id="outUrl" name="outUrl"
								value="${(obj.outUrl)!''}" class="txt w200 easyui-validatebox"
								missingMessage="外部链接URL应以http://开头"
								data-options="required:true,validType:'length[7,999]'" />
						</p>
					</div>

				</dd>
			</dl>

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>内容编辑
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<script type="text/plain" id="myEditor"
							style="width: 99%; height: 240px; max-height: 600px">
							<#if obj??>
								<#noescape>
									${(obj.content)!''}
								</#noescape>
							</#if>
						</script>
					</div>
				</dd>
			</dl>

			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="提交" /> <input
					type="button" id="back" class="btn" value="返回" />
			</p>
			</@form.form>
		</div>
	</div>
</div>


<!-- 上级分类对话窗 -->
<div id="newstypeWin">
	<form id="newstypeForm" method="post">
		<div style="margin-top: 5px; border-bottom: 2px solid #C9DDFF;">
			<label class="lab-item">文章分类: </label><span id="roleNameSpan"></span><br />
		</div>

		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto;"></ul>
	</form>
</div>

<script>
	var um = UM.getEditor('myEditor');
	um.addListener('blur', function() {
		$('#focush2').html('编辑器失去焦点了')
	});
	um.addListener('focus', function() {
		$('#focush2').html('')
	});
</script>

<#include "/admin/commons/_detailfooter.ftl" />
