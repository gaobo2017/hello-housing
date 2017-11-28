<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">
$(function(){
	$("#edit").click(function(){
		if($("#addForm").form('validate')){
			$.ajax({
				type:"POST",
				url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/config/update",
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
		<h2 class="h2-title">系统配置</h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<input type="hidden" id="id" name="id" value="${(config.id)!''}" data-options="required:true"/>
                    
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>积分换算比例: </label>
                            <!-- <input type="text" id="integralScale" name="integralScale" value="${(config.integralScale)!}" class="txt w200 easyui-validatebox" data-options="required:true,validType:'length[8,12]'"/> -->
                            <input type="text" id="integralScale" name="integralScale" value="${(config.integralScale)!''}" class="txt w200 easyui-numberbox" data-options="min:1,max:99999,precision:0,required:true"/>
                        </p>
                    </div>
                    
				</dd>
			</dl>
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>帮助</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>用户下单使用积分时换算成人民币的比例，如填100表示100个积分当做1元钱使用。</label>
						</p>
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