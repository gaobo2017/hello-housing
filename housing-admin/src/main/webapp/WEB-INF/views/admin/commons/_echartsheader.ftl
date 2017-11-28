<script
	src='${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js'></script>
<script
	src='${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/echarts/theme.js'></script>
<script
	src='${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/echarts/echarts.js'></script>

<script>
	
	$(function(){
		//注册主题
		var theme = new EchartsTheme();
		echarts.registerTheme("infographic", theme.getTheme("infographic"));
		echarts.registerTheme("macarons", theme.getTheme("macarons"));
		echarts.registerTheme("blue", theme.getTheme("blue"));
		echarts.registerTheme("macarons2", theme.getTheme("macarons2"));
		echarts.registerTheme("red", theme.getTheme("red"));
		echarts.registerTheme("sakura", theme.getTheme("sakura"));
		echarts.registerTheme("helianthus", theme.getTheme("helianthus"));
		echarts.registerTheme("mint", theme.getTheme("mint"));
		echarts.registerTheme("roma", theme.getTheme("roma"));
		
		//eharts初始化
		var dom_ = <#if chartDom??>$("#${chartDom}")[0]<#else>$("#chartdiv")[0]</#if>;
		if(!dom_)
			throw new Error("没有指定图表dom");
		var myChart = echarts.init($("#chartdiv")[0]<#if theme??>,'${theme}'</#if>);
		// 使用刚指定的配置项和数据显示图表。
		<#if option??>
		<#noescape>
	    myChart.setOption(eval(${option}));
		</#noescape>
		</#if>
		
		//动态条件
		<#if model??&&model=='month'>
		$("#year").parent('p').hide();
		$("#month").parent('p').show();
		</#if>
		$(":radio").click(function(){
			if($(this).attr('id')=='r_year'){
				$("#year").parent('p').show();
				$("#month").parent('p').hide();
			} else{
				$("#year").parent('p').hide();
				$("#month").parent('p').show();
			}
		});
		$("#byseller").change(function(){
			if($(this).is(":checked")){
				$("#sel_seller").show();
			} else{
				$("#sel_seller").hide();
			}
		});
	});
</script>