package top.linzeliang.blog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.linzeliang.blog.entity.Tag;
import top.linzeliang.blog.mapper.TagMapper;
import top.linzeliang.blog.service.TagService;

import java.util.List;

/**
 * @Description: TODO
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Service
public class TagServiceImpl implements TagService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TagMapper tagMapper;

    @Override
    public String insertTag(Tag tag) {
        try {
            int result = tagMapper.insertTags(tag);
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
    public int deleteTag(int id) {
        return tagMapper.deleteTags(id);
    }

    @Override
    public String updateTag(Tag tag) {

        try {
            int result = tagMapper.updateTags(tag);
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
    public Tag selectTagById(int id) {
        return tagMapper.queryTagsById(id);
    }

    @Override
    public List<Tag> listTags() {
        return tagMapper.queryAllTags();
    }

    @Override
    public int getTheNumberOfAllArticles() {
        return tagMapper.countTheNumberOfArticles();
    }
}
