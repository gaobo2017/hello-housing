<#--商家申请分类停用-->
<#assign prefix="stop"/>
<div id="${prefix}Dlg" class="easyui-dialog popBox" title="商家申请分类停用" style="width:330px;height:200px;" data-options="resizable:true,closable:true,closed:true,cache: false,modal: true" buttons="#dlg-buttons-${prefix}">
    <div class="form-contbox">
    <@form.form method="post" class="validForm" id="addForm" name="addForm">
        <dl class="dl-group">
            <dd class="dd-group">
                <div class="fluidbox">
                    <p class="p12 p-item">
                        <label class="lab-item"><font class="red">*</font>停用原因: </label>
                        <input type="text" name="reason" id="reason">
                    </p>
                </div>
            </dd>
            <p class="p-item p-btn">
                <input type="button" id="${prefix}add" class="btn" value="停用"/>
                <input type="button" id="${prefix}cancel" class="btn" value="取消"/>
            </p>
        </dl>
    </@form.form>
    </div>
</div>
<script language="javascript">
    $(function(){
        $('#${prefix}cancel').click(function(){
            $('#${prefix}Dlg').dialog('close');
        });
        $('#${prefix}add').click(function(){
            var callbackfunc = eval('${prefix}CallBack');
            callbackfunc($('#reason').val());
            $("#${prefix}Dlg").dialog('close');
        });
    });
</script>