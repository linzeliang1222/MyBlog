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
        url: '/api/admin/link/list',
        method: 'GET',
        title: '我的友链',
        cols: [
            [{
                field: 'id', title: 'ID', align: 'center', templet: '#tableId', sort: false, type: 'numbers', width: 100,
            }, {
                field: 'linkName', title: '名称', align: 'center',
            }, {
                field: 'linkSite', title: '网址', align: 'center',
            }, {
                field: 'linkAvatar', title: '头像', align: 'center',
            }, {
                field: 'linkDescription', title: '描述', align: 'center',
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

    window.member_del = function (obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.post(
                "/api/admin/link/delete/" + id,
                {},
                function (data) {
                    if (data && data['code'] === 0) {
                        $(obj).parents("tr").remove();
                        layer.msg('已删除！', {
                            icon: 1,
                            time: 1000
                        });
                    } else {
                        layer.msg('删除失败！', {
                            icon: 2,
                            time: 1000
                        });
                    }
                }
            );
        });
    }
});
