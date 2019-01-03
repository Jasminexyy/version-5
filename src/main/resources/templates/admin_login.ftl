<!DOCTYPE html>
<html>
	<head>
	<link href="h3.css" type="text/css" rel="stylesheet"/> 
		<title>登陆</title>
	</head>
	<body>
		<div class="myhead">翻转课堂管理平台</div>
		
		<div class="logink"><center>翻转课堂管理员登陆</center>
		<br/>
		<form action="/cm/login" method="post">
		 <input type="text" value="账户名" name="id" onfocus="javascript:if(this.value=='账户名')this.value='';"/>
		<br/>
		<br/>
		 <input type="text" value="账户密码" name="password" onfocus="javascript:if(this.value=='账户密码')this.value='';"/>
		 <br/>
		 <br/>
		 <input type="submit" class="btn" value="登陆" />
		</div>
		</form>
	</body>
</html>
