package top.linzeliang.blog.controller.article;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.linzeliang.blog.dto.article.ArticleExtend;
import top.linzeliang.blog.dto.article.ArticleShow;
import top.linzeliang.blog.dto.article.ArticleSimple;
import top.linzeliang.blog.dto.comment.MessageList;
import top.linzeliang.blog.dto.link.LinkShow;
import top.linzeliang.blog.dto.tag.TagInformation;
import top.linzeliang.blog.entity.Article;
import top.linzeliang.blog.entity.Comment;
import top.linzeliang.blog.entity.Link;
import top.linzeliang.blog.entity.Tag;
import top.linzeliang.blog.service.ArticleService;
import top.linzeliang.blog.service.CommentService;
import top.linzeliang.blog.service.LinkService;
import top.linzeliang.blog.service.TagService;
import top.linzeliang.blog.utils.ControllerUtil;
import top.linzeliang.blog.utils.IpAddressUtil;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.*;

/**
 * @Description: Article Controller
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Controller
@Api("前台文章相关Controller")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LinkService linkService;

    private final Map<String, Date> ipVisitLimit = new HashMap<>();

    @RequestMapping(value = {"/", "/index.html", "index.htm"}, method = RequestMethod.GET)
    @ApiOperation("首页")
    public String index(Model model) {
        PageHelper.startPage(0, 3);
        List<ArticleShow> articles = new ArrayList<>();
        for (Article a : articleService.listPopularArticles()) {
            ArticleShow article = new ArticleShow(
                    a.getArticleId(),
                    a.getArticleTitle(),
                    a.getArticleDigest(),
                    a.getArticleCreateTime(),
                    a.getArticleReadCount(),
                    a.getArticleCommentCount(),
                    a.getTag().getTagName(),
                    a.getArticleSetTop());

            articles.add(article);
        }
        model.addAttribute("articles", articles);

        return "index";
    }

    @RequestMapping(value = {"/article.html", "article.htm"}, method = RequestMethod.GET)
    @ApiOperation("文章列表页")
    public String list(Model model,
                       @RequestParam(value = "tid", required = false, defaultValue = "0") String tagId,
                       @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        int tid = Integer.parseInt(tagId);
        // 获取所有文章列表
        List<ArticleShow> articles = new ArrayList<>();
        if (!keyword.isEmpty() && keyword.length() != 0) {
            for (Article a : articleService.searchArticles(keyword)) {
                ArticleShow article = new ArticleShow(
                        a.getArticleId(),
                        a.getArticleTitle(),
                        a.getArticleDigest(),
                        a.getArticleCreateTime(),
                        a.getArticleReadCount(),
                        a.getArticleCommentCount(),
                        a.getTag().getTagName(),
                        a.getArticleSetTop());

                articles.add(article);
            }
        } else if (0 == tid) {
            for (Article a : articleService.listArticles()) {
                ArticleShow article = new ArticleShow(
                        a.getArticleId(),
                        a.getArticleTitle(),
                        a.getArticleDigest(),
                        a.getArticleCreateTime(),
                        a.getArticleReadCount(),
                        a.getArticleCommentCount(),
                        a.getTag().getTagName(),
                        a.getArticleSetTop());

                articles.add(article);
            }
            model.addAttribute("articles", articles);
        } else {
            for (Article a : articleService.listArticlesByTagId(tid)) {
                ArticleShow article = new ArticleShow(
                        a.getArticleId(),
                        a.getArticleTitle(),
                        a.getArticleDigest(),
                        a.getArticleCreateTime(),
                        a.getArticleReadCount(),
                        a.getArticleCommentCount(),
                        a.getTag().getTagName(),
                        a.getArticleSetTop());

                articles.add(article);
            }
        }
        model.addAttribute("articles", articles);

        // 获取所有标签
        ArrayList<TagInformation> tags = new ArrayList<>();
        for (Tag t : tagService.listTags()) {
            TagInformation tag = new TagInformation(
                    t.getTagId(),
                    t.getTagName()
            );

            tags.add(tag);
        }
        model.addAttribute("tags", tags);

        // 获取热门文章
        PageHelper.startPage(1, 10);
        List<ArticleSimple> hotArticles = new ArrayList<>();
        for (Article a : articleService.listPopularArticles()) {
            ArticleSimple article = new ArticleSimple(a.getArticleId(), a.getArticleTitle());
            hotArticles.add(article);
        }
        model.addAttribute("hotArticles", hotArticles);

        // 获取置顶推荐
        PageHelper.startPage(1, 3);
        List<ArticleSimple> topRecommends = new ArrayList<>();
        for (Article a : articleService.listTopArticlesAndRecommend()) {
            ArticleSimple article = new ArticleSimple(a.getArticleId(), a.getArticleTitle());
            topRecommends.add(article);
        }
        model.addAttribute("topRecommends", topRecommends);

        return "article";
    }

    @RequestMapping(value = {"/read.html", "read.htm"}, method = RequestMethod.GET)
    @ApiOperation("文章详情")
    public String read(Model model, @RequestParam(value = "aid", required = false, defaultValue = "0") String articleId, HttpServletRequest request) {

        int aid = Integer.parseInt(articleId);

        if (0 == aid) {
            return "404";
        } else {
            // 增加阅读量
            Date interval = ipVisitLimit.get(IpAddressUtil.getIpAddress(request)+articleId);
            Date now = new Date();
            if (interval != null && now.getTime() - interval.getTime() > 1000*60) {
                ipVisitLimit.put(IpAddressUtil.getIpAddress(request)+articleId, new Date());
                articleService.increaseReadingCountById(aid);
            } else if (interval == null) {
                ipVisitLimit.put(IpAddressUtil.getIpAddress(request)+articleId, new Date());
                articleService.increaseReadingCountById(aid);
            }

            // 获取文章
            Article article = articleService.getArticleById(aid);

            // 获取文章对应的标签
            long tid = article.getArticleTagId();
            // 获取扩展文章
            ArrayList<ArticleExtend> articlesByExtend = new ArrayList<>();
            List<Article> articlesByTag = articleService.listArticlesByTagId((int) tid);
            for (Article articleByTag : articlesByTag) {
                if (!articleByTag.getArticleId().equals(article.getArticleId()) && articlesByExtend.size() < 3) {
                    articlesByExtend.add(new ArticleExtend(articleByTag.getArticleId(), articleByTag.getArticleTitle()));
                }
            }

            // 获取评论
            List<Comment> comments = commentService.listCommentsByArticle(aid);
            ArrayList<MessageList> commentLists = new ArrayList<>();
            for (Comment m : comments) {
                if (m.getCommentParentId() == 0) {
                    MessageList message = new MessageList(
                            m.getCommentId(),
                            m.getCommentUsername(),
                            "",
                            m.getCommentContent(),
                            m.getCommentCreateTime(),
                            m.getCommentParentId(),
                            m.getCommentArticleId()
                    );
                    commentLists.add(message);
                } else {
                    for (MessageList ml : commentLists) {
                        if (ml.getId() == m.getCommentBaseId()) {
                            String parentName = null;
                            if (ml.getId() == m.getCommentParentId()) {
                                parentName = ml.getUsername();
                            } else {
                                for (MessageList child : ml.getChildMessage()) {
                                    if (child.getId() == m.getCommentParentId()) {
                                        parentName = child.getUsername();
                                    }
                                }
                            }
                            MessageList message = new MessageList(
                                    m.getCommentId(),
                                    m.getCommentUsername(),
                                    parentName,
                                    m.getCommentContent(),
                                    m.getCommentCreateTime(),
                                    m.getCommentParentId(),
                                    m.getCommentArticleId()
                            );
                            ml.getChildMessage().add(message);
                        }
                    }
                }
            }

            model.addAttribute("article", article);
            model.addAttribute("articlesByExtend", articlesByExtend);
            model.addAttribute("commentLists", commentLists);

            return "read";
        }
    }
}
