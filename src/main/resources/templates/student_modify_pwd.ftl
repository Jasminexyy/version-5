<!DOCTYPE html>
<html>
	<head style="font-size:35px">
	     <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
	    <link href="login.css" type="text/css" rel="stylesheet"/> 
		<title>�޸�����</title>
	</head>
	<body>
	<script>

		function send(){
			var p0=document.getElementById("pwd0");
			var p1=document.getElementById("pwd1");
			var p2=document.getElementById("pwd2");
			if(p1!=p2)
				alert("�����������벻ͬ");
			else if(p0!=${student.password})
				alert("���벻��ȷ");
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
	        �޸�����
	        </center>
	            
	        </div>
	    <form id="myform">
	    <table class="table0" cellspacing="0" cellpadding="0">
	        <tr><td class="c11">ԭ���룺</td><td class="c13"><input class="in3" value="ԭ����" id="pwd0"></input></td></tr>
	        <tr><td class="c12">�����룺</td><td class="c14"><input class="in4" value="��д������" id="pwd1"></input></td></tr>
	        <tr><td class="c11">ȷ�����룺</td><td class="c13"><input class="in3" value="�ٴ���дȷ��" id="pwd2"></input></td></tr>
	    </table>
		</form>
	    </center>
	    <center>
	    <p class="p11">���볤��8-12λ����������֡���ĸ���������ֻ�����Ԫ��</p>
	    <button class="button9" onclick="send()">ȷ���ύ</button></br>
	   
	    </center>

	</body>
</html>