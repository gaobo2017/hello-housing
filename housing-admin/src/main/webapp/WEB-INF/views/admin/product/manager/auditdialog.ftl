<#--商家申请分类审核-->
<#assign prefix1="audit"/>
<div id="${prefix1}Dlg" class="easyui-dialog popBox" title="商家申请分类审核" style="width:330px;height:200px;" data-options="resizable:true,closable:true,closed:true,cache: false,modal: true" buttons="#dlg-buttons-${prefix1}">
    <div class="form-contbox">
    <@form.form method="post" class="validForm" id="addForm" name="addForm">
        <dl class="dl-group">
            <dd class="dd-group">
                <div class="fluidbox">
                    <p class="p12 p-item">
                        <label class="lab-item"><font class="red">*</font>审核状态: </label>
                        <@cont.radio id="type" codeDiv="AUDIT_STATE"/>
                    </p>
                </div>
            </dd>
            <p class="p-item p-btn">
                <input type="button" id="${prefix1}add" class="btn" value="审核"/>
                <input type="button" id="${prefix1}cancel" class="btn" value="取消"/>
            </p>
        </dl>
    </@form.form>
    </div>
</div>
<script language="javascript">
    $(function(){
        $('#${prefix1}cancel').click(function(){
            $('#${prefix1}Dlg').dialog('close');
        });
        $('#${prefix1}add').click(function(){
            var callbackfunc = eval('${prefix1}CallBack');
            callbackfunc($('#type').val());
            $("#${prefix1}Dlg").dialog('close');
        });
    });
</script>