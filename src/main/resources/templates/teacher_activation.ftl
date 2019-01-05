<!DOCTYPE html>
<html lang="ch">
<head style="font-size:35px">
    <link href="/css/userlogin.css" type="text/css" rel="stylesheet" charset="GB2312"/>
    <meta name="viewport"
          content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0"
          charset="GB2312">
    <script src="/js/jquery_min.js" type="text/javascript"></script>
    <title>教师激活</title>
</head>
<body>
<center>
    <div id="header">
        <span><</span>
        <center>密码设置</center>
    </div>
    <form action="/cm/teacher/activation" method="post">
        <input class="input1" type="text" name="password" value="输入密码" onfocus="this.value='';this.onfocus='';"onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9_ \.]/g,'');"/><br/>
        <input class="input1" type="text" name="password1" value="确认密码" onfocus="this.value='';this.onfocus='';"onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9_ \.]/g,'');"/><br/>
        <p1>可包含数字、字母、下划线，长度不小于6位</p1></br>
        <button type="submit" class="submit">确认提交</button>
    </form>
</center>
</body>
<html>