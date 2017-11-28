<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/courierCompany"/>

<script language="javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/lodop/LodopFuncs.js"></script>
<script language="javascript" type="text/javascript"> 
    var LODOP; //声明为全局变量       
	function myBlankDesign() {   
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
		LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}/${(obj.imagePath)!}'>");
		<#noescape>
		${(obj.content)!''}
		</#noescape>
		LODOP.PRINT_DESIGN();
	};
</script>

<script language="javascript">
	$(function() {
		$("#back").click(function() {
			location.href = '${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/courierCompany';
		});
	})
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			设计打印模板
			<span class="s-poar">
				<a class="a-back" href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>物流公司名称:
							</label> ${(obj.companyName)!''}
						</p>
						<p class="p6 p-item">
							<label class="lab-item">物流公司代码: </label> ${(obj.companyMark)!''}
						</p>
					</div>
					<p class="p-item p-btn">
						<input type="button" onclick="myBlankDesign()" class="btn" value="设置打印单" /> 
						<input type="button" id="back" class="btn" value="返回" />
					</p>
				</dd>
			</dl>

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>帮助
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
						  打印快递单是集成Lodop，版本是：Lodop 6.198<br/>
						  Lodop 官网是：http://www.lodop.net/<br/>
						  第一次使用需要安装浏览器插件 <a href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/lodop/Lodop.zip"><b style="color:red">下载</b></a> <br/>
						  也可以去Lodop下载插件，下载地址是：http://www.lodop.net/download.html<br/>
						  
						  <b>操作步骤：</b> <br/>
						  <p>1、录入快递公司的时候，把快递公司的图片上传上去。</p>
						  <p>2、在列表页点击“设计打印单”</p>
						  <p>3、在设计打印单中根据需求录入以下信息，注意不能有空格，如图：</p>
						  <img border='0' src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/lodop/1.jpg' /><br/>
						  点击图上面三角图标，新建文本项，可以录入一下内容：<br/>
						  寄件人姓名：${r'${sendName}'} <br/>
						  寄件人电话：${r'${sendPhone}'}<br/>
						  寄件人单位：${r'${sendUnit}'}<br/>
						  寄件人省：${r'${sendProvince}'}<br/>
						  寄件人市：${r'${sendCity}'}<br/>
						  寄件人地址：${r'${sendAdds}'}<br/>
						  包裹内容：${r'${sendGoods}'}<br/>
						  商品数量：${r'${sendNumber}'}<br/>
						  
						  收件人姓名：${r'${consigneeName}'}<br/>
						  收件人电话：${r'${consigneePhone}'}<br/>
						  收件人省：${r'${consigneeProvince}'}<br/>
						  收件人市：${r'${consigneeCity}'}<br/>
						  收件人区：${r'${consigneeArea}'}<br/>
						  收件人地址：${r'${consigneeAdds}'}<br/>
						  注意：其他信息不能录入，必须按照格式，否则打印不出来信息<br/>
						   <p>4、点击图上面三角图标，生成程序代码，如下如：</p>
						    <img border='0' src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/lodop/2.jpg' /><br/>
						    其中第一行和第二行数据不能选择，其他信息复制下来，然后返回到列表页，在模板内容选项中粘贴刚才复制的内容，如下如：<br/>
						    <img border='0' src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/lodop/3.jpg' /><br/>
						    点击保存模板录入完成，可以使用了
						  <br/>
						</label>
					</div>
				</dd>
			</dl>

		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />
