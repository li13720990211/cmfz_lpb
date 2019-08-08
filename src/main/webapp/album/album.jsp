<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<style type="text/css">
    th{
        text-align: center;
    }
</style>
<script>
    $(function () {
       $("#albTable").jqGrid({
           url:"${path}/album/showAll",
           datatype:"json",
           styleUI:"Bootstrap",
           caption:"专辑列表",
           autowidth: true,
           //生成分页的工具栏
           pager:"#albPager",
           //每页展示条数
           rowNum:5,
           //展示每页记录数
           rowList:[4,6,8,10,12],
           //展示总条数
           viewrecords:true,
           multiselect : false,
           height:"500px",
           //增删改的action路径
           editurl:"${path}/album/edit",
           colNames:["id","名字","封面","作者","评分","播音员","集数","简要","时间"],
           colModel:[
               {name: "id", align: "center"},
               {name: "title", align: "center", editable: true},
               {name: "cover_img", align: "center", editable: true,edittype:"file",
                   formatter:function (cellvalue) {
                       return "<img src='${path}/album/image/"+cellvalue+"' style='width:150px;height:80px'/>";
                   }
               },
               {name: "author", align: "center", editable: true},
               {name: "score", width:80, align: "center",editable: true},
               {name: "broadcast", align: "center", editable: true},
               {name: "count", align: "center"},
               {name: "content", align: "center", editable: true},
               {name: "pub_date", align: "center"}
           ],
           subGrid : true, //开启子表格支持
           //subgrid_id  子表格的Id,当开启子表格式,会在主表格中创建一个子表格，subgrid_id就是这个子表格的Id
           subGridRowExpanded : function(subgridId, rowId) {
               addSubGrid(subgridId,rowId);
           }
       });
       //增删改操作
        $("#albTable").jqGrid('navGrid','#albPager',{edit:true,add:true,del:true,addtext:"添加",edittext:"编辑",deltext:"删除"},
            {
                afterSubmit:function (data) {
                    //文件上传
                    $.ajaxFileUpload({
                        url:"${path}/album/uploadCover",
                        type:"POST",
                        datatype: "JSON",
                        fileElementId:"cover_img",
                        data:{id:data.responseText},
                        success:function () {
                            //刷新表单
                            $("#albTable").trigger("reloadGrid");
                        }
                    });
                    //这是个坑 一定要有返回值   随便返回
                    return "emmmmmmmmmmmmmm";
                },
                closeAfterEdit: true
            },
            {
                afterSubmit:function (data) {
                    //文件上传
                    $.ajaxFileUpload({
                        url:"${path}/album/uploadCover",
                        type:"POST",
                        datatype: "JSON",
                        fileElementId:"cover_img",
                        data:{id:data.responseText},
                        success:function () {
                            //刷新表单
                            $("#albTable").trigger("reloadGrid");
                        }
                    });
                    //这是个坑 一定要有返回值   随便返回
                    return "emmmmmmmmmmmmmm";
                },
                closeAfterAdd: true
            },
            {}
        )
    });

    //创建子表单
    function addSubGrid(subgridId, rowId) {
        var subgridTableId = subgridId + "table";
        var pagerId = subgridId+"pager";
        //初始化表单,分页工具栏
        $("#" + subgridId).html("<table id='" + subgridTableId+ "'/><div id='"+ pagerId + "'/>");
        //创建表单
        $("#"+ subgridTableId).jqGrid({
            url:"${path}/chapter/showAll?album_id="+rowId,
            datatype:"json",
            styleUI:"Bootstrap",
            caption:"章节列表",
            autowidth: true,
            //生成分页的工具栏
            pager:"#"+pagerId,
            //每页展示条数
            rowNum:5,
            //展示每页记录数
            rowList:[4,6,8,10,12],
            //展示总条数
            viewrecords:true,
            multiselect : false,
            height:"200px",
            //增删改的action路径
            editurl:"${path}/chapter/edit?album_id="+rowId,
            colNames:["编号","音频","大小","时长","上传时间","操作"],
            colModel:[
                {name: "id", align: "center"},
                {name: "url", align: "center",editable: true,edittype:"file"},
                {name: "size", align: "center"},
                {name: "duration", align: "center"},
                {name: "up_date", align: "center"},
                {name:"url",align:"center",
                    formatter:function (cellvalue) {
                        return "<a href='#' onclick='palyChapter(\""+cellvalue+"\")'><span class='glyphicon glyphicon-play-circle'/></a>&emsp;&emsp;&emsp;" +
                                "<a href='#' onclick='downloadChapter(\""+cellvalue+"\")'><span class='glyphicon glyphicon-download'/></a> ";
                    }
                }
            ]
        });
        /*增删改查操作*/
        $("#" + subgridTableId).jqGrid('navGrid',"#" + pagerId,
            {add : true,edit : false,del : true,addtext:"添加",deltext:"删除"},
            {},
            {
                //添加操作
                afterSubmit:function (data) {
                    //文件上传
                    $.ajaxFileUpload({
                        url:"${path}/chapter/uploadChapter?album_id="+rowId,
                        type:"POST",
                        datatype:"JSON",
                        fileElementId:"url",
                        data:{id:data.responseText},
                        success:function(data){
                            //刷新表单
                            $("#albTable").trigger("reloadGrid");
                        }
                    });
                    //一定要有返回值，返回什么都行
                    return "emmmmmmmmmmmmm";
                },
                closeAfterAdd: true
    },
            {
                afterSubmit:function (data) {
                    //刷新表单
                    $("#albTable").trigger("reloadGrid");
                    //一定要有返回值，返回什么都行
                    return "emmmmmmmmmmmmm";
                },
                closeAfterDel: true
            }
        );
    }
    //下载
    function downloadChapter(fileName) {
        location.href="${path}/chapter/downloadChapter?fileName="+fileName;
    }
    //在线播放
    function palyChapter(fileName) {
        //展示模态框
        $("#modal").modal("show");
        //播放
        $("#palyAudio").attr("src","${path}/album/music/"+fileName);
    }
</script>


<%--初始化面板--%>
<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>专辑信息</h2>
    </div>
    <br/>
    <div>
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a class="active" href="#" aria-controls="showAll" role="tab" data-toggle="tab">专辑信息</a></li>
        </ul>
    </div>
    <%--初始化表单--%>
    <table id="albTable"></table>
    <%--分页工具栏--%>
    <div id="albPager"></div>
    <%--在线播放和下载--%>
    <div id="modal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <audio id="palyAudio" src="" controls></audio>
        </div>
    </div>
</div>