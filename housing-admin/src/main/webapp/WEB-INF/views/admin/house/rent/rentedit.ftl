<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
var codeBox;
$(function(){
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("HOUSE_COST_TYPE")})');
			
		</#noescape>

	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/rent/manage";
	});
	
	$("#update").click(function(){
		
		$.messager.confirm('确认', '确定要修改租房记录吗,收入会重新计算?', function(r){
		 if (r) {
		
				if($("#addForm").form('validate')){
			 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/rent/manage/update")
		  				 .attr("method", "POST")
		  				 .submit();
		  		   }		 
		   } 
	    
		});

	});
	
    

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})


</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">新增租房信息<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/rent/manage">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<input type="hidden" id="id" name="id" value="${(housingLease.id)!''}">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>租房信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>房源编号 ：</label>
							<input class="easyui-validatebox txt w280" type="text" id="roomCodeNo" name="roomCodeNo" value="${(housingLease.roomCodeNo)!''}" data-options="required:true,validType:'length[0,20]'" >
						</p>
					</div>
					<br/>
					 <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>房源id ：</label>
							<input class="easyui-validatebox txt w280" type="text" id="houseId" name="houseId" value="${(housingLease.houseId)!''}" data-options="required:true,validType:'length[0,20]'" >
						</p>
					</div>
					<br/>

			       <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>租赁开始时间</label>
							<input class="easyui-datebox" type="text" id="leaseStartTime" name="leaseStartTime" value="${(housingLease.leaseStartTime?string("yyyy-MM-dd"))!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
			       <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>租赁结束时间</label>
							<input class="easyui-datebox" type="text" id="leaseEndTime" name="leaseEndTime" value="${(housingLease.leaseEndTime?string("yyyy-MM-dd"))!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>月租：</label>
							<input class="easyui-validatebox txt w280" type="text" id="rent" name="rent" value="${(housingLease.rent)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>								
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>合同租金总额：</label>
							<input class="easyui-validatebox txt w280" type="text" id="allRent" name="allRent" value="${(housingLease.allRent)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
					 <p class="p12 p-item">
						<label class="lab-item"><font class="red">*</font>交租方式 :</label> 
						<@cont.select id="payWay" 
						 value="${(housingLease.payWay)!''}" codeDiv="HOUSE_PAY_WAY" name="payWay" style="width:100px" mode="1"/>
					</p>
					</div>  
					<br/>								
				    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>租赁合同号：</label>
							<input class="easyui-validatebox txt w280" type="text" id="contract" name="contract" value="${(housingLease.contract)!''}" data-options="validType:'length[0,20]'" >
						</p>
					</div>
					<br/>
				    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>备注：</label>
							<input class="easyui-validatebox txt w280" type="text" id="remark" name="remark" value="${(housingLease.remark)!''}" data-options="validType:'length[0,20]'" >
						</p>
					</div>
					<br/>									
				    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red"></font>销售员：</label>
							<input class="easyui-validatebox txt w280" type="text" id="seller" name="seller" value="${(housingLease.seller)!''}" data-options="validType:'length[0,20]'" >
						</p>
					</div>
					<br/>					
			
					
															
					
			
				</dd>
			</dl>

			
			
			
			
			

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="update" class="btn" value="修改" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>



<#include "/admin/commons/_detailfooter.ftl" />