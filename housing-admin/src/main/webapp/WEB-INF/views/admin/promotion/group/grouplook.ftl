<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">
$(function(){
	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/actgroup";
	});
})
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">团购查看<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/actgroup">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>团购分类：</label>
							<select name="type" disabled="disabled">
								<#if actGroupTypes ??>
									<#list actGroupTypes as actGroupType>
							  			<option value="${actGroupType.id}" <#if actGroupType.id==actGroup.type>selected</#if> >${actGroupType.name}</option>
							  		</#list>
							  	</#if>
							</select>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>所属商家：</label>
							${(actGroup.sellerName)!}
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font><a target="_blank" href="${(domainUrlUtil.EJS_FRONT_URL)!}/product/${(actGroup.productId)!}.html">商品(点击查看)：</a></label>
							<a target="_blank" href="${(domainUrlUtil.EJS_FRONT_URL)!}/product/${(actGroup.productId)!}.html">${(actGroup.productName)!}</a>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>促销标题：</label>
							${(actGroup.name)!}
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>渠道：</label>
							<@cont.select id="channel" name="channel" codeDiv="CHANNEL" value="${(actGroup.channel)!}" mode="2" disabled="disabled"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动图片：</label>
							<#if (actGroup.image)??> 
								&nbsp;&nbsp;&nbsp;&nbsp;<a href="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(actGroup.image)!''}" target="_blank">查看图片</a>
							</#if>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>市场价: </label>
                           ${(actGroup.marketPrice)!''}
                        </p>
                    </div>
                    <br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>团购价: </label>
                            ${(actGroup.price)!''}
                        </p>
                    </div>
                     <br/>
                    
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>库存：</label>
							${(actGroup.stock)!''}
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<label class="lab-item"><font class="red">*</font>活动时间：</label>
						${(actGroup.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}
						~
						${(actGroup.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}
					</div>
					<br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>活动描述: </label>
                        <div style="padding-left: 140px;padding-top: 2px;">
                        	<#noescape>
                        	${(actGroup.descinfo)!}
                        	</#noescape>
                        </div>
                        </p>
                    </div>
					<br/>
				</dd>
			</dl>

		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />