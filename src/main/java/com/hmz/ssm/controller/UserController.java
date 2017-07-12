package com.hmz.ssm.controller;

import com.hmz.ssm.model.Collect;
import com.hmz.ssm.model.Message;
import com.hmz.ssm.model.User;
import com.hmz.ssm.service.CollectService;
import com.hmz.ssm.service.MessageService;
import com.hmz.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/6/18.
 */
@Controller
@SessionAttributes("username")
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private CollectService collectService;
    @Resource
    private MessageService messageService;


    @RequestMapping("/show")
    public String Show(){

        return "LoginOld";
    }
    @RequestMapping("/Register")
    public String Register(){

        return "register";
    }
    @RequestMapping(value = "/RegisterInfo",method =RequestMethod.POST)
    public String RegisterInfo(Model model, User user, HttpSession session){
        if(userService.insertUser(user)==1){
            model.addAttribute("user",user.getUsername());
            session.setAttribute("username",user.getUsername());
            return "all";
        }else
            return "LoginOld";


    }




    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public String Login(HttpServletRequest request, User user,HttpSession session) {

        String username = user.getUsername();
//        String username=request.getParameter("username");
        boolean a = userService.selectUser(username);
        if (a) {
            session.setAttribute("username",username);
            return "all";
        } else {
            System.out.print("账户密码输入错误");
            return "LoginOld";
        }
    }

    @RequestMapping("/colle")
    public String Colle(Model model, HttpServletRequest httpServletRequest,HttpSession session)throws Exception{

        String id=httpServletRequest.getParameter("id");
        String username= (String) session.getAttribute("username");
        User user=userService.selectUserMes(username);
        int userid=user.getId();
        int mid=Integer.parseInt(id);
        Collect collect=new Collect();
        collect.setUid(userid);
        collect.setColId(mid);
        if(collectService.insertColle(collect)==1){
            List<Message> messageList=new ArrayList<Message>();
            List<Collect> listcol=collectService.selectColl(userid);
            Message message;
            for(Collect collect1:listcol){
                int mid_1=collect1.getColId();
                message=messageService.selectMes(mid_1);
                message.setId(collect1.getId());
                messageList.add(message);
            }

            model.addAttribute("colist",messageList);
            return "Collect";
        }else
            return "Message";
    }

    @RequestMapping("/seeColle")
    public String seeColle(Model model,HttpSession session){
        String username= (String) session.getAttribute("username");
        User user=userService.selectUserMes(username);
        int userid=user.getId();
        Collect collect=new Collect();
        collect.setUid(userid);
        List<Message> messageList=new ArrayList<Message>();
        List<Collect> listcol=collectService.selectColl(userid);
        Message message;
        for(Collect collect2:listcol)
        {
            int mid_1=collect2.getColId();
            message=messageService.selectMes(mid_1);
            message.setId(collect2.getId());
            messageList.add(message);
        }
        model.addAttribute("colist",messageList);
        return "Collect";
    }
}
