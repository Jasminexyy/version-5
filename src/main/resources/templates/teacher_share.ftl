<!DOCTYPE html>
<html>
	<head>
	<head style="font-size:35px;">
		<meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
		<title>��ʦ��������</title>
		<link href="/css/share.css" type="text/css" rel="stylesheet"/>
		<script src="/js/jquery.min.js"></script>
	</head>
	<body>
	<center>
		<div id="header1">
	            <span>
	                <b><</b>
	            </span>
	        ��������
	            <span1>
	                <b><li class="dao li1">+
						<ul class="sub sub1">
							<a href="/cm/teacher/notification">	<li class="main">����</li></a>
							<a href="/cm/teacher/setting"><li class="main">����ҳ</li></a>
							<a href="/cm/teacher/seminar"><li class="main">���ۿ�</li></a>
						</ul>
					</li>
					</b>
					</span1>	            
	        </div>
		<#list shareCourseList as shareCourse>
			<div class="backcolor">
				<details>
					<summary>${shareCourse.courseName}(${shareCourse.teacherName}��ʦ)</summary>
					<table>
						<tr>
							<td class="color">�������ͣ�</td>
							<td>�������</td><td></td>
						</tr>
						<tr>
							<td class="color">���������</td>
							<td>
								${shareCourse.shareRelation}
								<#if shareCourse.shareRelation==1>
									���γ�
								<#elseif shareCourse.shareRelation==2>�ӿγ�
								</#if>
							</td><td></td>
						</tr>
						<tr><td></td><td></td>
							<td><input class="cancel" value="ȡ������" onclick=""/></td>
						</tr>
					</table>
				</details>
			</div>
			<br/>
		</#list>
		<br/>
		<br/>
		<input class="green" value="��������" onclick="createShare();"/>
	</center>
	</body>
<script type="text/javascript">
	function createShare() {
		jQuery.ajax({
			type:"POST",
			url:"",
			headers:{"contentType":"application/json"},
			processData:false,
			// data:$('#myform').serialize(),
			dataType:"json",
			complete:function(data){
				if(data.status==200)
					window.location="/cm/teacher/course/shareCreate";
			}
		})
	}
</script>
</html>
