<!DOCTYPE html>
<html>
<head style="font-size:35px;">
    <meta name="viewport" content="width=device-width,user-scale=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
    <link href="/css/details.css" type="text/css" rel="stylesheet"/>

    <title></title>
</head>

<body>
<center>
    <div id="header1">
	            <span>
	                <
	            </span>
        ${courseName}
        <span1>
            <li class="dao li1">+
                <ul class="sub sub1">
                    <li class="main">代办</li>
                    <li class="main">个人页</li>
                    <li class="main">讨论课</li>
                </ul>
            </li>

        </span1>
    </div>
    </div>
</center>

<center>
    <div style="width:80%">
        <#list roundList as roundVO>
        <details>
            <!--所有轮次都在这里-->
            <summary  id="sumbackgroundw">第${roundVO.roundNumber}轮</summary>
            <div  id="backcolor">
                该轮轮次设置
                <span class="right">></span>
                <br/>
            </div>
<#list roundVO.seminarVOList as seminarVO>
            <details>
                <!--每个轮次讨论课名字不一样-->
                <summary id="sumbackgroundw-summary">${seminarVO.seminarTopic}</summary>
                <#list klassList as klassVO>
                <div  id="backcolor">
                   ${klassVO.klassName}
                    <span class="right">></span>
                    <br/>
                </div>
                </#list>

            </details>
</#list>

        </details>
        </#list>
    </div>
    <button type="submit">+新建轮次</button>
    <button type="submit">+新建讨论课</button>


</center>

</body>
</html>