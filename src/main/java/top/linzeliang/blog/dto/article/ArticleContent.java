package top.linzeliang.blog.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.linzeliang.blog.entity.Tag;

import java.util.List;

/**
 * @Description: 文章对象内容
 * @Author: LinZeLiang
 * @Date: 2021-08-02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleContent {

    private String title;

    private String content;

    private Tag tag;

    private List<Tag> tags;


}
