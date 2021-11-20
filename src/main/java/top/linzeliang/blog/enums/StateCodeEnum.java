package top.linzeliang.blog.enums;

/**
 * @Description: 状态码
 * @Author: LinZeLiang
 * @Date: 2021-08-02
 */
public enum StateCodeEnum {

    /**
     * 请求成功
     */
    SUCCESS(0, "请求成功"),

    /**
     * 请求失败
     */
    FAIL(1, "请求失败");

    private int state;
    private String stateInfo;

    StateCodeEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public static StateCodeEnum stateOf(int index) {
        for (StateCodeEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
