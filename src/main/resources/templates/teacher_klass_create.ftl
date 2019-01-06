<#--新建班级-->
<!DOCTYPE html>
<html lang="ch">
<head style="font-size:35px">
    <meta name="viewport"
          content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0"
          charset="GB2312">
    <link rel="stylesheet" href="/css/seminar.css" charset="GB2312"/>
    <script src="/js/jquery_min.js" type="text/javascript"></script>
    <title>新建班级</title>
</head>
<body>
<center>
    <div id="header1">
        <span><</span>
        <center>新建班级</center>
    </div>
    <form id="myForm" name="addForm" method="post">
        <div class="div6">
            <table class="table_d6" cellpadding="" cellspacing="">
                <tr>
                    <td class="c">班级</td>
                    <td class="c"><input class="input1" type="text" id="grade" value="年级" onfocus="this.value='';this.onfocus='';"></td>
                    <td class="c"><input class="input1" type="text" id="klass" value="班级" onfocus="this.value='';this.onfocus='';"></td>
                </tr>
                <tr>
                    <td class="c">上课时间</td>
                    <td class="c"><input class="input1" type="time" id="time" value="" onfocus="this.value='';this.onfocus='';"></td>
                </tr>
                <tr>
                    <td class="c">上课地点</td>
                    <td class="c"><input class="input3" type="text" id="location" value="" onfocus="this.value='';this.onfocus='';"></td>
                </tr>
                <tr>
                    <td class="c">班级学生名单</td>
                    <td class="c"><form id="uploadFile" class="upload" action="/cm/teacher/course/klass" method="post"
                                        enctype="multipart/form-data">
                            <p>
                                选择文件:<input id="studentList" type="file" name="multipartFile"/>
                            </p>
                            <p></p>
                            <p style="margin-top: 20px;">
                                <input style="" value="上传"/>
                            </p>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
        </br>
        <button class="button1" id="save" onclick="save();">保 存</button>
    </form>
</center>
<script type="text/javascript">

    function save() {
        var grade=document.getElementById("grade");
        var klass=document.getElementById("klass");
        var time=document.getElementById("time");
        var location=document.getElementById("location");
        var file=document.getElementById("studentList");
        function checkForm() {
            if(grade.value==''|klass.value==''|time.value==''|location.value==''||file.value()==null){
                alert("有项目为空!");
                return false;
            }
            else
                return true;
        };
        if(checkForm()){
            jQuery.ajax({
                type:"POST",
                url:"/cm/teacher/course/klass/create",
                headers:{"contentType":"application/json"},
                processData:false,
                // data:$('#myform').serialize(),
                data:$('#myForm').serialize(),
                dataType:"json",
                complete:function(data){
                    if(data.status==200)
                        window.location="/cm/teacher/course/klassList/teacher_klassList";
                }
            })
            confirm("创建成功！");
        }
    }
</script>
</body>
</html>