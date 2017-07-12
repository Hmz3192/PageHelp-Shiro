package com.hmz.ssm.service.impl;

import com.hmz.ssm.dao.AdminMapper;
import com.hmz.ssm.model.Admin;
import com.hmz.ssm.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/7/11.
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Resource
    AdminMapper adminMapper;

    @Override
    public List<Admin> selectAllPage() {
        return adminMapper.selectAllPage();
    }

    @Override
    public Admin findUserByName(String username) {
        return adminMapper.findUserByName(username);
    }
}
