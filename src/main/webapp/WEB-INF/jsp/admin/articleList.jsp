<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="../common/include.jsp" %>
    <script type="text/javascript" src="${rc.contextPath}/js/admin/articleList.js"></script>
    <style>
        html body{margin-top: 0px;}
    </style>
</head>
<body>

<div class="layui-card">
    <div class="layui-card-header" style="background-color: #c2c2c2">文章列表</div>
    <div class="layui-card-body">
        <div class="layui-btn-group demoTable">
            <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="addArticle">
                <i class="layui-icon" title="添加">&#xe654;</i>
            </button>
            <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="changeArticle">
                <i class="layui-icon" title="修改">&#xe642;</i>
            </button>
            <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="delArticle">
                <i class="layui-icon" title="删除">&#xe640;</i>
            </button>
            <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="reload">
                <i class="layui-icon" title="刷新">&#xe666;</i>
            </button>
        </div>
        <table id="articleList" lay-filter=""></table>
    </div>
</div>

</body>
</html>
