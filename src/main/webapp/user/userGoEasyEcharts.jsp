<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>



    <%--javascript  方式获取sdk--%>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>



<script>

    var myChart = echarts.init(document.getElementById('main'));

    var goEasy = new GoEasy({
        appkey: "BC-ecaae94e21524c4486a88e9df5cd1ff0" //你自己的appkeys
    });
    goEasy.subscribe({
        channel: "xiaobo", //管道标识
        onMessage: function (message) {

            console.log(message.content);

            //将json字符串转化为json对象
            var data = JSON.parse(message.content);

            var option = {
                title: {
                    text: "用户注册量展示图", //标题
                    subtext:"用户信息"  //副标题
                },
                tooltip: {},  //鼠标提示
                legend: {
                    show:true,
                    data:["女","男"]  //选项
                },
                xAxis: {
                    data: data.month //横坐标
                },
                yAxis: {},  //纵坐标    自适应
                series: [{
                    name: '女',
                    type: 'bar',
                    data: data.girls
                },{
                    name: '男',
                    type: 'bar',
                    data: data.boys
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });
</script>

    <script type="text/javascript">
        $(function () {

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            $.post("${path}/user/echarts",function (data) {
                console.log(data);
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: "用户注册量展示图", //标题
                        subtext:"用户信息"  //副标题
                    },
                    tooltip: {},  //鼠标提示
                    legend: {

                        data:["女","男"]  //选项
                    },
                    xAxis: {
                        data: data.month //横坐标
                    },
                    yAxis: {},  //纵坐标    自适应
                    series: [{
                        name: '女',
                        type: 'bar',
                        data: data.girls
                    },{
                        name: '男',
                        type: 'bar',
                        data: data.boys
                    }]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            },"json")
        });
    </script>



<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 800px;height:600px;"></div>
