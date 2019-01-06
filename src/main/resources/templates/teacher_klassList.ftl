<!DOCTYPE html>
<html>
<head style="font-size:35px">
	<meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
	<link href="/css/login.css" type="text/css" rel="stylesheet"/>
	<script src="/js/jquery_min.js" type="text/javascript"></script>
	<title>班级信息</title>
</head>
<body>
<center>
	<div id="header1">
		<center>
	            <span>
	                <b><</b>
	            </span>
			班级信息
			<span1>
				<li class="dao li1">+
					<ul class="sub sub1">
						<a href="/cm/teacher/notification"><li class="main">代办</li></a>
						<a href="/cm/teacher/person"><li class="main">个人页</li></a>
						<a href="/cm/teacher/seminar"><li class="main">讨论课</li></a>
					</ul>
				</li>

			</span1>
		</center>
	</div>
</center>
<center>
	<#list klassList as Klass>
		<div id="d1">
			<p class="p8">${Klass.klassName}</p>
			<table class="table0" cellspacing="0" cellpadding="0">
				<tr><td class="c7">讨论课时间：</td><td class="c7">${Klass.klassTime}</td></tr>
				<tr><td class="c7">讨论课地点：</td><td class="c7">${Klass.klassLocation}</td></tr>
				<tr><td class="c7">班级学生名单：</td><td class="c7">
						<u class="u1">点击查看</u>
					</td></tr></a>

			</table>
			<button class="button6">删除班级</button>
		</div>
	</#list>
	<a href="/cm/teacher/course/klass/create"><button class="button1"><b>+ 新增班级</b></button></a></br>
</center>

</body>
</html>
