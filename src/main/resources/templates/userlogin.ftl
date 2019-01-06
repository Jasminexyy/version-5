<!DOCTYPE html>
<html lang="ch">
<head style="font-size:35px">
    <link href="/css/userlogin.css" type="text/css" rel="stylesheet">
    <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0" charset="GB2312"/>
    <title>登录</title>
    <script src="/js/jquery.min.js" type="text/javascript"></script>

    <script type="text/javascript">
        function submit() {
            var account=document.getElementById("account").value;
            sessionStorage["userAccount"]=JSON.stringify(account);
            jQuery.ajax({
                type:"POST",
                url:"/cm/login",
                processData:false,
                data:$("#myform").serialize(),
                dataType:"json"
            });
        }
    </script>
</head>
<body>
<center>
    <div id="header">
        <center>课堂管理系统登录</center>
    </div>
    <form id="myform" action="/cm/login" method="post">
        <input type="text" id="account" name="account" value="用户名" onfocus="this.value='';this.onfocus='';"/><br/>
        <input type="password" name="password" value="登录密码" onfocus="this.value='';this.onfocus='';"/><br/>
        <button id="login" onclick="submit()">登 录</button></br>
    </form>
    <p style="cursor: pointer" onclick="forget()">忘记密码</p>
    <div id="bottom">
        初次登陆默认密码为123456
    </div>
</center>
<script type="text/javascript">
    function forget() {
        alert('请联系管理员重置密码!');
    }
</script>
</body>
</html>
