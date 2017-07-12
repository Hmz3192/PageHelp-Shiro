package com.hmz.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hmz.ssm.model.Admin;
import com.hmz.ssm.service.AdminService;
import com.hmz.ssm.util.PageHelp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/7/11.
 */
@Controller
@RequestMapping("/page")
public class PagerController {

    @Resource
    AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public PageHelp list(@RequestParam(defaultValue = "2") int page,@RequestParam(defaultValue = "3") int rows) {
        PageHelper.startPage(page, rows);
        List<Admin> adminslist = adminService.selectAllPage();
        PageHelp pageHelp = new PageHelp();

        pageHelp.setRows(adminslist);
        PageInfo<Admin> pageInfo = new PageInfo<>(adminslist);
        pageHelp.setTotal(pageInfo.getTotal());
        return pageHelp;
    }



}
