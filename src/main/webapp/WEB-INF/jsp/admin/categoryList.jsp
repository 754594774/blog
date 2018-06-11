<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分类列表</title>
    <%@include file="../common/include.jsp" %>
    <script type="text/javascript" src="${rc.contextPath}/js/admin/categoryList.js"></script>
    <style>
        html body{margin-top: 0px;}
    </style>
</head>
<body>
<div class="layui-card">
    <div class="layui-card-header" style="background-color: #c2c2c2">分类列表</div>
    <div class="layui-card-body">
        <div class="layui-btn-group demoTable">
            <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="addCategory">
                <i class="layui-icon">&#xe654;</i>
            </button>
            <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="changeCategory">
                <i class="layui-icon">&#xe642;</i>
            </button>
            <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="delCategory">
                <i class="layui-icon">&#xe640;</i>
            </button>
            <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="reload">
                <i class="layui-icon">&#xe666;</i>
            </button>
        </div>
        <table id="categoryList" lay-filter=""></table>
    </div>
</div>
</body>
</html>
