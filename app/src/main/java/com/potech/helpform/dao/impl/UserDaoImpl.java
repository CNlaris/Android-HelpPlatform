package com.potech.helpform.dao.impl;

import android.util.Log;

import com.potech.helpform.dao.HelperPostBarDao;
import com.potech.helpform.dao.UserDao;
import com.potech.helpform.entity.HelperPostBar;
import com.potech.helpform.entity.User;
import com.potech.helpform.utils.ConnOpen;
import com.potech.helpform.utils.MD5Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements UserDao {

    @Override
    public int judgeLoginName(String loginName) {
        Connection conn = new ConnOpen().getConnection("helpform");
        try{
            Statement st = conn.createStatement();
            String sql = "select * from user where login_name = \"" + loginName + "\"";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                return 0;
            } else {
                return 1;
            }
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public User userLogin(String loginName, String password) {
        Connection conn = new ConnOpen().getConnection("helpform");
        try{
            Statement st = conn.createStatement();
            String sql = "select * from user where login_name = \"" + loginName + "\" and login_password = \"" + password + "\"";
            Log.v("test",sql);
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                Log.v("test",sql);
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLoginName(rs.getString("login_name"));
                user.setUserName(rs.getString("user_name"));
                user.setLoginPassword(rs.getString("login_password"));
                user.setUuid(rs.getString("uuid"));
                String uuid = rs.getString(5);
                conn.close();
                return user;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertUser(User user) {
        Connection conn = new ConnOpen().getConnection("helpform");
        String str = user.getLoginName() + user.getUserName();
        String uuid = MD5Utils.getMD5String(str);
        user.setUuid(uuid);
        try{
            Statement st = conn.createStatement();
            String sql = "insert into user(login_name, login_password, user_name, uuid) values ("+ user.toStringWithoutId()+ ")";
            Log.v("SQL:",sql);
            st.execute(sql);
            conn.close();
            return 1;
        }catch(Exception e){

            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public String getUserUUid(String loginName) {
        Connection conn = new ConnOpen().getConnection("helpform");
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select uuid from user where login_name=\"" + loginName+"\"");

            if (rs==null){

                return "-1";
            }
            rs.next();
            String uuid = rs.getString(1);
            conn.close();
            Log.v("find!",uuid);
            return uuid;
        }catch(Exception e){
            e.printStackTrace();
            return "-1";
        }
    }
}
