package com.potech.helpform.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.potech.helpform.R;
import com.potech.helpform.dao.UserDao;
import com.potech.helpform.dao.impl.UserDaoImpl;
import com.potech.helpform.entity.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText loginName;
    private EditText userName;
    private EditText password;
    private EditText passwordAgain;
    private Button registerButton;
    private String loginNameString;
    private String userNameString;
    private String passwordString;
    private String passwordAgainString;
    private UserDao userDao;
    private int is_repetitive = 0;
    private int is_success = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loginName = (EditText) findViewById(R.id.register_login_name);
        userName = (EditText) findViewById(R.id.register_user_name);
        password = (EditText) findViewById(R.id.register_user_password);
        passwordAgain = (EditText) findViewById(R.id.register_user_password_again);
        registerButton = (Button) findViewById(R.id.register_button);
        userDao = new UserDaoImpl();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginNameString = loginName.getText().toString();
                userNameString = userName.getText().toString();
                passwordString = password.getText().toString();
                passwordAgainString = passwordAgain.getText().toString();
                if(!passwordString.equals(passwordAgainString)) {
                    Toast.makeText(RegisterActivity.this,"您的两次密码输入不一致，请核实！",Toast.LENGTH_SHORT).show();
                } else {
                    Thread myThread=new Thread(){//创建子线程
                        @Override
                        public void run() {
                            is_repetitive = userDao.judgeLoginName(loginNameString);
                        }
                    };
                    myThread.start();
                    try {
                        myThread.join(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(is_repetitive == 0) {
                        Toast.makeText(RegisterActivity.this,"您输入的登录账号重复，请修改！",Toast.LENGTH_SHORT).show();
                    } else {
                        User user = new User();
                        user.setUserName(userNameString);
                        user.setLoginName(loginNameString);
                        user.setLoginPassword(passwordString);
                        Thread myThread1=
                                new Thread(){//创建子线程
                            @Override
                            public void run() {
                                is_success = userDao.insertUser(user);
                            }
                        };
                        myThread1.start();
                        try {
                            myThread1.join(5000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(is_success == 1) {
                            finish();
                            Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this,"注册失败，请检查网络或者相应设置是否正确！",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
