package com.potech.helpform.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.potech.helpform.R;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUserNameEditText;
    private EditText loginPasswordEditText;
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginUserNameEditText = findViewById(R.id.editText_Username);
        loginPasswordEditText = findViewById(R.id.editText_Password);
        loginButton = findViewById(R.id.button_login);

    }
    private class loginButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String username = loginUserNameEditText.getText().toString();
            String password = loginPasswordEditText.getText().toString();
            Toast.makeText(LoginActivity.this,username + " " + password,Toast.LENGTH_LONG).show();
        }
    }
}
