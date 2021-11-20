package top.linzeliang.blog.mapper;

import org.apache.ibatis.annotations.Param;
import top.linzeliang.blog.entity.Link;

import java.util.List;

/**
 * @Description: Link Mapper
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
public interface LinkMapper {

    /**
     * 添加友链
     * @param link 友链实体类
     * @return int
     * @date 2021-08-01
     */
    int insertLink(Link link);

    /**
     * 通过id删除友链
     * @param linkId 友链id
     * @return int
     * @date 2021-08-01
     */
    int deleteLinkById(@Param("linkId") int linkId);

    /**
     * 通过用户id获取友链
     * @param userId 用户id
     * @return java.util.List<top.linzeliang.blog.entity.Link>
     * @date 2021-08-01
     */
    List<Link> listLinksByUid(@Param("userId") int userId);

    /**
     * 查询所有友链
     * @return java.util.List<top.linzeliang.blog.entity.Link>
     * @date 2021-08-01
     */
    List<Link> listLinks();

    /**
     * 根据id查询友链
     * @param linkId 友链id
     * @return top.linzeliang.blog.entity.Link
     * @date 2021-08-01
     */
    Link queryLinkById(@Param("linkId") int linkId);

    /**
     * 更新友链
     * @param link 友链实体对象
     * @return int
     * @date 2021-08-01
     */
    int updateLink(Link link);

    /**
     * 获取友链总数
     * @return int
     * @date 2021-08-01
     */
    int getTheTotalNumberOfLink();

    /**
     * 根据id获取某用户的友链总数
     * @param userId 用户id
     * @return int
     * @date 2021-08-01
     */
    int getTheTotalNumberOfLinksAccordingToUid(@Param("userId") int userId);
}
