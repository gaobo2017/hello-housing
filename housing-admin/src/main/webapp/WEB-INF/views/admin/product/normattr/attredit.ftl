<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/normattr/"/>
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
        $("#back").click(function () {
            window.location.href = backUrl;
        });
        $("#add").click(function () {
            var validator = $('#addForm').validate();
            if(validator.form()){
                $('#addForm').ajaxSubmit(options);
            }
        });

        $("#selectNorm").click(function(){
            $('#normDialog').dialog('open');
        });

        //初始化规格名称
        $.ajax({
            type:"get",
            url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/norm/getById",
            dataType: "json",
            data: "id=${(attr.productNormId)!''}",
            cache:false,
            success:function(data, textStatus){
                if (data.success) {
                    $('#normName').val(data.rows.name);
                }
            }
        });
        <#if message??>$.messager.progress('close');
        alert('${message}');</#if>
    });

    function normCallBack(data) {
        $("#productNormId").val(data.id);
        $("#normName").val(data.name);
    }
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">规格属性编辑
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
                            <label class="lab-item"><font class="red">*</font>规格属性名称: </label>
                            <input type="text" id="name" name="name" value="${(attr.name)!''}"  class="txt w200 {required:true,maxlength:20}"
                                   data-options="required:true"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>规格名称: </label>
                            <input type="text" name="normName" id="normName" readonly="true"/>
                            <input type="hidden" name="productNormId" id="productNormId" value="${(attr.productNormId)!''}"/>
                            <image id="selectNorm" href="javascript:void(0)"  
                            	src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/easyui/themes/icons/search.png" />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>顺序位: </label>
                            <input type="text" id="sort" name="sort" value="${(attr.sort)!''}" class="txt w200 {required:true,integer:true}" />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>默认图片: </label>
                            <input type="text" id="image" name="image" value="${(attr.image)!''}" class="txt w200 {required:true,integer:true}" />
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
<#include "normdialog.ftl" />
</div>

<#include "/admin/commons/_detailfooter.ftl" />