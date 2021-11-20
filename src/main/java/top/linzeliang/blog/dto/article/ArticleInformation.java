package top.linzeliang.blog.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.linzeliang.blog.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 文章对象简要信息
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleInformation {
    private long id;

    private String title;

    private String createTime;

    private String updateTime;

    private String tag;

    private int read;

    private int comment;

    private int top;

    public ArticleInformation(long id, String title, Date createTime, Date updateTime, String tag, int read, int comment, int top) {
        this.id = id;
        this.title = title;
        this.createTime = DateUtil.dateToString(createTime, "yyyy-MM-dd HH:mm:ss");
        this.updateTime = DateUtil.dateToString(updateTime, "yyyy-MM-dd HH:mm:ss");
        this.tag = tag;
        this.read = read;
        this.comment = comment;
        this.top = top;
    }
}
