<#include "/admin/commons/_detailheader.ftl" />

<#include "/admin/commons/_echartsheader.ftl" />

<script language="JavaScript">
	String.prototype.getLength = function() {
		var len = 0;
		for (var i = 0; i < this.length; i++) {
			if (this.charCodeAt(i) > 127 || this.charCodeAt(i) == 94) {
				len += 2;
			} else {
				len++;
			}
		}
		return len;
	}

	String.prototype.subString = function(end) {
		return this.substring(0, end) + '...';
	}

	$(function() {
		$("#selPro").click(function() {
			$('#productDialog').dialog('open');
		});
		
		$("#proid").change(function(){
			$("#queryForm").submit();
		});
		
	});

	function productCallBack(data) {
		$("#proid").val(data.id);
		var name = data.name1;
		var subname = name.getLength() > 63 ? name.subString(63) : name;
		$("#proName").html(subname);
		$("#proName").attr('title',name);
		$("#hd_proName").val(subname);
	}
	
	function doSearch(){
		var proid = $("#proid").val();
		if(!proid){
			$.messager.alert('提示','请选择商品');
			return;			
		}
		$("#queryForm").submit();
	}
</script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;">
	<h2 class="h2-title">
		商品销量统计 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox report">
		<div class="w-p99 marauto searchCont">
			<form class="form-search"
				action="${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/product/productSale"
				method="get" id="queryForm" name="queryForm">
				
				<input type="hidden" name="productId" id="proid" value="${productId!}"/>
				<input type="hidden" name="proName" id="hd_proName" value="${proName!}"/>
				
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
						<label class="lab-item-cho">商品：</label>
						<i id="proName" style="cursor: default;color: rgb(80, 94, 99);">${proName!'请选择'}</i>
					</p>
					<p class="p-item p4">
						<input type="button" id="selPro" class="btn" value="选择商品" />
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
						<input type="button" class="btn" value="提交" onclick="doSearch();"/>
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
				<label class="lab-item help"> 商品销量统计展示网店在一段时间内所选商品对应的货品的销售量，
					可以按照年度和月份为单位，分别进行查询 。 </label>
			</div>
			<div class="fluidbox">
				<label class="lab-item help"> 按月查询时，数据为当前年、所选月份的具体订单及销售情况 </label>
			</div>
		</dd>
	</dl>
</div>

<#include "incproductcss.ftl" />
<#include "productdialog.ftl" />
<#include "/admin/commons/_detailfooter.ftl" />
