package top.linzeliang.blog.service;

import top.linzeliang.blog.entity.Tag;

import java.util.List;

/**
 * @Description: TODO
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
public interface TagService {

    String insertTag(Tag tag);

    int deleteTag(int tagId);

    String updateTag(Tag tag);

    Tag selectTagById(int tagId);

    List<Tag> listTags();

    /**
     * 获取文章总数
     * @return int
     * @date 2021-08-02
     */
    int getTheNumberOfAllArticles();
}
