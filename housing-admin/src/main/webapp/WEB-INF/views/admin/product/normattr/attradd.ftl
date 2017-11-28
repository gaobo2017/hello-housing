<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/normattr1/"/>
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
	             $('#addForm').ajaxSubmit(options);
	       	}
        });

        $("#selectNorm").click(function(){
            $('#normDialog').dialog('open');
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
        <h2 class="h2-title">规格属性新增
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
                            <label class="lab-item"><font class="red">*</font>规格属性名称: </label>
                            <input type="text" id="name" name="name" value="${(attr.name)!''}"  class="txt w200 easyui-validatebox"
                                   data-options="required:true"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>规格名称: </label>
                            <input type="text" name="normName" id="normName" readonly="true"/>
                            <input type="hidden" name="productNormId" id="productNormId"/>
                            <img id="selectNorm" href="javascript:void(0)"  
                            	src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/easyui/themes/icons/search.png" />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>顺序位: </label>
                            <input type="text" id="sort" name="sort" value="${(attr.sort)!''}" class="txt w200 easyui-validatebox" 
                           	 data-options="required:true"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">默认图片: </label>
                            <input type="text" id="image" name="image" value="${(attr.image)!''}" class="txt w200"/>
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

<div style="display: none">
<#include "normdialog.ftl" />
</div>

<#include "/admin/commons/_detailfooter.ftl" />