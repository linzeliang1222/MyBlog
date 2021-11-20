package top.linzeliang.blog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.linzeliang.blog.entity.Link;
import top.linzeliang.blog.mapper.LinkMapper;
import top.linzeliang.blog.service.LinkService;

import java.util.List;

/**
 * @Description: TODO
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Service
public class LinkServiceImpl implements LinkService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public String insertLink(Link link) {
        try {
            int result = linkMapper.insertLink(link);
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
    public String deleteLink(int id) {

        try {
            int result = linkMapper.deleteLinkById(id);
            // 删除成功
            if (1 == result) {
                return "success";
            } else {
                // 删除失败
                return "fail";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "error";
        }
    }

    @Override
    public String updateLink(Link link) {
        try {
            int result = linkMapper.updateLink(link);
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
    public List<Link> listLinks() {
        return linkMapper.listLinks();
    }

    @Override
    public Link selectLinkById(int id) {
        return linkMapper.queryLinkById(id);
    }

    @Override
    public List<Link> listLinksByUid(int uid) {
        return linkMapper.listLinksByUid(uid);
    }

    @Override
    public int countOfLink() {
        return linkMapper.getTheTotalNumberOfLink();
    }
}
