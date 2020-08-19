package com.ninjutsu.mybatis.io.sqlsession;

/**
 * @author:arxobject
 * @desc : sql sessoin factory interface
 * @create time:8/19
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
