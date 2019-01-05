<!DOCTYPE html>
<html>
<head style="font-size:35px">
	<meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
	<link href="/css/login.css" type="text/css" rel="stylesheet"/>
	<script src="/js/jquery.min.js" type="text/javascript"></script>
	<title>账户与设置</title>
</head>
<body>
<center>
	<div id="header1">
		<center>
			<a href="/cm/teacher/index?account=${curUser.account}"><span><</span></a>
			账户与设置
			<span1>
				<li class="dao li1">+
					<ul class="sub sub1">
						<a href="/cm/teacher/notification"><li <li class="main">代办</li></a>
						<a href="/cm/student/setting?account=${curUser.account}"><li class="main">个人页</li></a>
						<a href="/cm/student/seminar"><li class="main">讨论课</li></a>
					</ul>
				</li>
			</span1>
		</center>
	</div>
	<table class="table0" cellspacing="0" cellpadding="0">
		<tr><td class="c10">姓名：</td><td class="c4">${curUser.name}</td><td class="c10"></td></tr>
		<tr><td class="c2">教工号：</td><td class="c5">${curUser.account}</td><td class="c2"></td></tr>
		<tr><td class="c10">联系方式(邮箱)：</td><td class="c4">${curUser.email}</td><td class="c10"><a href="/cm/teacher/setting/modifyEmail?account=${curUser.account}"><u class="u1">修改</u></a></td></tr>
		<tr><td class="c2">账户密码：</td><td class="c5">${curUser.password}</td><td class="c2"><a href="/cm/teacher/setting/modifyPwd?account=${curUser.account}"><span class="right">></span></a></td></tr>
		<tr><td class="c10">管理员邮箱：</td><td class="c4">123456@admin.com</td><td class="c10"></td></tr>
	</table>
</center>
<center>
	<a href="/login"><button class="button8">退出登录</button></a></br>
	</form>
</center>
</body>
</html>