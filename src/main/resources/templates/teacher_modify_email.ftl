<!DOCTYPE html>
<html>
	<head style="font-size:35px">
	     <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
	    <link href="/css/login.css" type="text/css" rel="stylesheet"/>
		<title>修改邮箱</title>
	</head>
	<body>
	<script>
		 function send(){
		 	var email=document.getElementById("new_email");
			 jQuery.ajax({
				 type: "POST",
				 url: "/cm/teacher/setting/modifyEmail",
				 headers: {"contentType": "application/json"},
				 processData: false,
				 data: {"email":email},
				 dataType: "json",
				 complete: function (data) {
					 if (data.status == 200)
						 window.location = "/login/teacher";
				 }
			 })
		 }
	</script>
	    <center>
	        <div id="header1">
	        <center>
	            <span>
	                <
	            </span>
	        修改邮箱
	            
	               
	        </center>
	            
	        </div>
	    
	    <table class="table0" cellspacing="0" cellpadding="0">
	        
	        <tr><td class="c12">email:</td><td class="c14"><input class="in4" name="email" value="填写新邮箱" id="new_email"></input></td></tr>
	        
	    </table>
	    </center>
	    <center>
	    <p class="p11">邮箱格式如：user@host.domainnames</p>
	    <button class="button9" onclick="send()">确认提交</button>
	   
	    </center>
	</body>
</html>