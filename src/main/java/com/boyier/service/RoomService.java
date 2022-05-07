package com.boyier.service;

import com.boyier.pojo.Room;
import java.util.List;

public interface RoomService {
    public String getAllRoom();

    public String addRoom(Room room);

    public String delRoom(Room room);

    public Room packRoom(String r_id,String  r_name);
}
