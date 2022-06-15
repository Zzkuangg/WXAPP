package com.boyier.service;

import com.alibaba.fastjson.JSON;
import com.boyier.mapper.CondMapper;
import com.boyier.pojo.Cond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CondServiceImpl implements CondService {

    @Autowired
    CondMapper condMapper;

    @Override
    public String getCondById(String c_facility) {
        List<Cond> conds = condMapper.queryCond(c_facility);

        return JSON.toJSONString(conds.get(0));
    }

    @Override
    public String addCond(Cond cond) {
        System.out.println(cond);
        int flag = condMapper.addCond(cond);
        System.out.println(flag);
        if (flag == 1) {
            return "Success";
        } else {
            return "Fail";
        }
    }
}
