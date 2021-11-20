package top.linzeliang.blog.service;

import top.linzeliang.blog.entity.Comment;

import java.util.List;

/**
 * @Description: TODO
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
public interface CommentService {

    String insertComment(Comment comment);

    int deleteComment(int id);

    List<Comment> listCommentsByType(int type);

    List<Comment> listCommentsByArticle(int articleId);

    Comment getCommentsById(int id);

    int countOfCommentByType(int type);

    int countOfCommentByArticle(int articleId);
}
