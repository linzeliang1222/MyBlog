package top.linzeliang.blog.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import top.linzeliang.blog.entity.User;
import top.linzeliang.blog.service.UserService;

import java.util.HashSet;
import java.util.Set;


/**
 * 自定义Realm，Shiro过滤器会从这里获取数据对用户进行认证
 * Created by ozc on 2017/12/8.
 *
 * @author Linzeliang
 * @version 1.0
 */
public class UserRealm extends AuthorizingRealm {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取权限信息，只有在身份验证成功后才调用此方法获取权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取用户名
        String username = (String) principals.getPrimaryPrincipal();
        //生成授权信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //给授权信息设置角色集合,只能放角色名
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        authorizationInfo.setRoles(roles);
        //给授权信息设置权限集合
        Set<String> stringPermissions = new HashSet<>();
        stringPermissions.add("9");
        authorizationInfo.setStringPermissions(stringPermissions);
        //返回用户授权信息
        return authorizationInfo;
    }

    /**
     * 获取身份验证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;
        //获取用户名
        String username = upToken.getUsername();
        //根据用户名获取User对象
        User user = userService.selectUserByName(username);

        if (user == null) {
            throw new UnknownAccountException();
        }

        /*
         * new一个身份验证信息
         */
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                user.getUserName(),
                //从数据库中查出的密文密码
                user.getUserPassword(),
                // 盐值
                //ByteSource.Util.bytes(username),
                //realm名称
                getName()
        );
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("user", user);
        return authenticationInfo;
    }

    /**
     * 清空realm缓存
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
