var iframeId = "mainFrame";

//控制Frame跳转
function jump(url,type) {
    var urlDe = encodeURI(encodeURI(url));
    console.log(urlDe);
    if (url != null && url != '') {
        var webApp,frame;
        if(type == 0){//ifrmae所在页面调用
            webApp = document.getElementById("webApp");
            frame = document.getElementById(iframeId);
            frame.src = urlDe;
        }
        if(type== 1){//iframe子页面调用
            webApp = parent.document.getElementById("webApp");
            frame = parent.document.getElementById(iframeId);
            frame.src = urlDe;
        }

    }
}

// 日期时间的过滤
//传入时间戳,转为特定格式的字符串
//默认格式  "yyyy-MM-dd hh:mm:ss"
function formatDateTime (gmt) { //author: meizz
    var date = new Date(gmt);
    var fmt = "yyyy-MM-dd hh:mm:ss";
    var o = {
        "M+": date.getMonth() + 1, //月份
        "d+": date.getDate(), //日
        "h+": date.getHours(), //小时
        "m+": date.getMinutes(), //分
        "s+": date.getSeconds(), //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;

}