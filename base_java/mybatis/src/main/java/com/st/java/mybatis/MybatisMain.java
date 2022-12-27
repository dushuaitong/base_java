package com.st.java.mybatis;

import com.st.java.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author dushuaitong
 * @description: mybatis
 * @date 2022/12/27
 */
public class MybatisMain {
    public static void main(String[] args) {
         test();
    }

    private static void test() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> users = userMapper.selectUsers();
        users.forEach((u) -> {
            System.out.println(u.getId());
            System.out.println(u.getName());
        });

    }
}
