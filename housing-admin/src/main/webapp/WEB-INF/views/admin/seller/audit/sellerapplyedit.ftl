<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){


	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/audit";
	});
	$("#edit").click(function(){

		var companyProvince = $("#companyProvince").val();
		var companyCity = $("#companyCity").val();
		if (companyProvince == null || companyProvince == "" || companyCity == null || companyCity == "") {
			$.messager.alert('提示','请选择公司所在地！');
			return false;
		}
		
		var bankProvince = $("#bankProvince").val();
		var bankCity = $("#bankCity").val();
		if (bankProvince == null || bankProvince == "" || bankCity == null || bankCity == "") {
			$.messager.alert('提示','请选择开户行所在地！');
			return false;
		}
		
		if($("#addForm").form('validate')){
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/audit/update")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});
	
	$("#companyProvince").change(function(){
        getRegion($("#companyCity"), $(this).val(), "");
    });

	$("#bankProvince").change(function(){
        getRegion($("#bankCity"), $(this).val(), "");
    });

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

	function getRegion(_select, _parentId, _selectId) {
	    _select.empty();
	    $.ajax({
	        type:"get",
	        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/regions/getRegionByParentId",
	        dataType: "json",
	        data: {parentId: _parentId},
	        cache:false,
	        success:function(data){
	            if (data.success) {
	                _select.empty();
	                var selectOption = '<option value ="">-- 请选择 --</option>'
	                $.each(data.data, function(i, region){
	                    if (_selectId == region.id) {
	                        selectOption += "<option selected='true' value=" + region.id + ">" + region.regionName + "</option>";
	                    } else {
	                        selectOption += "<option value=" + region.id + ">" + region.regionName + "</option>";
	                    }
	                })
	                _select.append(selectOption);
	            } else {

	            }
	        }
	    });
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">修改商家申请<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/audit">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<input type="hidden" id="id" name="id" value="${(sellerApply.id)!''}">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>公司及联系人信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>公司名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="company" name="company" value="${(sellerApply.company)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>公司所在地：</label>
							<select class="" id="companyProvince" name="companyProvince">
								<option value="">-- 请选择 --</option>
								<#if provinceList ??>
		               			<#list provinceList as region>
		                   			<option <#if sellerApply?? && sellerApply.companyProvince?? && sellerApply.companyProvince == (region.id)?string >selected='true'</#if> value="${(region.id)!''}">${(region.regionName)!''}</option>
		               			</#list>
		           				</#if>
		       				</select>
		       				<select class="" id="companyCity" name="companyCity">
								<option value="">-- 请选择 --</option>
								<#if companyCityList ??>
		                 		<#list companyCityList as region>
		                     		<option <#if sellerApply?? && sellerApply.companyCity?? && sellerApply.companyCity == (region.id)?string >selected='true'</#if> value="${(region.id)!''}">${(region.regionName)!''}</option>
		               			</#list>
		             			</#if>
		         			</select>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>公司详细地址：</label>
							<input class="easyui-validatebox txt w280" type="text" id="companyAdd" name="companyAdd" value="${(sellerApply.companyAdd)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>公司电话：</label>
							<input class="easyui-validatebox txt w280" type="text" id="telephone" name="telephone" value="${(sellerApply.telephone)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">传真：</label>
							<input class="easyui-validatebox txt w280" type="text" id="fax" name="fax" value="${(sellerApply.fax)!''}" data-options="validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>法定代表人：</label>
							<input class="easyui-validatebox txt w280" type="text" id="legalPerson" name="legalPerson" value="${(sellerApply.legalPerson)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>联系人电话：</label>
							<input class="easyui-validatebox txt w280" type="text" id="personPhone" name="personPhone" value="${(sellerApply.personPhone)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>邮箱：</label>
							<input class="easyui-validatebox txt w280" type="email" id="email" name="email" value="${(sellerApply.email)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
				</dd>
			</dl>

			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>营业执照信息（副本）</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>营业执照号：</label>
							<input class="easyui-validatebox txt w280" type="text" id="bussinessLicense" name="bussinessLicense" value="${(sellerApply.bussinessLicense)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>组织机构代码：</label>
							<input class="easyui-validatebox txt w280" type="text" id="organization" name="organization" value="${(sellerApply.organization)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<label class="lab-item"><font class="red">*</font>营业日期：</label>
						<input type="text" id="companyStartTime" name="companyStartTime"
								class="txt w200 easyui-validatebox" missingMessage="开始时间必填"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'companyEndTime\')}'});"
								value="${(sellerApply.companyStartTime?string('yyyy-MM-dd'))!''}" readonly="readonly">
						~
						<input type="text" id="companyEndTime" name="companyEndTime"
								class="txt w200 easyui-validatebox" missingMessage="结束时间必填"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'companyStartTime\')}'});"
								value="${(sellerApply.companyEndTime?string('yyyy-MM-dd'))!''}" readonly="readonly">
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">营业执照扫描件：</label>
							<input type="file" id="up_bussinessLicenseImage" name="up_bussinessLicenseImage"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox"/>
							<input type="hidden" id="bussinessLicenseImage" name="bussinessLicenseImage" value="${(sellerApply.bussinessLicenseImage)!''}">
							
							<a href="${domainUrlUtil.EJS_IMAGE_RESOURCES}/${(sellerApply.bussinessLicenseImage)!''}" target="_blank">点击查看</a>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: #808080">
							请确保图片清晰，文字可辨并有清晰的红色公章。
							</font>
						</p>
					</div>
					<br/>
				</dd>
			</dl>
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>一般纳税人证明</dt>
				<dd class="dd-group">
					<!-- <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: red">
							注：所属企业具有一般纳税人证明时，此项为必填。
							</font>
						</p>
					</div>
					<br/> -->
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>法定代表人身份证：</label>
							<input class="easyui-validatebox txt w280" type="text" id="legalPersonCard" name="legalPersonCard" value="${(sellerApply.legalPersonCard)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">身份证正面图片：</label>
							<input type="file" id="up_personCardUp" name="up_personCardUp"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" />
							<input type="hidden" id="personCardUp" name="personCardUp" value="${(sellerApply.personCardUp)!''}">
							<a href="${domainUrlUtil.EJS_IMAGE_RESOURCES}/${(sellerApply.personCardUp)!''}" target="_blank">点击查看</a>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">身份证背面图片：</label>
							<input type="file" id="up_personCardDown" name="up_personCardDown"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" />
							<input type="hidden" id="personCardDown" name="personCardDown" value="${(sellerApply.personCardDown)!''}">
							
							<a href="${domainUrlUtil.EJS_IMAGE_RESOURCES}/${(sellerApply.personCardDown)!''}" target="_blank">点击查看</a>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: #808080">
							请确保图片清晰。
							</font>
						</p>
					</div>
					<br/>
				</dd>
			</dl>
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>开户银行信息(此账号为结算账号)</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>开户行账号名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="bankUser" name="bankUser" value="${(sellerApply.bankUser)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>开户行：</label>
							<input class="easyui-validatebox txt w280" type="text" id="bankName" name="bankName" value="${(sellerApply.bankName)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>开户行支行名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="bankNameBranch" name="bankNameBranch" value="${(sellerApply.bankNameBranch)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>开户行支行联行号：</label>
							<input class="easyui-validatebox txt w280" type="text" id="brandNameCode" name="brandNameCode" value="${(sellerApply.brandNameCode)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>银行账号：</label>
							<input class="easyui-validatebox txt w280" type="text" id="bankCode" name="bankCode" value="${(sellerApply.bankCode)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>开户行所在地：</label>
							<select class="" id="bankProvince" name="bankProvince">
								<option value="">-- 请选择 --</option>
								<#if provinceList ??>
		               			<#list provinceList as region>
		                   			<option <#if sellerApply?? && sellerApply.bankProvince?? && sellerApply.bankProvince == (region.id)?string >selected='true'</#if> value="${(region.id)!''}">${(region.regionName)!''}</option>
		               			</#list>
		           				</#if>
		       				</select>
		       				<select class="" id="bankCity" name="bankCity">
								<option value="">-- 请选择 --</option>
								<#if bankCityList ??>
		                 		<#list bankCityList as region>
		                     		<option <#if sellerApply?? && sellerApply.bankCity?? && sellerApply.bankCity == (region.id)?string >selected='true'</#if> value="${(region.id)!''}">${(region.regionName)!''}</option>
		               			</#list>
		             			</#if>
		         			</select>
						</p>
					</div>
					<br/>
				</dd>
			</dl>
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>其他信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>税务登记证号：</label>
							<input class="easyui-validatebox txt w280" type="text" id="taxLicense" name="taxLicense" value="${(sellerApply.taxLicense)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>商家账号：</label>
							<input class="easyui-validatebox txt w280" type="text" id="userName" name="userName" value="${(userName)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: #808080">
							此账号为日后登录并管理商家中心时使用，密码默认123456，请及时通知商家修改此密码。
							</font>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>店铺名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="sellerName" name="sellerName" value="${(sellerName)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: #808080">
							店铺名称注册后不可修改，请认真填写。
							</font>
						</p>
					</div>
					<br/>
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="edit" class="btn" value="修改" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>



<#include "/admin/commons/_detailfooter.ftl" />