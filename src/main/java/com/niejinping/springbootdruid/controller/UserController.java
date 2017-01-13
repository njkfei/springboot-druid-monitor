package com.niejinping.springbootdruid.controller;

import com.niejinping.springbootdruid.mapper.UserDAO;
import com.niejinping.springbootdruid.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by niejinping on 2017/1/13.
 */
@RestController
public class UserController {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/user/all")
    public List<User> users(){
        return userDAO.users();
    }
}
