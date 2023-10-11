package com.potech.helpform.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.potech.helpform.R;
import com.potech.helpform.dao.UserDao;
import com.potech.helpform.dao.impl.UserDaoImpl;
import com.potech.helpform.entity.User;
import com.potech.helpform.utils.SharedPreferenceUtils;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUserNameEditText;
    private EditText loginPasswordEditText;
    private Button loginButton;
    private UserDao userDao;
    private User user;
    private LoginButtonOnClickListener loginButtonOnClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginUserNameEditText = findViewById(R.id.editText_Username);
        loginPasswordEditText = findViewById(R.id.editText_Password);
        loginButton = findViewById(R.id.button_login);
        loginButtonOnClickListener = new LoginButtonOnClickListener();
        userDao = new UserDaoImpl();
        loginButton.setOnClickListener(loginButtonOnClickListener);
    }
    private class LoginButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String loginName = loginUserNameEditText.getText().toString();
            String password = loginPasswordEditText.getText().toString();
            Thread myThread=new Thread(){//创建子线程
                @Override
                public void run() {
                    user = userDao.userLogin(loginName,password);
                }
            };
            myThread.start();
            try {
                myThread.join(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(user != null) {
                SharedPreferences sharedPreferences = getSharedPreferences("user",0);
                SharedPreferenceUtils.insertUserToPhone(sharedPreferences,user);
                Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                Log.v("test",String.valueOf(user == null));
                finish();
            } else {
                Toast.makeText(LoginActivity.this,"您的账号或者密码有错误，请查找后重试！",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
