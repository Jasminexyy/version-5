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
	                <b><li class="dao li1">
	                &nbsp;
					</li>               
					</b>
					</span1>	            
	        </div> 
			
			<div style="height:0.5rem;background-color:#e8e8e8"></div>
			
			<div class="sumbackgroudw">		
			<!--�鿴�Լ���ĳ�Ա-->
				<font size="5" color="#9ACD32">${myTeam.teamNumber} ${myTeam.teamName}</font>
				
				<div style="border-style:none">
				<table>
				<tr>
				<!--�鳤��Ա��ѧ�š����֡��γ�����-->
					<td class="fontgreen">�鳤��</td>
					<td>${myTeam.leader.account}</td>
					<td class="fontred">��</td>
				</tr>
					<#list myTeam.students as student>
						<tr>
							<td class="fontgreen">��Ա��</td>
							<td>${student.account}</td>
							<td onclick="del()">${student.studentName}</td>

						</tr>
					</#list>

				</table>
				</div>
		
			</div>
		
			<div style="height:5rem;background-color:#e8e8e8;border-style:none;"></div>
			<input type="submit" value="����" class="subr" onclick="/cm/student/course/team/myteam/quit"/>
		</center>



	</body>
</html>
