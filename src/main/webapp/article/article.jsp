<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script charset="UTF-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.create("#editor_id", {
        uploadJson: "${path}/editor/upload",
        filePostName: "photo",
        allowFileManager: true,
        fileManagerJson: "${path}/editor/showAllPhoto",
        afterChange: function () {  //编辑器失去焦点(blur)时执行的回调函数。
            this.sync();  //将编辑器中的内容同步到表单
        }
    })
</script>


<style type="text/css">
    th{
        text-align: center;
    }
</style>
<script type="application/javascript">
    $(function () {
        $("#showAllArticle").jqGrid({
            url:"${path}/article/showAll",
            datatype:"JSON",
            styleUI:"Bootstrap",
            caption:"用户列表",
            autowidth: true,
            //生成分页的工具栏
            pager:"#articlePager",
            //每页展示条数
            rowNum:5,
            //展示每页记录数
            rowList:[4,6,8,10,12],
            //展示总条数
            viewrecords:true,
            height:"500px",
            //增删改的action路径
            editurl:"${path}/article/edit",
            colNames:["id","封面","标题","上传时间","状态","上师ID","内容","操作"],
            colModel:[
                {name: "id", align: "center"},
                {name: "insert_img", align: "center", editable: true,edittype:"file",
                    formatter:function (cellvalue) {
                        return "<img src='${path}/article/image/"+cellvalue+"' style='width:150px;height:80px'/>";
                    }
                },
                {name: "title", align: "center"},
                {name: "up_date", align: "center"},
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
                {name: "guru_id", align: "center"},
                {name: "content", align: "center",hidden:true},
                {name: "caozuo", align: "center",
                    formatter:function (cellvalue) {
                        return "<a href='#' onclick='deleteOne(\""+cellvalue+"\")'><span class='glyphicon glyphicon-th-list'/></a>"
                    }
                }
            ]
        });
        //增删改操作
        $("#showAllArticle").jqGrid('navGrid','#articlePager',{edit:false,add:false,del:true,addtext:"添加",edittext:"编辑",deltext:"删除"},
            //执行修改操作的
            {},
            //执行添加操作的
            {

            },
            //删除操作
            {}
        )
    });

    //点击修改文章状态
    function change(id,value) {
        if(value=="正常"){
            $.ajax({
                url:"${path}/article/updateStatus",
                type:"POST",
                dataUrl:"JSON",
                data:{"id":id,"status":"冻结"},
                success:function (data) {
                    //刷新表单
                    $("#showAllArticle").trigger("reloadGrid");
                }
            })
        }else {
            $.ajax({
                url:"${path}/article/updateStatus",
                type:"POST",
                dataUrl:"JSON",
                data:{"id":id,"status":"正常"},
                success:function (data) {
                    //刷新表单
                    $("#showAllArticle").trigger("reloadGrid");
                }
            });
        }
    };

    //点击展开文章详细信息(可修改)
    $("#updateArtile").click(function () {
        //选中一行 获取行id
        var rowId = $("#showAllArticle").jqGrid("getGridParam","selrow");
        //判断是否选中一行
        if(rowId!=null){
            //根据行id获取该行数据
            var row = $("#showAllArticle").jqGrid("getRowData",rowId);

            //展示模态框
            $("#myModal").modal("show");
            //给inout框设置内容
            $("#title").val(row.title);
            //给KingEditor设置内容
            KindEditor.html("#editor_id",row.content);
            //给模态框添加按钮
            $("#modalButton").html("<button type='button' onclick='updateArticle(\""+rowId+"\")' class='btn btn-default'>提交</buttont><button type='button' class='btn btn-primary' data-dismiss='modal'>关闭</buttont> ")
        }else {
            alert("请选中一行数据");
        }
    });

    //点击添加文章
    $("#addArticle").click(function () {
        //给表单置空   添加文章信息不需要回显
        $("#articleFrom")[0].reset();

        //给KindEditor框置空
        KindEditor.html("#editor_id","");

        //展示模态框
        $("#myModal").modal("show");
        //给模态框添加按钮
        $("#modalButton").html("<button type='button' onclick='addArticle()' class='btn btn-default'>添加</buttont><button type='button' class='btn btn-primary' data-dismiss='modal'>关闭</buttont> ")
    });

    // 点击按钮操作提交信息
    function addArticle() {
        //向后台提交
        $.ajax({
            url:"${path}/article/addOne",
            type:"post",
            dateType:"json",
            data:$("#articleFrom").serialize(),//像后台传送表单中所有的信息
            success:function (data) {
                $("#myModal").modal('hide');//隐藏模态框
                $("#showAllArticle").trigger("reloadGrid");//刷新jqGrid
                //上传封面
                $.ajaxFileUpload({
                    url:"${path}/article/upload",
                    type:"post",
                    dataType: "JSON",
                    fileElementId:"upload",
                    data:{id:data},
                    success:function () {
                        alert("哈哈哈");
                        //刷新表单
                        $("#showAllArticle").trigger("reloadGrid");
                    }
                })
                //一定要有返回值
                return "emmmmmm";
            }
        })
    }
    //点击删除文章
    $("#delArticle").click(function () {
        //选中一行 获取行id
        var rowId = $("#showAllArticle").jqGrid("getGridParam","selrow");
        if(rowId!=null){
            $.ajax({
                url:"${path}/article/deleteOne?id="+rowId,
                type:"POST",
                dataUrl:"JSON",
                success:function (data) {
                    //刷新表单
                    $("#showAllArticle").trigger("reloadGrid");
                }
            })
        }else {
            alert("请选中一行数据");
        }
    });

    //点击修改的提交操作
    function updateArticle(rowId) {
        var insert_img = $("insert_img").prop("name");
        //向后台提交
        $.ajax({
            url:"${path}/article/updateOne?id="+rowId,
            type:"post",
            dateType:"JSON",
            data:$("#articleFrom").serialize(),//像后台传送表单中所有的信息
            success:function (data) {
                //隐藏模态框
                $("#myModal").modal("hide");
                //刷新表格
                $("#showAllArticle").trigger("reloadGrid")

                //上传封面
                $.ajaxFileUpload({
                    url:"${path}/article/upload",
                    type:"post",
                    dataType: "JSON",
                    fileElementId:"upload",
                    data:{id:data},
                    success:function () {
                        //刷新表单
                        $("#showAllArticle").trigger("reloadGrid");
                    }
                })
                //一定要有返回值
                return "emmmmmm";
            }
        })
    };


