package com.potech.helpform.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.potech.helpform.R;
import com.potech.helpform.entity.HelperPostBar;

public class HelpInfoBarActivity extends AppCompatActivity implements View.OnClickListener{
    private HelperPostBar helperPostBar;
    private TextView username;
    private ImageView profilePhoto;
    private TextView releaseTime;
    private TextView trappedDescription;
    private TextView needHelpKind;
    private TextView urgencyDegree;
    private TextView helpLocation;
    private TextView trappedPeopleNumber;
    private TextView trappedTime;
    private TextView contactPeople;
    private TextView getHelp;
    private TextView contactPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_info_bar);
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        helperPostBar = (HelperPostBar) data.getSerializable("helperPostBar");

        username = (TextView) findViewById(R.id.main_username);
        profilePhoto = (ImageView) findViewById(R.id.main_profile_photo);
        releaseTime = (TextView) findViewById(R.id.main_release_time);
        trappedDescription = (TextView) findViewById(R.id.main_trapped_description);
        needHelpKind = (TextView) findViewById(R.id.main_need_help_kind);
        urgencyDegree = (TextView) findViewById(R.id.main_urgency_degree);
        helpLocation = (TextView) findViewById(R.id.main_help_location);
        trappedPeopleNumber = (TextView) findViewById(R.id.main_trapped_people_number);
        trappedTime = (TextView) findViewById(R.id.main_trapped_time);
        contactPeople = (TextView) findViewById(R.id.main_contact_people);
        getHelp = (TextView) findViewById(R.id.main_get_help);
        contactPhoneNumber = (TextView) findViewById(R.id.main_contact_phone_number);

        username.setText(helperPostBar.getHelpUsername());
        profilePhoto.setBackground(getResources().getDrawable(R.mipmap.profile_photo));
        releaseTime.setText("发布时间：" + helperPostBar.getReleaseTime());
        trappedDescription.setText("受灾情况：" + helperPostBar.getTrappedDescription());
        needHelpKind.setText("需要何种帮助：" + helperPostBar.getNeedHelpKind());
        urgencyDegree.setText("紧急程度：" + helperPostBar.getUrgencyDegree());
        helpLocation.setText("被困地点：" + helperPostBar.getHelpLocation());
        trappedPeopleNumber.setText("被困人数：" + helperPostBar.getTrappedPeopleNumber());
        trappedTime.setText("被困时间：" + helperPostBar.getTrappedTime());
        contactPeople.setText("联系人：" + helperPostBar.getContactPeople());
        boolean isGetHelp = helperPostBar.isGetHelp();
        if(isGetHelp == true) {
            getHelp.setText("已得到帮助");
            getHelp.setTextColor(Color.GREEN);
        } else {
            getHelp.setText("未得到帮助");
            getHelp.setTextColor(Color.RED);
        }
        contactPhoneNumber.setText("联系方式：" + helperPostBar.getContactPhoneNumber());
/*      ActionBar actionBar = getActionBar();
        // 显示返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 去掉logo图标
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle("返回");*/



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:   //返回键的id
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {


            }

    }
}
