package top.linzeliang.blog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.linzeliang.blog.entity.Article;
import top.linzeliang.blog.mapper.ArticleMapper;
import top.linzeliang.blog.service.ArticleService;

import java.util.List;

/**
 * @Description: ArticleService实现类
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public String insertArticle(Article article) {
        try {
            int result = articleMapper.insertArticle(article);
            if (1 == result) {
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @Override
    public String deleteArticle(int articleId) {
        try {
            int deleted = articleMapper.articleIsDeleted(articleId);
            // 已删除
            if (1 == deleted) {
                return "fail";
            } else {
                int result = articleMapper.deleteArticleById(articleId);
                // 删除成功
                if (1 == result) {
                    return "success";
                } else {
                    // 删除失败
                    return "fail";
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @Override
    public String updateArticle(Article article) {
        try {
            int result = articleMapper.updateArticle(article);
            if (1 == result) {
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @Override
    public Article getArticleById(int articleId) {
        return articleMapper.getArticlesById(articleId);
    }

    @Override
    public List<Article> listArticles() {
        return articleMapper.listArticles();
    }

    @Override
    public List<Article> listArticlesByUserId(int userId) {
        return articleMapper.listArticlesByUserId(userId);
    }

    @Override
    public List<Article> listArticlesByTagId(int tagId) {
        return articleMapper.listArticleByTagId(tagId);
    }

    @Override
    public List<Article> listTopArticles() {
        return articleMapper.listTopArticles();
    }

    @Override
    public List<Article> listTopArticlesAndRecommend() {
        return articleMapper.listTopArticlesAndRecommend();
    }

    @Override
    public List<Article> listPopularArticles() {
        return articleMapper.listPopularArticles();
    }

    @Override
    public List<Article> listPopularArticlesByUserId(int userId) {
        return articleMapper.listPopularArticlesByUserId(userId);
    }

    @Override
    public String updateArticleTopById(int articleId) {
        int setTop = articleMapper.articleIsTop(articleId);
        if (1 == setTop) {
            return "fail";
        } else {
            int result = articleMapper.updateArticleTopById(articleId);
            if (1 == result) {
                return "success";
            } else {
                return "false";
            }
        }
    }

    @Override
    public String updateArticleNoTopById(int articleId) {
        int setTop = articleMapper.articleIsTop(articleId);
        if (1 == setTop) {
            int result = articleMapper.updateArticleNoTopById(articleId);
            if (1 == result) {
                return "success";
            } else {
                return "false";
            }
        } else {
            return "fail";
        }
    }

    @Override
    public String increaseReadingCountById(int articleId) {
        try {
            int result = articleMapper.increaseReadingVolumeById(articleId);
            if (1 == result) {
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @Override
    public int increaseCommentCountById(int articleId) {
        return articleMapper.incrementTheNumberOfCommentsById(articleId);
    }

    @Override
    public int getTheNumberOfAllArticles() {
        return articleMapper.countTheNumberOfArticles();
    }

    @Override
    public List<Article> searchArticles(String keyword) {
        return articleMapper.searchArticles(keyword);
    }

    @Override
    public int getTheNumberOfAllArticlesByKeyword(String keyword) {
        return articleMapper.countTheNumberOfArticlesByKeyword(keyword);
    }
}
