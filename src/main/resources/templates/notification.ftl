<#--����-->
<!DOCTYPE html>
<html lang="ch">
<head style="font-size: 35px">
    <meta name="viewport"
          content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0"
          charset="GB2312">
    <link rel="stylesheet" href="../static/css/seminar.css" charset="GB2312"/>
    <script src="../static/js/jquery_min.js" type="text/javascript"></script>
    <title>����</title>
</head>
<body>
<center>
    <div id="header1">
        <center>
	            <span>
	                <b><</b>
	            </span>
            ����
            <span1>
                <b><li class="dao li1">+
                    <ul class="sub sub1">
                        <a href="/cm/teacher/notification"><li class="main">����</li></a>
                        <a href="/cm/teacher/person"><li class="main">����ҳ</li></a>
                        <a href="/cm/teacher/seminar/"><li class="main">���ۿ�</li></a>
                    </ul>
                </li>
                </b>
            </span1>
        </center>
    </div>
    <#list >
        <div class="r" onclick="showOtherDiv('r_1');">${}-${}������${}</div>
    </#list>
    <div id="r_1" >
        <br/>
        <table>
            <tr><td><button class="button_r_1" id="agree" onclick="Iagree();">ͬ��</button></td>
            <td><button class="button_r" id="reject" onclick="Ireject();">�ܾ�</button></td></tr>
        </table>
    </div>
</center>
<script type="text/javascript">
    function showOtherDiv(id){
        var  obj=document.getElementById(id);
        if(obj.style.display=="none")
            obj.style.display="block";
        else
            obj.style.display="none";
    }
    function Iagree() {
        var status=1;
        jQuery.ajax({
            type:"POST",
            url:"",
            headers:{"contentType":"application/json"},
            processData:false,
            // data:$('#myform').serialize(),
            data:{"status":status},
            dataType:"json",
            complete:function(data){
                if(data.status==200)
                    window.location="/cm/teacher/person";
            }
        })
    }
    function Ireject() {
    }
</script>
</body>
</html>