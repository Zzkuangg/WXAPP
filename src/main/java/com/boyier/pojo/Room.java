package com.boyier.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private String r_id;
    private String r_name;
    private String r_img;
    private String r_usr;
    private String identify;
}
