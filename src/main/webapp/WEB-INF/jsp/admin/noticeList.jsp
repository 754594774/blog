<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>通知列表</title>
    <%@include file="../common/include.jsp" %>
    <script type="text/javascript" src="${rc.contextPath}/js/admin/noticeList.js"></script>
    <style>
        html body{margin-top: 0px;}
    </style>
</head>
<body>
    <div class="layui-card">
        <div class="layui-card-header" style="background-color: #c2c2c2">通知列表</div>
        <div class="layui-card-body">
            <div class="layui-btn-group demoTable">
                <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="addNotice">
                    <i class="layui-icon">&#xe654;</i>
                </button>
                <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="changeNotice">
                    <i class="layui-icon">&#xe642;</i>
                </button>
                <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="delNotice">
                    <i class="layui-icon">&#xe640;</i>
                </button>
                <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="reload">
                    <i class="layui-icon">&#xe666;</i>
                </button>
            </div>
            <table id="noticeList" lay-filter=""></table>
        </div>
    </div>
</body>
</html>
