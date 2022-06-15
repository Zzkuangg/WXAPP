package com.boyier.service;

import com.boyier.pojo.Room;

public interface RoomService {
    public String getAllRoom();

    public String addRoom(Room room);

    public String delRoom(Room room);

    public String getRoomById(String u_id);
}
