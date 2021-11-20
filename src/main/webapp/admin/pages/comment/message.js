layui.extend({
    admin: '{/}../../static/js/admin'
});
layui.use(['table', 'jquery', 'form', 'admin'], function () {
    var table = layui.table,
        $ = layui.jquery,
        form = layui.form,
        admin = layui.admin;

    table.render({
        elem: '#messageList',
        cellMinWidth: 80,
        url: '/api/admin/comment/listMessage',
        method: 'GET',
        title: '我的留言',
        cols: [
            [{
                field: 'content', title: '留言内容', width: '55%', align: 'center',
            }, {
                field: 'username', title: '留言者', width: '15%', sort: true, align: 'center',
            }, {
                field: 'host', title: '留言地址', width: '15%', align: 'center',
            }, {
                field: 'createTime', title: '创建时间', align: 'center', width: '15%'
            }]
        ],
        event: true,
        page: {
            limits: [5, 10, 15, 20, 30, 50],
            limit: 10
        },
        loading: true,
    });

    form.val("message", {
        "host": "hahaha"
    })

    $(function () {
        form.render();
    });
});