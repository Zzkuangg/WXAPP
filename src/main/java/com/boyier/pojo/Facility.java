package com.boyier.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Facility {
    private String f_id;
    private String f_name;
    private String f_room;
    private String f_img;
    private String f_band;
    private String identify;
}
