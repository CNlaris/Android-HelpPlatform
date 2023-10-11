package com.potech.helpform.ui.notifications;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.potech.helpform.R;
import com.potech.helpform.activity.HelpInfoActivity;
import com.potech.helpform.activity.MyHelpPostBarActivity;
import com.potech.helpform.activity.SendHelperPostBarActivity;
import com.potech.helpform.activity.UserActivity;
import com.potech.helpform.databinding.FragmentNotificationsBinding;
import com.potech.helpform.entity.User;
import com.potech.helpform.utils.SharedPreferenceUtils;

public class NotificationsFragment extends Fragment implements View.OnClickListener {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private Button button;
    private ImageView profilePhotoImageView;
    private ImageView helpInfoImage;
    private ImageView myPostBarImage;
    private ImageView postHelpImage;
    private LinearLayout userControl;
    private LinearLayout helpInfo;
    private LinearLayout myPostBar;
    private LinearLayout postHelp;

    private TextView userName;
    private TextView loginName;
    private Button exitLoginButton;
    private boolean isLogin = false;
    private SharedPreferences sharedPreferences;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        userControl = (LinearLayout) getActivity().findViewById(R.id.user_control);
        helpInfo = (LinearLayout) getActivity().findViewById(R.id.help_info);
        myPostBar = (LinearLayout) getActivity().findViewById(R.id.my_post_bar);
        postHelp = (LinearLayout) getActivity().findViewById(R.id.post_help);
        profilePhotoImageView = (ImageView) getActivity().findViewById(R.id.profile_photo);
        helpInfoImage = (ImageView) getActivity().findViewById(R.id.help_info_image);
        myPostBarImage = (ImageView) getActivity().findViewById(R.id.my_post_bar_image);
        postHelpImage = (ImageView) getActivity().findViewById(R.id.post_help_image);
        exitLoginButton = (Button) getActivity().findViewById(R.id.exit_login_button);

        userName = (TextView) getActivity().findViewById(R.id.user_name);
        loginName = (TextView) getActivity().findViewById(R.id.login_name);
        profilePhotoImageView.setBackground(getResources().getDrawable(R.mipmap.profile_photo));
        helpInfoImage.setBackground(getResources().getDrawable(R.mipmap.turn));
        myPostBarImage.setBackground(getResources().getDrawable(R.mipmap.turn));
        postHelpImage.setBackground(getResources().getDrawable(R.mipmap.turn));



        userControl.setOnClickListener(this);
        helpInfo.setOnClickListener(this);
        myPostBar.setOnClickListener(this);
        postHelp.setOnClickListener(this);
        exitLoginButton.setOnClickListener(this);

        sharedPreferences = getActivity().getSharedPreferences("user",0);

        user = SharedPreferenceUtils.getUserFromPhone(sharedPreferences);

        if(user.getUserName().equals("null")) {
            userName.setText("请登录");
            loginName.setText("请登录");
        } else {
            userName.setText(user.getUserName());
            loginName.setText(user.getLoginName());
            isLogin = true;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case (R.id.user_control): {
                if(isLogin == false) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), UserActivity.class);
                    startActivityForResult(intent,1);
                }
                break;
            }
            case (R.id.help_info): {
                Intent intent = new Intent();
                intent.setClass(getActivity(), HelpInfoActivity.class);
                startActivity(intent);
                break;
            }
            case (R.id.my_post_bar): {
                if(isLogin == true) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), MyHelpPostBarActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(),"您还未登录，请先登录！",Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case (R.id.post_help): {
                if(isLogin == true) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), SendHelperPostBarActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(),"您还未登录，请先登录！",Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case (R.id.exit_login_button): {
                if(isLogin == true) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("退出");
                    builder.setMessage("您确定要退出登录吗？");
                    builder.setCancelable(false);
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "您取消了退出登录！", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            User user = new User();
                            user.setId(0);
                            user.setUuid("null");
                            user.setUserName("null");
                            user.setLoginName("null");
                            user.setLoginPassword("null");
                            SharedPreferenceUtils.insertUserToPhone(sharedPreferences, user);
                            isLogin = false;
                            userName.setText("请登录");
                            loginName.setText("请登录");
                            Toast.makeText(getActivity(), "您已退出登录！", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                } else {
                    Toast.makeText(getActivity(),"您还没登录，请先登录！",Toast.LENGTH_SHORT).show();
                }
                break;
            }
            default: {

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        user = SharedPreferenceUtils.getUserFromPhone(sharedPreferences);
        if(user.getUserName().equals("null")) {
            userName.setText("请登录");
            loginName.setText("请登录");
        } else {
            userName.setText(user.getUserName());
            loginName.setText(user.getLoginName());
            isLogin = true;
        }
    }
}