<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

    <!-- 引入 ECharts 文件 -->
    <script src="${path}/js/echarts.js"></script>
</head>
<body>
<!--顶部导航-->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <ul class=class="navbar-brand">
                <a class="navbar-brand" href="#">持名法州管理系统</a>
            </ul>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <a class="navbar-brand" href="#">欢迎：<span style="color: red">${sessionScope.admin.username}</span></a>
            </li>
            <li><a href="${path}/admin/exit" class="glyphicon glyphicon-log-out">退出登录</a></li>
        </ul>
    </div>
    </div>
</nav>
<!--栅格系统-->
<div class="container-fluid">
    <div class="row">
        <!--左边手风琴部分-->
        <div class="col-sm-2" align="center">

            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-info">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                               aria-expanded="true" aria-controls="collapseOne">
                                <h3>用户管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${path}/user/user.jsp')">用户信息</a>
                                    </button>
                                </li>
                                <br/>
                                <li>
                                    <button type="button" class="btn btn-success">
                                        <a href="javascript:$('#mainId').load('${path}/user/userGoEasyEcharts.jsp')">用户统计</a>
                                    </button>
                                </li>
                                <br/>
                                <li>
                                    <button type="button" class="btn btn-warning">
                                        <a href="javascript:$('#mainId').load('${path}/user/userEchartsMap.jsp')">用户分布</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="panel panel-success">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                <h3>轮播图管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${path}/banner/banner.jsp')">轮播图信息</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="panel panel-danger">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                <h3>专辑管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingThree">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${path}/album/album.jsp')">专辑信息</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="panel panel-warning">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                <h3>上师管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingThree">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="#">上师信息</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="panel panel-success">
                    <div class="panel-heading" role="tab" id="headingFive">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                <h3>文章管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingThree">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${path}/article/article.jsp')">文章信息</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="panel panel-info">
                    <div class="panel-heading" role="tab" id="headingSix">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                                <h3>管理员管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseSix" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingThree">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button class="btn btn-info" type="button">
                                        <a href="javascript:$('#pic').load('${path}/banner/banner.jsp')">轮播图信息</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel-group col-sm-10" id="mainId">
            <!--巨幕开始-->
            <div class="jumbotron">
                <center><h1>欢迎来到持名法洲后台管理系统</h1></center>
            </div>
            <!--右边轮播图部分-->
            <div id="myCarousel" class="carousel slide" align="center">
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                    <li data-target="#myCarousel" data-slide-to="4"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="active item">
                        <img src="${path}/bootstrap/img/shouye.jpg"/>
                    </div>
                    <div class="item">
                        <img src="${path}/bootstrap/img/1.png"/>
                    </div>
                    <div class="item">
                        <img src="${path}/bootstrap/img/2.png"/>
                    </div>
                    <div class="item">
                        <img src="${path}/bootstrap/img/3.png"/>
                    </div>
                    <div class="item">
                        <img src="${path}/bootstrap/img/4.png"/>
                    </div>
                    <a class="carousel-control left" href="#myCarousel" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                    </a>
                    <a class="carousel-control right" href="#myCarousel" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<!--栅格系统-->
<!--页脚-->
<div class="panel panel-footer" align="center">
    <div>@百知教育 lpb@zparkhr.com</div>
</div>
<!--栅格系统-->

</body>
</html>
