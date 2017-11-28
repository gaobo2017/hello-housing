<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>房源 智能管理系统</title>
	<style>
		body,div{margin:0; padding:0;}
body{
    background:#f9fdfe url(${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/images/bodybg.jpg) repeat-x;	
	font-size: 14px;
	color:#404040;
}
h2{
	color:#fff;
	text-align: center;
	margin-top: 180px;
}
.formbox{
	background:#ceeef9;
	width: 430px;
	border-radius: 4px;
	box-shadow: 1px 2px 3px #8adfe2;
	margin:0 auto;
	font-size:12px;
	padding-bottom: 10px;
	margin-top:40px;
}
.formbox h3{
	height: 40px;
	line-height: 40px;
	border-bottom: solid 1px #8adfe2;
	font-size: 14px;
	font-weight: normal;
	text-align: center;
}
.inputbox{
	margin-bottom: 10px;
}
.inputbox label,.inputbox input{
	display: inline-block;
}
.inputbox label{
	width: 150px;
	text-align: right;
	padding-right: 5px;
}
.inputbox input{
	border:solid 1px #FF0000;
	background-color:#f9fdfe;
	width: 200px;
}
.bottom{
	position: fixed;
	bottom: 10px;
	width: 100%;
	text-align: center;
}
	</style>
</head>
<body>
  	<h2>房源 智能管理系统</h2>
	 <div class="formbox">	
          <h3>用户登录</h3>
          <form action="${domainUrlUtil.EJS_URL_RESOURCES}/admin/doLogin" method="post" onsubmit="return doSubmit();">
          	  <div class="inputbox">	
	          	    <label for="">用户名</label>
	          	    <input type="text" name="name" id="name">
          	   </div>
          	   <div class="inputbox">	
	          	    <label for="">密码</label>
	          	    <input type="password" type="password" name="password" id="pwd">
          	   </div>
          	   <div class="inputbox">	
          	   	<label for=""></label>
          	   	<font color="red">${(message)!}</font>
          	   </div>
          	   <#--
          	   <div class="inputbox">	
	          	    <label for="">验证码</label>
	          	    <input type="text" value="" style="width:100px;">
	          	    <div style="display:inline-block; width:40px; height:20px; padding-right:20px;" ><img src="" alt=""></div>
          	   </div>-->
          	   <div class="inputbox">	
	          	    <label for="">&nbsp;</label>
	          	    <input type="submit" value="登录">
          	   </div>
          </form>
	 </div>
	 <div class="bottom"> 版权所有 2017-2018  </div>
</body>
</html>