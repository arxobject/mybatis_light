package com.ninjutsu.mybatis.io.sqlsession;

/**
 * @author:arxobject
 * @desc : sql mapper interface
 * @create time:8/19
 */
public interface SqlSession {
    <T> T getMapper(Class<T> daoInterfaceClass);
    void close();
}
