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
    <!-- 引入 ECharts 文件 -->
    <script src="${path}/js/echarts.js"></script>

    <script type="text/javascript">
        $(function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: "用户注册量展示图", //标题
                    subtext:"用户信息"  //副标题
                },
                tooltip: {},  //鼠标提示
                legend: {
                    show:true,
                    data:["男","女"]  //选项
                },
                xAxis: {
                    data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"] //横坐标
                },
                yAxis: {},  //纵坐标    自适应
                series: [{
                    name: '男',
                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20]
                },{
                    name: '女',
                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20]
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        })

    </script>
</head>
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 800px;height:600px;"></div>
</body>
</html>