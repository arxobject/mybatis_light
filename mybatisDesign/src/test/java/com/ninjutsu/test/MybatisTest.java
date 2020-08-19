package com.ninjutsu.test;

import com.ninjutsu.dao.IUserDao;
import com.ninjutsu.domain.User;
import com.ninjutsu.mybatis.io.Resources;
import com.ninjutsu.mybatis.io.sqlsession.SqlSession;
import com.ninjutsu.mybatis.io.sqlsession.SqlSessionFactory;
import com.ninjutsu.mybatis.io.sqlsession.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author:arxobject
 * @desc : main test
 * @create time:8/19
 */
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);

        SqlSession session = factory.openSession();
        IUserDao dao = session.getMapper(IUserDao.class);
        List<User> users = dao.findAll();
        for (User user:users){
            System.out.println(users);
        }
        session.close();
        inputStream.close();
    }
}
