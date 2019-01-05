<!DOCTYPE html>
<html>
<head style="font-size:35px">
    <link href="/css/login.css" type="text/css" rel="stylesheet"/>
    <script src="/js/jquery.min.js" type="text/javascript"></script>
    <title>修改邮箱</title>
</head>
<body>
<center>
    <div id="header1">
        <center>
            <a href="/cm/student/setting?account="${student.account}><span><</span></a>
            修改邮箱
        </center>
    </div>
    <table class="table0" cellspacing="0" cellpadding="0">
        <tr><td class="c12">email:</td>
            <form action="/cm/student/setting/modifyEmail" name="myform" method="post">
                <td class="c14"><input class="in4" name="email" placeholder="填写新邮箱" id="new_email"/></td>
            </form>
        </tr>
    </table>
</center>
<center>
    <p class="p11">邮箱格式如：user@host.domainnames</p>
    <button class="button9" onclick="send()" >确认提交</button>
</center>
<script>
    function send(){
        var email="email="+document.getElementById("new_email").value;
        jQuery.ajax({
            type: "POST",
            url: "/cm/student/setting/modifyEmail",
            processData: false,
            data:email,
            dataType: "json",
            complete: function (data) {
                if (data.status == 200)
                    window.location = "/cm/student/index?account=${student.account}";
            }
        });
    }
</script>
</body>
</html>