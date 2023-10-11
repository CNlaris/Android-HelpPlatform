package com.potech.helpform.dao;

import com.potech.helpform.entity.HelperPostBar;

import java.util.List;

public interface HelperPostBarDao {
    /**
     * @Description: 添加用户求助信息
     * @Param: HelpPostBar 用户求助的信息
     * @return: int 如果添加成功则返回1,否则返回0
     **/
    public int insertHelpPostBar(HelperPostBar helperPostBar);

    /**
     * @Description: 获取所有用户求助信息
     * @Param: null
     * @return: List,包含所有用户求助信息
     **/
    public List<HelperPostBar> getHelperPostBarList();

    /**
     * @Description: 获取某个用户求助信息
     * @Param: 用户uuid
     * @return: List,包含所有这名用户求助信息
     **/
    public List<HelperPostBar> getHelperPostBarListByUuid(String uuid);


    /**
     * @Description: 插入求助帖子
     * @Param: HelperPostBar 用户求助信息
     * @Param: double 用户的X坐标
     * @Param: double 用户的Y坐标
     * @return: int 如果发送成功，则返回1，否则返回0
     **/
    public int insertHelpPostBarWithCoordinate(HelperPostBar helperPostBar,double latitude,double longitude);
}
