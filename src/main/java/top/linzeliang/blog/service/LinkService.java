package top.linzeliang.blog.service;

import top.linzeliang.blog.entity.Link;

import java.util.List;

/**
 * @Description: TODO
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
public interface LinkService {

    String insertLink(Link link);

    String deleteLink(int id);

    String updateLink(Link link);

    List<Link> listLinks();

    Link selectLinkById(int id);

    List<Link> listLinksByUid(int uid);

    int countOfLink();
}
