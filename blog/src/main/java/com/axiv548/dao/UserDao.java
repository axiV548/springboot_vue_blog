package com.axiv548.dao;

import com.axiv548.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


/**
 * UserDao
 *
 * @date 2021/2/26 13:51
 */
@Mapper
public interface UserDao {

//    用户登录
    User login(Map<String, String> name_password);
}
