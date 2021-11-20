package top.linzeliang.blog.mapper;

import org.apache.ibatis.annotations.Param;
import top.linzeliang.blog.entity.Loginlog;

import java.util.Date;
import java.util.List;

/**
 * @Description: Loginlog Mapper
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
public interface LoginlogMapper {

    /**
     * 写入登录日志
     * @param loginlog
     * @return int
     * @date 2021-08-01
     */
    int writeLoginlog(Loginlog loginlog);

    /**
     * 获取所有登录日志
     * @return java.util.List<top.linzeliang.blog.entity.Loginlog>
     * @date 2021-08-01
     */
    List<Loginlog> queryAllLoginlogs();

    /**
     * 根据用户id查询登录日志
     * @param userId 用户id
     * @return java.util.List<top.linzeliang.blog.entity.Loginlog>
     * @date 2021-08-01
     */
    List<Loginlog> queryLoginlogBasedOnUserId(@Param("userId") int userId);

    /**
     * 查询某个时间段的登录日志
     * @param startDate	开始时间
     * @param endDate 结束时间
     * @return java.util.List<top.linzeliang.blog.entity.Loginlog>
     * @date 2021-08-01
     */
    List<Loginlog> queryLoginlogForACertainPeriodOfTime(@Param("start") Date startDate, @Param("end") Date endDate);

    /**
     * 获取登录日志数量
     * @return int
     * @date 2021-08-01
     */
    int getTheNumberOfLoginlogs();
}
