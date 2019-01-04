<!DOCTYPE html>
<html>
<head style="font-size:35px;">
<meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
	<link href="/css/details.css" type="text/css" rel="stylesheet"/>
	<script src="/js/jquery.min.js"></script>
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
<center>
<div style="width:80%">
	<#list scoreDetails as roundScore>
		<details>
			<!--好多轮次的讨论课-->
			<summary  id="sumbackground">第${roundScore.roundNumber}轮-${roundScore.totalScore}</summary>
			<details>
				<!--每轮不同讨论课名字和分数-->
				<#list roundScore.simpleSeminarScoreVOList as seminarScore>
					<summary id="sumbackground-summary">${seminarScore.seminarName}</summary>
					<div  id="backcolor">
						<table>
							<tr>
								<td>展示：<font color="#9ACD32">${seminarScore.presentationScore}</font></td>
								<td>提问：<font color="#9ACD32">${seminarScore.questionScore}</font></td>
								<td>书面报告：<font color="#9ACD32">${seminarScore.reportScore}</font></td>
							</tr>
						</table>
						<br/>
					</div>
				</#list>
			</details>
		</details>
	</#list>
</div>
	<button class="submit">导出成绩</button>
</center>
</body>
</html>