layui.use(['form', 'jquery', 'table'], function () {
    var form = layui.form,
        $ = layui.jquery;

    $.post(
        "/api/admin/user/get",
        {},
        function (data) {
            if (data && data['code'] === 0) {
                form.val('userForm', {
                    "userName": data['data']['userName'],
                    "userNickname": data['data']['userNickname'],
                    "userAvatar": data['data']['userAvatar'],
                    "userEmail": data['data']['userEmail'],
                    "userIntroduction": data['data']['userIntroduction']
                });
            } else {
                layer.msg('数据获取失败！', {
                    icon: 2,
                    time: 1000
                });
            }
        }
    );

    //监听提交
    form.on('submit(update)', function (data) {
        $.post(
            "/api/admin/user/update",
            {
                "userNickname": data.field['userNickname'],
                "userAvatar": data.field['userAvatar'],
                "userEmail": data.field['userEmail'],
                "userIntroduction": data.field['userIntroduction']
            },
            function (data) {
                if (data && data['code'] === 0) {
                    layer.msg('更新成功！', {
                        icon: 1,
                        time: 1000
                    });
                    // 更新后刷新窗口
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