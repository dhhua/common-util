package com.dredh.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisHelper {

    public static final MybatisHelper instance = new MybatisHelper();
    private static final String RESOURCE_FILE = "mybatis-config.xml";
    private SqlSessionFactory sessionFactory;
    {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(RESOURCE_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public SqlSession openSession() {
        return sessionFactory.openSession();
    }

    public SqlSession openSession(boolean autoCommit) {
        return sessionFactory.openSession(autoCommit);
    }
}
