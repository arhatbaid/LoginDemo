package com.arhat.SocialShayari.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.arhat.SocialShayari.R;

public class ActOnboarding extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_onboarding);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(android.R.color.black));
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, ActLoginSignUp.class);
        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this,
                btnStart,
                getString(R.string.trans_onboard_login));
        startActivity(i, transitionActivityOptions.toBundle());

    }
}
