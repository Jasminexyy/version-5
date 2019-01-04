<#--展示-->
<!DOCTYPE html>
<html lang="ch">
<head style="font-size:35px">
    <link href="/static/css/seminar.css" type="text/css" rel="stylesheet" charset="GB2312"/>

    <meta name="viewport"
          content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0"
          charset="GB2312">
    <script src="/static/js/jquery.min.js"></script>

    <title>展示</title>
</head>
<body class="body1">
<center>
    <!--头都用这一个，不要改了-->
    <div id="header1">
        <center>
            <span><b><</b></span>${seminarInfo.courseName}-讨论课
            <span1>
                <b><li class="dao li1">+
                        <ul class="sub sub1">
                            <a href="/cm/teacher/notification"><li class="main">代办</li></a>
                            <a href="/cm/teacher/person"><li class="main">个人页</li></a>
                            <a href="/cm/teacher/seminar"><li class="main">讨论课</li></a>
                        </ul></li></b>
            </span1>
        </center>
    </div>
    <div class="header1">
        <center>${seminarInfo.seminarName}</center></div></br>
    </br>
    <div class="middle">
        <table class="table_m" cellpadding="0" cellspacing="0">
            <tr><td class="c1">展示队列</td></tr>
            <#list seminarInfo.attendanceListVO as attendance>
                <tr><td class="c1">${attendance.teamName}</td> </tr>
            </#list>
        </table>
    </div>
    <div class="middle1">
        <u2>展示分数</u2></br>
        <input class="div1" type="text" id="curScore" value=""/><br/>
    </div>
    <div class="middle2">
        <table class="table_m2">
            <tr><td class="c1">当前展示：</td></tr>
            <tr><td class="c" id="curPreTeam"></td></tr>
            <tr><td class="c1">当前提问：</td></tr>
            <tr><td class="c" id="curQueTeam"></td></tr>
        </table>
    </div>
    <div>
        <button class="button7" onclick="storeScores();">确认打分</button>
        </br>
        <button class="button3" onclick="sendBackEnd();">提问</button>
        </br>
        <button class="button4" onclick="nextAttendance();">下组展示</button>
        </br>
    </div>
    <div id="target_1" class="fade" style="display: none">
        <div class="popupWindow" id="popup" style="display: none">
            <p>讨论课已结束</p>
            <p>请设定书面报告截至时间</p>
            <p class="p1"><input type="datetime-local" id="reportRollTime"/></p>
            <div class="but">
                <a href="#" onclick="b();">SURE</a>
            </div>
        </div>
    </div>
</center>
<script type="text/javascript">
    $(document).ready(function(){
        connect();
    });
    var stompClient = null;
    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        document.getElementById('response').innerHTML = '';
    }
    function disconnect() {
        if (stompClient != null)
            stompClient.disconnect();
        setConnected(false);
        console.log("Disconnected");
    }
    function connect() {
        var socket = new SockJS("http://localhost:8080/springmvc/hello");//??
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/broadcast', function(selectTempQuestion){
                showSelectedQuestion(JSON.parse(selectTempQuestion.body).studentAccount,
                    JSON.parse(selectTempQuestion.body).studentName,
                    JSON.parse(selectTempQuestion.body).teamNumber
                );
            });
            stompClient.subscribe('/topic/broadcast', function(nextAttendance){
                showNextAttendance(JSON.parse(nextAttendance.body).teamNumber,
                    JSON.parse(nextAttendance.body).teamOrder,
                    JSON.parse(nextAttendance.body).klassSeminarId,
                    JSON.parse(nextAttendance.body).teamId
                );
            });
        });
    }

    var curQuestionTeamInfo=new Array();
    var preScores=new Array();
    var queScores={};
    var reportSubmitTime;
    var curKlassSeminarId;
    var curTeamId;
    var curAttendanceId;

    function showSelectedQuestion(studentAccount,studentName,teamNumber) {
        curQuestionTeamInfo.push(studentAccount,studentName,teamNumber);
        document.getElementById("curQueTeam").value=teamNumber+studentName;
    }
    function showNextAttendance(teamNumber,teamOrder,klassSeminarId,teamId) {
        curKlassSeminarId=klassSeminarId;
        // curAttendanceTeamInfo.push(teamNumber,teamOrder,klassSeminarId,teamId);
        curTeamId=teamId;
        document.getElementById("curPreTeam").value=teamNumber;
    }
    //存储展示成绩
    function storeScores() {
        var curScore=document.getElementById("curScore");
        preScores.add(curScore.value);
    }
    //发给前端当前展示组信息以及学生可以提问的信息+存储提问成绩
    function sendBackEnd() {
        stompClient.send("/teacher/select/"+curKlassSeminarId+"/"+curAttendanceId, {}, JSON.stringify({ 'name': name }));
        var curScore=prompt("请输入当前提问小组的成绩！","");
        if(curScore.value>queScores[curQuestionTeamInfo])
            queScores[curQuestionTeamInfo]=curScore.value;//提问小组id
    }
    //下组展示
    function nextAttendance() {
        stompClient.send("/teacher/select/"+curKlassSeminarId+"/"+curAttendanceId, {}, JSON.stringify({ 'name': name }));
        if(curTeamId==null)
            a();
    }
    //设置讨论课报告提交时间
    function a() {
        var wai=document.getElementById("target_1");
        var nei=document.getElementById("popup");
        wai.style.display="block";
        nei.style.display="block";
    }
    function b() {
        var wai=document.getElementById("target_1");
        var nei=document.getElementById("popup");
        wai.style.display="none";
        nei.style.display="none";
        reportSubmitTime=document.getElementById("reportRollTime").value;

    //    数据传输
        jQuery.ajax({
            type:"POST",
            url:"/cm/teacher/course/seminar/progressing/end",
            headers:{"contentType":"application/json"},
            processData:false,
            // data:$('#myform').serialize(),
            data:{"preScores":preScores.value,"queScores":queScores.value,"reportSubmitTime":reportSubmitTime.value},
            dataType:"json",
            complete:function(data){
                if(data.status==200)
                    window.location="/cm/teacher/course/seminar";
            }
        })
    }
</script>
</body>
</html>