var TT = TAOTAO = {
    checkLogin: function () {
        var _ticket = $.cookie("TT_TOKEN");
        if (!_ticket) {
            return;
        }
        $.ajax({
            url: "http://localhost:8084/user/token/" + _ticket,
            dataType: "jsonp",
            type: "GET",
            success: function (data) {
                if (data.success) {
                    var username = data.data.username;
                    var html = username + '，欢迎来到淘淘！<a href="#"  class="link-logout">[退出]</a>';
                    $("#loginbar").html(html);
                }
            }
        });
    }
}

$(function () {
    // 查看是否已经登录，如果已经登录查询登录信息
    TT.checkLogin();
    var _ticket = $.cookie("TT_TOKEN");
    $('a.link-logout').live('click',function () {
        $.ajax({
            url: href = "http://localhost:8084/user/logout/" + _ticket,
            dataType: "jsonp",
            type: "GET",
            success: function (data) {
                if (data.success) {
                    alert("成功退出！");
                    location.href = "http://localhost:8082";
                }
            }
        })
    })


});