</script>



<%--初始化面板--%>
<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>文章信息</h2>
    </div>
    <br/>
    <div>
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a class="active" href="#" aria-controls="showAll" role="tab" data-toggle="tab">文章信息</a></li>
        </ul>
    </div>
    <%--添加修改用户信息--%>
    <div class="panel panel-body">
        <button id="updateArtile" type="button" class="btn btn-success" >查看文章</button>
        <button id="addArticle" type="button" class="btn btn-info" >添加文章</button>
        <button id="delArticle" type="button" class="btn btn-danger" >删除文章</button>
    </div>
    <%--初始化表单--%>
    <table id="showAllArticle"></table>
    <%--分页工具栏--%>
    <div id="articlePager"></div>
</div>
<%--初始化模态框--%>
<div id="myModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document" style="width: 730px">
        <div class="modal-content">
            <%--模态框标题--%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">Modal title</h4>
            </div>
            <%--模态框内容--%>
            <div class="modal-body">
                <form class="form-horizontal" id="articleFrom" enctype="multipart/form-data " method="post">

                    <div class="input-group">
                        <%--title标题--%>
                        <span class="input-group-addon" id="basic-addon1">标题</span>
                        <input id="title" name="title" type="text" class="form-control" aria-describedby="basic-addon1"/>
                        <span class="input-group-addon" id="basic-addon2">封面</span>
                        <input id="upload" name="upload" type="file" class="form-control" aria-describedby="basic-addon2"/>
                    </div><br>
                    <div class="input-group">
                        <%--初始化富文本编辑器--%>
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;">

                        </textarea>
                    </div>
                </form>
            </div>
            <%--  模态框按钮  --%>
            <div class="modal-footer" id="modalButton">
                <%--<button type="button" class="btn btn-default">提交</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>--%>
            </div>
        </div>
    </div>
</div>