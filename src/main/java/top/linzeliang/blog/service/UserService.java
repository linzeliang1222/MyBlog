package top.linzeliang.blog.service;

import top.linzeliang.blog.entity.User;

import java.util.List;

/**
 * @Description: TODO
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
public interface UserService {

    String insertUser(User user);

    String updateUserInfo(User user);

    String changePassword(User user);

    User selectUserByNameAndPassword(String name, String password);

    User selectUserById(int id);

    List<User> selectAllUsers();

    User selectUserByName(String name);
}
