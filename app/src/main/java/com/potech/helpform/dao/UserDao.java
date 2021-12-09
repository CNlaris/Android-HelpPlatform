package com.potech.helpform.dao;

import com.potech.helpform.entity.User;

public interface UserDao {

    /**
     * @Description: 用户登录验证
     * @Param: String 用户账户
     * @Param: String 用户密码
     * @return: String 如果用户存在，则返回uuid,否则返回的字符串为"-1"
     **/
    public String userLogin(String loginName, String password);

    /**
     * @Description: 用户注册
     * @Param: User 用户的信息，没有id和uuid
     * @return: int 如果注册成功则返回1,否则返回0
     **/
    public int insertUser(User user);


    /**
     * @Description: 获取用户的唯一uuid标识
     * @Param: String 用户的loginName,保证唯一
     * @return: String 用户的uuid,如果不存在则返回-1
     **/
    public String getUserUUid(String loginName);
}
