package com.potech.helpform.dao;

import com.potech.helpform.entity.User;

public interface UserDao {

    /**
     * @Description: 用户登录验证
     * @Param: String 用户账户
     * @Param: String 用户密码
     * @return: User 如果不存在则返回null
     **/
    public User userLogin(String loginName, String password);

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

    /**
     * @Description: 查看用户输入的loginName是否唯一
     * @Param: String 用户的loginName
     * @return: int 是否唯一的标识,如果唯一，则返回1，否则返回0
     **/
    public int judgeLoginName(String loginName);
}
