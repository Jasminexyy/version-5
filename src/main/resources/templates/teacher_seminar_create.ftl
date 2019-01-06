<#--新建讨论课-->
<!DOCTYPE html>
<html lang="ch">
<head style="font-size: 35px">
    <link rel="stylesheet" href="/css/seminar.css" charset="GB2312"/>
    <script src="/js/jquery.min.js"></script>
    <title>新建讨论课</title>
    <script type="text/javascript">
        create=function(){
            var seminarName=document.getElementById("seminar_name");
            var introduction=document.getElementById("seminar_ask");
            var iv=document.getElementById("isVisible");
            var seminarStatus=iv.options[iv.selectedIndex].value;
            var enrollStartTime=document.getElementById("start_time");
            var enrollEndTime=document.getElementById("end_time");
            var teamNumLimit=document.getElementById("team_num");
            var round_serial=document.getElementById("round_serial");
            var roundSerial=round_serial.options[round_serial.selectedIndex].value;
            var tt="isVisible="+seminarStatus.value+"&roundSerial="+roundSerial+"&seminarName="+seminarName.value+
                "&introduction="+introduction.value+"&teamNumLimit="+teamNumLimit.value+
                "&enrollStartTime="+enrollStartTime.value+"&enrollEndTime="+enrollEndTime.value;
            console.log("I'm here");
            $.ajax({
                type:"POST",
                url:"/cm/teacher/course/seminar/creat",
                //headers:{"contentType":"application/json"},
                processData:false,
                // data:$('#myform').serialize(),
                data:JSON.stringify(tt),
                dataType:"json",
                complete:function(data){
                    if(data.status==200){
                        confirm("创建成功！");
                        window.location="/cm/teacher/course/seminar/course";
                    }
                }
            });
        }
    </script>
</head>
<body>
<center>
    <div id="header1">
        <span>X</span>
        <center>
            新建讨论课
        </center>
    </div>
    <form>
        <input class="input_for_course" id="seminar_name" type="text" placeholder="主题" onfocus="this.value='';this.onfocus='';">
        </br>
        <textarea class="textarea_for_course" id="seminar_ask" placeholder="讨论课具体要求" onfocus="this.value='';this.onfocus='';"></textarea>
        <div class="div2">
            <table class="table_d2_1">
                <tr>
                    <td class="c2">讨论课可见：</td>
                    <td class="c2"></td>
                    <td class="c2"><p7><select id="isVisible">
                                <option value="0">可见</option>
                                <option value="1">不可见</option>
                            </select></p7></td>
                </tr>
            </table>
        </div>
        <div class="div5">
                <table class="table_d5" cellspacing="" cellpadding="">
                    <tr>
                        <td class="c">展示报名开始时间：</td><td class="c"></td>
                        <td class="c"><p5><input type="datetime-local" id="start_time"> </p5></td>
                    </tr>
                    <tr>
                        <td class="c">展示报名截至时间：</td><td class="c"></td>
                        <td class="c"><p5><input type="datetime-local" id="end_time"> </p5></td>
                    </tr>
                    <tr>
                        <td class="c">报名小组数：</td>
                        <td class="c"></td>
                        <td><p7><input class="input1" type="text" id="team_num" placeholder="6" onfocus="this.value='';this.onfocus='';"></p7></td>
                    </tr>
                    <tr>
                        <td class="c">所属Round：</td>
                        <td class="c"></td>
                        <td><select id="round_serial">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                            </select></td>
                    </tr>
                </table>
        </div>
        <button class="button1" onclick="create()">发布</button>
    </form>
</center>
</body>
</html>