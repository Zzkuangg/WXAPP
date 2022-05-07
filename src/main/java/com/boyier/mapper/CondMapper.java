package com.boyier.mapper;

import com.boyier.pojo.Cond;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CondMapper {

    public List<Cond> queryCond(String c_facility);

    public int addCond(Cond cond);


}
