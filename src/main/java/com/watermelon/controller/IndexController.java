package com.watermelon.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/"})
    public String index(){
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        try {
            //登陆以后进入UserRealm类doGetAuthenticationInfo方法进行认证
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e){
            model.addAttribute("msg","用户名错误!");
            return "login";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误!");
            return "login";
        }catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
    }

    @RequestMapping("/user/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "/user/update";
    }
}
