package top.linzeliang.blog.controller.comment;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.linzeliang.blog.constant.Constant;
import top.linzeliang.blog.dto.comment.CommentInformation;
import top.linzeliang.blog.dto.comment.MessageInformation;
import top.linzeliang.blog.entity.Comment;
import top.linzeliang.blog.enums.StateCodeEnum;
import top.linzeliang.blog.service.CommentService;
import top.linzeliang.blog.utils.DateUtil;
import top.linzeliang.blog.utils.JsonResultUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Admin Comment Controller
 * @Author: linzeliang
 * @Date: 2021/8/22
 */
@RestController
@RequestMapping("/api/admin/comment")
@Api("留言相关API")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/listMessage",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.GET)
    @ApiOperation("获取留言")
    public JSONObject listMessages(@RequestParam("page") int page, @RequestParam("limit") int limit) {

        // 分页
        PageHelper.startPage(page, limit);
        // 存放要返回的集合
        List<MessageInformation> messages = new ArrayList<>();
        // 从数据库获取所有文章
        List<Comment> lists = commentService.listCommentsByType(0);
        // 获取总文章数量
        int count = commentService.countOfCommentByType(0);

        // 将Aritcle转换成ArticleInformation
        for (Comment list : lists) {
            MessageInformation message = new MessageInformation(
                    list.getCommentContent(),
                    list.getCommentUsername(),
                    list.getCommentIp(),
                    DateUtil.dateToString(list.getCommentCreateTime(), "yyyy-MM-dd HH:mm:ss")
            );

            messages.add(message);
        }

        return JsonResultUtil.getJson(StateCodeEnum.SUCCESS, count, messages, Constant.SUCCESS_RESULT);
    }


    @RequestMapping(value = "/listComment",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.GET)
    @ApiOperation("获取评论")
    public JSONObject listComment(@RequestParam("page") int page, @RequestParam("limit") int limit) {

        // 分页
        PageHelper.startPage(page, limit);
        // 存放要返回的集合
        List<CommentInformation> comments = new ArrayList<>();
        // 从数据库获取所有文章
        List<Comment> lists = commentService.listCommentsByType(1);
        // 获取总文章数量
        int count = commentService.countOfCommentByType(1);

        // 将Aritcle转换成ArticleInformation
        for (Comment list : lists) {
            CommentInformation comment = new CommentInformation(
                    list.getCommentId(),
                    list.getCommentContent(),
                    list.getCommentUsername(),
                    list.getCommentIp(),
                    list.getCommentParentId(),
                    list.getCommentArticleId(),
                    list.getCommentCreateTime()
            );

            comments.add(comment);
        }

        return JsonResultUtil.getJson(StateCodeEnum.SUCCESS, count, comments, Constant.SUCCESS_RESULT);
    }
}
