package com.mos.console.rbac.controller;

import com.mos.console.rbac.entity.SystemUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description:
 * @date: 2020/11/17 19:16
 * @author: wangchaodz@gmail.com
 */
@Slf4j
@Controller(value = "/")
public class LoginController {

    @RequestMapping("/loginHtml")
    public String loginHtml() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(SystemUser user) {
        if (StringUtils.isEmpty(user.getLoginName()) || StringUtils.isEmpty(user.getLoginPwd())) {
            return "请输入用户名和密码！";
        }
        //用户认证
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getLoginPwd());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return "没有账户！";
        } catch (AuthenticationException e) {
            return "认证失败！";
        } catch (AuthorizationException e) {
            return "没有权限！";
        }
        return "home";
    }

    @RequestMapping("/unrole")
    public String unrole(Model model) {
        model.addAttribute("msg", "没有权限");
        return "unrole";
    }

    @RequestMapping("thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("title", "test");
        model.addAttribute("body", "hello world");
        return "index";
    }
}
