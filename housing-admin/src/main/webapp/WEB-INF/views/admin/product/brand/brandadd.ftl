<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/brand/"/>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>

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
                    $.messager.alert('提示',data.message);
                }
            }
        };
        $("#back").click(function () {
            window.location.href = backUrl;
        });
        $("#add").click(function () {
            if($('#addForm').form('validate')){
                $('#addForm').ajaxSubmit(options);
            }
        });
        <#if message??>$.messager.progress('close');
         $.messager.alert('提示','${message}');</#if>
    })
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">品牌新增
            <span class="s-poar">
                <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>

        <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>品牌名称: </label>
                            <input type="text" id="name" name="name" value="${(brand.name)!''}" class="txt w200 easyui-validatebox" missingMessage="品牌名称必填，2-20个字符" data-options="required:true,validType:'length[2,20]'"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>首字母: </label>
                            <input type="text" id="nameFirst" name="nameFirst" value="${(brand.nameFirst)!''}" class="txt w200 easyui-validatebox" missingMessage="首字母必填，1个字符" data-options="required:true,validType:'length[1,1]'"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>品牌图片: </label>
                            <input type="file" id="imageFile" name="imageFile" class="txt w240 easyui-validatebox" data-options="required:true"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>顺序位: </label>
                            <input type="text" id="sort" name="sort" value="${(brand.sort)!''}" class="txt w200 easyui-numberbox" data-options="min:1,max:200,required:true" missingMessage="顺序位必填，1-200"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>是否推荐: </label>
                            <@cont.radio id="top" value="${(brand.top)!''}" codeDiv="BRAND_TOP" />
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
                <input type="button" id="add" class="btn" value="新增"/>
                <input type="button" id="back" class="btn" value="返回"/>
            </p>
        </@form.form>
        </div>
    </div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />