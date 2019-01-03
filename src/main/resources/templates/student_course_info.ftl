<!--�γ���Ϣ-->
<!DOCTYPE html>
<html lang="ch">
<head style="font-size: 35px">
	<meta name="viewport"
		  content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0"
		  charset="GB2312">
	<link rel="stylesheet" href="../static/seminar.css" charset="GB2312"/>
	<title>�γ���Ϣ</title>
</head>
<body>
<center>
	<!--ͷ������һ������Ҫ����-->
	<div id="header1">
		<center>
	            <span>
	                <b><</b>
	            </span>
			${curCourse.courseName}
			<span1>
				<b><li class="dao li1">+
						<ul class="sub sub1">
							<a href="/cm/student/notification"><li class="main">����</li></a>
							<a href="/cm/student/index"><li class="main">����ҳ</li></a>
							<a href="/cm/student/course/seminar/"><li class="main">���ۿ�</li></a>
						</ul>
					</li>
				</b>
			</span1>
		</center>
	</div>
	<div class="div3">
		�γ�Ҫ��
	</div>
	<div class="div2">
		<p4>�ɼ��������</p4>
		<table class="table_d2" cellspacing="" cellpadding="">
			<tr>
				<td class="c">����չʾ</td>
				<td class="c"></td>
				<td class="c">${curCourse.presentationPercentage}</td>
			</tr>
			<tr>
				<td class="c">��������</td>
				<td class="c"></td>
				<td class="c">${curCourse.questionPercentage}</td>
			</tr>
			<tr>
				<td class="c">���汨��</td>
				<td class="c"></td>
				<td class="c">${curCourse.reportPercentage}</td>
			</tr>
		</table>
	</div>
	<div class="div3">
		<p4>��Ա����Ҫ��</p4>
	</div>
	<div class="div2">
		<table class="table_d2"cellspacing=""cellpadding="">
			<tr>
				<td class="c">С��������(���鳤)</td>
				<td class="c"></td>
				<td class="c">${TeamNeedVO.teamMemberLimitStrategy.minMember}-${TeamNeedVO.teamMemberLimitStrategy.maxMember}</td>
			</tr>
			<tr><td class="c">����ѡ�޿γ�����</td></tr>
			<#list TeamNeedVO.CourseMemberLimitStrategyList as course>
				<tr>
					<td class="c">${course.courseId}</td>
					<td class="c"></td>
					<td class="c">${course.minMember}-${course.maxMember}</td></tr>
			</#list>
		</table>
	</div>
	<div class="div3"><p4>��ͻ�γ�</p4></div>
	<div class="div2">
		<#list TeamNeedVO.ConflictCourseStrategyList as courses>
			<div style="border: 1px solid #e8e8e8">
				<table class="table_d2" cellspacing="" cellpadding="">
					<tr>
						<#list courses as course>
							<td class="c">${course.courseId}</td>
						</#list>
					</tr>
				</table>
			</div>
		</#list>
		</br>
	</div>
	<div class="div2">
		<table class="table_d2" cellpadding="" cellspacing="">
			<tr><td class="c">��ӿ�ʼʱ��</td>
				<td class="c"></td>
				<td class="c">${curCourse.teamStartTime}</td>
			</tr>
			<tr><td class="c">��ӽ�ֹʱ��</td>
				<td class="c"></td>
				<td class="c">${curCourse.teamEndTime}</td>
			</tr>
		</table>
	</div>

</center>

</body>
</html>