package com.boyier.service;

import com.boyier.pojo.Facility;

import java.util.List;

public interface FacilityService {
    public String getFacilityByRoom(String f_room);

    public String getFacilityById(String f_id);

    public String addFacility(Facility facility);

    public String delFacility(String f_id);

}
