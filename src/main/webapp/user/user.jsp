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
        $("#showAllUser").jqGrid({
            url:"${path}/user/showAll",
            datatype:"json",
            styleUI:"Bootstrap",
            caption:"用户列表",
            autowidth: true,
            //生成分页的工具栏
            pager:"#userPager",
            //每页展示条数
            rowNum:5,
            //展示每页记录数
            rowList:[4,6,8,10,12],
            //展示总条数
            viewrecords:true,
            height:"500px",
            //增删改的action路径
            editurl:"${path}/user/edit",
            colNames:["id","头像","名字","法名","密码","性别","状态","手机号","注册时间"],
            colModel:[
                {name: "id", align: "center"},
                {name: "pic_img", align: "center", editable: true,edittype:"file",
                    formatter:function (cellvalue) {
                        return "<img src='${path}/user/image/"+cellvalue+"' style='width:150px;height:80px'/>";
                    }
                },
                {name: "name", align: "center"},
                {name: "ahama", align: "center"},
                {name: "password", align: "center"},
                {name: "sex", align: "center"},
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
                {name: "phone", align: "center"},
                {name: "reg_date", align: "center"}
            ]
        });
        //增删改操作
        $("#showAllUser").jqGrid('navGrid','#userPager',{edit:false,add:false,del:true,addtext:"添加",edittext:"编辑",deltext:"删除"},
            //执行修改操作的
            {},
            //执行添加操作的
            {},
            //删除操作
            {}
        )
    });

    //点击修改轮播图状态
    function change(id,value) {
        if(value=="正常"){
            $.ajax({
                url:"${path}/user/updateStatus",
                type:"POST",
                dataUrl:"JSON",
                data:{"id":id,"status":"冻结"},
                success:function (data) {
                    //刷新表单
                    $("#showAllUser").trigger("reloadGrid");
                }
            })
        }else {
            $.ajax({
                url:"${path}/user/updateStatus",
                type:"POST",
                dataUrl:"JSON",
                data:{"id":id,"status":"正常"},
                success:function (data) {
                    //刷新表单
                    $("#showAllUser").trigger("reloadGrid");
                }
            });
        }
    };

    //导出用户信息
    function easyPoi() {
        $.ajax({
            url:"${path}/user/easyPoi",
            type:"post",
            datatype:"JSON",
            success:function (data) {
                alert(data.message);
            }
        })
    }

    //添加用户
    function addOne() {
        $.ajax({
            url:"${path}/user/addOne",
            type:"post",
            dataType:"json",
            success:function () {
                //刷新表单
                alert("添加成功");
                $("#showAllUser").trigger("reloadGrid");
            }
        })
    }
    //点击向手机发送验证码
    function phoneCode() {
        $.ajax({
            url:"${path}/user/phoneCode",
            type:"post",
            dataType:"json",
            success:function () {}
        })
    }

</script>



<%--初始化面板--%>
<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>
    <br/>
    <div>
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a class="active" href="#" aria-controls="showAll" role="tab" data-toggle="tab">用户信息</a></li>
        </ul>
    </div>
    <%--添加修改用户信息--%>
    <div class="panel panel-body">
        <button onclick="easyPoi()" type="button" class="btn btn-info" >导出用户信息</button>
        <button onclick="addOne()" type="button" class="btn btn-info" >添加用户</button>
        <button onclick="phoneCode()" type="button" class="btn btn-success" >点击发送验证码</button>
    </div>
    <%--初始化表单--%>
    <table id="showAllUser"></table>
    <%--分页--%>
    <div id="userPager"></div>
</div>