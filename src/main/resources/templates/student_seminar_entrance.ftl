<!DOCTYPE html>
<html>
<head style="font-size:35px;">
    <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0"/>
    <link href="/css/group.css" type="text/css" rel="stylesheet"/>
    <title></title>
</head>
<body>
<center>
    <div id="header1">
        <a href="/cm/student/index"><span><b><</b></span></a>
        讨论课
        <span1>
            <b><li class="dao li1">+
                <ul class="sub sub1">
                    <a href="/cm/student/notification"> <li class="main">代办</li></a>
                    <a href="/cm/student/index"><li class="main">个人页</li></a>
                    <a href="/cm/student/seminar"><li class="main">讨论课</li></a>
                </ul>
            </li>
            </b>
        </span1>
    </div>
</center>
<center>
    <#assign keys=courseAndKlassList?keys/>
    <#list keys as key>
        <div class="div1">
            <!--是链接其他页面的超链接，从数据库中读取课程--->
            <a href="/cm/student/seminar/List/${courseAndKlassList[key].klassId}">
                <span>${key} ${courseAndKlassList[key].klassName}</span><span class="right">>
                </span></a>
        </div>
    </#list>
</center>
</body>
</html>