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
<center>
<div style="width:80%">
	<#list scoreDetails as roundScore>
		<details>
			<!--�ö��ִε����ۿ�-->
			<summary  id="sumbackground">��${roundScore.roundNumber}��-${roundScore.totalScore}</summary>
			<details>
				<!--ÿ�ֲ�ͬ���ۿ����ֺͷ���-->
				<#list roundScore.simpleSeminarScoreVOList as seminarScore>
					<summary id="sumbackground-summary">${seminarScore.seminarName}</summary>
					<div  id="backcolor">
						<table>
							<tr>
								<td>չʾ��<font color="#9ACD32">${seminarScore.presentationScore}</font></td>
								<td>���ʣ�<font color="#9ACD32">${seminarScore.questionScore}</font></td>
								<td>���汨�棺<font color="#9ACD32">${seminarScore.reportScore}</font></td>
							</tr>
						</table>
						<br/>
					</div>
				</#list>
			</details>
		</details>
	</#list>
</div>
	<button class="submit">�����ɼ�</button>
</center>
</body>
</html>