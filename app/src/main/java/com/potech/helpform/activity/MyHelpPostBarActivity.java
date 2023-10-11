package com.potech.helpform.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.potech.helpform.R;
import com.potech.helpform.dao.HelperPostBarDao;
import com.potech.helpform.dao.impl.HelperPostBarDaoImpl;
import com.potech.helpform.entity.HelperPostBar;
import com.potech.helpform.entity.User;
import com.potech.helpform.ui.home.HomeFragment;
import com.potech.helpform.utils.SharedPreferenceUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyHelpPostBarActivity extends AppCompatActivity {
    private List<HelperPostBar> list;

    private ListView helperPostBarListView;
    private MyHelpPostBarActivity.HelperPostBarAdapter helperPostBarAdapter;
    private MyHelpPostBarActivity.HelperPostBarClickListener helperPostBarClickListener;
    private MyHelpPostBarActivity.HelpPostBarLongClickListener helpPostBarLongClickListener;
    private MyHelpPostBarActivity.HelpPostBarSelectedClickListener helpPostBarSelectedClickListener;
    private HelperPostBarDao helperPostBarDao;
    private SharedPreferences sharedPreferences;
    private TextView info;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_help_post_bar);
        info = (TextView) findViewById(R.id.my_info_textView);
        helperPostBarDao = new HelperPostBarDaoImpl();
        sharedPreferences = getSharedPreferences("user",0);
        Thread a = new Thread (){
            @Override
            public void run() {
                getList();
            }
        };
        a.start();
        try {
            a.join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        helperPostBarAdapter = new MyHelpPostBarActivity.HelperPostBarAdapter();
        helperPostBarClickListener = new MyHelpPostBarActivity.HelperPostBarClickListener();
        helpPostBarLongClickListener = new MyHelpPostBarActivity.HelpPostBarLongClickListener();
        helpPostBarSelectedClickListener = new MyHelpPostBarActivity.HelpPostBarSelectedClickListener();
        helperPostBarListView = (ListView) findViewById(R.id.helper_post_bar_list_view);
        helperPostBarListView.setAdapter(helperPostBarAdapter);
        helperPostBarListView.setOnItemClickListener(helperPostBarClickListener);
        helperPostBarListView.setOnItemLongClickListener(helpPostBarLongClickListener);
        helperPostBarListView.setOnItemSelectedListener(helpPostBarSelectedClickListener);

    }

    public void getList() {
        User user = SharedPreferenceUtils.getUserFromPhone(sharedPreferences);
        String uuid = user.getUuid();
        if(!uuid.equals("null")) {
            list = helperPostBarDao.getHelperPostBarList();
        } else {
            info.setText("无法获得信息！");
        }
        if(list.size() == 0) {
            info.setText("您未发布求助信息！");
        }
    }


    private class HelperPostBarAdapter extends BaseAdapter {

        private ImageView profilePhoto;
        private TextView username;
        private TextView releaseTime;
        private TextView trappedDescription;
        private TextView needHelpKind;
        private TextView urgencyDegree;

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) list.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HelperPostBar helperPostBar = list.get(position);
            View view = View.inflate(MyHelpPostBarActivity.this, R.layout.list_view_helper_post_bar,null);

            profilePhoto = view.findViewById(R.id.info_profile_photo);
            username = view.findViewById(R.id.info_username);
            releaseTime = view.findViewById(R.id.info_release_time);
            trappedDescription = view.findViewById(R.id.info_trapped_description);
            needHelpKind = view.findViewById(R.id.info_need_help_kind);
            urgencyDegree = view.findViewById(R.id.info_urgency_degree);

            profilePhoto.setBackground(getResources().getDrawable(R.mipmap.profile_photo));
            username.setText(helperPostBar.getHelpUsername());
            releaseTime.setText("发布时间：" + helperPostBar.getReleaseTime());
            trappedDescription.setText("受灾情况描述：" + helperPostBar.getTrappedDescription());
            needHelpKind.setText("需要何种帮助：" + helperPostBar.getNeedHelpKind());
            urgencyDegree.setText(helperPostBar.getUrgencyDegree());

            return view;

        }
    }
    private class HelperPostBarClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HelperPostBar helperPostBar = (HelperPostBar) parent.getItemAtPosition(position);
            Intent intent = new Intent();
            Bundle data = new Bundle();
            data.putSerializable("helperPostBar",helperPostBar);
            intent.putExtras(data);
            intent.setClass(MyHelpPostBarActivity.this, HelpInfoBarActivity.class);
            startActivity(intent);
        }
    }

    private class HelpPostBarLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            final String[] items = { "修改求助","删除求助" };
            AlertDialog.Builder listDialog =
                    new AlertDialog.Builder(MyHelpPostBarActivity.this);
            listDialog.setTitle("选项");
            listDialog.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch(which) {
                        case (0): {
                            Intent intent = new Intent();
                            intent.putExtra("helper_post_bar",list.get(position));
                            intent.setClass(MyHelpPostBarActivity.this,ModifyHelperPostBarActivity.class);
                            startActivity(intent);
                            break;
                        }
                        case(1): {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MyHelpPostBarActivity.this);
                            builder.setTitle("删除求助信息");
                            builder.setMessage("您确定要删除求助信息吗？");
                            builder.setCancelable(false);
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(MyHelpPostBarActivity.this, "您取消了删除求助信息！", Toast.LENGTH_SHORT).show();
                                }
                            });

                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(MyHelpPostBarActivity.this, "您已删除求助信息！", Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder.show();
                        }
                    }
                }
            });
            listDialog.show();
            return true;
        }
    }

    private class HelpPostBarSelectedClickListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MyHelpPostBarActivity.this,"test2",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(MyHelpPostBarActivity.this,"test3",Toast.LENGTH_SHORT).show();
        }
    }
    
}
