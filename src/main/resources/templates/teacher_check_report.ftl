<!DOCTYPE html>
<html>
<head style="font-size:35px">
	<meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
	<link href="/css/login.css" type="text/css" rel="stylesheet"/>
	<title>教师给讨论课报告打分</title>
</head>
<body>
<script type="text/javascript">

var score=document.getElementById("scores");
var map=new HushMap();
for(var i=0;i<score.length;i++){
	map.put( ${seminarInfoVO.AttendanceListVO[i].attendenceId},score[i].value);
}

function send_score() {
	$.ajax({
		url: "",
		type: "post",
		datatype:JSON;
		data: {"attendanceId_score_map": map},
		success: function (data) {
			alert(data.result);
		}
	})
}
</script>
<center>
	<div id="header1">
		<center>
	            <span>
	                <
	            </span>
			${seminarInfoVO.courseName}-书面报告
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

	<#assign index=1>
	<table class="table0" cellspacing="0" cellpadding="0">

		<tr><td class="c3">小组</td><td class="c4">报告</td><td class="c3"><left>打分</left></td></tr>

		<#list seminarInfoVO.AttendanceListVO as attendanceVO>

			<tr><td class="c3">第${index}组：</td><td class="c4">${attendanceVO.reportName}</td><td class="c3"><input class="in2" id="scores" > </input></td></tr>

			<#assign index=index+1>
		</#list>
	</table>
</center>
<center>
	<button class="button1">批量下载</button></br>
	<a href="/cm/teacher/seminar/grade"><button class="button2"  onclick="send_score()">确认</button></a></br>
</center>
</body>
</html>
