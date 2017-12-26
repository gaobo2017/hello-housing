<style>
    .ra_div a {
        display: block;
        border-bottom: dotted 1px #dedede;
        padding-bottom: 3px;
        text-decoration: none;
        color: #000;
        font-weight: normal;
        padding-top: 5px;
        padding-left: 15px;
    }
</style>
<div id="aa" class="easyui-accordion" data-options="fit:true"
     style="height: auto;">

    <@shiro.hasPermission name="/admin_menu_house">
	<div title="房源管理" class="ra_div">
		<@shiro.hasPermission name="/admin/house/manage">
		<a id='599' href="javascript:void(0);" onclick="addTab('房源列表', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/house/manage')">房源列表</a>
		</@shiro.hasPermission>
	    <@shiro.hasPermission name="/admin/house/manage/add">
		<a id='598' href="javascript:void(0);" onclick="addTab('新增房源', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/house/manage/add')">新增房源</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	
	    <@shiro.hasPermission name="/admin_menu_cost">
	<div title="成本管理" class="ra_div">
		<@shiro.hasPermission name="/admin/cost/manage">
		<a id='601' href="javascript:void(0);" onclick="addTab('房源总成本列表', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/cost/manage')">房源总成本列表</a>
		</@shiro.hasPermission>
	    <@shiro.hasPermission name="/admin/cost/manage/add">
		<a id='602' href="javascript:void(0);" onclick="addTab('新增XXX详情', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/cost/manage/add')">新增成本详情</a>
		</@shiro.hasPermission>
	    <@shiro.hasPermission name="/admin/costdetail/manage">
		<a id='602' href="javascript:void(0);" onclick="addTab('成本明细列表', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/costdetail/manage')">成本明细列表</a>
		</@shiro.hasPermission>
		
	    <@shiro.hasPermission name="/admin/cost/manage">
		<a id='603' href="javascript:void(0);" onclick="addTab('空置期列表', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/cost/manage/add')">空置期列表</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	
	    <@shiro.hasPermission name="/admin_menu_rent">
	<div title="租房管理" class="ra_div">

		<@shiro.hasPermission name="/admin/rent/manage">
		<a id='604' href="javascript:void(0);" onclick="addTab('租房列表', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/rent/manage')">租房列表</a>
		</@shiro.hasPermission>
	    <@shiro.hasPermission name="/admin/rent/manage/reminder">
		<a id='605' href="javascript:void(0);" onclick="addTab('收租退房提醒', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/rent/manage/reminder')">收租退房提醒</a>
		</@shiro.hasPermission>
	    <@shiro.hasPermission name="/admin/income/manage">
		<a id='607' href="javascript:void(0);" onclick="addTab('房源收入列表', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/income/manage')">房源收入列表</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	

	
	
	<@shiro.hasPermission name="/admin_menu_product">
	<div title="商品管理" class="ra_div">
		<@shiro.hasPermission name="/admin/product/brand">
		<a id='301' href="javascript:void(0);" onclick="addTab('品牌管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/brand')">品牌管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/norm">
		<a id='302' href="javascript:void(0);" onclick="addTab('规格管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/norm')">规格管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/type">
		<a id='303' href="javascript:void(0);" onclick="addTab('类型管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/type')">类型管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/cate">
		<a id='304' href="javascript:void(0);" onclick="addTab('分类管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/cate')">分类管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/brand/todo">
		<a id='305' href="javascript:void(0);" onclick="addTab('待审核品牌', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/brand/todo')">待审核品牌</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/cate/audit">
		<a id='306' href="javascript:void(0);" onclick="addTab('商家分类申请', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/cate/audit')">商家分类申请</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/waitSale">
		<a id='308' href="javascript:void(0);" onclick="addTab('待售商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/waitSale')">待售商品</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/onSale">
		<a id='309' href="javascript:void(0);" onclick="addTab('在售商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/onSale')">在售商品</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/delSale">
		<a id='310' href="javascript:void(0);" onclick="addTab('已删除商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/delSale')">已删除商品</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_order">
	<div title="交易管理" class="ra_div">
		<@shiro.hasPermission name="/admin/order/orders">
		<a id='336' href="javascript:void(0);" onclick="addTab('全部订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders')">全部订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/state1">
		<a id='337' href="javascript:void(0);" onclick="addTab('未付款订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state1')">未付款订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/state2">
		<a id='338' href="javascript:void(0);" onclick="addTab('待确认订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state2')">待确认订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/state3">
		<a id='339' href="javascript:void(0);" onclick="addTab('待发货订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state3')">待发货订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/state4">
		<a id='340' href="javascript:void(0);" onclick="addTab('已发货订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state4')">已发货订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/state5">
		<a id='341' href="javascript:void(0);" onclick="addTab('已完成订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state5')">已完成订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/state6">
		<a id='342' href="javascript:void(0);" onclick="addTab('已取消订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state6')">已取消订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/productBack">
		<a id='343' href="javascript:void(0);" onclick="addTab('退货管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/productBack')">退货管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/productExchange">
		<a id='344' href="javascript:void(0);" onclick="addTab('换货管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/productExchange')">换货管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/complaint">
		<a id='62' href="javascript:void(0);" onclick="addTab('投诉管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/complaint')">投诉管理</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_promotion">
	<div title="促销管理" class="ra_div">
		<@shiro.hasPermission name="/admin/promotion/coupon">
		<a href="javascript:void(0);" onclick="addTab('优惠券管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/coupon')">优惠券管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/full">
		<a href="javascript:void(0);" onclick="addTab('订单满减', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/full')">订单满减</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/single">
		<a href="javascript:void(0);" onclick="addTab('单品立减', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/single')">单品立减</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/flash">
		<a href="javascript:void(0);" onclick="addTab('限时抢购', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/flash')">限时抢购</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/grouptype">
		<a href="javascript:void(0);" onclick="addTab('团购分类', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/grouptype')">团购分类</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/promotion/actgroup">
		<a href="javascript:void(0);" onclick="addTab('团购管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/actgroup')">团购管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/actgroupbanner/banner">
		<a href="javascript:void(0);" onclick="addTab('团购首页图片', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/actgroupbanner/banner')">团购首页图片</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_member">
	<div title="会员管理" class="ra_div">
		<@shiro.hasPermission name="/admin/member/member">
		<a id='66' href="javascript:void(0);" onclick="addTab('会员管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/member')">会员管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/config/gradevalue">
		<a id='69' href="javascript:void(0);" onclick="addTab('会员经验规则管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/config/gradevalue')">会员经验规则管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/config/intvalue">
		<a id='70' href="javascript:void(0);" onclick="addTab('会员积分规则管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/config/intvalue')">会员积分规则管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/config/grade">
		<a id='71' href="javascript:void(0);" onclick="addTab('会员等级配置', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/config/grade')">会员等级配置</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/drawmoney">
		<a id='72' href="javascript:void(0);" onclick="addTab('会员提款申请', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/drawmoney')">会员提款申请</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/productcomments">
		<a id='150' href="javascript:void(0);" onclick="addTab('会员商品评价管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/productcomments')">会员商品评价管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/productask">
		<a id='151' href="javascript:void(0);" onclick="addTab('会员商品咨询管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/productask')">会员商品咨询管理</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_seller">
	<div title="商家管理" class="ra_div">
		<@shiro.hasPermission name="/admin/seller/audit">
		<a id='67' href="javascript:void(0);" onclick="addTab('商家申请', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/audit')">商家申请</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/seller/manage">
		<a id='68' href="javascript:void(0);" onclick="addTab('商家管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/manage')">商家管理</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_mobile">
	<div title="移动端管理" class="ra_div">
		<@shiro.hasPermission name="/admin/mindex/banner">
		<a id='404' href="javascript:void(0);" onclick="addTab('首页轮播图', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/mindex/banner')">首页轮播图</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/mindex/floor">
		<a id='408' href="javascript:void(0);" onclick="addTab('移动端首页楼层', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/mindex/floor')">移动端首页楼层</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/mindex/floordata">
		<a id='412' href="javascript:void(0);" onclick="addTab('楼层数据', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/mindex/floordata')">楼层数据</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_pcindex">
	<div title="PC端首页管理" class="ra_div">
		<@shiro.hasPermission name="/admin/pcindex/banner">
		<a id='445' href="javascript:void(0);" onclick="addTab('PC首页轮播图', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/banner')">PC首页轮播图</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/recommend">
		<a id='452' href="javascript:void(0);" onclick="addTab('PC推荐商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/recommend')">PC推荐商品</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floor">
		<a id='459' href="javascript:void(0);" onclick="addTab('PC首页楼层', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floor')">PC首页楼层</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floorclass">
		<a id='466' href="javascript:void(0);" onclick="addTab('PC首页楼层分类', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorclass')">PC首页楼层分类</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floordata">
		<a id='474' href="javascript:void(0);" onclick="addTab('PC首页楼层分类数据', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floordata')">PC首页楼层分类数据</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floorpatch">
		<a id='478' href="javascript:void(0);" onclick="addTab('PC首页楼层碎屑', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorpatch')">PC首页楼层碎屑</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_operate">
	<div title="运营管理" class="ra_div">
		<@shiro.hasPermission name="/admin/operate/courierCompany">
		<a id='352' href="javascript:void(0);" onclick="addTab('物流公司', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/courierCompany')">物流公司</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/config">
		<a id='383' href="javascript:void(0);" onclick="addTab('配置管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/config')">配置管理</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_settlement">
	<div title="统计结算" class="ra_div">
		<@shiro.hasPermission name="/admin/settlement">
		<a id='347' href="javascript:void(0);" onclick="addTab('结算管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/settlement')">结算管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/orderday">
		<a id='364' href="javascript:void(0);" onclick="addTab('每日订单统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/orderday')">每日订单统计</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/productday">
		<a id='365' href="javascript:void(0);" onclick="addTab('每日商品统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/productday')">每日商品统计</a>
		</@shiro.hasPermission>
		
		<@shiro.hasPermission name="/admin/report/orders/orderOverview">
		<a href="javascript:void(0);" onclick="addTab('订单概况', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/orders/orderOverview')">订单概况</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/product/productSale">
		<a href="javascript:void(0);" onclick="addTab('商品销量统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/product/productSale')">商品销量统计</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/orders/saleOverview">
		<a href="javascript:void(0);" onclick="addTab('订单销量统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/orders/saleOverview')">订单销量统计</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/product/phurchaseRate">
		<a href="javascript:void(0);" onclick="addTab('购买率统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/product/phurchaseRate')">购买率统计</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/product/pvStatistics">
		<a href="javascript:void(0);" onclick="addTab('商品浏览量统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/product/pvStatistics')">商品浏览量统计</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/orders/goodsReturnRate">
		<a href="javascript:void(0);" onclick="addTab('退货率统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/orders/goodsReturnRate')">退货率统计</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/orders/CPIstatistics">
		<a href="javascript:void(0);" onclick="addTab('人均消费统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/orders/CPIstatistics')">人均消费统计</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_search">
	<div title="搜索管理" class="ra_div">
		<@shiro.hasPermission name="/admin/searchlogs">
		<a id='570' href="javascript:void(0);" onclick="addTab('搜索词历史记录', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/searchlogs')">搜索词历史记录</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/searchIndexes">
		<a id='368' href="javascript:void(0);" onclick="addTab('索引初始化', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/searchIndexes')">索引初始化</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/search/keyword">
		<a id='368' href="javascript:void(0);" onclick="addTab('关键词设置', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/search/keyword')">关键词设置</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/search/keywordfilter">
		<a id='370' href="javascript:void(0);" onclick="addTab('敏感词过滤', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/search/keywordfilter')">敏感词过滤</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/searchrecord">
		<a id='370' href="javascript:void(0);" onclick="addTab('模糊搜索词', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/searchrecord')">模糊搜索词</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/searchkeyword">
		<a id='384' href="javascript:void(0);" onclick="addTab('敏感词', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/searchkeyword')">敏感词</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_system">
	<div title="系统管理" class="ra_div">
		<@shiro.hasPermission name="/admin/system/code">
		<a id='55' href="javascript:void(0);" onclick="addTab('数据字典', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/code')">数据字典</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/role">
		<a id='56' href="javascript:void(0);" onclick="addTab('角色管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/role')">角色管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/resource">
		<a id='57' href="javascript:void(0);" onclick="addTab('资源管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/resource')">资源管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/adminuser">
		<a id='82' href="javascript:void(0);" onclick="addTab('管理员管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/adminuser')">管理员管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/adminuser/editpwd">
		<a id='399' href="javascript:void(0);" onclick="addTab('修改密码', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/adminuser/editpwd')">修改密码</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_news">
	<div title="网站文章" class="ra_div">
		<@shiro.hasPermission name="/admin/system/newstype">
		<a id='63' href="javascript:void(0);" onclick="addTab('文章分类', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/newstype')">文章分类</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/news">
		<a id='64' href="javascript:void(0);" onclick="addTab('文章管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/news')">文章管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/newsParter">
		<a id='65' href="javascript:void(0);" onclick="addTab('合作伙伴管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/newsParter')">合作伙伴管理</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>

</div>


