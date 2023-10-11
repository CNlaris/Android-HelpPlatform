package com.potech.helpform.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.potech.helpform.R;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{

    private Button loginButton;
    private Button registerButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        loginButton = (Button) findViewById(R.id.user_login);
        registerButton = (Button) findViewById(R.id.user_register);
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case(R.id.user_login): {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case(R.id.user_register): {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            default: {

            }
        }
    }
}
