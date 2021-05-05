package com.shiro.service.impl;

import com.shiro.bean.Admin;
import com.shiro.bean.Permission;
import com.shiro.mapper.AdminMapper;
import com.shiro.service.AdminService;
import com.shiro.util.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public void register(Admin admin) {
        //处理业务调用mapper
        //1.生存随机盐
        String salt = SaltUtils.getSalt(7);
        //2.将随机盐保存到数据
        admin.setSalt(salt);
        //3.明文密码进行md5+salt+hash散列
        Md5Hash md5Hash = new Md5Hash(admin.getPassword(),salt,1023);
        admin.setPassword(md5Hash.toHex());
        adminMapper.save(admin);
    }

    @Override
    public Admin findByUsername(String username) {
        return adminMapper.findByUsername(username);
    }

    @Override
    public Admin findRoleByUsername(String username) {
        return adminMapper.findRoleByUsername(username);
    }

    @Override
    public List<Permission> findPermissionByRoleId(String id) {
        return adminMapper.findPermissionByRoleId(id);
    }
}
