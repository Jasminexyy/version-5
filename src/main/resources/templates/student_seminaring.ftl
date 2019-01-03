<!DOCTYPE html>
<html>
<head style="font-size:35px">
    <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
    <link href="login.css" type="text/css" rel="stylesheet"/>
    <title>学生正在进行讨论课提问</title>
</head>
<body>

<script>
    var temp=0;
    var count=0;

    for(var i=0;i<${seminarInfoVO.attendanceListVO.length()};i++) {
        if (attendanceListVO[i].teamOrder == 1)
            count = AttendanceListVO[i].attendanceId;
    }
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
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

    //this line.
    function connect() {
        var socket = new SockJS("http://localhost:8080/springmvc/hello");
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/broadcast', function(SelectedQuestion){
                showGreeting(JSON.parse(SelectedQuestion.body).studentAccount,
                    JSON.parse(SelectedQuestion.body).studentName,
                    JSON.parse(SelectedQuestion.body).teamNumber,
                    JSON.parse(SelectedQuestion.body).status
                );
            });

            stompClient.subscribe('/topic/broadcast', function(nextAttendance){
                showGreeting2(JSON.parse(nextAttendance.body).teamNumber,
                    JSON.parse(nextAttendance.body).teamOrder,
                    JSON.parse(SelectedQuestion.body).klassSeminarId,
                    JSON.parse(SelectedQuestion.body).teamId,
                    JSON.parse(SelectedQuestion.body).status
                );
            });
        });
    }

    function send_klassSeminarId_attendanceId_teamId_studentId(klassSeminarId,attendanceId,teamId,studentId) {
        var name = document.getElementById('name').value;
        stompClient.send("/teacher/select/"+klassSeminarId+"/"+attendanceId+"/"+teamId+"/"+studentId, {}, JSON.stringify({ 'klassSeminarId':klassSeminarId},
            {'attendanceId',attendanceId},{'teamId',teamId},{'studentId',studentId}));
    }

    function showGreeting(studentAccount,studentName,teamNumber,status) {

        document.getElementById("teamN").value=teamNumber;
        document.getElementById("account").value=studentAccount;
        document.getElementById("name").value=studentName;
        temp=status;
    }

    function showGreeting2(teamNumber) {
        document.getElementById("teamNum").value=teamNumber;
    }

</script>
<center>
    <div id="header1">
        <center>
	            <span>
	                <
	            </span>
            ${seminarInfoVO.courseName}-讨论课
            <span1>
                <li class="dao li1">+
                    <ul class="sub sub1">
                        <a href="/cm/student/notification"><li class="main">代办</li></a>
                        <a href="/cm/student/person"><li class="main">个人页</li></a>
                        <a href="/cm/student/seminar"><li class="main">讨论课</li></a>
                    </ul>
                </li>

            </span1>
        </center>

    </div>
    <p class="p1">${seminarInfoVO.seminarName}</p>
    <p class="p2" id="teamNum">第${team.teamNumber}组 展示</p>

    <#assign index=1>

    <table class="table0" cellspacing="0" cellpadding="0">

        <#list seminarInfoVO.AttendanceListVO as attendanceVO>
            >
            <#if index%2==1>
                <tr><td class="c10">第${index}组：</td><td class="c4">${attendanceVO.teamName}</td>

            <#elseif index%2==0>
                <tr><td class="c2">第${index}组：</td><td class="c5">${attendanceVO.teamName}</td><td class="c2"></td></tr>
            </#if>

            <#assign index=index+1>
        </#list>
    </table>
</center>
<center><button class="button3" onclick="send_klassSeminarId_attendanceId_teamId_studentId(${klassSeminarId},count,${teamId},${myId})">Q&A</button></br></center>

<#if temp=1>
    <div id="target_1" class="fade2">
        <div class="popupWindow">
            <p class="p9">正在提问</p>
            <p class="p5" id="teamN"> </p>
            <p class="p5" id="account"></p>
            <p class="p5" id="name"></p>

        </div>
    </div>
</#if>

</body>
</html>