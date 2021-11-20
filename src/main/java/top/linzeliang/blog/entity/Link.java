package top.linzeliang.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Description: 友链实体类
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Link {

    private long linkId;

    private String linkName;

    private String linkSite;

    private String linkAvatar;

    private String linkDescription;

    private Date linkCreateTime;

    private long linkUserId;

    public Link(String linkName, String linkSite, String linkAvatar, String linkDescription, Date linkCreateTime, long linkUserId) {
        this.linkName = linkName;
        this.linkSite = linkSite;
        this.linkAvatar = linkAvatar;
        this.linkDescription = linkDescription;
        this.linkCreateTime = linkCreateTime;
        this.linkUserId = linkUserId;
    }
}
