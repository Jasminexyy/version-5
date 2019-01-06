<!DOCTYPE html>
<html>
<head style="font-size:35px;">
	<meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
	<link href="/css/login.css" type="text/css" rel="stylesheet"/>
	<title>教师讨论课信息</title>
</head>
<body>
<center>
	<div id="header1">
		<center>
	            <span>
	                <
	            </span>
			${Seminar.courseName}-讨论课
			<span1>
				<li class="dao li1">+
					<ul class="sub sub1">
						<a href="/cm/teacher/notification">	<li class="main">代办</li></a>
						<a href="/cm/teacher/setting"><li class="main">个人页</li></a>
						<a href="/cm/teacher/seminar"><li class="main">讨论课</li></a>
					</ul>
				</li>
			</span1>
		</center>
	</div>



	<table class="table0" cellspacing="0" cellpadding="0">
		<tr><td class="c2">轮次：</td><td class="c5">${Seminar.roundSerial}</td><td class="c2"></td></tr>
		<tr><td class="c10">主题：</td><td class="c4">${Seminar.seminarName}</td></tr>
		<tr><td class="c2">课次序号：</td><td class="c5">${Seminar.seminarSerial}</td><td class="c2"></td></tr>
		<tr><td class="c10">要求：</td><td class="c4">${Seminar.introduction}</td></tr>
		<tr><td class="c2">课程情况：</td><td class="c5">
				<#if Seminar.seminarStatus== 1>
				正在进行
				<#elseif Seminar.seminarStatus==2>
					已结束
				<#elseif Seminar.seminarStatus==0>
					未开始
				</#if>
			</td><td class="c2"><a href="/cm/teacher/course/seminar/enrollList"><u class="u1">查看信息</u></a></td></tr>
	</table>
</center>
<center>
	<#if Seminar.seminarStatus== 1>
		<a href=cm/teacher/seminar/processing?seminarId=${Seminar.seminarId}"><button class="button1">进入讨论课</button></a></br>
	<#elseif Seminar.seminarStatus==2>
		<a href=cm/teacher/seminar/finished"><button class="button1">书面报告</button></a></br>
		<a href=cm/teacher/seminar/grade"><button class="button2">查看成绩</button></a></br>
	<#elseif Seminar.seminarStatus==0>
		<a href="/cm/teacher/course/seminar/progressing?seminarId=${Seminar.seminarId}&klassId=${Seminar.klassId}"><button class="button1">开始讨论课</button></a></br>
		<a href=cm/teacher/seminar/"><button class="button2">修改讨论课信息</button></a></br>
	</#if>
</center>
</body>
</html>
