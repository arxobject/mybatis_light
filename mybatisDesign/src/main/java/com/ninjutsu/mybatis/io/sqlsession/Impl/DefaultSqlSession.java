package com.ninjutsu.mybatis.io.sqlsession.Impl;

import com.ninjutsu.mybatis.cfg.DBConfiguration;
import com.ninjutsu.mybatis.io.sqlsession.SqlSession;
import com.ninjutsu.mybatis.io.sqlsession.proxy.MapperProxy;
import com.ninjutsu.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author:arxobject
 * @desc : connection
 * @create time:8/19
 */
public class DefaultSqlSession implements SqlSession {

    private DBConfiguration cfg;
    private Connection connection;

    public DefaultSqlSession(DBConfiguration cfg){
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }

    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),connection));
    }

    @Override
    public void close() {
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
