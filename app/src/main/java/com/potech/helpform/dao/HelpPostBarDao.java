package com.potech.helpform.dao;

import com.potech.helpform.entity.HelperPostBar;

public interface HelpPostBarDao {
    /**
     * @Description: 添加用户求助信息
     * @Param: HelpPostBar 用户求助的信息
     * @return: int 如果添加成功则返回1,否则返回0
     **/
    public int insertHelpPostBar(HelperPostBar helperPostBar);
}
