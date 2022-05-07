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
        boolean judge = false;
        //判断有没有这个房间
        for (Room room : roomMapper.queryAll()) {
            if (room.getR_id().equals(facility.getF_room())) {
                judge = true;
                break;
            }
        }
        if (!judge) {
            return "无此房间";
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
        if ("".equals(f_id)) {
            return "信息不完整";
        }
        boolean judge = false;
        for (Facility facility : facilityMapper.queryAllFacility()) {
            if (f_id.equals(facility.getF_id())) {
                judge = true;
                break;
            }
        }
        if (!judge) {
            return "无此设备";
        }

        int flag = facilityMapper.delFacility(f_id);
        System.out.println(flag);
        if (flag == 1) {
            return "Success";
        } else {
            return "Fail";
        }
    }

    @Override
    public Facility packFacility(String f_id, String f_name, String r_id, String f_band) {
        String f_img = "";
        switch (f_name) {
            case "空调":
                f_img = "/img/air_conditioner.png";
                break;
            case "电脑":
                f_img = "/img/computer.png";
                break;
            case "台灯":
                f_img = "/img/lamp.png";
                break;
            case "净水器":
                f_img = "/img/purifier.png";
                break;
            case "路由器":
                f_img = "/img/router.png";
                break;
        }
        Facility facility = new Facility(f_id, f_name, r_id, f_img, f_band);
        return facility;
    }
}
