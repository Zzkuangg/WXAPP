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
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    FacilityMapper facilityMapper;

    @Override
    public String getAllRoom() {
        List<Room> res = roomMapper.queryAll();
        return JSON.toJSONString(res);
    }

    @Override
    public String addRoom(Room room) {
        if ("".equals(room.getR_id()) ||
                "".equals(room.getR_name())) {
            return "信息不完整";
        }

        boolean judge = false;
        for (Room room1 : roomMapper.queryAll()) {
            if (room1.getR_id().equals(room.getR_id())) {
                judge = true;
                break;
            }
        }
        if (judge) {
            return "房间已存在";
        }

        int flag = roomMapper.addRoom(room);
        System.out.println(flag);
        if (flag == 1) {
            return "Success";
        } else {
            return "Fail";
        }
    }

    @Override
    public String delRoom(Room room) {
        if ("".equals(room.getR_id())) {
            return "信息不完整";
        }
        boolean judge = false;
        for (Room room1 : roomMapper.queryAll()) {
            if (room1.getR_id().equals(room.getR_id())) {
                judge = true;
                break;
            }
        }
        if (!judge) {
            return "无此房间";
        }
        int flag = roomMapper.delRoom(room);

        for (Facility facility : facilityMapper.queryFacility(room.getR_id())) {
            facilityMapper.delFacility(facility.getF_id());
        }


        if (flag == 1) {
            return "Success";
        } else {
            return "Fail";
        }
    }

    @Override
    public Room packRoom(String r_id, String r_name) {
        String r_img = "";
        switch (r_name) {
            case "卧室":
                r_img = "/img/bedroom.jpg";
                break;
            case "实验室":
                r_img = "/img/lab.jpg";
                break;
            case "办公室":
                r_img = "/img/working.jpg";
                break;
        }
        Room room = new Room(r_id, r_name, r_img);
        return room;
    }
}
