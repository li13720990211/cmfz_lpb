<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%--javascript  方式获取sdk--%>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>

    <script>
        var goEasy = new GoEasy({
            appkey: "BC-ecaae94e21524c4486a88e9df5cd1ff0" //你自己的appkeys
        });
        goEasy.subscribe({
            channel: "xiaobo", //管道标识
            onMessage: function (message) {
                alert("Channel:" + message.channel + " content:" + message.content);
            }
        });
    </script>
</head>
<body>
<h4>拉拉拉拉ahhha</h4>
</body>
</html>