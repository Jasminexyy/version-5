<!DOCTYPE html>
<html>
<head style="font-size:35px;">
<meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
		<link href="/css/details.css" type="text/css" rel="stylesheet"/>
		<title>学生讨论课</title>
	</head>

<body>
<center>
    <#assign roundNames=roundAndSeminarList?keys/>
<div id="header1">
	            <span>
	                <b><</b>
	            </span>
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
                <div  class="backcolor1" onclick="goTo(${seminar.seminarId});">
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
<script type="text/javascript">
    function goTo(e) {
        jQuery.ajax({
            type:"POST",
            url:"/cm/student/seminar/List",
            headers:{"contentType":"application/json"},
            processData:false,
            // data:$('#myform').serialize(),
            data:{"seminarId":e},
            dataType:"json",
            complete:function(data){
                if(data.status==200)
                    window.location="/cm/student/seminar/info";
            }
        })
    }
</script>
</body>
</html>