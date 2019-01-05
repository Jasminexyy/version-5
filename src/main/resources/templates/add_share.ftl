<!DOCTYPE html>
<html>
	<head style="font-size:35px">
	     <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
	    <link href="/css/login.css" type="text/css" rel="stylesheet"/>
		<title>新增共享</title>
	</head>

	<body>
	<script>
function send_courseId(){
	var sel=document.getElementById("selected_course");
	var index = sel.selectedIndex;
	var selected_courseId;
	for(var i=0;i<courseList.length;i++){
		if(sel.options[index].value.equals(courseList[i].name+courseList[i].teacherName)
			selected_courseId=courseList[i].id;
	}

		jQuery.ajax({
			type:"POST",
			url:"/cm/student/course/shareCreate",
			processData:false,
			data:{"courseId": selected_courseId},
			dataType: 'json',
			complete:function(data){
				if(data.status==200)
					window.location="/cm/teacher/course/share";
			}
	});
}
	</script>
	    <center>
	        <div id="header1">
	        <center>
	            <span>
	                <
	            </span>
	        新增共享      
	        </center>
	            
	        </div>
	    
	    <div id="d3">
	    
	    <p>共享类型：<select class="s1">
	    <option>共享讨论课</option>
	    <option>共享分组</option>
	    </select></p>
	    
	    </div>
	   <div id="d3">
	    
	    <p>共享对象：<select id="selected_course" class="s1">
				<#list courseList as courseVO>
	    <option>${courseVO.name} ${courseVO.teacherName}</option>
				</#list>
	    </select></p>
	    </div>
	    </center>
	    <center>
	    
	    <button class="button10" onclick="send_courseId()">确认共享</button></br>
	   
	    </center>
	</body>
</html>