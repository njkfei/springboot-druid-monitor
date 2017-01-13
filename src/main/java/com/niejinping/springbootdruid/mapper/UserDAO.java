package com.niejinping.springbootdruid.mapper;

import org.apache.ibatis.annotations.Select;
import java.util.List;
import com.niejinping.springbootdruid.model.User;

/**
 * Created by niejinping on 2017/1/13.
 */
public interface UserDAO {
    @Select("select * from user")
    List<User> users();
}
