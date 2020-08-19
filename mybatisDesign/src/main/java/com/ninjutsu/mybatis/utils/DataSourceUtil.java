package com.ninjutsu.mybatis.utils;

import com.ninjutsu.mybatis.cfg.DBConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author:arxobject
 * @desc : create sql connection
 * @create time:8/19
 */
public class DataSourceUtil {
    public static Connection getConnection(DBConfiguration cfg){
        try {
            Class.forName(cfg.getDriver());
            return DriverManager.getConnection(cfg.getUrl(),cfg.getUsername(),cfg.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
