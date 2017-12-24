<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){


	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/house/manage";
	});
	$("#add").click(function(){



		if($("#addForm").form('validate')){
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/house/manage/create")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});
	


	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})


</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">新增房源信息<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/house/manage">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>房源信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>房源编号 ：</label>
							<input class="easyui-validatebox txt w280" type="text" id="roomCodeNo" name="roomCodeNo" value="${(housingResources.roomCodeNo)!''}" data-options="required:true,validType:'length[0,20]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>房源名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="houseName" name="houseName" value="${(housingResources.houseName)!''}" data-options="required:true,validType:'length[0,20]'" >
						</p>
					</div>
					<br/>
		 <!--
				    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>户型：</label>
							<input class="easyui-validatebox txt w280" type="text" id="houseType" name="houseType" value="${(housingResources.houseType)!''}" data-options="required:true,validType:'length[0,20]'" >
						</p>
					</div>
					<br/>

				    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>朝向：</label>
							<input class="easyui-validatebox txt w280" type="text" id="toward" name="toward" value="${(housingResources.toward)!''}" data-options="validType:'length[0,20]'" >
						</p>
					</div>
					<br/>
					
				    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>房号：</label>
							<input class="easyui-validatebox txt w280" type="text" id="roomCode" name="roomCode" value="${(housingResources.roomCode)!''}" data-options="validType:'length[0,20]'" >
						</p>
					</div>
					<br/>
				    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>楼层：</label>
							<input class="easyui-validatebox txt w280" type="text" id="floor" name="floor" value="${(housingResources.floor)!''}" data-options="required:true,validType:'length[0,20]'" >
						</p>
					</div>
					<br/>
			-->
				    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>房子简介：</label>
							<input class="easyui-validatebox txt w280" type="text" id="houseAddress" name="houseAddress" value="${(housingResources.houseAddress)!''}" data-options="validType:'length[0,20]'" >
						</p>
					</div>
					<br/>
					
					
															
					
			
				</dd>
			</dl>

			
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>租房信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>合同开始时间：</label>
							
							<input class="easyui-datebox" type="text" id="contractStartTime" name="contractStartTime" value="${(housingResources.contractStartTime?string("yyyy-MM-dd"))!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>合同结束时间：</label>
							<input class="easyui-datebox" type="text" id="contractEndTime" name="contractEndTime" value="${(housingResources.contractEndTime?string("yyyy-MM-dd"))!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>每月价格：</label>
							<input class="easyui-validatebox txt w280" type="text" id="monthlyRent" name="monthlyRent" value="${(housingResources.monthlyRent)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>房源总价：</label>
							<input class="easyui-validatebox txt w280" type="text" id="pricesSum" name="pricesSum" value="${(housingResources.pricesSum)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>收房时间：</label>
							<input class="easyui-datebox" type="text" id="gainTime" name="gainTime" value="${(housingResources.gainTime?string("yyyy-MM-dd"))!''}" data-options="required:true,validType:'length[0,50]'" >
							
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>销售人员：</label>
							<input class="easyui-validatebox txt w280" type="text" id="seller" name="seller" value="${(housingResources.seller)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					
					
				</dd>
			</dl>
			
			

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="新增" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>



<#include "/admin/commons/_detailfooter.ftl" />