layui.define(['jquery', 'layer'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer;

    var baseApiUrl = 'http://localhost:8080/admin';

    var request = {
        hello: function (str) {
            alert('Hello ' + (str || 'mymod'));
        },
        login: function (obj) {
            $.post({
                url: '/api/admin/user/login',
                data: {
                    "username": obj.username,
                    "password": obj.password
                },
                success: function (res) {
                    if (res.code === 0) {
                        layer.msg('登录成功，即将跳转~', {
                            icon: 1
                        }, function () {
                            location.href = '../../admin/index.html'
                        });
                    } else {
                        layer.msg(res.msg);
                    }
                }
            })

        },
        getMenu: function () {
            var that = this;
            if (!$('#side-nav').length) {
                return;
            }
            $.get({
                url: baseApiUrl + '/json/menu.json',
                success: function (res) {
                    if (res.status === 1) {
                        var menuList = res.data;
                        var items = [];
                        var _ul = $('<ul></ul>').addClass('nav').attr('id', 'nav');
                        $.each(menuList, function (index, val) {
                            var icon = val.icon ? '<i class="layui-icon layui-icon-' + val.icon + '"></i>' : '<i class="layui-icon layui-icon-app"></i>';
                            var item = '<li id="menu' + val.id + '"><a _href="' + val.url + '">' + icon + '<cite>' + val.name +
                                '</cite><i class="iconfont nav_right">&#xe697;</i></a></li>';

                            items.push(item);
                            if (val.children && val.children.length) {
                                //有二级子菜单
                                setTimeout(function () {
                                    that.getSubMenu(val.id, val.children);
                                }, 500);
                            }
                        });
                        $('#side-nav').empty();
                        _ul.append(items.join(''));
                        $('#side-nav').append(_ul);
                    } else {
                        layer.msg(res.msg, function () {
                        });
                    }
                }
            })
        },
        /**
         * @param {Object} id 上级子菜单id,拼接menu获取元素
         * @param {Object} subList 子菜单列表
         */
        getSubMenu: function (id, subList) {
            var that = this;
            var subItems = [];
            var subUl = $('<ul></ul>').addClass('sub-menu');
            $.each(subList, function (idx, sub) {
                var subItem = '<li id="menu' + sub.id + '"><a _href="' + sub.url + '"><i class="iconfont">&#xe6a7;</i><cite>' +
                    sub.name + '</cite></a></li>';
                subItems.push(subItem);
                if (sub.children && sub.children.length) {
                    setTimeout(function () {
                        that.getSubMenu(sub.id, sub.children);
                    }, 1500);
                }
            });
            subUl.append(subItems.join(''));
            $('#menu' + id).append(subUl);
        }
    }
    exports('http', request);

});
