package top.linzeliang.blog.utils;

import com.alibaba.fastjson.JSONObject;
import top.linzeliang.blog.constant.Constant;
import top.linzeliang.blog.dto.article.ArticleInformation;
import top.linzeliang.blog.entity.Article;
import top.linzeliang.blog.enums.StateCodeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: LinZeLiang
 * @Date: 2021-08-05
 */
public class ControllerUtil {

    public static JSONObject setResult(String result) {
        if (Constant.SUCCESS_RESULT.equals(result)) {
            return JsonResultUtil.getJson(StateCodeEnum.SUCCESS, result);
        } else {
            return JsonResultUtil.getJson(StateCodeEnum.FAIL, result);
        }
    }

    public static List<ArticleInformation> articleToArticleInformation(List<Article> lists) {
        List<ArticleInformation> articles = new ArrayList<>();

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
        return articles;
    }
}
