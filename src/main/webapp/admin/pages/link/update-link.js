layui.use(['form', 'jquery', 'table'], function () {
    var form = layui.form,
        $ = layui.jquery;

    //监听提交
    form.on('submit(update)', function (data) {
        $.post(
            "/api/admin/link/update",
            {
                "linkId": data.field['linkId'],
                "linkName": data.field['linkName'],
                "linkSite": data.field['linkSite'],
                "linkAvatar": data.field['linkAvatar'],
                "linkDescription": data.field['linkDescription']
            },
            function (data) {
                if (data && data['code'] === 0) {
                    layer.msg('更新成功！', {
                        icon: 1,
                        time: 1000
                    });
                    // 更新后关闭窗口
                    setTimeout(function () {
                        // 先得到当前iframe层的索引
                        var index = parent.layer.getFrameIndex(window.name);
                        // 再执行关闭
                        parent.layer.close(index);
                        window.parent.location.reload();
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