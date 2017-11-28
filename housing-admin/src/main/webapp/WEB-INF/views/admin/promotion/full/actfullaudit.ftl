<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">

$(function(){

	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/full";
	});
	
	$("#auditPass").click(function(){
        $.messager.confirm('提示', '确定审核通过该活动吗？', function(r){
            if (r){
                $.messager.progress({text:"提交中..."});
                $.ajax({
                    type:"POST",
                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/full/doaudit",
                    dataType: "json",
                    data: "id=${(actFull.id)!''}&status=3&auditOpinion="+$("#auditOpinion").val(),
                    cache:false,
                    success:function(data, textStatus){
                    	$.messager.progress('close');
                        if (data.success) {
                        	$.messager.alert('提示', "审核成功。");
                        	window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/full";
                        }else{
                            $.messager.alert('提示', data.message);
                        }
                    }
                });
            }
        });
    });
	
	$("#auditReject").click(function(){
		var auditOpinion = $("#auditOpinion").val();
		if (auditOpinion == null || auditOpinion == "") {
			$.messager.alert('提示', "请填写审核失败原因。");
			return;
		}
        $.messager.confirm('提示', '确定驳回该活动吗？', function(r){
            if (r){
                $.messager.progress({text:"提交中..."});
                $.ajax({
                    type:"POST",
                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/full/doaudit",
                    dataType: "json",
                    data: "id=${(actFull.id)!''}&status=4&auditOpinion="+$("#auditOpinion").val(),
                    cache:false,
                    success:function(data, textStatus){
                    	$.messager.progress('close');
                        if (data.success) {
                        	$.messager.alert('提示', "操作成功。");
                        	window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/full";
                        }else{
                            $.messager.alert('提示', data.message);
                        }
                    }
                });
            }
        });
    });

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">审核订单满减<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/full">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>满减活动名称：</label>
							<input disabled class="easyui-validatebox txt w280" type="text" id="actFullName" name="actFullName" value="${(actFull.actFullName)!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>第一档：</label>
							满
							<input disabled class="easyui-numberbox txt w100" type="text" id="firstFull" name="firstFull" value="${(actFull.firstFull)!''}" data-options="required:true,precision:2,min:1" >
							减
							<input disabled class="easyui-numberbox txt w100" type="text" id="firstDiscount" name="firstDiscount" value="${(actFull.firstDiscount)!''}" data-options="required:true,precision:2" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								满额与订单减去单品立减金额后计算出的金额相比较，如满1000减50，如果订单总额减去单品立减优惠金额后大于100，则满足规则。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">第二档：</label>
							满
							<input disabled class="easyui-numberbox txt w100" type="text" id="secondFull" name="secondFull" value="${(actFull.secondFull)!''}" data-options="precision:2" >
							减
							<input disabled class="easyui-numberbox txt w100" type="text" id="secondDiscount" name="secondDiscount" value="${(actFull.secondDiscount)!''}" data-options="precision:2" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								第二、三档可为空，为空或设定为0，表示不设定该档次
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								第二档满额必须比第一档满额金额大；同理第三档满额必须比第二档满额金额大。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">第三档：</label>
							满
							<input disabled class="easyui-numberbox txt w100" type="text" id="thirdFull" name="thirdFull" value="${(actFull.thirdFull)!''}" data-options="precision:2" >
							减
							<input disabled class="easyui-numberbox txt w100" type="text" id="thirdDiscount" name="thirdDiscount" value="${(actFull.thirdDiscount)!''}" data-options="precision:2" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动时间：</label>
							<input disabled class="easyui-validatebox txt w200" type="text" id="startTime" name="startTime" value="${(actFull.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}" data-options="required:true,validType:'length[0,100]'" >
							~
							<input disabled class="easyui-validatebox txt w200" type="text" id="endTime" name="endTime" value="${(actFull.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>应用渠道：</label>
							<@cont.select disabled="disabled" id="channel" value="${(actFull.channel)!''}" codeDiv="CHANNEL" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">活动描述：</label>
							<textarea disabled name="remark" rows="4" cols="60" id="remark" class="{maxlength:255}" >${(actFull.remark)!''}</textarea>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">审核意见：</label>
							<textarea name="auditOpinion" rows="4" cols="60" id="auditOpinion" class="{maxlength:255}" >${(actFull.auditOpinion)!''}</textarea>
						</p>
					</div>
					<br/>
					
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="auditPass" class="btn" value="审核通过" />
				<input type="button" id="auditReject" class="btn" value="审核失败" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>



<#include "/admin/commons/_detailfooter.ftl" />