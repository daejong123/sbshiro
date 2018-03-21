package com.dottie.sbshiro.controller;

import com.dottie.sbshiro.domain.User;
import com.dottie.sbshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author lindazhong
 * @date 2018/3/20 10:39
 */
@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // 在shiro中配置过了，必须认证后才可以
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/loginUser")
    public String loginUser(String username,String password,HttpSession session) {
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);   //完成登录
            User user = (User) subject.getPrincipal();
            session.setAttribute("user", user);
            System.out.println("登录成功");
            return "redirect:/index";
        } catch (UnknownAccountException ex) {
            session.setAttribute("msg", ex.getMessage());
            System.out.println(ex.getMessage());
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码不正确!");
            session.setAttribute("msg", "密码不正确!");
        } catch (Exception e) {
            System.out.println("登录不对。。。" + e.getMessage());
        }
        return "redirect:/login";//返回登录页面
    }

    @RequestMapping("/logOut")
    public String logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }
}
/**
 * 我们和shiro框架的交互完全通过Subject这个类去交互,用它完成登录,注销,获取当前的用户对象等操作
 * Subject subject = SecurityUtils.getSubject();
 */

/**
 *下面建立两个类,这也是shiro中唯一需要程序员编写的两个类:
 * 类AuthRealm完成根据用户名去数据库的查询,并且将用户信息放入shiro中,
 * 供第二个类调用.CredentialsMatcher,完成对于密码的校验.
 */

/**
 *guest标签：验证当前用户是否为“访客”，即未认证（包含未记住）的用户；shiro标签：<shiro:guest></shiro:guest>  ；freemark中： <@shiro.guest>  </@shiro.guest>
 user标签：认证通过或已记住的用户 shiro标签：<shiro:user> </shiro:user>  ；freemark中： <@shiro.user> </@shiro.user>
 authenticated标签：已认证通过的用户。不包含已记住的用户，这是与user标签的区别所在。 shiro标签：<shiro:authenticated> </shiro:authenticated>；freemark中： <@shiro.authenticated></@shiro.authenticated>
 notAuthenticated标签：未认证通过的用户。与authenticated标签相对。 shiro标签：<shiro:notAuthenticated> </shiro:notAuthenticated>；freemark中： <@shiro.notAuthenticated></@shiro.notAuthenticated>
 principal标签：输出当前用户信息，通常为登录帐号信息  shiro标签：Hello,  <@shiro.principal property="name" />  ；freemarker中：  Hello,  <@shiro.principal property="name" />, how are you today?
 hasRole标签：验证当前用户是否属于该角色 ，shiro标签： <shiro:hasRole name="administrator">  Administer the system </shiro:hasRole> ；freemarker中：<@shiro.hasRole name=”admin”>Hello admin!</@shiro.hasRole>
 hasAnyRoles标签：验证当前用户是否属于这些角色中的任何一个，角色之间逗号分隔 ，shiro标签： <shiro:hasAnyRoles name="admin,user,operator">  Administer the system </shiro:hasAnyRoles> ；freemarker中：<@shiro.hasAnyRoles name="admin,user,operator">Hello admin!</@shiro.hasAnyRoles>
 hasPermission标签：验证当前用户是否拥有该权限 ，shiro标签： <shiro:hasPermission name="/order:*">  订单 </shiro:hasPermission> ；freemarker中：<@shiro.hasPermission name="/order:*">订单/@shiro.hasPermission>
 lacksRole标签：验证当前用户不属于该角色，与hasRole标签想反，shiro标签： <shiro:hasRole name="admin">  Administer the system </shiro:hasRole> ；freemarker中：<@shiro.hasRole name="admin">Hello admin!</@shiro.hasRole>
 lacksPermission标签：验证当前用户不拥有某种权限，与hasPermission标签是相对的，shiro标签： <shiro:lacksPermission name="/order:*"> trade </shiro:lacksPermission> ；freemarker中：<@shiro.lacksPermission name="/order:*">trade</@shiro.lacksPermission>
 */