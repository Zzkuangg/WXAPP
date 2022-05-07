package com.boyier.controller;

import com.alibaba.fastjson.JSON;
import com.boyier.pojo.Cond;
import com.boyier.pojo.Facility;
import com.boyier.pojo.Room;
import com.boyier.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class WXController {

    @Autowired
    RoomService roomService;

    @Autowired
    FacilityService facilityService;

    @Autowired
    CondService condService;

    @RequestMapping("/test")
    public String Test() {

        String p[] = new String[10];
        String c[] = new String[10];
        for (int i = 0; i < 10; i++) {
            p[i] = String.format("%.2f", Math.random() * 10);
            c[i] = i + "";
        }
        Cond cond = new Cond();
        cond.setC_power(Arrays.toString(p));
        cond.setC_categories(Arrays.toString(c));
        cond.setC_state("working");
        cond.setC_facility("10101");
//        return condService.addCond(cond);
        return JSON.toJSONString(cond);
    }

    @RequestMapping("/getRoom")
    public String getRoom() {
        return roomService.getAllRoom();
    }

    @RequestMapping("/getFacility")
    public String getFacility(String room) {
        return facilityService.getFacilityByRoom(room);
    }

    @RequestMapping("/getDeatil")
    public String getDeatil(String id) {
        return facilityService.getFacilityById(id);
    }

    @RequestMapping("/getTable")
    public String getTable(String id) {
        return condService.getCondById(id);
    }

    @RequestMapping("/postData")
    public String postData(String r_id, String r_name, String f_band, String f_id, String f_name, String img, String method) {
        String res = "";
        if (f_id == null) {
            //房间操作
            if ("add".equals(method)) {
                //插入
                res = roomService.addRoom(new Room(r_id,r_name,img));
            } else {
                //删除
                res = roomService.delRoom(new Room(r_id,r_name,img));
            }

        } else {
            //设备操作
            if ("add".equals(method)) {
                //插入
                res = facilityService.addFacility(new Facility(f_id, f_name, r_id, img, f_band));
            } else {
                //删除
                res = facilityService.delFacility(f_id);
            }
        }
        return res;
    }
}
