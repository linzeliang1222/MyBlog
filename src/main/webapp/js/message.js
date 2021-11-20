layui.use(['element', 'jquery', 'form', 'layedit', 'flow'], function () {
    var element = layui.element;
    var form = layui.form;
    var $ = layui.jquery;
    var layedit = layui.layedit;
    var flow = layui.flow;
    //留言的编辑器
    var editIndex = layedit.build('remarkEditor', {
        height: 150,
        tool: ['face', '|', 'link'],
    });
    //留言的编辑器的验证
    form.verify({
        content: function (value) {
            value = $.trim(layedit.getContent(editIndex));
            if (value == "") return "请输入内容";
            layedit.sync(editIndex);
        },
        replyContent: function (value) {
            if (value == "") return "请输入内容";
        },
        username: function (value) {
            if (value == "") return "请输入昵称";
        }
    });
    //回复按钮点击事件
    $('#message-list').on('click', '.btn-reply', function () {
        var targetId = $(this).data('targetid')
            , baseId = $(this).data('baseid')
            , targetName = $(this).data('targetname')
            , $container = $(this).parent('p').parent().siblings('.replycontainer');
        if ($(this).text() == '回复') {
            $container.find('textarea').attr('placeholder', '回复【' + targetName + '】');
            $container.removeClass('layui-hide');
            $container.find('input[name="targetId"]').val(targetId);
            $container.find('input[name="baseId"]').val(baseId);
            $(this).parents('.message-list li').find('.btn-reply').text('回复');
            $(this).text('收起');
        } else {
            $container.addClass('layui-hide');
            $container.find('input[name="targetUserId"]').val(0);
            $(this).text('回复');
        }
    });

    form.on('submit(formReply)', function (data) {
        $.post(
            "/api/comment/message/reply",
            {
                "commentUsername": data.field['username'],
                "commentContent": data.field['replyContent'],
                "commentParentId": data.field['targetId'],
                "commentBaseId": data.field['baseId']
            },
            function (data) {
                if (data && data['code'] === 0) {
                    layer.msg(
                        '回复成功！', {
                            icon: 1,
                            time: 1000
                        });
                } else {
                    layer.msg(
                        '回复出错！', {
                            icon: 5,
                            time: 1000
                        });
                }
            }
        );
        return false;
    });

    form.on('submit(formLeaveMessage)', function (data) {
        $.post(
            "/api/comment/message/add",
            {
                "commentUsername": data.field['username'],
                "commentContent": data.field['editorContent']
            },
            function (data) {
                if (data && data['code'] === 0) {
                    layer.msg(
                        '留言成功！', {
                            icon: 1,
                            time: 1000
                        });
                } else {
                    layer.msg(
                        '留言出错！', {
                            icon: 5,
                            time: 1000
                        });
                }
                setTimeout(function () {
                    window.parent.location.reload();
                }, 1000);
            }
        );
        return false;
    });
});

