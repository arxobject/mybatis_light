package com.ninjutsu.mybatis.io.sqlsession.proxy;

import com.ninjutsu.mybatis.cfg.SqlMapper;
import com.ninjutsu.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @author:arxobject
 * @desc : sql mapper proxy
 * @create time:8/19
 */
public class MapperProxy implements InvocationHandler {

    private Map<String, SqlMapper> mappers;
    private Connection connection;

    public MapperProxy(Map<String, SqlMapper> mappers, Connection connection)
    {
        this.mappers = mappers;
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        String key = className+"."+methodName;
        SqlMapper sqlMapper = mappers.get(key);
        if(sqlMapper ==null){
            return null;
        }

        return new Executor().selectList(sqlMapper,connection);
    }
}
