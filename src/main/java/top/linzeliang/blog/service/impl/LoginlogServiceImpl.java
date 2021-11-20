package top.linzeliang.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.linzeliang.blog.entity.Loginlog;
import top.linzeliang.blog.mapper.LoginlogMapper;
import top.linzeliang.blog.service.LoginlogService;

import java.util.List;

/**
 * @Description: TODO
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Service
public class LoginlogServiceImpl implements LoginlogService {

    @Autowired
    private LoginlogMapper loginlogMapper;

    @Override
    public int writeLoginlog(Loginlog loginlog) {
        return loginlogMapper.writeLoginlog(loginlog);
    }

    @Override
    public int getTheNumberOfLoginlog() {
        return loginlogMapper.getTheNumberOfLoginlogs();
    }

    @Override
    public List<Loginlog> selectLoginlogByUid(int uid) {
        return loginlogMapper.queryLoginlogBasedOnUserId(uid);
    }

    @Override
    public List<Loginlog> selectAllLoginlogs() {
        return loginlogMapper.queryAllLoginlogs();
    }
}
