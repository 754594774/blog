layui.use(['table','layer'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var $ = layui.jquery;

    //第一个实例
    var tableIns = table.render({
        elem: '#articleList'
        ,id:'articleList'
        ,height: 'pull'//高度自适应
        ,url: 'admin/getArticleListDate' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {type:'checkbox'}
            ,{field: 'id', title: 'ID',  sort: true}
            ,{field: 'title', title: '标题'}
            ,{field: 'author', title: '作者'}
            ,{field: 'isStick', title: '置顶'}
            ,{field: 'allowComment', title: '禁止评论'}
            ,{field: 'gmtCreate', title: '创建时间', sort: true}
            ,{field: 'gmtModified', title: '修改时间', sort: true}
        ]]
    });

    var $ = layui.$, active = {
        addArticle: function(){ //添加
            var checkStatus = table.checkStatus('articleList')
                ,data = checkStatus.data;
            //layer.alert(JSON.stringify(data));
            layer.open({
                type: 2,
                title: '发表新帖',
                shadeClose: false,
                shade: 0.8,
                area: ['95%', '90%'],
                content: ['admin/toArticleForm','no'],
                scrollbar: false
            });
        }
        ,changeArticle: function(){ //修改
            var checkStatus = table.checkStatus('articleList')
                ,data = checkStatus.data;
            if(data.length != 1){
                layer.alert('请选择一条记录', {icon: 0});
                return;
            }
            layer.open({
                type: 2,
                title: '发表新帖',
                shade: 0.8,
                shadeClose: false,
                area: ['95%', '90%'],
                content: ['admin/toArticleForm?articleId=' + data[0].id,'no'],
                scrollbar: false
            });
        }
        ,delArticle: function(){ //删除
            var checkStatus = table.checkStatus('articleList')
                ,data = checkStatus.data;
            if(data.length <= 0){
                layer.alert('未选择任何记录', {icon: 0});
                return;
            }
            var ids = data.map(function(v){return v.id;});
            layer.confirm("删除后不可恢复，是否确认删除？", function () {
                $.ajax({
                    url: 'admin/delCategory',
                    type: 'POST',
                    traditional:true,
                    data:{ids:ids},
                    success: function (data) {
                        if(data.errNo == 0){
                            layer.alert(data.errMsg, {
                                icon: 1, yes: function (index) {
                                    tableIns.reload();
                                    layer.close(index);
                                }
                            });
                        }else {
                            layer.alert(data.errMsg, {icon: 2});
                        }
                    },
                    error: function () {
                        layer.alert("请求失败！", {icon: 2});
                    }
                });
            });
            //layer.msg(checkStatus.isAll ? '全选': '未全选')
        }
        ,reload: function(){
            //执行重载
            tableIns.reload();
        }
    };

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});

