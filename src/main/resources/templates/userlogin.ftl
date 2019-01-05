<!DOCTYPE html>
<html lang="ch">
<head style="font-size:35px">
    <link href="/css/userlogin.css" type="text/css" rel="stylesheet">
    <title>登录</title>
    <script src="/js/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<center>
    <div id="header">
        <span>X</span>
        <center>课堂管理系统登录</center>
    </div>
    <form action="/cm/login" method="post">
        <input type="text" name="account" value="用户名" onfocus="this.value='';this.onfocus='';"/><br/>
        <input type="password" name="password" value="登录密码" onfocus="this.value='';this.onfocus='';"/><br/>
        <button id="login" type="submit" >登 录</button></br>
    </form>
    <div id="bottom">
        初次登陆默认密码为123456
    </div>
</center>
<script type="text/javascript">
    function submit() {
        jQuery.ajax({
            type:"POST",
            url:"/cm/login",//怎么定向？教师or学生
            headers:{"contentType":"application/json"},
            processData:false,
            data:$("#myform").serialize(),
            dataType:"json",
            complete:function(data){
                if(data.status==200)
                    window.location="";//
            }
        });
        return false;
    };
</script>
</body>
</html>
