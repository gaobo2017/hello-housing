<div id="_membervalueopt" class="easyui-dialog popBox" title="会员经验值积分变更" style="width:800px;height:300px;" data-options="resizable:true,closable:true,closed:true,cache: false,modal: true">
	<#--1.valueOptForm----------------->
	<div class="form-contbox">
		<@form.form method="post" class="validForm" id="valueOptForm" name="valueOptForm">
		<dl class="dl-group">
			<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
			<dd class="dd-group">
				<input type="reset" id="valuereset" style="display:none;" /> 
				<input type="hidden" id="value_memberId" name="memberId" value="${(memberGradeIntegralLogs.memberId)!''}" data-options="required:true"/>
				<input type="hidden" id="value_memberName" name="memberName" value="${(memberGradeIntegralLogs.memberName)!''}" data-options="required:true"/>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item">会员ID: </label>
						<label style="float:left" id="lbl_value_memberId"></label>
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item">会员名称: </label>
						<label style="float:left" id="lbl_value_memberName"></label>
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item"><font class="red">*</font>操作类型: </label>
						<@cont.select id="type" value="${(memberGradeIntegralLogs.type)!''}" codeDiv="MEMBER_GRD_INT_LOG_T" mode="1"/>
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item"><font class="red">*</font>具体操作: </label>
						<select id="optType" value="${(memberGradeIntegralLogs.optType)!''}" class="drop" panelheight="auto" style="width:100px" name="optType">
							<option value="">-- 请选择 --</option>
							<option value="5">系统增加</option>
							<option value="6">系统减少</option>
						</select>
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item"><font class="red">*</font>变更数值: </label>
						<!-- <input class="easyui-numberbox" data-options="min:0,precision:0,required:true"> -->
						<input class="easyui-numberbox" id="value" name="value" value="${(memberGradeIntegralLogs.value)!''}" data-options="min:0,required:true">
						<!-- <input type="text" id="value" name="value" value="${(memberGradeIntegralLogs.value)!''}" class="txt w100 {required:true,maxlength:11,integer:true}" data-options="required:true"/> -->
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item"><font class="red">*</font>描述: </label>
						<input class="easyui-validatebox txt w400" type="text" id="optDes" name="optDes" value="${(memberGradeIntegralLogs.optDes)!''}" data-options="required:true,validType:'length[0,255]'" >
					</p>
				</div>
			</dd>
		</dl>
		<#--2.batch button-------------->
		<p class="p-item p-btn">
			<input type="button" id="valueOptOk" class="btn" value="确定"/>
			<input type="button" id="valueOptCancel" class="btn" value="取消"/>
		</p>
		</@form.form>
	</div>
</div>
