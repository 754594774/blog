layui.use(('form'), function(){
    setParentIframeHeight();
    var form = layui.form
        ,$ = layui.jquery;

    form.on('submit(contactMe)', function(data){
        $.ajax({
            url: "addLetter",
            data: {
                userName:data.field.name,
                contactWay:data.field.contactInfo,
                content:data.field.msg
            },
            type: "POST",
            success: function(response){
                if(response.errNo == 0){
                    alert("成功!", response.errMsg, "success")
                    $("#msg").val("");
                }else {
                    alert("失败!", response.errMsg, "error")
                }
            },
            error: function () {
                alert("发送失败", "系统内部错误！", "error");
            }
        });
        return false;
    });

});


