<!DOCTYPE html>
<html lang="ch">
<head style="font-size:35px">
    <link href="/css/userlogin.css" type="text/css" rel="stylesheet"/>
    <meta name="viewport"
          content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0"
          charset="GB2312">
    <script src="/js/jquery_min.js" type="text/javascript"></script>
    <title>ѧ������</title>
</head>
<body>
<center>
    <div id="header">
        <span><</span>
        <center>��������</center>
    </div>
    <form action="/cm/student/activation" method="post">
        <input class="input1" type="text" name="password" value="��������" onfocus="this.value='';this.onfocus='';"onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9_ \.]/g,'');"/><br/>
        <input class="input1" type="text" name="password1" value="ȷ������" onfocus="this.value='';this.onfocus='';"onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9_ \.]/g,'');"/><br/>
        <input class="input1" type="text" name="email" value="��д����" onfocus="this.value='';this.onfocus='';"onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9_\@^a-z\.]/g,'');"/><br/>
        <p1>�ɰ������֡��»��ߡ���ĸ�����Ȳ���С��6λ</p1></br>
        <button type="submit" class="submit">ȷ���ύ</button>
    </form>
</center>
</body>
</html>