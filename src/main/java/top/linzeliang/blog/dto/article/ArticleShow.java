package top.linzeliang.blog.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Description: TODO
 * @Author: linzeliang
 * @Date: 2021/8/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleShow {

    private long id;

    private String title;

    private String digest;

    private Date createTime;

    private int readCount;

    private int commentCount;

    private String tagName;

    private int top;
}
