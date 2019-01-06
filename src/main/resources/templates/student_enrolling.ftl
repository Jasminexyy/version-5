<!DOCTYPE html>
<html>
<head style="font-size:35px">
	<meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
	<link href="/css/login.css" type="text/css" rel="stylesheet"/>
	<title>讨论课</title>
</head>
<body>
<center>
	<div id="header1">
		<center>
	            <span><</span>
			${seminarInfo.courseName}-讨论课
			<span1>
				<li class="dao li1">+
					<ul class="sub sub1">
						<a href="/cm/student/person"><li class="main">个人页</li></a>
						<a href="/cm/student/seminar"><li class="main">讨论课</li></a>
					</ul>
				</li>
			</span1>
		</center>
	</div>
	<table class="table0" cellspacing="0" cellpadding="0">
		<#--<#assign flag=0>-->
		<#--<#assign index=1 >-->
		<#--<#list seminarInfo.attendanceListVO as attendanceVO>-->
		<#--<#if attendanceVO.teamName==team.teamNumber>-->
			<#--<#assign flag=1>-->
		<#--</#if>-->
		<#--<#if index%2==1>-->
			<#--<#if index==attendanceVO.teamOrder>-->
				<#--<tr><td class="c10">第${index}组：</td><td class="c4">${attendanceVO.teamName}</td></tr>-->
			<#--<#else>-->
				<#--<tr><td class="c10">第${index}组：</td><td class="c4"><a href="#target_1"><u>可报名</u></a></td></tr>-->
			<#--</#if>-->
		<#--<#elseif index%2==0>-->
			<#--<#if index==attendanceVO.teamOrder>-->
				<#--<tr><td class="c2">第${index}组：</td><td class="c5">${attendanceVO.teamName}</td><td class="c2"></td></tr>-->
			<#--<#else>-->
				<#--<tr><td class="c2">第${index}组：</td><td class="c5"><a href="#target_1"><u>可报名</u></a></td><td class="c2"></td></tr>-->
			<#--</#if>-->
		<#--</#if>-->
		<#assign attendances=seminarInfo.attendanceListVO.attendanceList/>
		<#list attendances as attendance>
			<tr><td class="c2">第${attendance.teamOrder}组：</td><td class="c5">${attendance.teamName}</td><td class="c2"></td></tr>
		</#list>
	</table>
	<#if attendances.size()<=seminarInfo.teamNumLimit>
		<a href="#target_1"> <button class="button7">确认报名</button></a></br>
	</#if>

	<a href="#target_2"> <button class="button7">取消报名</button></a></br>
</center>
<div id="target_1" class="fade">
	<div class="popupWindow">
		<p class="p9">确认报名</p>
		<p class="p5">${seminarInfo.courseName}</p>
		<p class="p5">$${seminarInfo.courseName}讨论课</p>
		<p class="p5">第${index}组</p>
		<div class="but">
			<a href="#">CANCEL</a>
			<a href="">SURE</a>
		</div>
	</div>
</div>
<div id="target_2" class="fade">
	<div class="popupWindow">
		<p class="p9">取消报名讨论课?</p>
		<p class="p5">${seminarInfo.courseName}</p>
		<p class="p5">${seminarInfo.courseName}讨论课</p>
		<p class="p5">第${seminarInfo.attendanceListVO[index].teamOrder}组</p>
		<div class="but">
			<a href="#">CANCEL</a>
			<a href="#">SURE</a>
		</div>
	</div>
</div>
</body>
</html>