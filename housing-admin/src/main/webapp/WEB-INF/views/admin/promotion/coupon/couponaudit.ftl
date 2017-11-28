<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">

$(function(){

	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/coupon";
	});

	$("#auditPass").click(function(){
        $.messager.confirm('提示', '确定审核通过该优惠券吗？', function(r){
            if (r){
                $.messager.progress({text:"提交中..."});
                $.ajax({
                    type:"POST",
                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/coupon/doaudit",
                    dataType: "json",
                    data: "id=${(coupon.id)!''}&status=3&auditOpinion="+$("#auditOpinion").val(),
                    cache:false,
                    success:function(data, textStatus){
                    	$.messager.progress('close');
                        if (data.success) {
                        	$.messager.alert('提示', "审核成功。");
                        	window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/coupon";
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
        $.messager.confirm('提示', '确定驳回该优惠券吗？', function(r){
            if (r){
                $.messager.progress({text:"提交中..."});
                $.ajax({
                    type:"POST",
                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/coupon/doaudit",
                    dataType: "json",
                    data: "id=${(coupon.id)!''}&status=4&auditOpinion="+$("#auditOpinion").val(),
                    cache:false,
                    success:function(data, textStatus){
                    	$.messager.progress('close');
                        if (data.success) {
                        	$.messager.alert('提示', "操作成功。");
                        	window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/coupon";
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
		<h2 class="h2-title">审核优惠券<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/coupon">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>优惠券名称：</label>
							<input disabled class="easyui-validatebox txt w280" type="text" id="couponName" name="couponName" value="${(coupon.couponName)!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>优惠券前缀：</label>
							<input disabled class="easyui-validatebox txt w280" type="text" id="prefix" name="prefix" value="${(coupon.prefix)!''}" data-options="required:true,validType:'length[4,4]'" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								用于生成优惠券序列号，固定4位长度，只能是A-Z的英文字母组成。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>优惠券面值：</label>
							<input disabled class="easyui-numberbox txt w280" type="text" id="couponValue" name="couponValue" value="${(coupon.couponValue)!''}" data-options="required:true,precision:2" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								优惠券抵用现金金额。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>适用的最低订单金额：</label>
							<input disabled class="easyui-numberbox txt w280" type="text" id="minAmount" name="minAmount" value="${(coupon.minAmount)!''}" data-options="required:true,precision:2" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								优惠券适用的订单最低金额，该金额是在订单扣除单品立减活动、订单满减活动后的订单金额。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>发放时间：</label>
							<input disabled class="easyui-validatebox txt w200" type="text" id="sendStartTime" name="sendStartTime" value="${(coupon.sendStartTime?string('yyyy-MM-dd HH:mm:ss'))!''}" data-options="required:true,validType:'length[0,100]'" >
							~
							<input disabled class="easyui-validatebox txt w200" type="text" id="sendEndTime" name="sendEndTime" value="${(coupon.sendEndTime?string('yyyy-MM-dd HH:mm:ss'))!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>使用时间：</label>
							<input disabled class="easyui-validatebox txt w200" type="text" id="useStartTime" name="useStartTime" value="${(coupon.useStartTime?string('yyyy-MM-dd HH:mm:ss'))!''}" data-options="required:true,validType:'length[0,100]'" >
							~
							<input disabled class="easyui-validatebox txt w200" type="text" id="useEndTime" name="useEndTime" value="${(coupon.useEndTime?string('yyyy-MM-dd HH:mm:ss'))!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								使用开始时间开始必须比发放开始时间晚，使用结束时间必须比发放结束时间晚。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>会员限制领取数量：</label>
							<input disabled class="easyui-numberbox txt w280" type="text" id="personLimitNum" name="personLimitNum" value="${(coupon.personLimitNum)!''}" data-options="required:true" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								每个会员限制领取的数量，0为不限。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>总数量：</label>
							<input disabled class="easyui-numberbox txt w280" type="text" id="totalLimitNum" name="totalLimitNum" value="${(coupon.totalLimitNum)!''}" data-options="required:true,min:1" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								总共预计发放的总数量，至少1张。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>优惠券类型：</label>
							<@cont.select disabled="disabled" id="type" value="${(coupon.type)!''}" codeDiv="COUPON_TYPE" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>应用渠道：</label>
							<@cont.select disabled="disabled" id="channel" value="${(coupon.channel)!''}" codeDiv="CHANNEL" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">优惠券描述：</label>
							<textarea disabled name="remark" rows="4" cols="60" id="remark" class="{maxlength:255}" >${(coupon.remark)!''}</textarea>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">审核意见：</label>
							<textarea name="auditOpinion" rows="4" cols="60" id="auditOpinion" class="{maxlength:255}" >${(coupon.auditOpinion)!''}</textarea>
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