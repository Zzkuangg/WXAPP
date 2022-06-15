package com.boyier.service;

import com.alibaba.fastjson.JSON;
import com.boyier.mapper.UserMapper;
import com.boyier.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public String login(String u_name, String u_pwd) {
        Users users = userMapper.queryUser(u_name);
        if (users.getU_pwd().equals(u_pwd)) {
            return JSON.toJSONString(users);
        } else {
            return "Failed";
        }
    }

    @Override
    public String addUser(Users user) {
        boolean flag = true;
        for (Users users : userMapper.queryAll()) {
            if (users.getU_name().equals(user.getU_name())){
                flag = false;
                break;
            }
        }

        if (flag){
            userMapper.addUser(user);
            return "Success";
        }else {
            return "Fail";
        }
    }

    @Override
    public String delUser(String u_name) {
        userMapper.delUser(u_name);
        return null;
    }

    @Override
    public String queryAll() {
        return JSON.toJSONString(userMapper.queryAll());
    }
}
