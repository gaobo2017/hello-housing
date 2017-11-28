<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/complaint"/>

<style>
.dl-group p img {
	max-width: 120px;
	float: left;
}

.fluidbox .p6 label a {
	font-weight: 900;
	color: #00F;
}
</style>

<link rel="stylesheet"
	href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/colorbox/colorbox.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/jquery.tooltip/jquery.tooltip.min.css"
	type="text/css"></link>
<script type="text/javascript"
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/colorbox/jquery.colorbox-min.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/jquery.tooltip/jquery.min.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/jquery.tooltip/jquery.tooltip.min.js"></script>

<script language="javascript">
	$j = jQuery.noConflict();
	$(function() {

		$j("a.creds").tooltip({
			dialog_content_selector:'div.tooltip_description',
			arrow_height:10,
		 	opacity:0.55
		});
		 
		$("#back").click(function() {
			location.href = '${currentBaseUrl}';
		});

		$("#agree").click(function() {
			$.messager.confirm('确认', '确定通过该投诉申请吗？', function(r) {
				if (r) {
					$("#stateType").val('agree');
					$.messager.progress({
						text : "提交中..."
					});
					$('#addForm').submit();
				}
			});
		});

		$("#disagree").click(function() {
			$.messager.confirm('确认', '确定驳回该投诉申请吗？', function(r) {
				if (r) {
					$("#stateType").val('disagree');
					$.messager.progress({
						text : "提交中..."
					});
					$('#addForm').submit();
				}
			});
		});

	})

	function closeWin() {
		$('#newstypeWin').window('close');
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			投诉仲裁 <span class="s-poar"> <a class="a-back"
				href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			name="addForm" enctype="multipart/form-data"
			action="${currentBaseUrl}/doAudit"> <input type="hidden"
				value="${(obj.id)!''}" name="id"> <input type="hidden"
				id="stateType" name="stateType">

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>投诉信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">投诉人账户: </label> <label>${obj.userName!}</label>
						</p>
						<p class="p6 p-item">
							<label class="lab-item">订单号: </label> <label>${obj.orderSn!}</label>
						</p>
					</div>
					
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">投诉来源: </label> <label>${obj.source!}</label>
						</p>
						<p class="p6 p-item">
							<label class="lab-item">问题描述: </label> <label>${obj.question!}</label>
						</p>
					</div>

					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">投诉商品: </label><label>${obj.orderProductName!}</label>
						</p>
						<p class="p6 p-item">
							<label class="lab-item">投诉内容: </label><label>${obj.content!}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">投诉图片: </label>
                            <#if obj.image?? && "" != obj.image>
                            <label>
                                <a href="${domainUrlUtil.EJS_IMAGE_RESOURCES}${obj.image!''}" target="_blank">查看图片</a>
							</label>
                            </#if>
						</p>
						<p class="p6 p-item">
							<label class="lab-item">投诉时间: </label><label>${obj.complaintTimeStr!}</label>
						</p>
					</div>

				</dd>
			</dl>

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>卖家信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">卖家账号: </label><label>${obj.sellerName!}</label>
						</p>
						<p class="p6 p-item">
							<label class="lab-item">卖家申诉时间: </label><label>${(obj.sellerComplaintTime?string("yyyy-MM-dd HH:mm:ss"))!""}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">卖家申拆内容: </label><label>${obj.sellerCompContent!}</label>
						</p>
						<p class="p6 p-item">
							<label class="lab-item">卖家申诉图片: </label>
                            <#if obj.sellerCompImage?? && "" != obj.sellerCompImage>
                            <label>
                                <a	href="${domainUrlUtil.EJS_IMAGE_RESOURCES}${obj.sellerCompImage!''}" target="_blank">查看图片</a>
                            </label>
                            </#if>
						</p>
					</div>

				</dd>
			</dl>

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>处理意见
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item">仲裁意见: </label>
						<textarea name="optContent" id="optContent" cols="90" rows="3">${obj.optContent!}</textarea>
					</div>

				</dd>
			</dl>

			<p class="p-item p-btn">
				<input type="button" id="agree" class="btn" value="通过审核" />
				<input type="button" id="disagree" class="btn" value="驳回" />
				<input type="button" id="back" class="btn" value="返回" />
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />
