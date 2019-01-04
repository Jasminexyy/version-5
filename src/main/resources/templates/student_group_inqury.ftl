<!DOCTYPE html>
<html>
	<head style="font-size:35px;">
  <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">

	<link href="group.css" type="text/css" rel="stylesheet"/> 
		<title>Enter your title here</title>
	</head>
	<body>
		<center>		
			<div id="header1" style="background-color:#ffffff;">
	            <span>
	                <b><</b>
	            </span>
				${myTeam.courseKlass.courseName} ${myTeam.courseKlass.klassName}
	            <span1>
	                <b><li class="dao li1">+
	                <ul class="sub sub1">
						<a href="/cm/student/index"><li class="main">个人页</li></a>
						<a href="/cm/student/course/seminar"><li class="main">讨论课</li></a>
				    </ul>
					</li>               
					</b>
					</span1>	            
	        </div> 
			
			<div style="height:0.5rem;background-color:#e8e8e8"></div>
			
			<div>
				<#list teamList as team>
			<details>
				<summary  class="sumbackgroundw">
				<font color="#9ACD32">${team.teamNumber} ${team.teamName}</font></summary>
				<div style="border-style:none">
				<table>
				<tr>
				<!--组长组员标识、学号、名字、课程名字-->
				<td class="fontgreen">组长：</td>
				<td>1111111111</td>
				<td>王二</td>
				<td class="fontgreen">J2EE</td>
				</tr>
				<tr>
				<td class="fontgreen">组员：</td>
				<td>1111111111</td>
				<td>王二</td>
				<td></td>
				</tr>

				</table>
				</div>
			</details>
				<div style="height:0.5rem;background-color:#e8e8e8"></div>
				</#list>
			</div>
			<#if !myteam>
				<a href="/cm/student/course/team/create">
			<div class="header" style="background-color:#9ACD32;color:#ffffff">
				+创建小组<span class="right">></span>
			</div>
			</a>
				<br/>
				<#else>
					<a href="/cm/student/course/team/myteam/${myTeam.teamId}">
					<div class="header" style="background-color:#9ACD32;color:#ffffff">
						${myTeam.teamNumber} ${myTeam.teamName}
						<span class="right">></span>
					</div>
					</a>
					<br/>
					</#if>
		</center>
	</body>
</html>
