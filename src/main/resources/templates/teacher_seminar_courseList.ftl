<!DOCTYPE html>
<html>
<head style="font-size:35px;">
    <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">

    <link href="/css/seminar_total.css" type="text/css" rel="stylesheet"/>
    <title>教师-讨论课-总界面</title>
</head>
<body>

<center>
    <div id="header1">
	            <span>
	                <b><</b>
	            </span>
        讨论课
        <span1>
            <b><li class="dao li1">+
                <ul class="sub sub1">
                    <a href="/cm/teacher/notification"><li class="main">代办</li></a>
                    <a href="/cm/teacher/person"><li class="main">个人页</li></a>
                    <a href="/cm/teacher/seminar"><li class="main">讨论课</li></a>
                </ul>
            </li>
            </b>
        </span1>
    </div>
</center>
<div id="wrap">
    <li class="first">
    <#list courseList as course>
       <li class="l">${course.courseName}<a href="/cm/teacher/course/seminar/seminarList?courseId=${course.id}"><span class="right">></span></a></li>
    </#list>
    </li>

    <button class="ing-into">正在进行讨论课</button>
</div>
</body>
</html>
