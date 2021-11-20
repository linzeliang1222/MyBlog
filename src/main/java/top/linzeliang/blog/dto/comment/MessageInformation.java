package top.linzeliang.blog.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description: TODO
 * @Author: linzeliang
 * @Date: 2021/8/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageInformation {
    private String content;

    private String username;

    private String host;

    private String createTime;
}
