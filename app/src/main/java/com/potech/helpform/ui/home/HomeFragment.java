package com.potech.helpform.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.potech.helpform.R;
import com.potech.helpform.activity.HelpInfoBarActivity;
import com.potech.helpform.dao.HelperPostBarDao;
import com.potech.helpform.dao.impl.HelperPostBarDaoImpl;
import com.potech.helpform.databinding.FragmentHomeBinding;
import com.potech.helpform.entity.HelperPostBar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private List<HelperPostBar> list;

    private ListView helperPostBarListView;
    private HelperPostBarAdapter helperPostBarAdapter;
    private HelperPostBarClickListener helperPostBarClickListener;
    private HelperPostBarDao helperPostBarDao;
    private TextView info;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        info = (TextView) getActivity().findViewById(R.id.list_info_textView);
        helperPostBarDao = new HelperPostBarDaoImpl();
        getList();
        helperPostBarAdapter = new HelperPostBarAdapter();
        helperPostBarClickListener = new HelperPostBarClickListener();
        helperPostBarListView = getActivity().findViewById(R.id.helper_post_bar_list_view);
        helperPostBarListView.setAdapter(helperPostBarAdapter);
        helperPostBarListView.setOnItemClickListener(helperPostBarClickListener);
    }

    public void getList() {
        Thread myThread=new Thread(){//创建子线程
            @Override
            public void run() {
                list = helperPostBarDao.getHelperPostBarList();
            }
        };
        myThread.start();
        try {
            myThread.join(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(list.size() == 0) {
            info.setText("尚未发现别人的求助信息！");
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
            View view = View.inflate(getActivity(), R.layout.list_view_helper_post_bar,null);

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
            intent.setClass(getActivity(), HelpInfoBarActivity.class);
            startActivity(intent);
        }
    }
}