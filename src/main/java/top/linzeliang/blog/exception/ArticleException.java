package top.linzeliang.blog.exception;

/**
 * @Description: 文章相关异常
 * @Author: LinZeLiang
 * @Date: 2021-08-02
 */
public class ArticleException extends BaseException {
    public ArticleException(String message) {
        super(message);
    }

    public ArticleException(String message, Throwable cause) {
        super(message, cause);
    }
}
