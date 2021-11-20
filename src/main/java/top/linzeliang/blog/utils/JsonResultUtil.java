package top.linzeliang.blog.utils;

import com.alibaba.fastjson.JSONObject;
import top.linzeliang.blog.enums.StateCodeEnum;

/**
 * @Description: 封装JSON工具类
 * @Author: LinZeLiang
 * @Date: 2021-08-02
 */
public class JsonResultUtil {

    /**
     * 1. code : 响应业务状态
     * 2. msg: 响应消息
     * 3. data : 响应中的数据
     */

    public static JSONObject getJson(StateCodeEnum stateEmun, int count, Object data, String msg) {
        JSONObject json = new JSONObject();
        json.put("code", stateEmun.getState());
        json.put("msg", msg);
        json.put("count", count);
        json.put("data", data);
        return json;
    }

    public static JSONObject getJson(StateCodeEnum stateEmun, Object data, String msg) {
        JSONObject json = new JSONObject();
        json.put("code", stateEmun.getState());
        json.put("msg", msg);
        json.put("data", data);
        return json;
    }

    public static JSONObject getJson(StateCodeEnum stateEmun, String msg) {
        JSONObject json = new JSONObject();
        json.put("code", stateEmun.getState());
        json.put("msg", msg);
        return json;
    }

}