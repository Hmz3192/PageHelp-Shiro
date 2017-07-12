package com.hmz.ssm.service;

import com.hmz.ssm.model.Admin;

import java.util.List;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/7/11.
 */
public interface AdminService {

    List<Admin> selectAllPage();

    Admin findUserByName(String username);
}
