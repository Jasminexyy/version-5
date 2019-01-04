<!--轮次设置-->
<!DOCTYPE html>
<html lang="ch">
<head style="font-size:35px">
    <meta name="viewport"
          content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0"
          charset="GB2312">
    <link rel="stylesheet" href="../static/css/seminar.css" charset="GB2312"/>
    <script src="/js/jquery_min.js" type="text/javascript"></script>
    <title>轮次设置</title>
</head>
<body>
<center>
    <#assign  RoundVO=Round?key/>
    <#assign KlassVOList=Round?value/>
    <div id="header1">
        <span><</span>
        第${RoundVO.roundNumber}轮
    </div>
    <div class="div2">
        <p class="p2">讨论课：</p>
        <table class="table_d2" cellspacing="" cellpadding="">
            <#list RoundVO.seminarVOList as seminar>
                <tr><td class="c">${seminar.seminarTopic}</td></tr>
            </#list>
        </table>
        </br>
    </div>
    <form method="post" id="setRound">
        <div class="div2">
            <p class="p2">成绩设置:</p>
            <table class="table_d2" cellspacing="" cellpadding="">
                <tr>
                    <td class="c">展示：</td>
                    <td class="c">
                        <select id="pre">
                            <option value="0">最高分</option>
                            <option value="1">平均分</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="c">提问：</td>
                    <td class="c">
                        <select id="que">
                            <option value="0">最高分</option>
                            <option value="1">平均分</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="c">报告：</td>
                    <td class="c">
                        <select id="rep">
                            <option value="0">最高分</option>
                            <option value="1">平均分</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
        <div class="div2">
            <p class="p2">本轮讨论课报名次数:</p>
            <table class="table_d2" cellspacing="" cellpadding="">
                <#list KlassVOList as klass>
                    <tr><td class="c">${klass.klassName}</td>
                        <td class="c">
                            <select id="round_num">
                            </select></td></tr>
                </#list>
            </table>
        </div>
        </br>
        <button class="button3" id="modify" onclick="modify();">修改</button>
    </form>
</center>
<script type="text/javascript">
    var list=new Array();
    var i=0;
    <#list RoundVO.seminarVOList as seminar>
    var objSelectNow=document.getElementById("round_num");
    var inner="<option value='1'>1(默认)</option>";
    objSelectNow.innerHTML=inner;
    var objOption = document.createElement("OPTION");
    objOption.text= i;
    objOption.value=i;
    objSelectNow.options.add(objOption);
    var value=objSelectNow.options[objSelectNow.selectedIndex].value;
    i++;
    list.push(value);
    </#list>
    <#--var objSelectNow=document.getElementById("${klass.klassVOList.klassName}");-->
    // var inner="<option value='1'>1(默认)</option>";
    // objSelectNow.innerHTML=inner;
    // var objOption = document.createElement("OPTION");
    // objOption.text= 2;
    // objOption.value=2;
    // objSelectNow.options.add(objOption);

    function modify(){
        jQuery.ajax({
            type:"POST",
            url:"/cm/teacher/course/setting",
            headers:{"contentType":"application/json"},
            processData:false,
            // data:$('#setRound').serialize(),
            data:{"roundset":list,"RoundVO":${RoundVO}},
            dataType:"json",
            complete:function(data){
                if(data.status==200)
                    window.location="/cm/teacher/course/setting";
            }
        })
        confirm("修改轮次成功！");
    }
</script>
</body>
</html>