<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){


	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash";
	});
	$("#add").click(function(){
		
		if($("#addForm").form('validate')){
			
			var startTime = parseInt($("#startTime").val());
			var endTime = parseInt($("#endTime").val());
			if(endTime < startTime) {
				$.messager.alert('提示','结束时间应该比开始时间大。');
				return;
			}
			
			var timeintervals = $("input[name=timeinterval]");
			if(timeintervals.length == 1) {
				var timeinterval1 = parseInt($(timeintervals[0]).val());
				if(timeinterval1 <= startTime) {
					$.messager.alert('提示','整点秒杀区间录入错误。');
					return;
				}
				if(timeinterval1 >= endTime) {
					$.messager.alert('提示','整点秒杀区间录入错误。');
					return;
				}
			}
			
			if(timeintervals.length > 1) {
				for(var i=0; i<(timeintervals.length-1); i++) {
					var timeinterval1 = parseInt($(timeintervals[i]).val());
					var timeinterval2 = parseInt($(timeintervals[i+1]).val());
					if(i == 0) {
						if(timeinterval1 <= startTime) {
							$.messager.alert('提示','整点秒杀区间录入错误。');
							return;
						}
					} 
					if(i == (timeintervals.length-2)) {
						if(timeinterval2 >= endTime) {
							$.messager.alert('提示','整点秒杀区间录入错误。');
							return;
						}
					} 
					if(timeinterval1 >= timeinterval2) {
						$.messager.alert('提示','整点秒杀区间录入错误。');
						return;
					}
				}
			}

			var channel = $("#channel").val();
			if (channel == null || channel == "") {
				$.messager.alert('提示','请选择活动应用渠道。');
				return;
			}
			
			var pcImageFile = $("#pcImageFile").val();
			var pcImage = $("#pcImage").val();
			var mobileImageFile = $("#mobileImageFile").val();
			var mobileImage = $("#mobileImage").val();
			if (channel == 1) {
				if ((pcImageFile == null || pcImageFile == "") && (pcImage == null || pcImage == "")) {
					$.messager.alert('提示','请选择PC端活动图片。');
					return;
				}
				if ((mobileImageFile == null || mobileImageFile == "") && (mobileImage == null || mobileImage == "")) {
					$.messager.alert('提示','请选择移动端活动图片。');
					return;
				}
			} else if (channel == 2) {
				if ((pcImageFile == null || pcImageFile == "") && (pcImage == null || pcImage == "")) {
					$.messager.alert('提示','请选择PC端活动图片。');
					return;
				}
			} else if (channel == 3) {
				if ((mobileImageFile == null || mobileImageFile == "") && (mobileImage == null || mobileImage == "")) {
					$.messager.alert('提示','请选择移动端活动图片。');
					return;
				}
			}
			
			var auditRule = $("#auditRule").val();
			if (auditRule == null || auditRule == "") {
				$.messager.alert('提示','请填写活动申请规则。');
				return;
			}
			
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash/update")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});
	
	$("#addStage").click(function(){
		
		var indexNum = parseInt($("#indexNum").val()) + 1;
		$("#indexNum").val(indexNum);
		
    	
		var prdHtml = '<div class="fluidbox addItemT">'
		+'	<label class="lab-item">&nbsp;</label>'
		+'	秒杀区间：'
		+'	<input class="easyui-numberbox txt w100" type="text" id="timeinterval" name="timeinterval" data-options="required:true,min:0,max:24" >'
		+'	<a class="a-del-Item" href="#"><b> —删除</b></a>'
		+'</div>'
		
   		$(".stage-addItem-box").append(prdHtml);
		$("input[type=text]").validatebox();//重新渲染验证，保证动态添加的内容可以支持easyui的验证框架
	});
	
	$(document).on('click','.a-del-Item',function () {
		$(this).parent('.addItemT').remove();
		return false;
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">修改整点秒杀<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<input type="hidden" id="id" name="id" value="${(actFlashSale.id)!''}">
			<input type="hidden" id="status" name="status" value="${(actFlashSale.status)!''}">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="actFlashSaleName" name="actFlashSaleName" value="${(actFlashSale.actFlashSaleName)!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动日期：</label>
							<input type="text" id="actDate" name="actDate"
								class="txt w200 easyui-validatebox" missingMessage="活动时间必填"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
								value="${(actFlashSale.actDate?string('yyyy-MM-dd'))!''}" readonly="readonly">
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>应用渠道：</label>
							<@cont.select id="channel" value="${(actFlashSale.channel)!''}" codeDiv="CHANNEL" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>PC图片：</label>
							<input type="file" id="pcImageFile" name="pcImageFile"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" />
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								图片最佳像素（或保持该比例）：宽1210，高可任意高度；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								用于PC端活动页展示。
								</font>
							</label>
						</p>
						<#if actFlashSale.pcImage?? >
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<img alt="图片" style="width: 192px;height: 45px;"
											src="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(actFlashSale.pcImage)!''}">
						</p>
						</#if>
						<input type="hidden" id="pcImage" name="pcImage" value="${(actFlashSale.pcImage)!''}">
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>移动图片：</label>
							<input type="file" id="mobileImageFile" name="mobileImageFile"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" />
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								图片建议像素（或保持该比例）：宽720，高350；
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								用于移动端活动页展示。
								</font>
							</label>
						</p>
						<#if actFlashSale.mobileImage?? >
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<img alt="图片" style="width: 144px;height: 70px;"
											src="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(actFlashSale.mobileImage)!''}">
						</p>
						</#if>
						<input type="hidden" id="mobileImage" name="mobileImage" value="${(actFlashSale.mobileImage)!''}">
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>秒杀阶段：</label> 
							<input type="button" value="增加阶段" id="addStage"/>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								整点秒杀阶段，必须是0到24点整数，阶段时间从小到大填写，至少有开始和结束的时间点，是必须填写的，可以在开始和结束的时间段内添加不同的阶段，
								<br/><label class="lab-item">&nbsp;</label>
								下一个阶段的开始时间是上一个阶段的结束时间。
								</font>
							</label>
						</p>
					</div>
					
					<div class="fluidbox addItemT">
							<label class="lab-item">&nbsp;</label>
							开始时间：
							<input class="easyui-numberbox txt w100" type="text" value="${startTime!}" id="startTime" name="startTime" data-options="required:true,min:0,max:24" >
					</div>
					<#if stageList??>
						<#list  stageList as stage>
							<div class="fluidbox addItemT">
									<label class="lab-item">&nbsp;</label>
									秒杀区间：
									<input class="easyui-numberbox txt w100" type="text" value="${(stage.startTime)!0}" id="timeinterval" name="timeinterval" data-options="required:true,min:0,max:24">
									<a class="a-del-Item" href="#"><b> —删除</b></a>
							</div>
						</#list>
					</#if>
					
					<div class="stage-addItem-box"></div>
					<div class="fluidbox addItemT">
							<label class="lab-item">&nbsp;</label>
							结束时间：
							<input class="easyui-numberbox txt w100" type="text" value="${endTime!}" id="endTime" name="endTime" data-options="required:true,min:0,max:24" >
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>申请规则：</label>
							<textarea name="auditRule" rows="2" cols="100" id="auditRule" class="{maxlength:500}" >${(actFlashSale.auditRule)!''}</textarea>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								用于给商家申请活动商品时的申请须知。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">活动描述：</label>
							<textarea name="remark" rows="2" cols="100" id="remark" class="{maxlength:500}" >${(actFlashSale.remark)!''}</textarea>
						</p>
					</div>
					<br/>
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="修改" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />