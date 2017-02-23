package com.arhat.SocialShayari.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arhat.SocialShayari.activity.ActBaseNavigation;
import com.arhat.SocialShayari.activity.ActProfile;
import com.arhat.SocialShayari.R;
import com.arhat.SocialShayari.customview.RoundedImageView;

/**
 * Created by babu on 12/9/2016.
 */

public class FragNavDrawer extends Fragment implements View.OnClickListener {

    private AppCompatActivity context = null;
    private RelativeLayout rlDrawerProfile = null;
    private TextView lblUserName = null;
    private RoundedImageView imgProfile = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = (ActBaseNavigation) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_nav_drawer, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        findViews();
    }

    private void findViews() {
        View view = getView();
        rlDrawerProfile = (RelativeLayout) view.findViewById(R.id.rlDrawerProfile);
        lblUserName = (TextView) view.findViewById(R.id.lblUserName);
        imgProfile = (RoundedImageView) view.findViewById(R.id.imgProfile);

        rlDrawerProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlDrawerProfile:
                Intent intent = new Intent(getActivity(), ActProfile.class);
                startActivity(intent);
                break;
        }
    }
}
