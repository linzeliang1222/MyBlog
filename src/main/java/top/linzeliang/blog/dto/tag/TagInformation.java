package top.linzeliang.blog.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description: TODO
 * @Author: LinZeLiang
 * @Date: 2021-08-06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TagInformation {

    private long id;

    private String title;
}
