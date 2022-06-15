package com.boyier.mapper;

import com.boyier.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    //添加数据
    public int addUser(Users user);

    public Users queryUser(String u_name);
    //删除数据

    public int delUser(String u_name);

    public List<Users> queryAll();

}
