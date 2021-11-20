package top.linzeliang.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Description: 评论实体类
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {

    private long commentId;

    private String commentUsername;

    private String commentContent;

    private String commentIp;

    private long commentParentId;

    private long commentBaseId;

    private Date commentCreateTime;

    private String commentPlace;

    private short commentType;

    private long commentArticleId;

    public Comment(String commentUsername, String commentContent, String commentIp, long commentParentId, long commentBaseId, Date commentCreateTime, String commentPlace, short commentType, long commentArticleId) {
        this.commentUsername = commentUsername;
        this.commentContent = commentContent;
        this.commentIp = commentIp;
        this.commentParentId = commentParentId;
        this.commentBaseId = commentBaseId;
        this.commentCreateTime = commentCreateTime;
        this.commentPlace = commentPlace;
        this.commentType = commentType;
        this.commentArticleId = commentArticleId;
    }
}
