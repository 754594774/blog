//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use(['element', 'layer','util'], function(){
    var $ = layui.jquery; //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
    var element = layui.element
        ,layer = layui.layer
        ,util = layui.util;

    //执行
    util.fixbar({
        bar1: true
        ,click: function(type){
            console.log(type);
            if(type === 'bar1'){
                alert('点击了bar1')
            }
        }
    });

    $('.fly-search').on('click', function(){
        var searchContent = $('#searchContent').val();
        jump("toArticleList?searchContent=" + searchContent,0)

    });
});