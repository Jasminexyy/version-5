<!DOCTYPE html>
<html>
	<head style="font-size:35px;">
  <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">

	<link href="/css/group.css" type="text/css" rel="stylesheet"/>
		<title>Enter your title here</title>
	</head>
	<body>
	<script type="text/javascript">

	</script>
		<center>		
			<div id="header1" style="background-color:#ffffff;">
	            <span>
	                <a href="/cm/student/course/team/student_teams"><b><</b></a>
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
					<td class="fontred">${myTeam.leader.name}</td>
				</tr>
					<#list myTeam.members as student>
						<tr>
							<td class="fontgreen">��Ա��</td>
							<td>${student.account}</td>
							<td >${student.name}</td>

						</tr>
					</#list>

				</table>
				</div>
		
			</div>

			<div style="height:5rem;background-color:#e8e8e8;border-style:none;"></div>
			<a href="cm/student/course/team/myteam/quit/${myTeam.teamId}"><input type="submit" value="����" class="subr" />
		</center>



	</body>
</html>
