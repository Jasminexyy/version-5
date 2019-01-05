<#--课程信息-->
<!DOCTYPE html>
<html lang="ch">
<head style="font-size: 35px">
	<meta name="viewport"
		  content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0"
		  charset="GB2312"/>
	<link rel="stylesheet" href="/css/seminar.css" charset="GB2312"/>
	<script src="/js/jquery.min.js" type="text/javascript"></script>
	<title>课程信息</title>
</head>
<body>
<center>
	<!--头都用这一个，不要改了-->
	<div id="header1">
		<center>
	            <span>
	                <
	            </span>
			${curCourse.courseName}
			<span1>
				<li class="dao li1">+
					<ul class="sub sub1">
						<a href="/cm/teacher/notification"><li class="main">代办</li></a>
						<a href="/cm/teacher/person"><li class="main">个人页</li></a>
						<a href="/cm/teacher/seminar/"><li class="main">讨论课</li></a>
					</ul>
				</li>

			</span1>
		</center>
	</div>
	<div class="div3">
		课程要求
	</div>
	<div class="div2">
		<p4>成绩计算规则</p4>
		<table class="table_d2" cellspacing="" cellpadding="">
			<tr>
				<td class="c">课堂展示</td>
				<td class="c"></td>
				<td class="c">${curCourse.presentationPercentage}</td>
			</tr>
			<tr>
				<td class="c">课堂提问</td>
				<td class="c"></td>
				<td class="c">${curCourse.questionPercentage}</td>
			</tr>
			<tr>
				<td class="c">书面报告</td>
				<td class="c"></td>
				<td class="c">${curCourse.reportPercentage}</td>
			</tr>
		</table>
	</div>
	<div class="div3">
		<p4>组员基本要求</p4>
	</div>

	<div class="div2">
		<table class="table_d2"cellspacing=""cellpadding="">
			<tr>
				<td class="c">小组总人数(含组长)</td>
				<td class="c"></td>
				<td class="c">${teamNeedVO.teamAndStrategyVO.memberLimitStrategyVO.minMember}-${teamNeedVO.teamAndStrategyVO.memberLimitStrategyVO.maxMember}</td>
			</tr>
			<tr><td class="c">组内选修课程人数</td></tr>
			<#list teamNeedVO.teamAndStrategyVO.teamOrStrategyVOList as teamOrStrategyVO>
				<#list teamOrStrategyVO.courseMemberLimitStrategyVOList as courseMemberLimitStrategyVO>
					<tr>
						<td class="c">${courseMemberLimitStrategyVO.courseName}</td>
						<td class="c"></td>
						<td class="c">${courseMemberLimitStrategyVO.minMember}-${courseMemberLimitStrategyVO.maxMember}</td></tr>
				</#list>
			</#list>
		</table>
	</div>
	<div class="div3"><p4>冲突课程</p4></div>
	<div class="div2">
		<#list teamNeedVO.conflictCourseStrategyVOList as conflictCourseStrategyVO>
			<div style="border: 1px solid #e8e8e8">
				<table class="table_d2" cellspacing="" cellpadding="">
					<tr>

						<td class="c">${conflictCourseStrategyVO.courseName} (${conflictCourseStrategyVO.teacherName})</td>

					</tr>
				</table>
			</div>
		</#list>
		</br>
	</div>
	<div class="div2">
		<table class="table_d2" cellpadding="" cellspacing="">
			<tr><td class="c">组队开始时间</td>
				<td class="c"></td>
				<td class="c">${curCourse.teamStartTime}</td>
			</tr>
			<tr><td class="c">组队截止时间</td>
				<td class="c"></td>
				<td class="c">${curCourse.teamEndTime}</td>
			</tr>
		</table>
	</div>

</center>
<script type="text/javascript">
	function deleteCourse() {
		jQuery.ajax({
			type:"POST",
			url:"/cm/teacher/course/${curCourse.id}",
			headers:{"contentType":"application/json"},
			processData:false,
			// data:$('#myform').serialize(),
			data:{"courseId":${curCourse.id}},
			dataType:"json",
			complete:function(data){
				if(data.status==200)
					window.location="/cm/teacher/course";
			}
		})
	}
</script>
</body>
</html>