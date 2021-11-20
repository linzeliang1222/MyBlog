package top.linzeliang.blog.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 用于显示文章列表
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleList {

    private long articleId;

    private String articleTitle;

    private String articleDigest;

    private Date articleCreateTime;

    private int articleReadCount;

    private int articleCommentCount;

    private short articleSetTop;

    private String articleTagName;
}
