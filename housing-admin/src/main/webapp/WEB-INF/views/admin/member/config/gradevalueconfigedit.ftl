<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">
$(function(){
	// 经验值规则操作界面确定按钮
	$("#edit").click(function(){
		var state = $("#state").val();
		if (state == null || state == "") {
			$.messager.alert('提示','使用状态不能为空。');
			return;
		}
		if($("#addForm").form('validate')){
			$.ajax({
				type:"POST",
				url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/config/gradevalue/update",
				dataType: "json",
				data: $('#addForm').serialize(),
				cache:false,
				success:function(data, textStatus){
					if (data.success) {
						$.messager.alert('提示','修改成功。');
						return;
					} else {
						$.messager.alert("提示",data.message);
					}
				}
			});
  		}
	});

	<#if message??>$.messager.progress('close');alert('${message}');</#if>
})
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">会员经验值规则配置</h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<input type="hidden" id="id" name="id" value="${(memberRule.id)!''}" data-options="required:true"/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>会员注册: </label>
							<input class="easyui-numberbox" id="register" name="register" value="${(memberRule.register)!''}" data-options="min:0,required:true">
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>每日登陆: </label>
							<input class="easyui-numberbox" id="login" name="login" value="${(memberRule.login)!''}" data-options="min:0,required:true">
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>订单评论: </label>
							<input class="easyui-numberbox" id="orderEvaluate" name="orderEvaluate" value="${(memberRule.orderEvaluate)!''}" data-options="min:0,required:true">
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>会员购物: </label>
							<input class="easyui-numberbox" id="orderBuy" name="orderBuy" value="${(memberRule.orderBuy)!''}" data-options="min:0,required:true">
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>购物送值上限: </label>
							<input class="easyui-numberbox" id="orderMax" name="orderMax" value="${(memberRule.orderMax)!''}" data-options="min:0,required:true">
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>使用状态: </label>
							<@cont.select id="state" value="${(memberRule.state)!''}" codeDiv="MEMBER_RULE_STATE" mode="1"/>
						</p>
					</div>
				</dd>
			</dl>
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>帮助</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item">帮助信息。</label>
					</div>
				</dd>
			</dl>
			
			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="edit" class="btn" value="确定"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />