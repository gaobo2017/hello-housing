<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/admin/product/brand/"/>
<script language="javascript">
    $(function () {
        var backUrl = "${domainUrlUtil.EJS_URL_RESOURCES}${currentBaseUrl}";
        var sucOptions = {
            url: '${domainUrlUtil.EJS_URL_RESOURCES}${currentBaseUrl}auditing?id=${brand.id}&status=2',
            type: 'post',
            success: function(data){
                if (data && null != data.success && data.success == true) {
                    window.location.href = backUrl;
                } else {
                    refrushCSRFToken(data.csrfToken);
                    $.messager.alert('提示',data.message);
                }
            }
        };
        var errOptions = {
            url: '${domainUrlUtil.EJS_URL_RESOURCES}${currentBaseUrl}auditing?id=${brand.id}&status=3',
            type: 'post',
            success: function(data){
                if (data && null != data.success && data.success == true) {
                    window.location.href = backUrl;
                } else {
                    refrushCSRFToken(data.csrfToken);
                    $.messager.alert('提示',data.message);
                }
            }
        };
        //审核成功
        $("#success").click(function () {
            $.messager.progress({text:"提交中..."});
            $.ajax({
                type:"POST",
                url: '${domainUrlUtil.EJS_URL_RESOURCES}${currentBaseUrl}auditing',
                dataType: "json",
                data: "id=${brand.id}&status=2"+"&"+getCSRFTokenParam(),
                cache:false,
                success:function(data){
                    $.messager.progress('close');
                    if (data && null != data.success && data.success == true) {
                        $.messager.alert('提示',"提交成功");
                        window.location.href = "${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/brand/todo";
                    } else {
                        refrushCSRFToken(data.csrfToken);
                        $.messager.alert('提示',data.message);
                    }
                }
            });
        });
        //审核失败
        $("#error").click(function () {
            $.messager.progress({text:"提交中..."});
            $.ajax({
                type:"POST",
                url: '${domainUrlUtil.EJS_URL_RESOURCES}${currentBaseUrl}auditing',
                dataType: "json",
                data: "id=${brand.id}&status=3"+"&"+getCSRFTokenParam(),
                cache:false,
                success:function(data){
                    $.messager.progress('close');
                    if (data && null != data.success && data.success == true) {
                        $.messager.alert('提示',"提交成功");
                        window.location.href = "${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/brand/todo";
                    } else {
                        refrushCSRFToken(data.csrfToken);
                        $.messager.alert('提示',data.message);
                    }
                }
            });
        });
        <#if message??>$.messager.progress('close');alert('${message}');</#if>
    });
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">品牌编辑
            <span class="s-poar">
                <a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/brand/todo">返回</a>
            </span>
        </h2>

    <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                <input type="hidden" name="id" value="${(brand.id)!''}"/>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>品牌名称: </label>
                            <input type="text" id="name" name="name" value="${(brand.name)!''}"
                                   class="txt w200 {required:true,maxlength:20}" readonly="true"
                                   style="background: #E0E0E0"
                                   data-options="required:true"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>首字母: </label>
                            <input type="text" id="nameFirst" name="nameFirst" value="${(brand.nameFirst)!''}"
                                   class="txt w200 {required:true,maxlength:20}" readonly="true"
                                   style="background: #E0E0E0"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>品牌图片: </label>
                            <#if brand.image?? && "" != brand.image>
                                <img src="${domainUrlUtil.EJS_IMAGE_RESOURCES}/${(brand.image)}"/>
                            </#if>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>品牌说明: </label>
                            <textarea id="explainInfo" name="explainInfo" rows="8" cols="60" class="required"
                                      readonly="true" style="background: #E0E0E0">${(brand.explainInfo)!''}</textarea>
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
                <input type="button" id="success" class="btn" value="通过"/>
                <input type="button" id="error" class="btn" value="不通过"/>
            </p>
        </@form.form>
        </div>
    </div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />