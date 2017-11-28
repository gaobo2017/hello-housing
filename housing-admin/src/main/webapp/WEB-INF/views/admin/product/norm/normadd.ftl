<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/norm/"/>
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
                var divs = $('.attr');
                var attr = '';
                if(divs && divs.length > 0){
                    for(var i = 0; i < divs.length; i++){
                        var name = $(divs[i]).find('[name=attrName]').val();
                        var sort = $(divs[i]).find('[name=attrSort]').val();
                        //var image = $(divs[i]).find('[name=attrImage]').val();
                        attr += name + ',' + sort + ';';
                    }
                }
                attr = attr.substring(0, attr.lastIndexOf(';'));
                $('#attrVal').val(attr);
                $('#addForm').ajaxSubmit(options);
            }
        });

        $('.delAttr').live("click", function(){
            $(this).parent().parent().remove();
        });
        $('.addAttr').click(function(){
            var htmlStr ="<div class=\"fluidbox attr\">" + $(this).parent().parent().prev().html() +"</div>";
            $(this).parent().parent().before(htmlStr);
            $("input[type=text]").validatebox();//重新渲染验证，保证动态添加的内容可以支持easyui的验证框架
        });

        <#if message??>$.messager.progress('close');alert('${message}');</#if>
    })
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">规格新增
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
                            <label class="lab-item"><font class="red">*</font>规格名称: </label>
                            <input type="text" id="name" name="name" value="${(norm.name)!''}"  class="txt w200 easyui-validatebox" missingMessage="规格名称必填，2-20个字符" data-options="required:true,validType:'length[2,20]'"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>顺序位: </label>
                            <input type="text" id="sort" name="sort" value="${(norm.sort)!''}" class="txt w200 easyui-numberbox" data-options="min:1,max:999,required:true" missingMessage="顺序位必填，1-999"/>
                        </p>
                    </div>
                </dd>
            </dl>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>新增规格值</dt>
                <input type="hidden" name="attrVal" id="attrVal"/>
                <dd class="dd-group">
                    <div class="fluidbox attr">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>规格值: </label>
                            <input type="text" name="attrName" value="${(attr.name)!''}"  class="txt w200 easyui-validatebox" missingMessage="规格值必填，1-20个字符" data-options="required:true,validType:'length[1,20]'"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>顺序位: </label>
                            <input type="text" name="attrSort" value="${(attr.sort)!''}" class="txt w200 easyui-numberbox" data-options="min:1,max:999,required:true" missingMessage="顺序位必填，1-999"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="javascript:void(0);" style="color: #2A7AD2;" class="delAttr">删除</a>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <a href="javascript:void(0);" class="addAttr"><img src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/images/newclass.jpg" style="padding-left: 100px"/>新增规格值</a>
                        </p>
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