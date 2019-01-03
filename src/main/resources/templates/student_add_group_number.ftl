<!DOCTYPE html>
<html>
	<head style="font-size:35px;">
  <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">

	<link href="group.css" type="text/css" rel="stylesheet"/> 
		<title>Enter your title here</title>
		
	<script type="text/javascript">
		function shenhe() {
			var person=prompt("申请理由","单行输入");
			if (person!=null && person!="")
			{
				alert("申请提交成功");
			}
		}
function del() { 
        if (!confirm("将${student.name}移出小组？")) { 
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
						<li class="main">个人页</li>
						<li class="main" onclick="javascrtpt:window.location.href='/cm/student/seminar/student_seminar_entrance'">
							讨论课</li>
				    </ul>
					</li>               
					</b>
					</span1>	            
	        </div> 
			
			<div style="height:0.5rem;background-color:#e8e8e8"></div>
			
			<div class="sumbackgroudw">		
			<!--查看自己组的成员-->
				<font size="5" color="#9ACD32">${myTeam.teamNumber} ${myTeam.teamName}</font>
				<#if myTeam.valid==0>
					<font size="5" color="red">invalid</font>
				</#if>
				<div style="border-style:none">
				<table>
				<tr>
				<!--组长组员、学号、名字、课程名字-->
				<td class="fontgreen">组长：</td>
				<td>${myTeam.leader.account}</td>
				<td class="fontred">我</td>

				</tr>
					<#list myTeam.students as student>
						<tr>
							<td class="fontgreen">组员：</td>
							<td>${student.account}</td>
							<td onclick="del()">${student.studentName}</td>

						</tr>
					</#list>

				</table>
				</div>
		
			</div>
		
			<div class="div1">
			<font style="float:left;margin-left:10%;" class="fontgreen">添加成员：</font>
			</div>
			<!--未分组的人学号、名字、课程名字-->
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
			<input onclick="shenhe()" type="submit" value="提交审核" class="sub3"/>
			<br/>
			</#if>
			<input type="submit" value="解散小组" class="subr"/>
			<input type="submit" value="添加" class="sub3"/>
		</center>



	</body>
</html>
