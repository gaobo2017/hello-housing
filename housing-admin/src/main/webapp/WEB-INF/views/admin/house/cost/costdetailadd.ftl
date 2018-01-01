<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
var codeBox;
$(function(){
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("HOUSE_COST_TYPE")})');
			
		</#noescape>

	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/costdetail/manage";
	});
	

	$("#add").click(function(){
		
		$.messager.confirm('确认', '确定添加成本明细吗?', function(r){
		 if (r) {
		
				if($("#addForm").form('validate')){
			 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/costdetail/manage/create")
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
		<h2 class="h2-title">新增成本明细信息<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/costdetail/manage">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<input type="hidden" id="houseId" name="houseId" value="${(housingCostDetail.houseId)!''}">
			<input type="hidden" id="costId" name="costId" value="${(housingCostDetail.costId)!''}">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>房源信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>房源编号 ：</label>
							<input class="easyui-validatebox txt w280" type="text" id="roomCodeNo" name="roomCodeNo" value="${(housingCostDetail.roomCodeNo)!''}" data-options="required:true,validType:'length[0,20]'" >
						</p>
					</div>
					<br/>
					
				 
					
					<div class="fluidbox">
					 <p class="p12 p-item">
						<label class="lab-item"><font class="red">*</font>费用类型 :</label> 
						<@cont.select id="costType" 
						 value="${(housingCostDetail.costType)!''}" codeDiv="HOUSE_COST_TYPE" name="costType" style="width:100px" mode="1"/>
					</p>
					</div>  
					<br/>
				  
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>发生金额：</label>
							<input class="easyui-validatebox txt w280" type="text" id="money" name="money" value="${(housingCostDetail.money)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					  <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>发生时间：</label>
							<input class="easyui-datebox" type="text" id="updateTime" name="updateTime" value="${(housingCostDetail.updateTime?string("yyyy-MM-dd"))!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
				    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>备注：</label>
							<input class="easyui-validatebox txt w280" type="text" id="remark" name="remark" value="${(housingCostDetail.remark)!''}" data-options="required:true,validType:'length[0,20]'" >
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