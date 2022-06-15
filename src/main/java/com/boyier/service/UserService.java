package com.boyier.service;

import com.boyier.pojo.Cond;
import com.boyier.pojo.Users;

public interface UserService {
    public String login(String u_name,String u_pwd);

    public String addUser(Users user);

    public String delUser(String u_name);

    public String queryAll();

}
