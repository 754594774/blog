layui.use('element', function(){
    var $ = layui.jquery
        ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

    //本地存储用户名 页面关闭后即失效
    layui.sessionData('user', {
        key: 'username'
        ,value: $("#username").val()
    });

});