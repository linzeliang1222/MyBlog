package top.linzeliang.blog.service;

import org.apache.ibatis.annotations.Param;
import top.linzeliang.blog.entity.Article;

import java.util.List;

/**
 * @Description: Article Service
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
public interface ArticleService {

    /**
     * 添加文章
     * @param article 文章实体
     * @return String
     * @date 2021-08-02
     */
    String insertArticle(Article article);

    /**
     * 根据id删除文章
     * @param articleId 文章id
     * @return String
     * @date 2021-08-02
     */
    String deleteArticle(int articleId);

    /**
     * 更新文章
     * @param article 文章实体
     * @return String
     * @date 2021-08-02
     */
    String updateArticle(Article article);

    /**
     * 通过id获取文章
     * @param articleId 文章id
     * @return top.linzeliang.blog.entity.Article
     * @date 2021-08-02
     */
    Article getArticleById(int articleId);

    /**
     * 获取所有文章
     * @return java.util.List<top.linzeliang.blog.entity.Article>
     * @date 2021-08-02
     */
    List<Article> listArticles();

    /**
     * 获取某用户的所有文章
     * @return java.util.List<top.linzeliang.blog.entity.Article>
     * @date 2021-08-02
     */
    List<Article> listArticlesByUserId(int userId);

    /**
     * 获取某标签的所有文章
     * @return java.util.List<top.linzeliang.blog.entity.Article>
     * @date 2021-08-02
     */
    List<Article> listArticlesByTagId(int tagId);

    /**
     * 查询所有置顶文章
     * @return java.util.List<top.linzeliang.blog.entity.Article>
     * @date 2021-08-02
     */
    List<Article> listTopArticles();

    /**
     * 根据uid查询置顶文章
     * @return java.util.List<top.linzeliang.blog.entity.Article>
     * @date 2021-08-01
     */
    List<Article> listTopArticlesAndRecommend();

    /**
     * 查询所有热门文章
     * @return java.util.List<top.linzeliang.blog.entity.Article>
     * @date 2021-08-01
     */
    List<Article> listPopularArticles();

    /**
     * 根据用户id查询热门文章
     * @param userId 用户id
     * @return java.util.List<top.linzeliang.blog.entity.Article>
     * @date 2021-08-01
     */
    List<Article> listPopularArticlesByUserId(int userId);

    /**
     * 置顶文章
     * @param articleId 文章id
     * @return String
     * @date 2021-08-01
     */
    String updateArticleTopById(int articleId);

    /**
     * 取消置顶
     * @param articleId 文章id
     * @return String
     * @date 2021-08-01
     */
    String updateArticleNoTopById(int articleId);

    /**
     * 根据文章id增加阅读量
     * @param articleId	文章id
     * @return String
     * @date 2021-08-01
     */
    String increaseReadingCountById(@Param("articleId") int articleId);

    /**
     * 根据文章id增加文章的总评论数
     * @param articleId	文章id
     * @return int
     * @date 2021-08-01
     */
    int increaseCommentCountById(@Param("articleId") int articleId);

    /**
     * 获取文章总数
     * @return int
     * @date 2021-08-02
     */
    int getTheNumberOfAllArticles();

    /**
     * 通过关键字搜索文章
     * @param keyword 文章标题关键字
     * @return java.util.List<top.linzeliang.blog.entity.Article>
     * @date 2021-08-06
     */
    List<Article> searchArticles(String keyword);

    /**
     * 获取文章搜索结果总数
     * @return int
     * @date 2021-08-02
     */
    int getTheNumberOfAllArticlesByKeyword(String keyword);
}
