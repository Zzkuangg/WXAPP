package com.boyier.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cond {
    private String c_id;
    private String c_facility;
    private String c_state;
    private String c_power;
    private String c_categories;
}
