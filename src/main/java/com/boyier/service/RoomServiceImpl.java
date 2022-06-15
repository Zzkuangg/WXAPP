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
        for (Room room1 : roomMapper.queryRoomById(room.getR_usr())) {
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


        int flag = roomMapper.delRoom(room);

        for (Facility facility : facilityMapper.queryFacility(room.getIdentify())) {
            facilityMapper.delFacility(facility.getF_id());
        }


        if (flag == 1) {
            return "Success";
        } else {
            return "Fail";
        }
    }

    @Override
    public String getRoomById(String u_id) {
        List<Room> rooms = roomMapper.queryRoomById(u_id);
        return JSON.toJSONString(rooms);
    }
}
