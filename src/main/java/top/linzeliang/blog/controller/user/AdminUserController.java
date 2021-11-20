package top.linzeliang.blog.controller.user;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.linzeliang.blog.constant.Constant;
import top.linzeliang.blog.entity.Loginlog;
import top.linzeliang.blog.entity.User;
import top.linzeliang.blog.enums.StateCodeEnum;
import top.linzeliang.blog.service.LoginlogService;
import top.linzeliang.blog.service.UserService;
import top.linzeliang.blog.utils.ControllerUtil;
import top.linzeliang.blog.utils.IpAddressUtil;
import top.linzeliang.blog.utils.JsonResultUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Description: User Controller
 * @Author: linzeliang
 * @Date: 2021/8/20
 */
@RestController
@RequestMapping("/api/admin/user")
@Api("用户信息相关API")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginlogService loginlogService;

    @RequestMapping(value = "/checkPassword",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("检查旧密码")
    public JSONObject checkPassword(@RequestParam(value = "oldPassword", required = true) String oldPassword,
                                    HttpServletRequest request) {
        // 检查旧密码
        Subject subject = SecurityUtils.getSubject();
        oldPassword = new SimpleHash("MD5", oldPassword, null, 1).toString();
        User user = (User)subject.getSession().getAttribute("user");

        if (!user.getUserPassword().equals(oldPassword)) {
            return JsonResultUtil.getJson(StateCodeEnum.FAIL, "密码错误");
        } else {
            return JsonResultUtil.getJson(StateCodeEnum.SUCCESS, Constant.SUCCESS_RESULT);
        }
    }

    @RequestMapping(value = "/updatePassword",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("更新密码")
    public JSONObject updatePassword(@RequestParam(value = "newPassword", required = true) String newPassword) {

        // 先计算密码加密结果
        newPassword = new SimpleHash("MD5", newPassword, null, 1).toString();

        // 获取session，直接吧session中的密码改成新的，传递给service修改
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        user.setUserPassword(newPassword);

        String result = userService.changePassword(user);
        JSONObject json = ControllerUtil.setResult(result);
        return json;
    }

    @RequestMapping(value = "/login",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("登陆")
    public JSONObject login(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpServletRequest request) {

        //得到Subject,通过SecurityUtils得到Subject，其会自动绑定到当前线程；如果在web环境在请求结束时需要解除绑定
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {

            //创建用户名/密码身份验证Token（即用户身份/凭证）
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            //关闭rememberMe
            token.setRememberMe(false);

            // 进行登陆
            try {
                subject.login(token);
                User user = (User)subject.getSession().getAttribute("user");
                loginlogService.writeLoginlog(new Loginlog(user.getUserId(), user.getUserPassword(), IpAddressUtil.getIpAddress(request), new Date()));
            } catch (UnknownAccountException | IncorrectCredentialsException e) {
                return JsonResultUtil.getJson(StateCodeEnum.FAIL, "账号或密码错误");
            }

            return JsonResultUtil.getJson(StateCodeEnum.SUCCESS, Constant.SUCCESS_RESULT);
        }

        return JsonResultUtil.getJson(StateCodeEnum.FAIL, "重复登陆");
    }

    @RequestMapping(value = "/logout",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.GET)
    @ApiOperation("登出")
    public JSONObject logout() {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return JsonResultUtil.getJson(StateCodeEnum.SUCCESS, Constant.SUCCESS_RESULT);
    }

    @RequestMapping(value = "/get",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("获取用户信息")
    public JSONObject getUser() {
        // 从session中获取user
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getSession().getAttribute("user");

        return JsonResultUtil.getJson(StateCodeEnum.SUCCESS, user, Constant.SUCCESS_RESULT);
    }

    @RequestMapping(value = "/update",
            produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    @ApiOperation("更新用户信息")
    public JSONObject updateUser(User user) {

        // 先获取session里的user
        Subject subject = SecurityUtils.getSubject();
        User sessionUser = (User)subject.getSession().getAttribute("user");

        // 进行更新
        user.setUserId(sessionUser.getUserId());
        String result = userService.updateUserInfo(user);
        // 数据库的用户信息更新成功那么也更新session中的用户
        if ("success".equals(result)) {
            sessionUser.setUserNickname(user.getUserNickname());
            sessionUser.setUserAvatar(user.getUserAvatar());
            sessionUser.setUserEmail(user.getUserEmail());
            sessionUser.setUserIntroduction(user.getUserIntroduction());
            subject.getSession().setAttribute("user", sessionUser);
        }

        // 返回更新结果
        JSONObject json = ControllerUtil.setResult(result);
        return json;
    }

}
