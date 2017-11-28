<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/type/"/>

<style>
	iframe .panel-fit, .panel-fit body {
	    overflow: scroll;
	}
</style>

<script language="javascript">
    $(function () {
        var backUrl = "${currentBaseUrl}";
        var options = {
            url: '${currentBaseUrl}create',
            type: 'post',
            success: function (data) {
                if (data && null != data.success && data.success == true) {
                    window.location.href=backUrl;
                }else if(data.backUrl != null){
                    alert(data.message);
                    window.location.href=data.backUrl;
                }else{
                    refrushCSRFToken(data.csrfToken);
                    alert(data.message);
                }
            }
        };
        $("#back").click(function () {
            window.location.href = backUrl;
        });
        $("#add").click(function () {
            if($('#addForm').form('validate')){
                //将属性构造成json字符串
                var divs = $('.attr');
                var attr = '';
                var json = '[{';
                //检索属性
                if(divs && divs.length > 0){
                    for(var i = 0; i < divs.length; i++){
                        var name = $(divs[i]).find('[name=attrName]').val();
                        var val = $(divs[i]).find('[name=attrValue]').val();
                        json += '"name":"' + name + '","type":"1","val":"' + val + '"},{';
                    }
                }
                //自定义属性
                var custDivs = $('.custAttr');
                if(custDivs && custDivs.length > 0){
                    for(var i = 0; i < custDivs.length; i++){
                        var name = $(custDivs[i]).find('[name=attrName]').val();
                        json += '"name":"' + name + '","type":"2","val":""},{';
                    }
                }
                json = json.substring(0, json.lastIndexOf(','));
                json += "]";
                if("]" != json){
                    $('#attrVal').val(json);
                }
                $('#addForm').ajaxSubmit(options);
            }
        });

        $("#selectNorm").click(function(){
            $('#normDialog').dialog('open');
        });
        $('#normName').click(function(){
            $('#normDialog').dialog('open');
        })

        $('#selectBrand').click(function(){
            $('#brandDlg').dialog('open');
        });
        $('#brandName').click(function(){
            $('#brandDlg').dialog('open');
        });

        var attrCont = 0;
        var custCont = 0;
        //检索属性删除
        $('.delAttr').live("click", function(){
            if(attrCont <= 1){
                $.messager.alert('提示','请至少保留一条检索属性。');
                return;
            }
            attrCont--;
            $(this).parent().parent().remove();
        });
        //自定义属性删除
        $('.delCustAttr').live("click", function(){
            if(custCont <= 1){
                $.messager.alert('提示','请至少保留一条自定义属性。');
                return;
            }
            custCont--;
            $(this).parent().parent().remove();
        });

        //新增一个属性 检索属性
        var addAttrHtml = "<div class=\"fluidbox attr\">" + $('[class=addAttr]').parent().parent().prev().html() + " </div>";
        $('.addAttr').click(function(){
            $(this).parent().parent().before(addAttrHtml);
            $("input[type=text]").validatebox();
            attrCont++;
        });

        //新增一个属性 自定义属性
        var addCustAttrHtml = "<div class=\"fluidbox custAttr\">" + $('[class=addCustAttr]').parent().parent().prev().html() + " </div>";
        $('.addCustAttr').click(function(){
            $(this).parent().parent().before(addCustAttrHtml);
            $("input[type=text]").validatebox();
            custCont++;
        });

        //属性类型修改
        $('[name=attrType]').live('change',function(){
            var val = $(this).val();
            if(1 == val){
                $('#value').addClass("txt w200 {required:true}");
                $(this).parent().next().show();
            }else{
                $(this).parent().next().hide();
                $('#value').removeClass();
            }
        });
        <#if message??>$.messager.progress('close');alert('${message}');</#if>
    });

    function normCallBack(data) {
        //重新选中清空
        $('#productNormId').val('');
        $('#normName').val('');
        if(data && data.length > 2) {
            $.messager.alert('提示','最多只能关联两个规格。');
        }else{
            var normNames = '';
            var normIds = '';
            $.each(data, function(j, n){
                normIds += n.id + ',';
                normNames += n.name + ',';
            })
            if(normIds.length > 0){
                normIds = normIds.substring(0, normIds.lastIndexOf(","));
                $('#productNormIds').val(normIds);
            }
            if(normNames.length > 0){
                normNames = normNames.substring(0, normNames.lastIndexOf(","));
                $('#normName').val(normNames);
            }
        }
    }

    function brandCallBack(data){
        $('#brandId').val('');
        $('#brandName').val('');
        if(data && data.length > 0){
            var brandNames = '';
            var brandIds = '';
            $.each(data, function(j, n){
                brandIds += n.id + ',';
                brandNames += n.name + ',';
            })
            if(brandIds.length > 0){
                brandIds = brandIds.substring(0, brandIds.lastIndexOf(","));
                $('#productBrandIds').val(brandIds);
            }
            if(brandNames.length > 0){
                brandNames = brandNames.substring(0, brandNames.lastIndexOf(","));
                $('#brandName').val(brandNames);
            }
        }
    }
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">商品类型新增
            <span class="s-poar">
                <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>

        <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>类型名称: </label>
                            <input type="text" id="name" name="name" value="${(type.name)!''}"  class="txt w200 easyui-validatebox" missingMessage="类型名称必填，2-20个字符" data-options="required:true,validType:'length[2,20]'"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">关联规格: </label>
                            <input type="text" name="normName" id="normName" readonly="true" class="txt w200" data-options="required:false,validType:'length[1,200]'"/>
                            <input type="hidden" name="productNormIds" id="productNormIds"/>
                            <image id="selectNorm" href="javascript:void(0)"  
                            	src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/easyui/themes/icons/search.png" />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>关联品牌: </label>
                            <input type="text" name="brandName" id="brandName" readonly="true" class="txt w200 easyui-validatebox" missingMessage="关联品牌必填，请选择" data-options="required:true,validType:'length[1,200]'"/>
                            <input type="hidden" name="productBrandIds" id="productBrandIds"/>
                            <image id="selectBrand" href="javascript:void(0)"  
                            	src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/easyui/themes/icons/search.png" />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>顺序位: </label>
                            <input type="text" id="sort" name="sort" value="${(type.sort)!''}" class="txt w200 easyui-numberbox" data-options="min:1,max:999,required:true" missingMessage="顺序位必填，1-999"/>
                        </p>
                    </div>
                </dd>
            </dl>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>检索属性</dt>
                <input type="hidden" id="attrVal" name="attrVal"/>
                <dd class="dd-group">
                    <div class="fluidbox attr">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>属性名称: </label>
                            <input type="text" name="attrName" value="${(attr.name)!''}"  class="txt w200 easyui-validatebox" missingMessage="属性名称必填，2-20个字符" data-options="required:true,validType:'length[2,20]'"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>属性值: </label>
                            <input type="text" name="attrValue" value="${(attr.value)!''}" class="txt w200 easyui-validatebox" missingMessage="属性值必填，1-10000个字符" data-options="required:true,validType:'length[1,10000]'"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="javascript:void(0);" style="color: #2A7AD2;" class="delAttr">删除</a>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <a href="javascript:void(0);" class="addAttr"><img src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/images/newclass.jpg" style="padding-left: 78px"/>新增一个属性</a>
                        </p>
                    </div>
                </dd>
            </dl>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>自定义属性</dt>
                <dd class="dd-group">
                    <div class="fluidbox custAttr">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>属性名称: </label>
                            <input type="text" name="attrName" value="${(attr.name)!''}"  class="txt w200 easyui-validatebox" missingMessage="属性名称必填，2-20个字符" data-options="required:true,validType:'length[2,20]'"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="javascript:void(0);" style="color: #2A7AD2;" class="delCustAttr">删除</a>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <a href="javascript:void(0);" class="addCustAttr"><img src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/images/newclass.jpg" style="padding-left: 78px"/>新增一个属性</a>
                        </p>
                    </div>
                </dd>
            </dl>

            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>提示</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <label>&nbsp;&nbsp;&nbsp;检索属性值如果为多个,请用半角逗号分隔</label><br/>
                        <br/><br/>
                    </div>
                </dd>
            </dl>

            <#--2.batch button-------------->
            <p class="p-item p-btn">
                <input type="button" id="add" class="btn" value="新增"/>
                <input type="button" id="back" class="btn" value="返回"/>
            </p>
        </@form.form>
        </div>
    </div>
</div>

<div style="display: none">
<#include "normdialog.ftl" />
</div>
<div style="display: none">
<#include "branddialog.ftl"/>
</div>

<#include "/admin/commons/_detailfooter.ftl" />