layui.extend({
    admin: '{/}../../static/js/admin'
});
layui.use(['table', 'jquery', 'form', 'admin'], function () {
    var table = layui.table,
        $ = layui.jquery,
        form = layui.form;

    table.render({
        elem: '#commentList',
        cellMinWidth: 80,
        url: '/api/admin/comment/listComment',
        method: 'GET',
        title: '我的评论',
        cols: [
            [{
                field: 'id', title: 'ID', align: 'center', sort: false, width: '5%'
            }, {
                field: 'content', title: '评论内容', templet: '#usernameTpl', width: '45%', align: 'center',
            }, {
                field: 'username', title: '留评论者', width: '10%', sort: true, align: 'center',
            }, {
                field: 'host', title: '评论地址', width: '10%', align: 'center',
            },  {
                field: 'parentId', title: '父评论ID', width: '10%', align: 'center',
            },  {
                field: 'article', title: '所属文章', width: '10%', align: 'center',
            }, {
                field: 'createTime', title: '评论时间', align: 'center', width: '10%'
            }]
        ],
        event: true,
        page: {
            limits: [5, 10, 15, 20, 30, 50],
            limit: 10
        },
        loading: true,
    });

    $(function () {
        form.render();
    });
});