<#include "/admin/commons/_detailheader.ftl" />

<#include "/admin/commons/_echartsheader.ftl" />

<script>
	$(function(){
		$("#doSearch").click(function(){
			if(!$("#startTime").val()){
				$.messager.alert('提示', '请选择开始时间');
				return;
			}
			if(!$("#endTime").val()){
				$.messager.alert('提示', '请选择结束时间');
				return;
			}
			$("#queryForm").submit();
		});
	});
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;">
	<h2 class="h2-title">
		订单概况 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search"
				action="${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/orders/orderOverview"
				method="get" id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item-cho">开始时间:</label> <input
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'});"
							type="text" class="txt" id="startTime" name="startTime"
							value="${startTime}" />
					</p>
					<p class="p4 p-item left15">
						<label class="lab-item-cho">结束时间:</label> <input
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'});"
							type="text" class="txt" id="endTime" name="endTime"
							value="${endTime}" />
					</p>
					<p class="p-item p4 report-query-p">
						<input type="button" id="doSearch" class="btn" value="提交" />
					</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false">
	<div id="chartdiv" style="width: 100%; height: 400px;"></div>
</div>
<div data-options="region:'south'" style="height:100px;">
	<dl class="dl-group">
		<dt class="dt-group">
			<span class="s-icon"></span>帮助
		</dt>
		<dd class="dd-group">
			<div class="fluidbox">
				<label class="lab-item help">
					订单概况展示平台所有商家在一段时间内的订单状态分布情况，例如：待确认的订单，已完成的订单等。 </label>
			</div>
		</dd>
	</dl>

</div>

<#include "/admin/commons/_detailfooter.ftl" />
