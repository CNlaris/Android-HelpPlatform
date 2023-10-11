package com.potech.helpform.utils;

import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.potech.helpform.entity.User;

import java.util.Map;
import java.util.Set;

public class SharedPreferenceUtils {
    //将User信息插入到手机中
    public static void insertUserToPhone(SharedPreferences sharedPreferences,User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", user.getUserName());
        editor.putString("loginName", user.getLoginName());
        editor.putString("uuid",user.getUuid());
        editor.commit();
    }
    //从手机中读取User信息
    public static User getUserFromPhone(SharedPreferences sharedPreferences) {
        User user = new User();
        user.setLoginName(sharedPreferences.getString("loginName","null"));
        user.setUserName(sharedPreferences.getString("userName","null"));
        user.setUuid(sharedPreferences.getString("uuid","null"));
        return user;
    }
}
