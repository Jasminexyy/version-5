<!DOCTYPE html>
<html>
	<head style="font-size:35px">
	     <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
	    <link href="login.css" type="text/css" rel="stylesheet"/> 
		<title>修改密码</title>
	</head>
	<body>
	<script>

		function send(){
			var p0=document.getElementById("pwd0");
			var p1=document.getElementById("pwd1");
			var p2=document.getElementById("pwd2");
			if(p1!=p2)
				alert("两次输入密码不同");
			else if(p0!=${student.password})
				alert("密码不正确");
			else {
				jQuery.ajax({
					type: "POST",
					url: "/cm/student/setting/modifyPwd",
					headers: {"contentType": "application/json"},
					processData: false,
					data: {"password":p1},
					dataType: "json",
					complete: function (data) {
						if (data.status == 200)
							window.location = "/login";
					}
				})
			}
		}
	</script>

	<center>
	        <div id="header1">
	        <center>
	            <span>
	                <
	            </span>
	        修改密码
	        </center>
	            
	        </div>
	    <form id="myform">
	    <table class="table0" cellspacing="0" cellpadding="0">
	        <tr><td class="c11">原密码：</td><td class="c13"><input class="in3" value="原密码" id="pwd0"></input></td></tr>
	        <tr><td class="c12">新密码：</td><td class="c14"><input class="in4" value="填写新密码" id="pwd1"></input></td></tr>
	        <tr><td class="c11">确认密码：</td><td class="c13"><input class="in3" value="再次填写确认" id="pwd2"></input></td></tr>
	    </table>
		</form>
	    </center>
	    <center>
	    <p class="p11">密码长度8-12位，须包含数字、字母、符号两种或以上元素</p>
	    <button class="button9" onclick="send()">确认提交</button></br>
	   
	    </center>

	</body>
</html>