package com.boyier.service;

import com.boyier.pojo.Cond;

import java.util.List;

public interface CondService {
    public String getCondById(String c_facility);

    public String addCond(Cond cond);
}
