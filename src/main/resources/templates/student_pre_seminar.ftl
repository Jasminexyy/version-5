<!DOCTYPE html>
<html>
<head style="font-size:35px">
	<meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
	<link href="/css/login.css" type="text/css" rel="stylesheet"/>
	<title>学生未开始为报名讨论课</title>
</head>
<body>
<center>
	<div id="header1">
		<center>
	            <span>
	                <
	            </span>
			${seminarInfo.courseName}
			<span1>
				<li class="dao li1">+
					<ul class="sub sub1">
						<a href="/cm/student/notification"><li class="main">代办</li></a>
						<a href="/cm/student/person"><li class="main">个人页</li></a>
						<a href="/cm/student/seminar"><li class="main">讨论课</li></a>
					</ul>
				</li>

			</span1>
		</center>

	</div>


	<#if "${seminarInfo.seminarStatus}"==0>
	<table class="table0" cellspacing="0" cellpadding="0">
		<tr><td class="c2">轮次：</td><td class="c5">${seminarInfo.roundSerial}</td><td class="c2"></td></tr>
		<tr><td class="c10">主题：</td><td class="c4">${seminarInfo.SeminarName}</td></tr>
		<tr><td class="c2">课次序号：</td><td class="c5">${seminarInfo.seminarSerial}</td><td class="c2"></td></tr>
		<tr><td class="c10">要求：</td><td class="c4">${seminarInfo.introduction}</td></tr>
		<tr><td class="c2">课程情况：</td><td class="c5">未开始</td><td class="c2"><a href="/cm/student/seminar/enrollList"><u class="u1">报名情况</u></a></td></tr>
		<tr><td class="c10"></td><td class="c4"></td></tr>
		<tr><td class="c8">报名开始时间：</td><td class="c9">${seminarInfo.enrollStartTime}</td></tr>
		<tr><td class="c8">报名截止时间：</td><td class="c9">${seminarInfo.enrollEndTime}</td></tr>
	</table>
</center>
<center>
	<a href="/cm/student/seminar/enrollList/"><button class="button1">报名</button></br></a>
</center>

<#elseif "${seminarInfo.seminarStatus}"==1>
	table class="table0" cellspacing="0" cellpadding="0">
	<tr><td class="c2">轮次：</td><td class="c5">${seminarInfo.roundSerial}</td><td class="c2"></td></tr>
	<tr><td class="c10">主题：</td><td class="c4">${seminarInfo.SeminarName}</td></tr>
	<tr><td class="c2">课次序号：</td><td class="c5">${seminarInfo.seminarSerial}</td><td class="c2"></td></tr>
	<tr><td class="c10">要求：</td><td class="c4">${seminarInfo.introduction}</td></tr>
	<tr><td class="c2">课程情况：</td><td class="c5">正在进行</td><td class="c2"><a href="/cm/student/seminar/enrollList"><u class="u1">报名情况</u></a></td></tr>
	<#if !"${attendance}">
		<tr><td class="c2">PPT：</td><td class="c5">
				<#if "${attendance.pptName}"!=NULL>
					已提交
				<#else>
					未提交
				</#if>
			</td><td class="c2"></td></tr>
	</#if>
	</table>
	</center>
	<center>
		<#if !"${attendance}">
			<button class="button1">PPT提交</button>
		</#if>
		<a href="/cm/student/seminar/processing"><button class="button2">进入讨论课</button></a>
	</center>
<#elseif "${seminarInfo.seminarStatus}"==2>
	<table class="table0" cellspacing="0" cellpadding="0">
		<tr><td class="c2">轮次：</td><td class="c5">${seminarInfo.roundSerial}</td><td class="c2"></td></tr>
		<tr><td class="c10">主题：</td><td class="c4">${seminarInfo.SeminarName}</td></tr>
		<tr><td class="c2">课次序号：</td><td class="c5">${seminarInfo.seminarSerial}</td><td class="c2"></td></tr>
		<tr><td class="c10">要求：</td><td class="c4">${seminarInfo.introduction}</td></tr>
		<tr><td class="c2">课程情况：</td><td class="c5">已结束</td><td class="c2"><a href="/cm/student/seminar/enrollList"><u class="u1">报名情况</u></a></td></tr>
		<#if !"${attendance}">
			<tr><td class="c2">PPT：</td><td class="c5">
					<#if "${attendance.pptName}"!=NULL>
						已提交
					<#else>
						未提交
					</#if>
				</td><td class="c2"></td></tr>
		</#if>
		<#if !"${attendance}">
			<tr><td class="c10">书面报告：</td><td class="c4">
					<#if "${attendance.reportName}"!=NULL>
						已提交
					<#else>
						未提交
					</#if>
			</tr>
		</#if>
	</table>
	</center>
	<#if !"${attendance}">
		<center>
			<a href="/cm/student/seminar/score"><button class="button1">查看成绩</button></a>
		</center>
	</#if>
</#if>
</body>
</html>