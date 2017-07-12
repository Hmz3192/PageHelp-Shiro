package com.hmz.ssm.controller;

import com.hmz.ssm.util.CipherUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/7/11.
 */
@Controller
public class ShiroController {


    /**
     * 初始登陆界面
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String tologin(HttpServletRequest request, HttpServletResponse response, Model model){
        System.out.println("来自IP[" + request.getRemoteHost() + "]的访问");
        return "login";
    }

    /**
     * 验证用户名和密码
     * @param request
     * @return
     */
    @RequestMapping("/checkLogin")
    public String login(HttpServletRequest request) {
        String result = "login";
        // 取得用户名
        String username = request.getParameter("username");
        //取得 密码，并用MD5加密
        String password = CipherUtil.generatePassword(request.getParameter("password"));
        //String password = request.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        Subject currentUser = SecurityUtils.getSubject();
        try {
            System.out.println("----------------------------");
            if (!currentUser.isAuthenticated()){//使用shiro来验证
                token.setRememberMe(true);
                currentUser.login(token);//验证角色和权限
            }
            System.out.println("result: " + result);
            result = "index";//验证成功
        } catch (Exception e) {
            System.out.println("验证失败"+e.getMessage());
            result = "login";//验证失败
        }
        return result;
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout() {

        Subject currentUser = SecurityUtils.getSubject();
        String result = "logout";
        currentUser.logout();
        return result;
    }

}
