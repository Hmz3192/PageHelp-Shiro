package com.hmz.ssm.controller;

import com.hmz.ssm.model.Tran;
import com.hmz.ssm.util.Translate.TransApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/6/18.
 */
@Controller
@RequestMapping("/tran")
public class TranController {
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    public String APP_ID = "20170704000062503";
    public String SECURITY_KEY = "jqjDKde5tZs4KRUL5tPp";


    @RequestMapping(value = "/tran",method = RequestMethod.POST)
    public String Translate(@RequestBody  Tran tran, Model model) {
        TransApi transApi = new TransApi(APP_ID, SECURITY_KEY);
        String res= tran.getRes();
        System.out.println(res);

        String result = transApi.getTransResult(res, "auto", "en");
        System.out.println("结果是："+result);
        model.addAttribute("res",result);
        System.out.println(result);
        return "Tran";
    }

    @RequestMapping("/show")
    public String show(Model model) {
        String res = "";
        model.addAttribute("res",res);
        return "Tran";
    }
}
