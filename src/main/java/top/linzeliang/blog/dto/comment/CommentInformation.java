package top.linzeliang.blog.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.linzeliang.blog.utils.DateUtil;

import java.util.Date;

/**
 * @Description: TODO
 * @Author: linzeliang
 * @Date: 2021/8/22
 */
@Data
@NoArgsConstructor
@ToString
public class CommentInformation {

    private long id;

    private String content;

    private String username;

    private String host;

    private long parentId;

    private String article;

    private String createTime;

    public CommentInformation(long id, String content, String username, String host, long parentId, long articleId, Date createTime) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.host = host;
        this.parentId = parentId;
        this.article = "http://localhost/read.html?aid=" + articleId;
        this.createTime = DateUtil.dateToString(createTime, "yyyy-MM-dd HH:mm:ss");
    }
}
