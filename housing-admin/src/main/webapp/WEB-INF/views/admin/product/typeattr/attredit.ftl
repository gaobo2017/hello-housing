<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/typeattr1/"/>

<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.validate.min.js"></script>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>

<script language="javascript">
    $(function () {
        var backUrl = "${currentBaseUrl}";
        var options = {
            url: '${currentBaseUrl}update',
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

        $('#value-div').hide();
        $('#type').change(function(){
            var val = $(this).val();
            if(1 == val){
                $('#value').addClass("txt w200 {required:true}");
                $('#value-div').show();
            }else{
                $('#value-div').hide();
                $('#value').removeClass();
            }

        })
        $("#back").click(function () {
            window.location.href = backUrl;
        });
        $("#add").click(function () {
            var validator = $('#addForm').validate();
            if(validator.form()){
                $('#addForm').ajaxSubmit(options);
            }
        });

        $("#selectType").click(function(){
            $('#typeDialog').dialog('open');
        });

        //初始化‘类型名称’
        $.ajax({
            type:"get",
            url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/type/getById",
            dataType: "json",
            data: "id=${(attr.productTypeId)!''}",
            cache:false,
            success:function(data, textStatus){
                if (data.success) {
                    $('#productTypeName').val(data.rows.name);
                }
            }
        });
        <#if message??>$.messager.progress('close');
        alert('${message}');</#if>
    });

    function typeCallBack(data) {
        $("#productTypeId").val(data.id);
        $("#productTypeName").val(data.name);
    }
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">商品类型属性编辑
            <span class="s-poar">
                <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>

    <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                <input type="hidden" id="id" name="id" value="${(attr.id)!''}"/>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>属性名称: </label>
                            <input type="text" id="name" name="name" value="${(attr.name)!''}"  class="txt w200 {required:true,maxlength:20}"
                                   data-options="required:true"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>属性类型: </label>
                            <@cont.select id="type" value="${(attr.type)!''}" codeDiv="ATTR_TYPE" mode="1"/>
                        </p>
                    </div>
                    <div class="fluidbox" id="value-div" style="display: none">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>属性值: </label>
                            <input type="text" id="value" name="value" value="${(attr.value)!''}"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>类型名称: </label>
                            <input type="text" name="productTypeName" id="productTypeName" readonly="true"/>
                            <input type="hidden" name="productTypeId" id="productTypeId" value="${(attr.productTypeId)!''}"/>
                            <image id="selectType" href="javascript:void(0)"  
                            	src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/easyui/themes/icons/search.png" />
                        </p>
                    </div>
                </dd>
            </dl>

            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>帮助</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <label class="lab-item">帮助信息。</label>
                    </div>
                </dd>
            </dl>

        <#--2.batch button-------------->
            <p class="p-item p-btn">
                <input type="button" id="add" class="btn" value="编辑"/>
                <input type="button" id="back" class="btn" value="返回"/>
            </p>
        </@form.form>
        </div>
    </div>
</div>

<div style="display: none">
<#include "typedialog.ftl" />
</div>

<#include "/admin/commons/_detailfooter.ftl" />