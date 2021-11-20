package top.linzeliang.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Description: 用户实体类
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private long userId;

    private String userName;

    private String userPassword;

    private String userNickname;

    private String userEmail;

    private String userAvatar;

    private Date userRegistrationTime;

    private String userIntroduction;

    public User(String userName, String userPassword, String userNickname, String userEmail, Date userRegistrationTime) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userRegistrationTime = userRegistrationTime;
    }

    public User(long userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }
}
