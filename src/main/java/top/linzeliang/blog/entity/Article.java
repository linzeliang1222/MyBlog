package top.linzeliang.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Description: Article实体类
 * @Author: LinZeLiang
 * @Date: 2021-07-31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article {

    private Long articleId;

    private String articleTitle;

    private String articleDigest;

    private String articleContent;

    private Date articleCreateTime;

    private Date articleUpdateTime;

    private Integer articleReadCount;

    private Integer articleCommentCount;

    private Byte articleSetTop;

    private Byte articleDeleted;

    private String articleIp;

    private Long articleUserId;

    private Long articleTagId;

    private User user;

    private Tag tag;

    public Article(String articleTitle, String articleDigest, String articleContent, Date articleCreateTime, Date articleUpdateTime, String articleIp, Long articleUserId, Long articleTagId) {
        this.articleTitle = articleTitle;
        this.articleDigest = articleDigest;
        this.articleContent = articleContent;
        this.articleCreateTime = articleCreateTime;
        this.articleUpdateTime = articleUpdateTime;
        this.articleIp = articleIp;
        this.articleUserId = articleUserId;
        this.articleTagId = articleTagId;
    }

    public Article(Long articleId, String articleTitle, String articleDigest, String articleContent, Date articleUpdateTime, String articleIp, Long articleUserId, Long articleTagId) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleDigest = articleDigest;
        this.articleContent = articleContent;
        this.articleUpdateTime = articleUpdateTime;
        this.articleIp = articleIp;
        this.articleUserId = articleUserId;
        this.articleTagId = articleTagId;
    }
}
