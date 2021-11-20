package top.linzeliang.blog.controller.tag;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.linzeliang.blog.constant.Constant;
import top.linzeliang.blog.dto.article.ArticleInformation;
import top.linzeliang.blog.dto.tag.TagInformation;
import top.linzeliang.blog.entity.Article;
import top.linzeliang.blog.entity.Tag;
import top.linzeliang.blog.enums.StateCodeEnum;
import top.linzeliang.blog.service.TagService;
import top.linzeliang.blog.utils.ControllerUtil;
import top.linzeliang.blog.utils.JsonResultUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: TODO
 * @Author: LinZeLiang
 * @Date: 2021-08-05
 */
@RestController
@RequestMapping("/api/admin/tag")
@Api("后台标签相关API")
public class AdminTagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/list",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.GET)
    @ApiOperation("获取标签信息列表")
    public JSONObject listArticles(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        // 分页
        PageHelper.startPage(page, limit);
        // 存放要返回的集合
        ArrayList<TagInformation> tags = new ArrayList<>();
        List<Tag> lists = tagService.listTags();
        int count = tagService.getTheNumberOfAllArticles();

        for (Tag list : lists) {
            TagInformation tag = new TagInformation(list.getTagId(), list.getTagName());
            tags.add(tag);
        }

        return JsonResultUtil.getJson(StateCodeEnum.SUCCESS, count, tags, "请求成功");
    }

    @RequestMapping(value = "/get/{tagId}",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.GET)
    @ApiOperation("获取标签")
    public JSONObject getTag(@PathVariable("tagId") int tagId) {
        Tag tagDetail = tagService.selectTagById(tagId);
        TagInformation tag = new TagInformation(tagDetail.getTagId(), tagDetail.getTagName());
        return JsonResultUtil.getJson(StateCodeEnum.SUCCESS, tag, Constant.SUCCESS_RESULT);
    }

    @RequestMapping(value = "/update",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("更新标签")
    public JSONObject updateTag(@RequestParam(value = "tagId", required = true) int tagId,
                                    @RequestParam(value = "title", required = true) String title,
                                    HttpServletRequest request) {
        Tag tag = new Tag(tagId, title, new Date());
        String result = tagService.updateTag(tag);
        JSONObject json = ControllerUtil.setResult(result);
        return json;
    }

    @RequestMapping(value = "/add",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("添加标签")
    public JSONObject addArticle(@RequestParam(value = "title", required = true) String title,
                                 HttpServletRequest request) {
        Tag tag = new Tag(title, new Date());
        String result = tagService.insertTag(tag);

        JSONObject json = ControllerUtil.setResult(result);
        return json;
    }
}
