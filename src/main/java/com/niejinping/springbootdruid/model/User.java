package com.niejinping.springbootdruid.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by niejinping on 2017/1/13.
 */
@Data
public class User implements Serializable {
    private int userId;
    private String userName;
    private String password;
}
