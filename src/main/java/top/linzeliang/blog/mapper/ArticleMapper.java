package top.linzeliang.blog.mapper;

import org.apache.ibatis.annotations.Param;
import top.linzeliang.blog.entity.Article;

import java.util.List;

/**
 * @Description: Article Mapper
 * @Author: LinZeLiang
 * @Date: 2021-07-31
 */
public interface ArticleMapper {

    /**
     * 添加文章
     * @param article 文章实体
     * @return int
     * @date 2021-08-01
     */
    int insertArticle(Article article);

    /**
     * 修改文章
     * @param article 文章实体
     * @return int
     * @date 2021-08-01
     */
    int updateArticle(Article article);

    /**
     * 删除文章
     * @param articleId 文章id
     * @return int
     * @date 2021-08-01
     */
    int deleteArticleById(@Param("articleId") int articleId);

    /**
     * 根据id查询文章
     * @param articleId 文章id
     * @return top.linzeliang.blog.entity.Article
     * @date 2021-08-01
     */
    Article getArticlesById(@Param("articleId") int articleId);

    /**
     * 根据用户id查询文章
     * @param userId 用户id
     * @return java.util.List<top.linzeliang.blog.entity.Article>
     * @date 2021-08-01
     */
    List<Article> listArticlesByUserId(@Param("userId") int userId);

    /**
     * 根据标签id查询文章
     * @param tagId 标签id
     * @return java.util.List<top.linzeliang.blog.entity.Article>
     * @date 2021-08-01
     */
    List<Article> listArticleByTagId(@Param("tagId") int tagId);

    /**
     * 查询所有文章
     * @return java.util.List<top.linzeliang.blog.entity.Article>
     * @date 2021-08-01
     */
    List<Article> listArticles();
    
    /**
     * 查询所有置顶文章
     * @return java.util.List<top.linzeliang.blog.entity.Article>
     * @date 2021-08-01
     */
    List<Article> listTopArticles();

    /**
     * 获取置顶推荐
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
    List<Article> listPopularArticlesByUserId(@Param("userId") int userId);

    /**
     * 获取文章总数量
     * @return int
     * @date 2021-08-01
     */
    int countTheNumberOfArticles();

    /**
     * 置顶文章
     * @param articleId 文章id
     * @return int
     * @date 2021-08-01
     */
    int updateArticleTopById(@Param("articleId") int articleId);

    /**
     * 取消置顶
     * @param articleId 文章id
     * @return int
     * @date 2021-08-01
     */
    int updateArticleNoTopById(@Param("articleId") int articleId);

    /**
     * 根据文章id增加阅读量
     * @param articleId	文章id
     * @return int
     * @date 2021-08-01
     */
    int increaseReadingVolumeById(@Param("articleId") int articleId);

    /**
     * 根据文章id增加文章的总评论数
     * @param articleId	文章id
     * @return int
     * @date 2021-08-01
     */
    int incrementTheNumberOfCommentsById(@Param("articleId") int articleId);

    /**
     * 根据id查询文章是否已删除
     * @param articleId 文章id
     * @return int
     * @date 2021-08-02
     */
    int articleIsDeleted(@Param("articleId") int articleId);

    /**
     * 根据id查询文章是否已置顶
     * @param articleId 文章id
     * @return int
     * @date 2021-08-02
     */
    int articleIsTop(@Param("articleId") int articleId);

    /**
     * 通过关键字搜索文章
     * @param keyword 文章标题关键字
     * @return java.util.List<top.linzeliang.blog.entity.Article>
     * @date 2021-08-06
     */
    List<Article> searchArticles(@Param("keyword") String keyword);

    /**
     * 获取文章搜索结果的数量
     * @param keyword 文章标题关键字
     * @return int
     * @date 2021-08-06
     */
    int countTheNumberOfArticlesByKeyword(@Param("keyword") String keyword);
}
