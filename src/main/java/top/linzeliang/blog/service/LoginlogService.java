package top.linzeliang.blog.service;

import top.linzeliang.blog.entity.Loginlog;

import java.util.List;

/**
 * @Description: TODO
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
public interface LoginlogService {

    int writeLoginlog(Loginlog loginlog);

    int getTheNumberOfLoginlog();

    List<Loginlog> selectLoginlogByUid(int uid);

    List<Loginlog> selectAllLoginlogs();
}
