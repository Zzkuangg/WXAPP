package com.boyier.mapper;

import com.boyier.pojo.Room;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoomMapper {
    /**
     * 这里放对于数据库的操作办法
     * 在mapper下的xml注册组件
     * @return
     */

    //查询接口
    public List<Room> queryAll();
    public List<Room> queryRoomById(String u_id);

    //添加数据
    public int addRoom(Room room);

    //删除数据

    public int delRoom(Room room);
}
