package com.ninjutsu.mybatis.io.sqlsession.Impl;

import com.ninjutsu.mybatis.cfg.DBConfiguration;
import com.ninjutsu.mybatis.io.sqlsession.SqlSession;
import com.ninjutsu.mybatis.io.sqlsession.SqlSessionFactory;

/**
 * @author:arxobject
 * @desc : connection abstract factory default implement
 * @create time:8/19
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private DBConfiguration cfg;

    public DefaultSqlSessionFactory(DBConfiguration cfg){
        this.cfg = cfg;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
