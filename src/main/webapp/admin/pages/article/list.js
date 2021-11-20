layui.extend({
    admin: '{/}../../static/js/admin'
});
layui.use(['table', 'jquery', 'form', 'admin'], function () {
    var table = layui.table,
        $ = layui.jquery,
        form = layui.form,
        admin = layui.admin;

    table.render({
        elem: '#articleList',
        cellMinWidth: 80,
        url: '/api/admin/article/list',
        method: 'GET',
        title: '我的文章',
        cols: [
            [{
                type: 'checkbox'
            }, {
                field: 'id', title: 'ID', width: 90, align: 'center', templet: '#tableId', sort: true, type: 'numbers',
            }, {
                field: 'title', title: '标题', templet: '#usernameTpl', width: 250, align: 'center',
            }, {
                field: 'createTime', title: '发布时间', width: 160, sort: true, align: 'center',
            }, {
                field: 'updateTime', title: '更新时间', width: 160, align: 'center',
            }, {
                field: 'tag', title: '标签', align: 'center',
            }, {
                field: 'read', title: '浏览量', sort: true, width: 90, align: 'center',
            }, {
                field: 'comment', title: '评论数', sort: true, width: 90, align: 'center',
            }, {
                field: 'top', title: '置顶', templet: '#topTpl', unresize: true, align: 'center', width: 100
            }, {
                field: 'operate', title: '操作', toolbar: '#operateTpl', width: 80, unresize: true, align: 'center',
            }]
        ],
        event: true,
        page: {
            limits: [5, 10, 15, 20, 30, 50],
            limit: 10
        },
        loading: true,
        toolbar: true,
        defaultToolbar: ['filter', 'exports']
    });
    /*
     *数据表格中form表单元素是动态插入,所以需要更新渲染下
     */
    $(function () {
        form.render();
    });

    form.on('switch(top)', function (data) {
        // 获取id
        var id = data.value;
        if (data.elem.checked) {
            // 开启开关
            $.post(
                "/api/admin/article/settop/" + id,
                {},
                function (data) {
                    if (data && data['code'] === 0) {
                        layer.msg('置顶成功！', {
                            icon: 1,
                            time: 1000
                        });
                    } else {
                        layer.msg('置顶失败！', {
                            icon: 2,
                            time: 1000
                        });
                    }
                }
            );
        } else {
            // 关闭开关
            $.post(
                "/api/admin/article/unpintop/" + id,
                {},
                function (data) {
                    if (data && data['code'] == 0) {
                        layer.msg('取消置顶成功！', {
                            icon: 1,
                            time: 1000
                        });
                    } else {
                        layer.msg('取消置顶失败！', {
                            icon: 2,
                            time: 1000
                        });
                    }
                }
            );
        }
    })
    
    form.on("submit(search)", function (data) {
        var flag = true;
        // 更新重新渲染
        table.render({
            elem: '#articleList',
            cellMinWidth: 80,
            url: '/api/admin/article/search?keyword=' + data.field['keyword'],
            method: 'GET',
            title: '我的文章',
            cols: [
                [{
                    type: 'checkbox'
                }, {
                    field: 'id', title: 'ID', width: 90, align: 'center', templet: '#tableId', sort: true, type: 'numbers',
                }, {
                    field: 'title', title: '标题', templet: '#usernameTpl', width: 250, align: 'center',
                }, {
                    field: 'createTime', title: '发布时间', width: 160, sort: true, align: 'center',
                }, {
                    field: 'updateTime', title: '更新时间', width: 160, align: 'center',
                }, {
                    field: 'tag', title: '标签', align: 'center',
                }, {
                    field: 'read', title: '浏览量', sort: true, width: 90, align: 'center',
                }, {
                    field: 'comment', title: '评论数', sort: true, width: 90, align: 'center',
                }, {
                    field: 'top', title: '置顶', templet: '#topTpl', unresize: true, align: 'center', width: 100
                }, {
                    field: 'operate', title: '操作', toolbar: '#operateTpl', width: 80, unresize: true, align: 'center',
                }]
            ],
            event: true,
            page: {
                limits: [5, 10, 15, 20, 30, 50],
                limit: 10
            },
            loading: true,
            toolbar: true,
            defaultToolbar: ['filter', 'exports'],
            done: function(res, curr, count){
                if (curr === 1 && flag) {
                    if (res && res['code'] === 0) {
                        flag = false;
                        layer.msg('共搜索到' + count + '条结果', {
                            icon: 1,
                            time: 1000
                        });
                    } else {
                        layer.msg('搜索失败，未匹配到结果！', {
                            icon: 2,
                            time: 1000
                        });
                    }
                }
            }
        });
        return false;
    })

    var active = {
        getCheckData: function () {
            //获取选中数据
            var checkStatus = table.checkStatus('articleList'),
                data = checkStatus.data;

            // 截取id，只上传id
            var articleIdList = [];
            data.forEach(function (object) {
                articleIdList.push(object.id);
            });

            if (data.length > 0) {
                layer.confirm('确认要删除吗？', function (index) {
                    $.post(
                        '/api/admin/article/delete',
                        {
                            "articleIdList": articleIdList
                        },
                        function (data) {
                            if (data && data['code'] === 0) {
                                layer.msg('删除成功！', {
                                    icon: 1,
                                    time: 1000
                                });
                                //找到所有被选中的，发异步进行删除
                                $(".layui-table-body .layui-form-checked").parents('tr').remove();
                            } else {
                                layer.msg('删除失败！', {
                                    icon: 2,
                                    time: 1000
                                });
                            }
                        }
                    );
                });
            } else {
                layer.msg("请先选择需要删除的文章！", {
                    icon: 0,
                    time: 1000
                });
            }
        }
    };

    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    /*用户-删除*/
    window.member_del = function (obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.post(
                "/api/admin/article/delete/" + id,
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

function delAll(argument) {
    var data = tableCheck.getData();
    layer.confirm('确认要删除吗？' + data, function (index) {
        //捉到所有被选中的，发异步进行删除
        layer.msg('删除成功', {
            icon: 1,
            time: 1000
        });
        $(".layui-form-checked").not('.header').parents('tr').remove();
    });
}