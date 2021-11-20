layui.extend({
  admin: '{/}../../static/js/admin'
});
layui.use(['laydate', 'jquery', 'admin'], function () {
  var laydate = layui.laydate,
    $ = layui.jquery,
    admin = layui.admin;
  //执行一个laydate实例
  laydate.render({
    elem: '#start' //指定元素
  });
  //执行一个laydate实例
  laydate.render({
    elem: '#end' //指定元素
  });
  /*用户-停用*/
  window.member_stop = function (obj, id) {
    layer.confirm('确认要停用吗？', function (index) {
      if ($(obj).attr('title') == '启用') {

        //发异步把用户状态进行更改
        // 启用 &#xe601; -  layui-icon-download-circle
        // 停用 &#xe62f; -  layui-icon-upload-circle
        $(obj).attr('title', '停用')
        // $(obj).find('i').html('&#xe62f;');
        $(obj).find('i').removeClass('layui-icon-download-circle').addClass('layui-icon-upload-circle');


        $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
        layer.msg('已停用!', {
          icon: 5,
          time: 1000
        });

      } else {
        $(obj).attr('title', '启用')
        // $(obj).find('i').html('&#xe601;');
        $(obj).find('i').removeClass('layui-icon-upload-circle').addClass('layui-icon-download-circle');

        $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
        layer.msg('已启用!', {
          icon: 5,
          time: 1000
        });
      }
    });
  }

  /*用户-删除*/
  window.member_del = function (obj, id) {
    layer.confirm('确认要删除吗？', function (index) {
      //发异步删除数据
      $(obj).parents("tr").remove();
      layer.msg('已删除!', {
        icon: 1,
        time: 1000
      });
    });
  }

  window.delAll = function (argument) {
    var data = admin.tableCheck.getData();
    if (data.length > 0) {
      layer.confirm('确认要删除吗？' + data, function (index) {
        //捉到所有被选中的，发异步进行删除
        layer.msg('删除成功', {
          icon: 1
        });
        $(".layui-form-checked").not('.header').parents('tr').remove();
      });
    } else {
      layer.msg("请先选择");
    }
  }
});