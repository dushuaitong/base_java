package com.st.java.mybatis;

import com.st.java.mybatis.entity.User;

import java.util.List;

/**
 * @author dushuaitong
 * @description: usermapper
 * @date 2022/12/27
 */
public interface UserMapper {
    List<User> selectUsers();
}
