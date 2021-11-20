package top.linzeliang.blog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.linzeliang.blog.entity.Comment;
import top.linzeliang.blog.mapper.CommentMapper;
import top.linzeliang.blog.service.CommentService;

import java.util.List;

/**
 * @Description: TODO
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public String insertComment(Comment comment) {
        try {
            int result = commentMapper.insertComment(comment);
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
    public int deleteComment(int id) {
        return commentMapper.deleteComment(id);
    }

    @Override
    public List<Comment> listCommentsByType(int type) {
        return commentMapper.listCommentsByType(type);
    }

    @Override
    public List<Comment> listCommentsByArticle(int articleId) {
        return commentMapper.listCommentsByArticle(articleId);
    }

    @Override
    public Comment getCommentsById(int id) {
        return commentMapper.getCommentById(id);
    }

    @Override
    public int countOfCommentByType(int type) {
        return commentMapper.getTheTotalNumberOfComments(type);
    }

    @Override
    public int countOfCommentByArticle(int articleId) {
        return commentMapper.getTheTotalNumberOfCommentsByArticle(articleId);
    }
}
