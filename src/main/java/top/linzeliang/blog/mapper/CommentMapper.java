package top.linzeliang.blog.mapper;

import org.apache.ibatis.annotations.Param;
import top.linzeliang.blog.entity.Comment;

import java.util.List;

/**
 * @Description: Common Mapper
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
public interface CommentMapper {

    /**
     * 增加评论
     * @param comment 评论实体
     * @return int
     * @date 2021-08-01
     */
    int insertComment(Comment comment);

    /**
     * 删除评论
     * @param commentId	评论id
     * @return int
     * @date 2021-08-01
     */
    int deleteComment(@Param("commentId") int commentId);

    /**
     * 获取所有评论/留言
     * @param type 评论/留言类型
     * @return java.util.List<top.linzeliang.blog.entity.Comment>
     * @date 2021-08-01
     */
    List<Comment> listCommentsByType(@Param("type")int type);

    /**
     * 获取文章对应的所有评论
     * @param articleId 文章ID
     * @return java.util.List<top.linzeliang.blog.entity.Comment>
     * @date 2021/8/24
     */
    List<Comment> listCommentsByArticle(@Param("articleId") int articleId);

    /**
     * 通过id查询对应的评论
     * @param commentId	评论id
     * @return top.linzeliang.blog.entity.Comment
     * @date 2021-08-01
     */
    Comment getCommentById(@Param("commentId") int commentId);

    /**
     * 获取评论/留言总数
     * @param type 留言类型
     * @return int
     * @date 2021/8/22
     */
    int getTheTotalNumberOfComments(@Param("type") int type);

    /**
     * 获取文章的评论总数
     * @param articleId 文章ID
     * @return int
     * @date 2021/8/22
     */
    int getTheTotalNumberOfCommentsByArticle(@Param("articleId") int articleId);
}
