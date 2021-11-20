package top.linzeliang.blog.mapper;

import org.apache.ibatis.annotations.Param;
import top.linzeliang.blog.entity.User;

import java.util.List;

/**
 * @Description: User Mapper
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
public interface UserMapper {

    /**
     * 增加用户
     * @param user 用户实体
     * @return int
     * @date 2021-08-01
     */
    int insertUser(User user);

    /**
     * 更新用户
     * @param user 用户实体
     * @return int
     * @date 2021-08-01
     */
    int updateUser(User user);

    /**
     * 更新用户密码
     * @param user 用户实体
     * @return int
     * @date 2021-08-01
     */
    int updateUserPassword(User user);

    /**
     * 根据id获取用户
     * @param userId 用户id
     * @return top.linzeliang.blog.entity.User
     * @date 2021-08-01
     */
    User getUsersBasedOnId(@Param("userId") int userId);

    /**
     * 根据用户名和密码查询用户
     * @param userName 用户名
     * @param userPassword 密码
     * @return top.linzeliang.blog.entity.User
     * @date 2021-08-01
     */
    User queryUsersBasedOnUserNameAndPassword(@Param("userName") String userName, @Param("userPassword") String userPassword);

    /**
     * 获取所有用户
     * @return java.util.List<top.linzeliang.blog.entity.User>
     * @date 2021-08-01
     */
    List<User> getAllUsers();

    /**
     * 根据用户名查询用户
     * @param name 用户名
     * @return top.linzeliang.blog.entity.User
     * @date 2021/8/20
     */
    User getUserByName(String name);
}
