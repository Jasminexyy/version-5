<!DOCTYPE html>
<html>
<head style="font-size:35px;">
		<link href="/css/details.css" type="text/css" rel="stylesheet"/>
	<script src="/js/jquery.min.js" type="text/javascript"></script>
		<title>学生讨论课</title>
	<script type="text/javascript">
		function goTo(e,e1) {
			var da=e.value;
			var da1=e1.value;
			jQuery.ajax({
				type:"GET",
				url:"/cm/student/seminar/info",
				headers:{"contentType":"application/json"},
				processData:false,
				// data:$('#myform').serialize(),
				data:"klassId="+e+"&seminarId="+e1,
				dataType:"json",
				complete:function(data){
					if(data.status==200)
						window.location="/cm/student/seminar/seminarEntrance?account=${student.account}";
				}
			});
		}
	</script>
	</head>
<body>
<center>
    <#assign roundNames=roundAndSeminarList?keys/>
<div id="header1">
	            <span><b><</b></span>
	讨论课列表
	            <span1>
					<b><li class="dao li1">+
						<ul class="sub sub1">
							<a href="/cm/student/person"><li class="main">个人页</li></a>
							<a href="/cm/student/seminar"><li class="main">讨论课</li></a>
						</ul></li>
					</b>
					</span1>	            
	        </div> 
</center>
<center>
<div>
	<#list roundNames as roundName>
        <details>
            <#assign seminarList=roundAndSeminarList[roundName]/>
            <summary class="sumbackgroundw-summary" style="background-color:#ffffff;"><font color="#9ACD32">${roundName}</font></summary>
            <#list seminarList.seminarVOList as seminar>
                <div  class="backcolor1" onclick="goTo(${klassId},${seminar.seminarId})">
                    <span class="left">${seminar.seminarOrder}</span>
                    ${seminar.seminarTopic}
                    <span class="right">></span>
                    <br/>
                </div>
            </#list>
        </details>
	</#list>
</div>
</center>
</body>
</html>