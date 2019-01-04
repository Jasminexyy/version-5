<!DOCTYPE html>
<html>
<head style="font-size:35px;">
<meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
	<link href="/css/details.css" type="text/css" rel="stylesheet"/>
	<script src="/js/jquery.min.js"></script>
	<title>�ҵĳɼ�</title>
</head>
<body>
<center>
<div id="header1" style="background-color:#ffffff;">
	            <span>
	                <b><</b>
	            </span>
	        �ҵĳɼ�
	            <span1>
	                <b><li class="dao li1">+
							<ul class="sub sub1">
								<a href="/cm/student/person"><li class="main">����ҳ</li></a>
								<a href="/cm/student/seminar"><li class="main">���ۿ�</li></a>
							</ul></li>
					</b>
					</span1>	            
	        </div> 
</center>
<#assign roundNames=scoreDetails?keys/>
<center>
<div style="width:80%">
	<#list roundNames as roundName>
		<#assign seminarScoreVO=scoreDetails[roundName]/>
		<#assign seminarScore=seminarScoreVO.simpleSeminarScoreVO/>
		<details>
			<!--�ö��ִε����ۿ�-->
			<summary  id="sumbackground">${roundName}</summary>
			<details>
				<!--ÿ�ֲ�ͬ���ۿ����ֺͷ���-->
				<#list seminarScore as seminar>
					<summary id="sumbackground-summary">${seminar.seminarName}</summary>
					<div  id="backcolor">
						<table>
							<tr>
								<td>չʾ��<font color="#9ACD32">5.0</font></td>
								<td>���ʣ�<font color="#9ACD32">5.0</font></td>
								<td>���汨�棺<font color="#9ACD32">5.0</font></td>
								<td><font color="#FF0000">5.0</font></td>
							</tr>
						</table>
						<span class="right" style="margin-right:15%"> ���ֳɼ���<font color="#FF0000">5.0</font></span>
						<br/>
					</div>
				</#list>
			</details>
		</details>
	</#list>
</div>
	<button type="submit" class="submit">�����ɼ�</button>
</center>

</body>
</html>