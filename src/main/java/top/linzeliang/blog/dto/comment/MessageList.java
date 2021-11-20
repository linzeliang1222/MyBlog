package top.linzeliang.blog.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: TODO
 * @Author: linzeliang
 * @Date: 2021/8/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageList {

    private long id;

    private String username;

    private String parentUsername;

    private String content;

    private Date createTime;

    private long parentId;

    private long articleId;

    private List<MessageList> childMessage;

    public MessageList(long id, String username, String parentUsername, String content, Date createTime, long parentId, long articleId) {
        this.id = id;
        this.username = username;
        this.parentUsername = parentUsername;
        this.content = content;
        this.createTime = createTime;
        this.parentId = parentId;
        this.articleId = articleId;
        childMessage = new ArrayList<>();
    }
}
