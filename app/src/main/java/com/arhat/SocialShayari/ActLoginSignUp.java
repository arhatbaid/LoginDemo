package com.arhat.SocialShayari;

import android.animation.Animator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.arhat.SocialShayari.R.id.imgFacebook;
import static com.arhat.SocialShayari.R.id.imgGoogle;
import static com.arhat.SocialShayari.R.id.imgTwitter;


/**
 * Created by babu on 12/10/2016.
 */

public class ActLoginSignUp extends AppCompatActivity implements View.OnClickListener {
    private CardView cardview = null;
    private View viewTemp = null;

    /*Login*/
    private View viewSignUp = null, viewLogin = null, viewLoginUserName = null,
            viewLoginPassword = null;
    private FloatingActionButton btnRegister;
    private ImageView imgLoginFacebook = null, imgLoginGoogle = null, imgLoginTwitter = null;
    private Button btnLogin = null;
    private TextView lblForgotPassword = null;

    /*Sign*/
    private ImageView imgCross = null, imgSignUpFacebook = null, imgSignUpGoogle = null,
            imgSignUpTwitter = null;
    private View viewSignUpUserName = null, viewSignUpEmail = null, viewSignUpPassword = null;
    private Button btnSignUp;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login_signup);
        findViews();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private void findViews() {
        viewLogin = (View) findViewById(R.id.viewLogin);
        viewSignUp = (View) findViewById(R.id.viewSignUp);
        viewTemp = (View) findViewById(R.id.viewTemp);
        cardview = (CardView) findViewById(R.id.cardview);

        /*Login*/
        btnRegister = (FloatingActionButton) viewLogin.findViewById(R.id.btnRegister);
        viewLoginUserName = viewLogin.findViewById(R.id.viewLoginUserName);
        viewLoginPassword = viewLogin.findViewById(R.id.viewLoginPassword);
        imgLoginFacebook = (ImageView) viewLogin.findViewById(imgFacebook);
        imgLoginGoogle = (ImageView) viewLogin.findViewById(imgGoogle);
        imgLoginTwitter = (ImageView) viewLogin.findViewById(imgTwitter);
        btnLogin = (Button) viewLogin.findViewById(R.id.btnLogin);
        lblForgotPassword = (TextView) viewLogin.findViewById(R.id.lblForgotPassword);

        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        lblForgotPassword.setOnClickListener(this);


        /*Sign Up*/
        imgCross = (ImageView) viewSignUp.findViewById(R.id.imgCross);
        viewSignUpUserName = viewSignUp.findViewById(R.id.viewSignUpUserName);
        viewSignUpEmail = viewSignUp.findViewById(R.id.viewSignUpEmail);
        viewSignUpPassword = viewSignUp.findViewById(R.id.viewSignUpPassword);
        imgSignUpFacebook = (ImageView) viewSignUp.findViewById(R.id.imgFacebook);
        imgSignUpGoogle = (ImageView) viewSignUp.findViewById(R.id.imgGoogle);
        imgSignUpTwitter = (ImageView) viewSignUp.findViewById(R.id.imgTwitter);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(this);
        imgCross.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnRegister) {
            animationLoginSignUp(btnRegister, viewSignUp, viewLogin);
//            viewSignUp.setVisibility(View.VISIBLE);
//            viewLogin.setVisibility(View.GONE);
        } else if (v == btnLogin) {
            goToMainApp();
        } else if (v == lblForgotPassword) {
            // Handle clicks for btnLogin
        } else if (v == btnSignUp) {
            goToMainApp();
        } else if (v == imgCross) {
            animationLoginSignUp(imgCross, viewLogin, viewSignUp);
//            viewSignUp.setVisibility(View.GONE);
//            viewLogin.setVisibility(View.VISIBLE);
        }
    }

    private void animationLoginSignUp(View clickedView, final View toBeVisible, final View toBeInvisible) {

        int cx = (int) (clickedView.getX() + clickedView.getWidth() / 2);
        int cy = (int) (clickedView.getY() + clickedView.getHeight() / 2);
        float finalRadius = (float) Math.hypot(viewTemp.getWidth(), viewTemp.getHeight());
        Animator anim =
                ViewAnimationUtils.createCircularReveal(viewTemp, cx, cy, 0, finalRadius);
//        anim.setDuration(300);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                viewTemp.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
                anim.setDuration(400);
                anim.setInterpolator(new AccelerateDecelerateInterpolator());
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        viewTemp.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                viewTemp.startAnimation(anim);
                toBeVisible.setVisibility(View.VISIBLE);
                toBeInvisible.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(700);
        anim.start();
    }

    private void goToMainApp() {
        Intent mainAct = new Intent(this, ActBase.class);
        startActivity(mainAct);
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ActLoginSignUp Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
