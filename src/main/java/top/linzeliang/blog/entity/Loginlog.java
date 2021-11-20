package top.linzeliang.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Description: 登录日志实体对象
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Loginlog {

    private long loginlogUserId;

    private String loginlogUserPassword;

    private String loginlogIp;

    private Date loginlogTime;
}
