<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<style type="text/css">
    th{
        text-align: center;
    }
</style>

    <script type="application/javascript">
        $(function () {
            $("#showAll").jqGrid({
                url:"${path}/banner/showAll",
                datatype:"json",
                styleUI:"Bootstrap",
                caption:"轮播图列表",
                autowidth: true,
                //生成分页的工具栏
                pager:"#pager",
                //每页展示条数
                rowNum:5,
                //展示每页记录数
                rowList:[4,6,8,10,12],
                //展示总条数
                viewrecords:true,
                height:"500px",
                //增删改的action路径
                editurl:"${path}/banner/edit",
                colNames:["id","名字","图片","描述","状态","上传时间"],
                colModel:[
                    {name: "id", align: "center"},
                    {name: "title", align: "center", editable: true},
                    {name: "img_path", align: "center", editable: true,edittype:"file",
                        formatter:function (cellvalue) {
                            return "<img src='${path}/banner/image/"+cellvalue+"' style='width:150px;height:80px'/>";
                        }
                    },
                    {name: "description", align: "center", editable: true},
                    {name: "status", width:80, align: "center",
                        formatter:function (cellvalue,option,row) {
                            if(cellvalue=="冻结"){
                                //展示状态
                                return "<button class='btn btn-danger' onclick='change(\""+row.id+"\",\""+cellvalue+"\")'>冻结</button>";
                            }else {
                                //不展示状态
                                return "<button class='btn btn-success' onclick='change(\""+row.id+"\",\""+cellvalue+"\")'>正常</button>";
                            }
                        }
                    },
                    {name: "up_date", align: "center"}
                ]
            });
            //增删改操作
            $("#showAll").jqGrid('navGrid','#pager',{edit:true,add:true,del:true,addtext:"添加",edittext:"编辑",deltext:"删除"},
                //执行修改操作的
                {
                    afterSubmit:function (data) {
                        //文件上传
                        $.ajaxFileUpload({
                            url:"${path}/banner/uploadBanner",
                            type:"post",
                            dataType: "JSON",
                            fileElementId:"img_path",
                            data:{id:data.responseText},
                            success:function () {
                                //刷新表单
                                $("#showAll").trigger("reloadGrid");
                            }
                        })
                        //一定要有返回值
                        return "emmmmmm";
                    },
                    closeAfterEdit:true
                },
                //执行添加操作的
                {
                    afterSubmit:function (data) {
                        //文件上传
                        $.ajaxFileUpload({
                            url:"${path}/banner/uploadBanner",
                            type:"post",
                            dataType: "JSON",
                            fileElementId:"img_path",
                            data:{id:data.responseText},
                            success:function () {
                                //刷新表单
                                $("#showAll").trigger("reloadGrid");
                            }
                        })
                        //一定要有返回值
                        return "emmmmmm";
                    },
                    closeAfterAdd:true
                },
                //删除操作
                {}
            )
        });
        //点击修改轮播图状态
        function change(id,value) {
            if(value=="正常"){
                $.ajax({
                    url:"${path}/banner/updateStatus",
                    type:"POST",
                    dataUrl:"JSON",
                    data:{"id":id,"status":"冻结"},
                    success:function (data) {
                        //刷新表单
                        $("#showAll").trigger("reloadGrid");
                        //提示框添加信息
                        $("#showMsg").html(data.message);
                        //把隐藏的提示框展示出来
                        $("#show").show();
                        //设置提示框展示的时间
                        setTimeout(function () {
                            //关闭提示框
                            $("#show").hide();
                        }, 3000);
                    }
                })
            }else {
                $.ajax({
                    url:"${path}/banner/updateStatus",
                    type:"POST",
                    dataUrl:"JSON",
                    data:{"id":id,"status":"正常"},
                    success:function (data) {
                        //刷新表单
                        $("#showAll").trigger("reloadGrid");
                        //提示框添加信息
                        $("#showMsg").html(data.message);
                        //把隐藏的提示框展示出来
                        $("#show").show();
                        //设置提示框展示的时间
                        setTimeout(function () {
                            //关闭提示框
                            $("#show").hide();
                        }, 3000);
                    }
                });
            }
        }
    </script>

<%--初始化面板--%>
<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>轮播图信息</h2>
    </div>
    <br/>
    <div>
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a class="active" href="#" aria-controls="showAll" role="tab" data-toggle="tab">轮播图信息</a></li>
        </ul>
    </div>
    <%--添加修改轮播图--%>
    <div class="panel panel-body">
        <button type="button" class="btn btn-info" >添加轮播图</button>
        <button type="button" class="btn btn-success" >修改轮播图</button>
    </div>
    <%--提示框--%>
    <div id="show" class="alert alert-warning alert-dismissible" role="alert"  style="width:200px;display: none">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong id="showMsg"></strong>
    </div>
    <%--初始化表单--%>
    <table id="showAll"></table>
    <%--分页--%>
    <div id="pager"></div>
</div>






