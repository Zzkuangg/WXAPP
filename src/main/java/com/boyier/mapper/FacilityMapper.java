package com.boyier.mapper;

import com.boyier.pojo.Facility;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FacilityMapper {

    public List<Facility> queryFacility(String f_room);

    public Facility queryOneFacility(String f_id);

    public int addFacility(Facility facility);

    public int delFacility(String f_id);

    public List<Facility> queryAllFacility();

}
