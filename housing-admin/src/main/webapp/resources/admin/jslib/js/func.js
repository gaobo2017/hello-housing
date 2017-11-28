$.fn.foucsText=function(c){var a=this;var b=(c==null)?$(a).val():c;a.val(b);a.focus(function(){if(a.val()==b){a.val("")}});a.blur(function(){if(a.val()==""){a.val(b)}});return a};

/* 分页函数 */
function pagerFilter(data){
	if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
		data = {
			total: data.length,
			rows: data
		}
	}
	var dg = $(this);
	var opts = dg.datagrid('options');
	var pager = dg.datagrid('getPager');
	pager.pagination({
		onSelectPage:function(pageNum, pageSize){
			opts.pageNumber = pageNum;
			opts.pageSize = pageSize;
			pager.pagination('refresh',{
				pageNumber:pageNum,
				pageSize:pageSize
			});
			dg.datagrid('loadData',data);
		}
	});
	if (!data.originalRows){
		data.originalRows = (data.rows);
	}
	var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
	var end = start + parseInt(opts.pageSize);
	data.rows = (data.originalRows.slice(start, end));
	return data;
}

$(function () {

	if($.validator){
		$.validator.setDefaults({//表单提交验证事件
			submitHandler: function(form) {
				if(window.afterValidate){
					$.messager.progress({text:"提交中..."});
					afterValidate();
				}
				form.submit();
			}
		});

		$(".validForm").validate();//form-a 验证
		//$(".op-form-b").validate();//form-b 验证
	}

	if ($('.a-extend').length) {
		var $s=$('#searchbar')
		var sh = $s.panel('options').height;
		$('.a-extend').toggle(function () {
			$(this).addClass('a-intend');
			$s.panel('resize',{height:26});
			$(window).trigger('resize');
		},function () {
			$(this).removeClass('a-intend');
			$s.panel('resize',{height:sh});
			$(window).trigger('resize');
		});
	}

	$('.txt-focus').each(function () {
		$(this).foucsText();
	});

	if ($('.dt-group').length) {
		$('.dt-group').click(function () {
			var $p = $(this).parent();
			if ($p.hasClass('dl-intend')) {
				$p.removeClass('dl-intend');
			}else {
				$p.addClass('dl-intend');
			}
		});
	}
});


function dataGridLoadSuccess(data){
	if(data.rows.length==0){//无数据提示
		var body1 = $(this).data().datagrid.dc.body1;
		var body2 = $(this).data().datagrid.dc.body2;
		body1.find('table').html('<tr class="datagrid-row"><td class="datagrid-td-rownumber"><div class="datagrid-cell-rownumber"></div></td></tr>');
		body2.find('table').width('100%').find('tbody').append('<tr><td style="height: 25px; text-align: center;">没有数据</td></tr>');
	}
	if('afterDataGridLoaded' in window) {
		afterDataGridLoaded(data);
	}
}

function queryParamsHandler(){
	var strParams = '{';
	$("[name^='q_']").each(function () {
		strParams+='"'+$(this).attr('name')+'"';
		strParams+=':';
		strParams+='"'+$(this).val()+'"';
		strParams+=',';
	});
	strParams = strParams.substr(0, strParams.length-1);
	strParams += '}';
	return eval('('+strParams+')');
}


//根据cookie的name取得value
function getCookie(c_name)
{
if (document.cookie.length>0)
  {
  c_start=document.cookie.indexOf(c_name + "=");
  if (c_start!=-1)
    { 
    c_start=c_start + c_name.length+1; 
    c_end=document.cookie.indexOf(";",c_start);
    if (c_end==-1) c_end=document.cookie.length;
    return decodeURIComponent(document.cookie.substring(c_start,c_end));
    } 
  }
return "";
}

function getErrMsg(errMsg) {
	if (errMsg) {
		if (errMsg.indexOf('"') == 0 && errMsg.lastIndexOf('"') == errMsg.length - 1) {
			errMsg = errMsg.substring(1, errMsg.length - 1);
		}
	}
	return errMsg;
}

//ajax防重复提交
function getCSRFTokenParam(){
	var cSRFTokenParam = 'CSRFToken=';
	cSRFTokenParam += $("input[name='CSRFToken']").val();
	cSRFTokenParam += '&CSRFMemKey=';
	cSRFTokenParam += $("input[name='CSRFMemKey']").val();
	return cSRFTokenParam;
}

function refrushCSRFToken(csrfToken){
	$("input[name='CSRFToken']").val(csrfToken);
}
