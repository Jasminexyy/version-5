


<!DOCTYPE html>
<html>
	<head style="font-size:35px">
	     <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
		<link href="/css/userlogin.css" type="text/css" rel="stylesheet">
		<title>教师个人页面</title>
	</head>
	<body class="body1">
		<center>
	        <div id="header1">
	        <center>
	            <span>
	                <
	            </span>
	        我
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
	    
	    <div id="d2">
	    <br/>
	    <p>${curTeacher.name} </p>  <!--姓名-->
	    <p>${curTeacher.account}</p> <!--教工号-->
	    </div>
	    <div id="wrap">
	    
	        <a href="/cm/teacher/course"><li class="l">我的课程<span class="right">></span></li></a>
	        <a href="/cm/teacher/setting"><li class="l">
				账户与设置
				<span class="right">></span></li></a>
	
		</div>
		</center>
	</body>
</html>