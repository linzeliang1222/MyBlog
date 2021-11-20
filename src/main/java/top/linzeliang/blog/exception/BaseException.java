package top.linzeliang.blog.exception;

/**
 * @Description: TODO
 * @Author: LinZeLiang
 * @Date: 2021-08-02
 */
public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
