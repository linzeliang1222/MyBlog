layui.extend({
    admin: '{/}../../static/js/admin'
});
layui.use(['table', 'jquery', 'form', 'admin'], function () {
    var table = layui.table,
        $ = layui.jquery,
        form = layui.form;

    table.render({
        elem: '#tagList',
        cellMinWidth: 80,
        url: '/api/admin/tag/list',
        method: 'GET',
        title: '我的标签',
        cols: [
            [{
                field: 'id', title: 'ID', align: 'center', templet: '#tableId', sort: false, type: 'numbers', width: 100,
            }, {
                field: 'title', title: '标签名称', templet: '#usernameTpl', align: 'center',
            }, {
                field: 'operate', title: '操作', toolbar: '#operateTpl', unresize: true, align: 'center', width: 100,
            }]
        ],
        event: true,
        page: {
            limits: [5, 10, 15, 20, 30, 50],
            limit: 10
        },
        loading: true,
        defaultToolbar: ['filter', 'exports']
    });
    $(function () {
        form.render();
    });
});
