package com.boyier.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private String u_id;
    private String u_name;
    private String u_pwd;
    private String u_prior;
}
