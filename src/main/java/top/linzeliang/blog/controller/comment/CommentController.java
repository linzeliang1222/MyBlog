package top.linzeliang.blog.controller.comment;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.linzeliang.blog.dto.comment.MessageList;
import top.linzeliang.blog.entity.Comment;
import top.linzeliang.blog.service.ArticleService;
import top.linzeliang.blog.service.CommentService;
import top.linzeliang.blog.utils.ControllerUtil;
import top.linzeliang.blog.utils.IpAddressUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Description: TODO
 * @Author: linzeliang
 * @Date: 2021/8/26
 */
@Controller
@Api("前台评论/留言相关Controller")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/api/comment/comment/add",
            method = RequestMethod.POST)
    @ApiOperation("发布评论")
    @ResponseBody
    public JSONObject addComment(HttpServletRequest request, Comment comment) {
        comment.setCommentPlace("");
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(IpAddressUtil.getIpAddress(request));
        comment.setCommentType((short)1);
        comment.setCommentBaseId(0);
        comment.setCommentParentId(0);

        String result = commentService.insertComment(comment);

        // 文章对应的评论数+1
        if ("success".equals(result)) {
            articleService.increaseCommentCountById((int)comment.getCommentArticleId());
        }

        return ControllerUtil.setResult(result);
    }

    @RequestMapping(value = "/api/comment/comment/reply",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ApiOperation("回复评论")
    @ResponseBody
    public JSONObject replyComment(HttpServletRequest request, Comment comment) {
        comment.setCommentPlace("");
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(IpAddressUtil.getIpAddress(request));
        comment.setCommentType((short) 1);

        String result = commentService.insertComment(comment);

        // 文章对应的评论数+1
        if ("success".equals(result)) {
            articleService.increaseCommentCountById((int)comment.getCommentArticleId());
        }

        return ControllerUtil.setResult(result);
    }

    @RequestMapping(value = "/api/comment/message/add",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ApiOperation("发布留言")
    @ResponseBody
    public JSONObject addMessage(HttpServletRequest request, Comment comment) {
        comment.setCommentPlace("");
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(IpAddressUtil.getIpAddress(request));
        comment.setCommentArticleId(-1);
        comment.setCommentType((short)0);
        comment.setCommentBaseId(0);
        comment.setCommentParentId(0);

        String result = commentService.insertComment(comment);

        return ControllerUtil.setResult(result);
    }

    @RequestMapping(value = "/api/comment/message/reply",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ApiOperation("回复留言")
    @ResponseBody
    public JSONObject replyMessage(HttpServletRequest request, Comment comment) {
        comment.setCommentPlace("");
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(IpAddressUtil.getIpAddress(request));
        comment.setCommentArticleId(-1);
        comment.setCommentType((short)0);

        String result = commentService.insertComment(comment);

        return ControllerUtil.setResult(result);
    }

    @RequestMapping(value = {"/message.html", "message.htm"}, method = RequestMethod.GET)
    @ApiOperation("留言板")
    public String message(Model model) {

        ArrayList<MessageList> messageLists = new ArrayList<>();
        for (Comment m : commentService.listCommentsByType(0)) {
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
                messageLists.add(message);
            } else {
                for (MessageList ml : messageLists) {
                    if (ml.getId() == m.getCommentBaseId()) {
                        String parentName = null;
                        if (m.getCommentParentId() == ml.getId()) {
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
        model.addAttribute("messageLists", messageLists);

        return "message";
    }
}
