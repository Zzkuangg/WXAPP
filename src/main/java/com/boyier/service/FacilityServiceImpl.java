package com.boyier.service;

import com.alibaba.fastjson.JSON;
import com.boyier.mapper.FacilityMapper;
import com.boyier.mapper.RoomMapper;
import com.boyier.pojo.Facility;
import com.boyier.pojo.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    FacilityMapper facilityMapper;

    @Autowired
    RoomMapper roomMapper;

    @Override
    public String getFacilityByRoom(String f_room) {
        List<Facility> res = facilityMapper.queryFacility(f_room);
        return JSON.toJSONString(res);
    }

    @Override
    public String getFacilityById(String f_id) {
        Facility res = facilityMapper.queryOneFacility(f_id);
        return JSON.toJSONString(res);
    }

    @Override
    public String addFacility(Facility facility) {
        if ("".equals(facility.getF_id()) ||
                "".equals(facility.getF_room()) ||
                "".equals(facility.getF_band()) ||
                "".equals(facility.getF_name())) {
            return "信息不完整";
        }

        int flag = facilityMapper.addFacility(facility);
        System.out.println(flag);
        if (flag == 1) {
            return "Success";
        } else {
            return "Fail";
        }
    }

    @Override
    public String delFacility(String f_id) {

        int flag = facilityMapper.delFacility(f_id);

        System.out.println(f_id);
        if (flag == 1) {
            return "Success";
        } else {
            return "Fail";
        }
    }


}
