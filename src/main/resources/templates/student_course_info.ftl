<!DOCTYPE html>
<html>
	<head style="font-size:35px">
	     <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
	    <link href="/css/login.css" type="text/css" rel="stylesheet"/>
		<title>ѧ���γ���Ϣ</title>
	</head>
	<body>
		<center>
	        <div id="header1">
	        <center>
	            <span>
	                <
	            </span>
				${curCourse.courseName}
	            <span1>
	                <li class="dao li1">+
	                <ul class="sub sub1">
                        <a href="/cm/student/index"><li class="main">����ҳ</li></a>
                        <a href="/cm/student/course/seminar"><li class="main">���ۿ�</li></a>
				    </ul>
					</li>               
					
					</span1>
	        </center>	            
	        </div> 
	    
	    <p class="p12">�γ̼��</p><p class="p7">${curCourse.introduction}</p>
	    <table class="table0" cellspacing="0" cellpadding="0">
	        
	        <tr><td class="c6">�ɼ��������</td><td class="c6">����չʾ��</td><td class="c6">${curCourse.presentationPercentage}%</td></tr>
	        <tr><td class="c6"></td><td class="c6">�������ʣ�</td><td class="c6">${curCourse.questionPercentage}%</td></tr>
	        <tr><td class="c6"></td><td class="c6">���汨�棺</td><td class="c6">${curCourse.reportPercentage}%</td></tr>
	        <tr><td class="c6"></td><td class="c6"></td><td class="c6"></td></tr>
			<tr><td class="c6">��ӿ�ʼʱ�䣺</td><td class="c6">${curCourse.teamStartTime}</td><td class="c6"></td></tr>
	        <tr><td class="c6">��ӽ�ֹʱ�䣺</td><td class="c6">${curCourse.teamEndTime}</td><td class="c6"></td></tr>
	        <tr><td class="c6"></td><td class="c6"></td><td class="c6"></td></tr>
	        <tr><td class="c2">���Ҫ��</td><td class="c2"></td><td class="c13"><span class="right">></span></td></tr>
	        
	    </table>
	    
	   
	    </center>
	</body>
</html>