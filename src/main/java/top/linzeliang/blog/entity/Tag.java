package top.linzeliang.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Description: 标签实体类
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tag {

    private long tagId;

    private String tagName;

    private Date tagCreateTime;

    public Tag(String tagName, Date tagCreateTime) {
        this.tagName = tagName;
        this.tagCreateTime = tagCreateTime;
    }
}
