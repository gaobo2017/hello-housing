<div id="_memberbalanceopt" class="easyui-dialog popBox" title="会员余额变更" style="width:800px;height:300px;" data-options="resizable:true,closable:true,closed:true,cache: false,modal: true">
	<#--1.valueOptForm----------------->
	<div class="form-contbox">
		<@form.form method="post" class="validForm" id="balanceOptForm" name="balanceOptForm">
		<dl class="dl-group">
			<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
			<dd class="dd-group">
				<input type="reset" id="balancereset" style="display:none;" /> 
				<input type="hidden" id="balance_memberId" name="memberId" value="${(memberBalanceLogs.memberId)!''}" data-options="required:true"/>
				<input type="hidden" id="balance_memberName" name="memberName" value="${(memberBalanceLogs.memberName)!''}" data-options="required:true"/>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item">会员ID: </label>
						<label style="float:left" id="lbl_balance_memberId"></label>
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item">会员名称: </label>
						<label style="float:left" id="lbl_balance_memberName"></label>
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item">会员余额: </label>
						<label style="float:left" id="lbl_balance_balance"></label>
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item"><font class="red">*</font>具体操作: </label>
						<select id="state" value="${(memberBalanceLogs.state)!''}" class="drop" panelheight="auto" style="width:100px" name="state">
							<option value="">-- 请选择 --</option>
							<option value="5">系统增加</option>
							<option value="6">系统减少</option>
						</select>
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item"><font class="red">*</font>变更余额: </label>
						<input class="easyui-numberbox" id="money" name="money" value="${(memberBalanceLogs.money)!''}" data-options="min:0,required:true">
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item"><font class="red">*</font>描述: </label>
						<input class="txt w400 easyui-validatebox" id="remark" name="remark" value="${(memberBalanceLogs.remark)!''}" data-options="required:true,validType:['length[1,255]']">
					</p>
				</div>
			</dd>
		</dl>
		<#--2.batch button-------------->
		<p class="p-item p-btn">
			<input type="button" id="balanceOptOk" class="btn" value="确定"/>
			<input type="button" id="balanceOptCancel" class="btn" value="取消"/>
		</p>
		</@form.form>
	</div>
</div>
