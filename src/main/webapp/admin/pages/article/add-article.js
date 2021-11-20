layui.use(['form', 'jquery'], function () {
    var form = layui.form,
        $ = layui.jquery;

    //监听提交
    form.on('submit(add)', function (data) {
        $.post(
            "/api/admin/article/add",
            {
                "title": data.field['title'],
                "content": data.field['content'],
                "tagId": data.field['tagId']
            },
            function (data) {
                console.log(data);
                if (data && data['code'] === 0) {
                    layer.msg('发布成功！', {
                        icon: 1,
                        time: 1000
                    });
                    // 添加后关闭窗口
                    setTimeout(function () {
                        // 先得到当前iframe层的索引
                        var index = parent.layer.getFrameIndex(window.name);
                        // 再执行关闭
                        parent.layer.close(index);
                        window.parent.location.reload();
                    }, 1000);
                } else {
                    layer.msg('发布失败！', {
                        icon: 5,
                        time: 1000
                    });
                }
            }
        );
        return false;
    });
});