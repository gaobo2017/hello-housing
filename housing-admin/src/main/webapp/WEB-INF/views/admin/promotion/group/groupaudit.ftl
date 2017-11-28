<div id="_activitybiddingaudit" class="easyui-dialog popBox" title="团购活动审核失败" style="width:500px;height:225px;" data-options="resizable:true,closable:true,closed:true,cache: false,modal: true">
	<#--1.valueOptForm----------------->
	<div class="form-contbox">
		<@form.form method="post" class="validForm" id="activitybiddingauditForm" name="activitybiddingauditForm">
		<dl class="dl-group">
			<dt class="dt-group"><span class="s-icon"></span>审核意见</dt>
			<dd class="dd-group">
				<input type="reset" id="balancereset" style="display:none;" /> 
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item"><font class="red">*</font>审核意见: </label>
						<!-- <input type="text" id="auditOpinion" name="auditOpinion" value=""  class="txt w400 {required:true, maxlength:255}" data-options="required:true"/> -->
						<textarea name="auditOpinion" rows="4" cols="40" id="auditOpinion" class="{maxlength:255}" ></textarea>
					</p>
				</div>
			</dd>
		</dl>
		<#--2.batch button-------------->
		<p class="p-item p-btn">
			<input type="button" id="auditRejectOk" class="btn" value="确定"/>
			<input type="button" id="auditRejectCancel" class="btn" value="取消"/>
		</p>
		</@form.form>
	</div>
</div>
