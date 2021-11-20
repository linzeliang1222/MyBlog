package top.linzeliang.blog.controller.article;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.linzeliang.blog.constant.Constant;
import top.linzeliang.blog.dto.article.ArticleContent;
import top.linzeliang.blog.dto.article.ArticleInformation;
import top.linzeliang.blog.entity.Article;
import top.linzeliang.blog.entity.Tag;
import top.linzeliang.blog.enums.StateCodeEnum;
import top.linzeliang.blog.service.ArticleService;
import top.linzeliang.blog.service.TagService;
import top.linzeliang.blog.utils.ControllerUtil;
import top.linzeliang.blog.utils.IpAddressUtil;
import top.linzeliang.blog.utils.JsonResultUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 后台文章相关接口
 * @Author: LinZeLiang
 * @Date: 2021-08-02
 */
@RestController
@RequestMapping("/api/admin/article")
@Api("后台文章相关API")
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/list",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.GET)
    @ApiOperation("获取所有文章信息列表")
    public JSONObject listArticles(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        // 分页
        PageHelper.startPage(page, limit);
        // 存放要返回的集合
        List<ArticleInformation> articles = new ArrayList<>();
        // 从数据库获取所有文章
        List<Article> lists = articleService.listArticles();
        // 获取总文章数量
        int count = articleService.getTheNumberOfAllArticles();

        // 将Aritcle转换成ArticleInformation
        for (Article list : lists) {
            ArticleInformation article = new ArticleInformation(
                    list.getArticleId(),
                    list.getArticleTitle(),
                    list.getArticleCreateTime(),
                    list.getArticleUpdateTime(),
                    list.getTag().getTagName(),
                    list.getArticleReadCount(),
                    list.getArticleCommentCount(),
                    list.getArticleSetTop()
            );

            articles.add(article);
        }

        return JsonResultUtil.getJson(StateCodeEnum.SUCCESS, count, articles, Constant.SUCCESS_RESULT);
    }

    @RequestMapping(value = "/delete/{articleId}",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("删除文章")
    public JSONObject deleteArticleById(@PathVariable(value = "articleId", required = true) int articleId) {
        // 删除，如果删除过了返回fail
        String result = articleService.deleteArticle(articleId);
        JSONObject json = ControllerUtil.setResult(result);
        return json;
    }

    @RequestMapping(value = "/delete",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("删除文章集合")
    public JSONObject deleteArticlesByIds(@RequestParam("articleIdList[]") List<Integer> articleIdList) {
        boolean flag = false;
        for (Integer articleId : articleIdList) {
            String result = articleService.deleteArticle(articleId);
            if (Constant.SUCCESS_RESULT.equals(result)) {
                flag = true;
            }
        }
        JSONObject json;
        if (flag) {
            json = JsonResultUtil.getJson(StateCodeEnum.SUCCESS, Constant.SUCCESS_RESULT);
        } else {
            json = JsonResultUtil.getJson(StateCodeEnum.FAIL, Constant.FAIL_RESULT);
        }
        return json;
    }

    @RequestMapping(value = "/settop/{articleId}",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("置顶文章")
    public JSONObject setTopArticle(@PathVariable("articleId") int articleId) {
        String result = articleService.updateArticleTopById(articleId);
        JSONObject json = ControllerUtil.setResult(result);
        return json;
    }

    @RequestMapping(value = "/unpintop/{articleId}",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("取消置顶文章")
    public JSONObject unpinTopArticle(@PathVariable("articleId") int articleId) {
        String result = articleService.updateArticleNoTopById(articleId);
        JSONObject json = ControllerUtil.setResult(result);
        return json;
    }

    @RequestMapping(value = "/add",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("发布文章")
    public JSONObject addArticle(@RequestParam(value = "title", required = true) String title,
                                 @RequestParam(value = "content", required = true) String content,
                                 @RequestParam(value = "tagId", required = true) int tagId,
                                 HttpServletRequest request) {
        String digest = content.length() > 100 ? content.substring(0, 100) : content;
        Article article = new Article(title, digest, content, new Date(), new Date(), IpAddressUtil.getIpAddress(request), 1L, (long) tagId);
        String result = articleService.insertArticle(article);

        JSONObject json = ControllerUtil.setResult(result);
        return json;
    }

    @RequestMapping(value = "/get/{articleId}",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.GET)
    @ApiOperation("获取文章")
    public JSONObject getArticle(@PathVariable("articleId") int articleId) {
        Article articleDetail = articleService.getArticleById(articleId);
        List<Tag> tags = tagService.listTags();
        ArticleContent article = new ArticleContent(articleDetail.getArticleTitle(), articleDetail.getArticleContent(), articleDetail.getTag(), tags);
        return JsonResultUtil.getJson(StateCodeEnum.SUCCESS, article, Constant.SUCCESS_RESULT);
    }

    @RequestMapping(value = "/update",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("更新文章")
    public JSONObject updateArticle(@RequestParam(value = "articleId", required = true) int articleId,
                                    @RequestParam(value = "title", required = true) String title,
                                    @RequestParam(value = "content", required = true) String content,
                                    @RequestParam(value = "tagId", required = true) int tagId,
                                    HttpServletRequest request) {
        String digest = content.length() > 100 ? content.substring(0, 100) : content;
        Article article = new Article((long)articleId, title, digest, content, new Date(), IpAddressUtil.getIpAddress(request), 1L, (long)tagId);
        String result = articleService.updateArticle(article);
        JSONObject json = ControllerUtil.setResult(result);
        return json;
    }

    @RequestMapping(value = "/search",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.GET)
    @ApiOperation("搜索文章")
    public JSONObject searchArticles(@RequestParam(value = "keyword", required = true) String keyword,
                                     @RequestParam(value = "page", required = true) int page,
                                     @RequestParam(value = "limit", required = true) int limit) {
        // 分页
        PageHelper.startPage(page, limit);
        // 存放要返回的集合
        List<ArticleInformation> articles = new ArrayList<>();
        // 从数据库获取所有文章
        List<Article> lists = articleService.searchArticles(keyword);
        // 获取总文章数量
        int count = articleService.getTheNumberOfAllArticlesByKeyword(keyword);

        // 将Aritcle转换成ArticleInformation
        articles = ControllerUtil.articleToArticleInformation(lists);
        JSONObject json;
        if (0 == count) {
            json = JsonResultUtil.getJson(StateCodeEnum.FAIL, "未匹配到数据");
        } else {
            json = JsonResultUtil.getJson(StateCodeEnum.SUCCESS, count, articles, "请求成功");
        }
        return json;
    }
}
