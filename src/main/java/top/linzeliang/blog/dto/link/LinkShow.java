package top.linzeliang.blog.dto.link;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Description: TODO
 * @Author: linzeliang
 * @Date: 2021/8/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LinkShow {

    private String name;

    private String site;

    private String avatar;

    private String description;
}
