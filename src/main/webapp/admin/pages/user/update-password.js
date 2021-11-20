layui.use(['form', 'jquery'], function () {
    var form = layui.form,
        $ = layui.jquery;

    var flag = true;
    $("#oldPassword").blur(function () {
        $.post(
            "/api/admin/user/checkPassword",
            {
                "oldPassword": $("#oldPassword").val()
            },
            function (data) {
                if (data['code'] === 0) {
                    $("#oldPassword").css("borderColor", "#e6e6e6");
                    flag = true;
                } else {
                    flag = false;
                    layer.msg('原密码错误！', {
                        icon: 2,
                        time: 1000
                    });
                    $("#oldPassword").css("borderColor", "red");
                }
            }
        )
    });

    //监听提交
    form.on('submit(updatepassword)', function (data) {

        if ($("#repeatNewPassword").val() != $("#newPassword").val()) {
            layer.msg('重复密码错误！', {
                icon: 2,
                time: 1000
            });
            $("#repeatNewPassword").css("borderColor", "red");
            return false;
        } else {
            $("#repeatNewPassword").css("borderColor", "#e6e6e6");
        }

        $.post(
            "/api/admin/user/updatePassword",
            {
                "newPassword": $("#newPassword").val()
            },
            function (data) {
                if (data && data['code'] === 0 && flag) {
                    layer.msg('更新成功！', {
                        icon: 1,
                        time: 1000
                    });
                    // 更新后重新加载页面
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);
                } else {
                    layer.msg('更新失败！', {
                        icon: 2,
                        time: 1000
                    });
                }
            }
        );
        return false;
    });
});