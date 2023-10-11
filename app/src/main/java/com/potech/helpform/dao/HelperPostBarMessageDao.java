package com.potech.helpform.dao;

import com.potech.helpform.entity.HelpPostBarMessage;

public interface HelperPostBarMessageDao {
    /**
     * @Description: 添加用户的回帖
     * @Param: HelpPostBarMessage 用户的回帖
     * @return: int 如果注册成功则返回1,否则返回0
     **/
    public int insertHelpBarMessage(HelpPostBarMessage helpPostBarMessage);
}
