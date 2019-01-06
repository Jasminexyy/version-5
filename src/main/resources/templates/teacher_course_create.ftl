<#--新建课程-->
<!DOCTYPE html>
<html lang="ch">
<head style="font-size: 35px">
    <link rel="stylesheet" href="/css/seminar.css" charset="GB2312"/>
    <script src="/js/jquery.min.js" type="text/javascript"></script>
    <title>新建课程</title>
    <script type="text/javascript">
        function publish() {
            $.ajax({
                type:"POST",
                url:"/cm/teacher/course/create",
                processData:false,
                // data:$('#addForm').serialize(),
                data: $('#myForm').serialize(),
                contentType:"application/x-www-form-urlencoded",
                complete:function(data){
                    if(data.status==200){
                        confirm("创建成功！");
                        window.location="/cm/teacher/course/courselist";
                    }
                }
            })
        }
    </script>
</head>
<body>
<center>
    <div id="header1">
        <span>X</span>
        <center>新建课程</center>
    </div>
    <form id="myForm">
        <input class="input_for_course" id="course_name" type="text" placeholder="课程名称" onfocus="this.value='';this.onfocus='';">
        <textarea class="textarea_for_course" id="course_ask" type="text" placeholder="课程要求" onfocus="this.value='';this.onfocus='';"></textarea>

        <input class="button1" type="submit" value="发布" onclick="publish()"/>
    </form>
</center>
</body>
</html>