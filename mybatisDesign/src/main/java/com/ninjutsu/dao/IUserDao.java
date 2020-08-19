package com.ninjutsu.dao;

import com.ninjutsu.domain.User;
import com.ninjutsu.mybatis.io.sqlsession.annotations.Select;

import java.util.List;

/**
 * @author:arxobject
 * @desc : user table dao interface
 * @create time:8/19
 */
public interface IUserDao {

    @Select("select * from user")
    List<User> findAll();
}
