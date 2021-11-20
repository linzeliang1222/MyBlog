package top.linzeliang.blog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.linzeliang.blog.entity.User;
import top.linzeliang.blog.mapper.UserMapper;
import top.linzeliang.blog.service.UserService;

import java.util.List;

/**
 * @Description: TODO
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public String insertUser(User user) {
        try {
            int result = userMapper.insertUser(user);
            if (1 == result) {
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @Override
    public String updateUserInfo(User user) {
        try {
            int result = userMapper.updateUser(user);
            if (1 == result) {
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @Override
    public String changePassword(User user) {
        try {
            int result = userMapper.updateUserPassword(user);
            if (1 == result) {
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @Override
    public User selectUserByNameAndPassword(String name, String password) {
        return userMapper.queryUsersBasedOnUserNameAndPassword(name, password);
    }

    @Override
    public User selectUserById(int id) {
        return userMapper.getUsersBasedOnId(id);
    }

    @Override
    public List<User> selectAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User selectUserByName(String name) {
        return userMapper.getUserByName(name);
    }
}
