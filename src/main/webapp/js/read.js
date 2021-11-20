layui.use(['element', 'jquery', 'form', 'layedit', 'flow'], function () {
    var element = layui.element;
    var form = layui.form;
    var $ = layui.jquery;
    var layedit = layui.layedit;
    var flow = layui.flow;

    //评论和留言的编辑器的验证
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
    $('#blog-comment').on('click', '.btn-reply', function () {
        var targetId = $(this).data('targetid')
            , baseId = $(this).data('baseid')
            , articleId = $(this).data('articleid')
            , targetName = $(this).data('targetname')
            , $container = $(this).parent('p').parent().siblings('.replycontainer');
        if ($(this).text() == '回复') {
            $container.find('textarea').attr('placeholder', '回复【' + targetName + '】');
            $container.removeClass('layui-hide');
            $container.find('input[name="targetId"]').val(targetId);
            $container.find('input[name="baseId"]').val(baseId);
            $container.find('input[name="articleId"]').val(articleId);
            $(this).parents('.blog-comment li').find('.btn-reply').text('回复');
            $(this).text('收起');
        } else if ($(this).text() == '收起'){
            $container.addClass('layui-hide');
            $container.find('input[name="targetUserId"]').val(0);
            $(this).text('回复');
        }
    });

    form.on('submit(formReply)', function (data) {
        $.post(
            "/api/comment/comment/reply",
            {
                "commentUsername": data.field['username'],
                "commentContent": data.field['replyContent'],
                "commentParentId": data.field['targetId'],
                "commentBaseId": data.field['baseId'],
                "commentArticleId": data.field['articleId']
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
});