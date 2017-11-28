<#include "/admin/commons/_detailheader.ftl" />

<link rel="stylesheet"
	href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/colorbox/colorbox.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/jquery.tooltip/jquery.tooltip.css"
	type="text/css"></link>
<script type="text/javascript"
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/colorbox/jquery.colorbox-min.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/jquery.tooltip/jquery.tooltip.js"></script>

<style>
	.fluidbox{
		width: 50%;
		overflow: hidden;
		padding: 3px 0px;
		float: left;
	}
	
	.formbox-a .fluidbox a{
		color: #957E84;
		text-decoration: none;
		font-weight: 600;
		font-size: 13px;
	}
	
	.jquery-gdakram-tooltip div.content{
		background-color: #671329;
		width: 280px;
		min-height: 15px;
		float: left;
		padding: 10px;
		font-size: 15px;
		font-weight: 900;
	}
	
</style>

<script language="javascript">
	$j = jQuery.noConflict();
	$(function() {
		$.ajax({
			url : '${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/regions/getArea.html?areaid=${app.companyCity}',
			success : function(e) {
				$("#companAdd").html(eval(e));
			}
		});
		
		 $j("a.creds").tooltip({
			 dialog_content_selector:'div.tooltip_description',
			 arrow_height:10,
			 opacity:0.55
		 });
		
	});
	
	function audit(){
		var state = Number("${app.state}");
 		if(state != 1 && state != 4){
 			$.messager.alert('提示','该申请已经审核通过，请勿重复操作。');
			return;
 		}
		$.messager.confirm('确认', '确定审核通过吗？', function(r){
			if (r){
				$.messager.progress({text:"提交中..."});
				$.ajax({
					type:"GET",
				    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/audit/pass?id=${app.id}",
					success:function(data, textStatus){
						if (data.success) {
							$.messager.alert('提示','修改成功。');
							$.messager.progress('close');
							location.href='${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/audit';
						} else {
							$.messager.alert("提示",data.message);
							$.messager.progress('close');
						}
						//$('#dataGrid').datagrid('reload');
						
					}
				});
		    }
		});
	}
	
	function reject(){
		var state = Number("${app.state}");
 		if(state != 1){
 			$.messager.alert('提示','只能驳回提交申请状态的申请。');
			return;
 		}
		$.messager.confirm('确认', '确定驳回该商家的申请吗？驳回后该商家将无法再登录', function(r){
			if (r){
				$.messager.progress({text:"提交中..."});
				$.ajax({
					type:"GET",
				    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/audit/reject?id=${app.id}",
					success:function(data, textStatus){
						if (data.success) {
							$.messager.alert('提示','修改成功。');
							$.messager.progress('close');
							location.href='${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/audit';
						} else {
							$.messager.alert("提示",data.message);
							$.messager.progress('close');
						}
					}
				});
		    }
		});
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">商家申请</h2>

		<div class="form-contbox">
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					<input type="hidden" id="id" name="id"
						value="${(memberRule.id)!''}" data-options="required:true" />
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">公司名称： </label> <label>${app.company!}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">公司所在地： </label> <label id="companAdd"></label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">公司详细地址： </label> <label>${app.companyAdd!}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">公司电话： </label> <label>${app.telephone!}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">传真： </label> <label>${app.fax!}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">联系人电话： </label> <label>${app.personPhone!}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">电子邮箱： </label> <label>${app.email!}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">法定代表人： </label> <label>${app.legalPerson!}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">营业执照号： </label> <label>${app.bussinessLicense!}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">组织机构代码： </label> <label>${app.organization!}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">营业开始时间： </label> <label>${app.companyStartTime?string("yyyy-MM-dd")!}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">营业结束： </label> <label>${app.companyEndTime?string("yyyy-MM-dd")!}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">税务登记号： </label> <label>${app.taxLicense!}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">证
							件： </label> 
								<label>
	                                <a	href="${domainUrlUtil.EJS_IMAGE_RESOURCES}${app.bussinessLicenseImage!''}" target="_blank">营业执照扫描件</a>
	                            </label>
								<label>
	                                <a	href="${domainUrlUtil.EJS_IMAGE_RESOURCES}${app.personCardUp!''}" target="_blank">身份证正面扫描件</a>
	                            </label>
								<label>
	                                <a	href="${domainUrlUtil.EJS_IMAGE_RESOURCES}${app.personCardDown!''}" target="_blank">身份证背面扫描件</a>
	                            </label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">申请状态： </label> 
							<label>
                                <#if app?? && app.state?? >
                                	<#if app.state == 1 >
                                		提交申请
                                	<#elseif app.state == 2 >
                                		审核通过
                                	<#elseif app.state == 3 >
                                		缴纳保证金
                                	<#elseif app.state == 4 >
                                		审核失败
                                	</#if>
                                </#if>
                            </label>
						</p>
					</div>
				</dd>
			</dl>

			<p class="p-item p-btn">
				<input type="button" id="send" class="btn" onclick="audit();" value="审核通过" />
				<input type="button" id="fail" class="btn" onclick="reject();" value="驳回" />
				<input type="button" id="back" class="btn" onclick="location.href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/seller/audit'" value="返回" />
			</p>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />
