<!DOCTYPE html>
<html>
<head style="font-size:35px;">
<meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
	<link href="../static/css/details.css" type="text/css" rel="stylesheet"/>
	<script src="/static/js/jquery.min.js"></script>
	<title>我的成绩</title>
</head>
<body>
<center>
<div id="header1" style="background-color:#ffffff;">
	            <span>
	                <b><</b>
	            </span>
	        我的成绩
	            <span1>
	                <b><li class="dao li1">+
							<ul class="sub sub1">
								<a href="/cm/student/person"><li class="main">个人页</li></a>
								<a href="/cm/student/seminar"><li class="main">讨论课</li></a>
							</ul></li>
					</b>
					</span1>	            
	        </div> 
</center>
<#assign roundNames=scoreDetails?keys/>
<center>
<div style="width:80%">
	<#list roundNames as roundName>
		<#assign seminarScoreVO=scoreDetails[roundName]/>
		<#assign seminarScore=seminarScoreVO.simpleSeminarScoreVO/>
		<details>
			<!--好多轮次的讨论课-->
			<summary  id="sumbackground">${roundName}</summary>
			<details>
				<!--每轮不同讨论课名字和分数-->
				<#list seminarScore as seminar>
					<summary id="sumbackground-summary">${seminar.seminarName}</summary>
					<div  id="backcolor">
						<table>
							<tr>
								<td>展示：<font color="#9ACD32">5.0</font></td>
								<td>提问：<font color="#9ACD32">5.0</font></td>
								<td>书面报告：<font color="#9ACD32">5.0</font></td>
								<td><font color="#FF0000">5.0</font></td>
							</tr>
						</table>
						<span class="right" style="margin-right:15%"> 本轮成绩：<font color="#FF0000">5.0</font></span>
						<br/>
					</div>
				</#list>
			</details>
		</details>
	</#list>
</div>
	<button type="submit" class="submit">导出成绩</button>
</center>

</body>
</html>