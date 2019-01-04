<!DOCTYPE html>
<html>
<head style="font-size:35px;">
    <meta name="viewport"
          content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">

    <link href="/css/group.css" type="text/css" rel="stylesheet"/>
    <title>Enter your title here</title>
</head>
<body>
<center>
    <div id="header1" style="background-color:#ffffff;">
	            <span>
	                <b><</b>
	            </span>
        学生组队
        <span1>
            <b>
                <li class="dao li1">+
                    <ul class="sub sub1">
                        <a href="/cm/teacher/notification">
                            <li class="main">代办</li>
                        </a>
                        <a href="/cm/student/setting">
                            <li class="main">个人页</li>
                        </a>
                        <a href="/cm/student/seminar">
                            <li class="main">讨论课</li>
                        </a>
                    </ul>
                </li>
            </b>
        </span1>
    </div>

    <#list teamList as team>
        <div style="height:0.5rem;background-color:#e8e8e8"></div>
        <div>
            <details>
                <summary class="sumbackgroundw">
                    <!--不合规矩的小组审核提交，其实一样，让它名字变红就好了-->
                    <#if "${team.valid}"==0><font color="#ff0000"></#if>
                        ${team.teamNumber} ${team.teamName}</font>
                </summary>
                <div style="border-style:none">
                    <table>
                        <tr>
                            <td class="fontgreen">组长：</td>
                            <td>${team.leader.account}</td>
                            <td>${team.leader.name}</td>
                        </tr>
                        <#list team.members as member>
                            <tr>
                                <td class="fontgreen">组员：</td>
                                <td>${member.account}</td>
                                <td>${member.name}</td>
                            </tr>
                        </#list>
                    </table>
                </div>
            </details>
        </div>
    </#list>


</center>


</body>
</html>
