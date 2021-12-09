package com.potech.helpform.ui.notifications;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.potech.helpform.R;
import com.potech.helpform.activity.HelpInfoActivity;
import com.potech.helpform.activity.LoginActivity;
import com.potech.helpform.databinding.FragmentNotificationsBinding;

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
    private Button exitLoginButton;

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


        profilePhotoImageView.setBackground(getResources().getDrawable(R.mipmap.profile_photo));
        helpInfoImage.setBackground(getResources().getDrawable(R.mipmap.turn));
        myPostBarImage.setBackground(getResources().getDrawable(R.mipmap.turn));
        postHelpImage.setBackground(getResources().getDrawable(R.mipmap.turn));

        userControl.setOnClickListener(this);
        helpInfo.setOnClickListener(this);
        myPostBar.setOnClickListener(this);
        postHelp.setOnClickListener(this);
        exitLoginButton.setOnClickListener(this);

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
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            }
            case (R.id.help_info): {
                Intent intent = new Intent();
                intent.setClass(getActivity(), HelpInfoActivity.class);
                startActivity(intent);
                break;
            }
            case (R.id.my_post_bar): {

                break;
            }
            case (R.id.post_help): {

                break;
            }
            case (R.id.exit_login_button): {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("退出");
                builder.setMessage("您确定要退出登录吗？");
                builder.setCancelable(false);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Toast.makeText(getActivity(),"您取消了退出登录！",Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Toast.makeText(getActivity(),"您已退出登录！",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                break;
            }
            default:
        }
    }
}