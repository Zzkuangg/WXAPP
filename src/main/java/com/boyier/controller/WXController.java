package com.boyier.controller;

import com.alibaba.fastjson.JSON;
import com.boyier.pojo.Cond;
import com.boyier.pojo.Facility;
import com.boyier.pojo.Room;
import com.boyier.pojo.Users;
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

    @Autowired
    UserService userService;

    @RequestMapping("/loginSys")
    public String loginSys(String u_name, String u_pwd) {
        return userService.login(u_name, u_pwd);
    }

    @RequestMapping("/addUsr")
    public String addUsr(String u_name, String u_pwd,String u_prior) {
        return userService.addUser(new Users("1",u_name,u_pwd,u_prior));
    }

    @RequestMapping("/getUsr")
    public String getUsr() {
        return userService.queryAll();
    }

    @RequestMapping("/delUsr")
    public String delUsr(String u_name) {
        return userService.delUser(u_name);
    }

    @RequestMapping("/getRoom")
    public String getRoom() {
        return roomService.getAllRoom();
    }

    @RequestMapping("/getRoomById")
    public String getRoomById(String id) {
        return roomService.getRoomById(id);
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
        System.out.println(id);
        return condService.getCondById(id);
    }

    @RequestMapping("/postData")
    public String postData(String r_id, String r_name, String f_band, String f_id, String f_name, String img, String method, String r_usr) {
        String res = "";
        if (f_id == null) {
            //房间操作
            if ("add".equals(method)) {
                //插入
                res = roomService.addRoom(new Room(r_id, r_name, img, r_usr,""));
            } else {
                //删除
                res = roomService.delRoom(new Room("", r_name, img, r_usr,r_id));
            }

        } else {
            //设备操作
            if ("add".equals(method)) {
                //插入
                res = facilityService.addFacility(new Facility(f_id, f_name, r_id, img, f_band,""));
            } else {
                //删除

                res = facilityService.delFacility(f_id);
            }
        }
        return res;
    }
}
