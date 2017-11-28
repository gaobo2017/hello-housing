<#-- 下拉框:mode：""全部，1请选择 -->
<#macro select id codeDiv name="" value="" mode="" class="drop" style="" disabled="">
	<#if name == "">
		<#local localName=id/>
	<#else>
		<#local localName=name/>
	</#if>
	<select id="${id}" name="${localName}" class="${class}" style="${style}" panelHeight="auto" ${disabled}>
		<#if mode == "">
			<option value="">-- 全部 --</option>
		<#elseif mode == "1">
			<option value="">-- 请选择 --</option>
		<#else>
			
		</#if>
		<#if codeManager.codeMap[codeDiv]?? && codeManager.codeMap[codeDiv]?size &gt; 0>
			<#list codeManager.codeMap[codeDiv] as code>
				<option value="${code.codeCd}" <#if code.codeCd == value> selected </#if>>${code.codeText!''}</option>
			</#list>
		</#if>
	</select>
</#macro>

<#-- 单选钮  -->
<#macro radio id name="" value="" codeDiv="" class="rad" isDefault = "true" >
	<#if name == ""> 	
		<#local localName=id/>
	<#else>
		<#local localName=name/>
	</#if>
	<#if codeManager.codeMap[codeDiv]?? && codeManager.codeMap[codeDiv]?size &gt; 0>
		<#list codeManager.codeMap[codeDiv] as code>
			<input type="radio" id="${id}" name="${localName}" class="${class}" value="${code.codeCd}" <#if code.codeCd == value  || (isDefault == 'true' && value == '' && code_index == 0)> checked </#if> /> ${code.codeText}
		</#list>	
	</#if>
</#macro>


<#--只读radio-->
<#macro radioonly id name="" value="" codeDiv="" class="rad"  >
	<#if name == "">
		<#local localName=id/>
	<#else>
		<#local localName=name/>
	</#if>
	<#list codeManager.codeMap[codeDiv] as code>
		<input type="radio" id="${id}" name="${localName}" class="${class}" value="${code.codeCd}" <#if code.codeCd == value || value == '' && code_index == 0> checked </#if>  disabled=""/> ${code.codeText}
	</#list>	
</#macro>
<#-- 复选钮  -->
<#macro checkbox id name="" value=[] codeDiv="">
	<#if name == "">
		<#local localName=id/>
	<#else>
		<#local localName=name/>
	</#if>
	<#list codeManager.codeMap[codeDiv] as code>
		<#local checked=""/>
		<#list value as val>
			<#if val == code.codeCd>
				<#local checked="checked"/>
				<#break>
			</#if>	
		</#list>
		<input type="checkbox" id="${id}" name="${localName}" value="${code.codeCd}" class="chk" ${checked} /> ${code.codeText}
	</#list>	
</#macro>

<#-- 日历控件  -->
<#macro calendar id name="" value="">
	<#if name == "">
		<#local localName=id/>
	<#else>
		<#local localName=name/>
	</#if>
	<input type="text" id="${id}" name="${localName}" value="${value}" class="Wdate" /> 
</#macro>