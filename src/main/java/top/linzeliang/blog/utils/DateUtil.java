package top.linzeliang.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 格式化日期工具类
 * @Author: LinZeLiang
 * @Date: 2021-08-02
 */
public class DateUtil {

    /**
     * 将Date转换成指定格式字符串
     * @param date 日期
     * @param pattern 格式化模式
     * @return java.lang.String
     * @date 2021-08-02
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

}
