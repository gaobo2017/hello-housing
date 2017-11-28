<#include "/admin/commons/_detailheader.ftl" />

<#include "/admin/commons/_echartsheader.ftl" />

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;">
	<h2 class="h2-title">
		购买率统计 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox report">
		<div class="w-p99 marauto searchCont">
			<form class="form-search"
				action="${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/product/phurchaseRate"
				method="get" id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item sat-condition">
						<label class="lab-item-cho"> <input type="radio"
							name="model" value="year"<#if
							!model??||model=='year'>checked="checked"</#if> id="r_year"/>按年份
						</label> <label class="lab-item-cho"> <input type="radio"
							name="model" value="month" id="r_month"<#if
							model??&&model=='month'>checked="checked"</#if>/>按月份
						</label>
					</p>
				</div>
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item-cho">年份:</label> <input
							onclick="WdatePicker({dateFmt:'yyyy'});" type="text" class="txt"
							id="year" name="year" value="${currentYear}" />
					</p>
					<p class="p4 p-item" style="display: none">
						<label class="lab-item-cho">月份:</label> <input
							onclick="WdatePicker({dateFmt:'MM'});" type="text" class="txt"
							id="month" name="month" value="${currentMonth}" />
					</p>
					<p class="p-item p4">
						<input type="submit" id="doSearch" class="btn" value="提交" />
					</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false">
	<div id="chartdiv" style="width: 100%; height: 407px;"></div>
</div>
<div data-options="region:'south'" style="height:100px;">
	<dl class="dl-group">
		<dt class="dt-group">
			<span class="s-icon"></span>帮助
		</dt>
		<dd class="dd-group">
			<div class="fluidbox">
				<label class="lab-item help"> 购买率统计展示网店在一段时间内的有效订单数与这些订单相关的总访客数的比值；
					可以按照年度和月份为单位，分别进行查询 。 </label>
			</div>
			<div class="fluidbox">
				<label class="lab-item help"> 按月查询时，数据为当前年、所选月份的具体订单及销售情况 </label>
			</div>
		</dd>
	</dl>
</div>

<#include "/admin/commons/_detailfooter.ftl" />
