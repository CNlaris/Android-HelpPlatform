package com.potech.helpform.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.potech.helpform.R;
import com.potech.helpform.dao.HelperPostBarDao;
import com.potech.helpform.dao.impl.HelperPostBarDaoImpl;
import com.potech.helpform.entity.HelperPostBar;
import com.potech.helpform.entity.User;
import com.potech.helpform.utils.SharedPreferenceUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifyHelperPostBarActivity extends AppCompatActivity {
    private EditText trappedDescription;
    private EditText needHelpKind;
    private EditText trappedPeopleNumber;
    private EditText trappedTime;
    private EditText contactPeople;
    private EditText contactPhoneNumber;
    private Spinner urgencyDegree;
    private Button modifyButton;
    private static final String[] urgencyDegreeList = {"一般","紧急","十分紧急"};
    private ArrayAdapter<String> urgencyDegreeAdapter;
    private String urgencyDegreeOption = "一般";
    private int is_modify;
    private HelperPostBarDao helperPostBarDao;
    public double latitude;
    public double longitude;
    public String location;
    public HelperPostBar oldHelperPostBar;
    public HelperPostBar newHelperPostBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_helper_post_bar);
        trappedDescription = (EditText) findViewById(R.id.modify_trapped_description);
        needHelpKind = (EditText) findViewById(R.id.modify_need_help_kind);
        trappedTime = (EditText) findViewById(R.id.modify_trapped_time);
        trappedPeopleNumber = (EditText) findViewById(R.id.modify_trapped_people_number);
        contactPeople = (EditText) findViewById(R.id.modify_contact_people);
        contactPhoneNumber = (EditText) findViewById(R.id.modify_contact_phone_number);
        urgencyDegree = (Spinner) findViewById(R.id.modify_urgency_degree);
        Intent intent = getIntent();
        oldHelperPostBar = (HelperPostBar) intent.getSerializableExtra("helper_post_bar");
        trappedDescription.setText(oldHelperPostBar.getTrappedDescription());
        needHelpKind.setText(oldHelperPostBar.getNeedHelpKind());
        trappedTime.setText(oldHelperPostBar.getTrappedTime());
        trappedPeopleNumber.setText(String.valueOf(oldHelperPostBar.getTrappedPeopleNumber()));
        contactPeople.setText(oldHelperPostBar.getContactPeople());
        contactPhoneNumber.setText(oldHelperPostBar.getContactPhoneNumber());
        urgencyDegreeOption = oldHelperPostBar.getUrgencyDegree();

        {
            SharedPreferences sf = this.getSharedPreferences("coor", 0);

            latitude = Double.parseDouble(sf.getString("latitude","0.0"));
            longitude = Double.parseDouble(sf.getString("longitude","0.0"));
        }

        {
            new Thread(){
                @Override
                public void run() {
                    GeoCoder geocoder = GeoCoder.newInstance();
                    geocoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                        @Override
                        public void
                        onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                        }

                        @Override
                        public void
                        onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

                            location = String.valueOf(reverseGeoCodeResult.getAddress());

                        }
                    });
                    geocoder.reverseGeoCode(new ReverseGeoCodeOption().location(new LatLng(latitude,longitude)));
                }
            }.start();
        }
        modifyButton = (Button) findViewById(R.id.modify_button);

        urgencyDegreeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,urgencyDegreeList);
        urgencyDegreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        urgencyDegree.setAdapter(urgencyDegreeAdapter);
        urgencyDegree.setOnItemSelectedListener(new SpinnerSelectedListener());
        urgencyDegree.setVisibility(View.VISIBLE);
        helperPostBarDao = new HelperPostBarDaoImpl();
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag1 = 1;
                HelperPostBar helperPostBar = new HelperPostBar();
                try {
                    helperPostBar.setTrappedPeopleNumber(Integer.parseInt(trappedPeopleNumber.getText().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                    flag1 = 0;
                    Toast.makeText(ModifyHelperPostBarActivity.this,"您的被困人数输入有误，请重试！",Toast.LENGTH_SHORT).show();
                }
                if(flag1 == 1) {
                    SharedPreferences sharedPreferences = getSharedPreferences("user", 0);
                    User user = SharedPreferenceUtils.getUserFromPhone(sharedPreferences);
                    helperPostBar.setTrappedDescription(trappedDescription.getText().toString());
                    helperPostBar.setNeedHelpKind(needHelpKind.getText().toString());
                    helperPostBar.setTrappedTime(trappedTime.getText().toString());

                    helperPostBar.setContactPeople(contactPeople.getText().toString());
                    helperPostBar.setContactPhoneNumber(contactPhoneNumber.getText().toString());
                    helperPostBar.setUrgencyDegree(urgencyDegreeOption);
                    helperPostBar.setHelpUserUUid(user.getUuid());
                    helperPostBar.setHelpUsername(user.getUserName());
                    helperPostBar.setHelpLocation(location);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    helperPostBar.setReleaseTime(df.format(new Date()));
                    helperPostBar.setGetHelp(false);
                    Thread myThread = new Thread() {//创建子线程
                        @Override
                        public void run() {
                            Log.v("Location:",helperPostBar.getHelpLocation());
                            is_modify = helperPostBarDao.insertHelpPostBarWithCoordinate(helperPostBar, latitude, longitude);
                        }
                    };
                    myThread.start();
                    try {
                        myThread.join(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (is_modify == 1) {
                        Toast.makeText(ModifyHelperPostBarActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ModifyHelperPostBarActivity.this, "修改失败！,请检查网络等相关信息是否正常", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            urgencyDegreeOption = urgencyDegreeList[arg2];
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}
