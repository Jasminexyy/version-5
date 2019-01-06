<!DOCTYPE html>
<html>
	<head style="font-size:35px;">
  <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">

	<link href="/css/group.css" type="text/css" rel="stylesheet"/>
		<title>Enter your title here</title>
	</head>
	<body>
		<center>		
			<div id="header1" style="background-color:#ffffff;">
	            <span>
	                <b><</b>
	            </span>
				${course.courseName} ${klass.klassName}
	            <span1>
	                <b><li class="dao li1">
	                &nbsp;
					</li>               
					</b>
					</span1>	            
	        </div> 
			
			<div style="height:0.5rem;background-color:#e8e8e8">
			</div>
			
			<div class="div1">
			<font style="float:left;margin-left:10%;">小组名：</font>
			<input type="text" name="groupname" value="填写组名">
			</div>

			<div class="div1">
			<font style="float:left;margin-left:10%;">添加成员：</font>
			</div>
			
				<div>
				<table>
				<#list studentList as student>
				<tr>
				<!--学号、名字、课程名字-->
				<td><input name="stuname" type="checkbox" value="" />${student.account}</td>
				<td>${student.studentName}</td>
				</tr>
				</#list>
				</table>
				</div>

			<br/>

			<div class="header" style="background-color:#9ACD32;color:#ffffff">
		确认提交
			</div>

		</center>


	</body>
</html>
