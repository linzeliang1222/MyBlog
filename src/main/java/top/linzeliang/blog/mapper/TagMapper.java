package top.linzeliang.blog.mapper;

import org.apache.ibatis.annotations.Param;
import top.linzeliang.blog.entity.Tag;

import java.util.List;

/**
 * @Description: Tag Mapper
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
public interface TagMapper {

    /**
     * 获取标签总数
     * @return int
     * @date 2021-08-01
     */
    int getTheTotalNumberOfTags();

    /**
     * 添加标签
     * @param tag 标签实体
     * @return int
     * @date 2021-08-01
     */
    int insertTags(Tag tag);

    /**
     * 标签实体
     * @param tagId	标签id
     * @return int
     * @date 2021-08-01
     */
    int deleteTags(@Param("tagId") int tagId);

    /**
     * 编辑标签
     * @param tag 标签实体
     * @return int
     * @date 2021-08-01
     */
    int updateTags(Tag tag);

    /**
     * 获取所有标签
     * @return java.util.List<top.linzeliang.blog.entity.Tag>
     * @date 2021-08-01
     */
    List<Tag> queryAllTags();

    /**
     * 通过id查询标签
     * @param tagId	标签id
     * @return top.linzeliang.blog.entity.Tag
     * @date 2021-08-01
     */
    Tag queryTagsById(@Param("tagId") int tagId);

    /**
     * 获取文章总数量
     * @return int
     * @date 2021-08-01
     */
    int countTheNumberOfArticles();
}
