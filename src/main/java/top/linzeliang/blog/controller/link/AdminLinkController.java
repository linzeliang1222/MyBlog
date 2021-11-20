package top.linzeliang.blog.controller.link;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.linzeliang.blog.constant.Constant;
import top.linzeliang.blog.entity.Link;
import top.linzeliang.blog.entity.User;
import top.linzeliang.blog.enums.StateCodeEnum;
import top.linzeliang.blog.service.LinkService;
import top.linzeliang.blog.utils.ControllerUtil;
import top.linzeliang.blog.utils.JsonResultUtil;

import java.util.Date;
import java.util.List;

/**
 * @Description: Admin Link Controller
 * @Author: linzeliang
 * @Date: 2021/8/21
 */
@RestController
@RequestMapping("/api/admin/link")
@Api("友链相关API")
public class AdminLinkController {

    @Autowired
    private LinkService linkService;

    @RequestMapping(value = "/list",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.GET)
    @ApiOperation("获取友链")
    public JSONObject listLinks(@RequestParam("page") int page, @RequestParam("limit") int limit) {

        // 分页
        PageHelper.startPage(page, limit);
        List<Link> links = linkService.listLinks();
        int count = linkService.countOfLink();
        if (null != links) {
            return JsonResultUtil.getJson(StateCodeEnum.SUCCESS, count, links, Constant.SUCCESS_RESULT);
        } else {
            return JsonResultUtil.getJson(StateCodeEnum.FAIL, Constant.FAIL_RESULT);
        }
    }

    @RequestMapping(value = "/delete/{linkId}",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("删除友链")
    public JSONObject listLinks(@PathVariable("linkId") int id) {
        String result = linkService.deleteLink(id);
        JSONObject json = ControllerUtil.setResult(result);
        return json;
    }

    @RequestMapping(value = "/add",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("添加友链")
    public JSONObject addLink(Link link) {
        link.setLinkCreateTime(new Date());
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        link.setLinkUserId(user.getUserId());
        String result = linkService.insertLink(link);
        JSONObject json = ControllerUtil.setResult(result);
        return json;
    }

    @RequestMapping(value = "/update",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("更新友链")
    public JSONObject updateLink(Link link) {
        String result = linkService.updateLink(link);
        JSONObject json = ControllerUtil.setResult(result);
        return json;
    }

    @RequestMapping(value = "/get/{linkId}",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.GET)
    @ApiOperation("获取友链")
    public JSONObject updateLink(@PathVariable("linkId") int id) {
        Link link = linkService.selectLinkById(id);
        return JsonResultUtil.getJson(StateCodeEnum.SUCCESS, link, Constant.SUCCESS_RESULT);
    }
}
