package top.linzeliang.blog.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description: TODO
 * @Author: linzeliang
 * @Date: 2021/8/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleExtend {
    private long id;

    private String title;
}
