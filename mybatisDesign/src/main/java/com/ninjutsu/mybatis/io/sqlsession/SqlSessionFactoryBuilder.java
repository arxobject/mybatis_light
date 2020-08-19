package com.ninjutsu.mybatis.io.sqlsession;

import com.ninjutsu.mybatis.cfg.DBConfiguration;
import com.ninjutsu.mybatis.io.sqlsession.Impl.DefaultSqlSessionFactory;
import com.ninjutsu.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @author:arxobject
 * @desc : build for session factory
 * @create time:8/19
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream){
        DBConfiguration DBConfiguration = XMLConfigBuilder.loadConfiguration(inputStream);
        return new DefaultSqlSessionFactory(DBConfiguration);
    }
}
