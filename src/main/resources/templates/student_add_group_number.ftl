<!DOCTYPE html>
<html>
	<head style="font-size:35px;">
  <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">

	<link href="group.css" type="text/css" rel="stylesheet"/> 
		<title>Enter your title here</title>
		
	<script type="text/javascript">
		function shenhe() {
			var person=prompt("��������","��������");
			if (person!=null && person!="")
			{
				alert("�����ύ�ɹ�");
			}
		}
function del() { 
        if (!confirm("��${student.name}�Ƴ�С�飿")) { 
            window.event.returnValue = false; 
        }else
		{
				jQuery.ajax({
					type:"POST",
					url:"/cm/student/course/team/delete",
					processData:false,
					data:{"studentid": ${student.id}},
					dataType: 'json',
					complete:function(data){
						if(data.status==200)
							window.location="cm/student/course/team/myteam";
					}
				});
		}
    } 
</script>		
		
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
						<li class="main">����ҳ</li>
						<li class="main" onclick="javascrtpt:window.location.href='/cm/student/seminar/student_seminar_entrance'">
							���ۿ�</li>
				    </ul>
					</li>               
					</b>
					</span1>	            
	        </div> 
			
			<div style="height:0.5rem;background-color:#e8e8e8"></div>
			
			<div class="sumbackgroudw">		
			<!--�鿴�Լ���ĳ�Ա-->
				<font size="5" color="#9ACD32">${myTeam.teamNumber} ${myTeam.teamName}</font>
				<#if myTeam.valid==0>
					<font size="5" color="red">invalid</font>
				</#if>
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
		
			<div class="div1">
			<font style="float:left;margin-left:10%;" class="fontgreen">��ӳ�Ա��</font>
			</div>
			<!--δ�������ѧ�š����֡��γ�����-->
			<$list studentsNotInTeam as s>
				<div>
				<table>
				<tr>
				<td><input name="stuname" type="checkbox" value="" />${s.account}</td>
				<td>${s.studentName}</td>
				</tr>
			<$list>
				</table>
				</div>
<div style="height:3rem;background-color:#e8e8e8;border-style:none;"></div>
			<#if myTeam.valid==0>
			<input onclick="shenhe()" type="submit" value="�ύ���" class="sub3"/>
			<br/>
			</#if>
			<input type="submit" value="��ɢС��" class="subr"/>
			<input type="submit" value="���" class="sub3"/>
		</center>



	</body>
</html>
