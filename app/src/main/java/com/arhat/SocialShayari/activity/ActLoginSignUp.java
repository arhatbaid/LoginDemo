package com.arhat.SocialShayari.activity;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.transition.Transition;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arhat.SocialShayari.R;
import com.arhat.SocialShayari.customview.MaterialEditText;
import com.arhat.SocialShayari.utils.AppHelper;
import com.arhat.SocialShayari.utils.FbLoginHelper;
import com.arhat.SocialShayari.utils.GoogleLoginHelper;
import com.arhat.SocialShayari.utils.TwitterLoginHelper;
import com.facebook.FacebookException;
import com.facebook.GraphResponse;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import org.json.JSONException;
import org.json.JSONObject;

import static com.arhat.SocialShayari.R.id.imgFacebook;
import static com.arhat.SocialShayari.R.id.imgGoogle;
import static com.arhat.SocialShayari.R.id.imgTwitter;

public class ActLoginSignUp extends AppCompatActivity implements
        View.OnClickListener,
        FbLoginHelper.OnFbLoginCompleted,
        GoogleLoginHelper.OnGPlusLoginComplete,
        TwitterLoginHelper.OnTwitterLoginCompleted {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.


    private CardView cardview = null;
    private View viewTemp = null;

    /*Login*/
    private View viewSignUp = null, viewLogin = null;
    private MaterialEditText viewLoginUserName = null, viewLoginPassword = null;
    private FloatingActionButton btnRegister;
    private ImageView imgLoginFacebook = null, imgLoginGoogle = null, imgLoginTwitter = null;
    private Button btnLogin = null;
    private TextView lblForgotPassword = null;

    /*Sign*/
    private ImageView imgCross = null, imgSignUpFacebook = null, imgSignUpGoogle = null,
            imgSignUpTwitter = null;
    private MaterialEditText viewSignUpUserName = null, viewSignUpEmail = null, viewSignUpPassword = null;
    private Button btnSignUp;


    /*facebook n Google*/
    private FbLoginHelper fbLoginHelper = new FbLoginHelper();
    private GoogleLoginHelper googleLoginHelper = new GoogleLoginHelper();
    private TwitterLoginHelper twitterLoginHelper = new TwitterLoginHelper();
    private int LOGIN_WITH = 0; //1 = FB n 2 = Google n 3 = Twitter

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login_signup);
        findViews();
        setupView();
        actTransListener();
        getWindow().getDecorView().clearFocus();
        fbLoginHelper.initLogin(this, this);
        googleLoginHelper.initGooglePlus(this, this);
        twitterLoginHelper.initTwitter(this, this);
    }


    private void findViews() {
        viewLogin = findViewById(R.id.viewLogin);
        viewSignUp = findViewById(R.id.viewSignUp);
        viewTemp = findViewById(R.id.viewTemp);
        cardview = (CardView) findViewById(R.id.cardview);

        /*Login*/
        btnRegister = (FloatingActionButton) viewLogin.findViewById(R.id.btnRegister);
        viewLoginUserName = (MaterialEditText) viewLogin.findViewById(R.id.viewLoginUserName);
        viewLoginPassword = (MaterialEditText) viewLogin.findViewById(R.id.viewLoginPassword);
        imgLoginFacebook = (ImageView) viewLogin.findViewById(imgFacebook);
        imgLoginGoogle = (ImageView) viewLogin.findViewById(imgGoogle);
        imgLoginTwitter = (ImageView) viewLogin.findViewById(imgTwitter);
        btnLogin = (Button) viewLogin.findViewById(R.id.btnLogin);
        lblForgotPassword = (TextView) viewLogin.findViewById(R.id.lblForgotPassword);

        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        lblForgotPassword.setOnClickListener(this);
        imgLoginFacebook.setOnClickListener(this);
        imgLoginGoogle.setOnClickListener(this);
        imgLoginTwitter.setOnClickListener(this);


        /*Sign Up*/
        imgCross = (ImageView) viewSignUp.findViewById(R.id.imgCross);
        viewSignUpUserName = (MaterialEditText) viewSignUp.findViewById(R.id.viewSignUpUserName);
        viewSignUpEmail = (MaterialEditText) viewSignUp.findViewById(R.id.viewSignUpEmail);
        viewSignUpPassword = (MaterialEditText) viewSignUp.findViewById(R.id.viewSignUpPassword);
        imgSignUpFacebook = (ImageView) viewSignUp.findViewById(R.id.imgFacebook);
        imgSignUpGoogle = (ImageView) viewSignUp.findViewById(R.id.imgGoogle);
        imgSignUpTwitter = (ImageView) viewSignUp.findViewById(R.id.imgTwitter);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(this);
        imgCross.setOnClickListener(this);
        imgSignUpFacebook.setOnClickListener(this);
        imgSignUpGoogle.setOnClickListener(this);
        imgSignUpTwitter.setOnClickListener(this);
    }

    private void setupView() {
        viewLoginUserName/*.changeFieldText("ABCD")*/
                .changeFieldHintText("Username")
                .changeInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                .changeFieldImage(R.drawable.mail)
                .invalidate();
        viewLoginPassword/*.changeFieldText("****")*/
                .changeFieldHintText("Password")
                .changeFieldImage(R.drawable.password)
                .changeInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                .invalidate();

        viewSignUpEmail/*.changeFieldText("aaa@aaa.com")*/
                .changeFieldHintText("Email")
                .changeFieldImage(R.drawable.mail)
                .changeInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                .invalidate();
        viewSignUpUserName/*.changeFieldText("ABCD")*/
                .changeFieldHintText("Username")
                .changeFieldImage(R.drawable.username)
                .changeInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                .invalidate();
        viewSignUpPassword/*.changeFieldText("****")*/
                .changeFieldHintText("Password")
                .changeFieldImage(R.drawable.password)
                .changeInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                .invalidate();
    }

    @Override
    public void onClick(View v) {
        if (v == btnRegister) {
            AppHelper.hidekeyboard(this, this.getCurrentFocus());
            animationLoginSignUp(btnRegister, viewSignUp, viewLogin);
        } else if (v == btnLogin) {
            goToMainApp();
        } else if (v == lblForgotPassword) {

        } else if (v == btnSignUp) {
            goToMainApp();
        } else if (v == imgCross) {
            AppHelper.hidekeyboard(this, this.getCurrentFocus());
            animationLoginSignUp(imgCross, viewLogin, viewSignUp);
        } else if (v == imgLoginFacebook) {
            LOGIN_WITH = 1;
            fbLoginHelper.signInWithFacebook(this);
//            signInWithFacebook();
        } else if (v == imgSignUpFacebook) {
//            signInWithFacebook();
            LOGIN_WITH = 1;
            fbLoginHelper.signInWithFacebook(this);
        } else if (v == imgLoginGoogle) {
            LOGIN_WITH = 2;
            googleLoginHelper.signInWithGplus();
        } else if (v == imgSignUpGoogle) {
            LOGIN_WITH = 2;
            googleLoginHelper.signInWithGplus();
        } else if (v == imgLoginTwitter) {
            LOGIN_WITH = 3;
            twitterLoginHelper.signInWithTwitter();
        } else if (v == imgSignUpTwitter) {
            LOGIN_WITH = 3;
            twitterLoginHelper.signInWithTwitter();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (LOGIN_WITH == 1) {
            fbLoginHelper.getCallbackManager().onActivityResult(requestCode, resultCode, data);
        } else if (LOGIN_WITH == 2) {
            googleLoginHelper.onActivityResult(requestCode, resultCode, data);
        } else if (LOGIN_WITH == 3) {
            twitterLoginHelper.onActivityResult(requestCode, resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    /*Facebook Callback*/
    @Override
    public void onSucess(JSONObject object, GraphResponse response) {
        try {
            String id = object.getString("id");
            String name = object.getString("name");
            if (object.has("email") && !object.isNull("email")) {
                String email = object.getString("email");
            }
//            sp.setString(Sharedpref.FB_NAME, name);
//            sp.setString(Sharedpref.FB_EMAIL, email);
//            sp.setString(Sharedpref.FB_IMAGE, "http://graph.facebook.com/" + id + "/picture?type=large");
//            sp.setBoolean(Sharedpref.IS_LOGNIED_FB, true);
//            if (socialLoginLogout != null) {
//                socialLoginLogout.socialLoginStatus(true, true);
//            }
//            LoginWith = 1;
            Toast.makeText(this, "Facebook Login successfully", Toast.LENGTH_SHORT).show();
            goToMainApp();
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println(e);
//                                            Toast.makeText(ActMain.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onError(FacebookException error) {

    }

    /*Google*/
    @Override
    public void onSuccess() {
        Toast.makeText(this, "Google Login successfully", Toast.LENGTH_SHORT).show();
        goToMainApp();
    }

    @Override
    public void onFailed() {
        Toast.makeText(this, "Google Login Failed", Toast.LENGTH_SHORT).show();
    }

    /*Twitter*/
    @Override
    public void onSuccess(Result<TwitterSession> mainResult, Result<String> emailResult) {
        Toast.makeText(this, "Login Twitter", Toast.LENGTH_SHORT).show();
        goToMainApp();
    }

    @Override
    public void onError(TwitterException exception) {

    }

    /*Animate ripple wave to move between Login n SignUp screen*/
    private void animationLoginSignUp(View clickedView, final View toBeVisible, final View toBeInvisible) {

        int cx = (int) (clickedView.getX() + clickedView.getWidth() / 2);
        int cy = (int) (clickedView.getY() + clickedView.getHeight() / 2);
        float finalRadius = (float) Math.hypot(toBeInvisible.getWidth(), toBeInvisible.getHeight());
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
        anim.setDuration(400);
        anim.start();
    }


    private void goToMainApp() {
        AppHelper.hidekeyboard(this, this.getCurrentFocus());

        Intent i = new Intent(this, ActBaseNavigation.class);
        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this,
                viewLogin.getVisibility() == View.VISIBLE ? btnRegister : imgCross,
                getString(R.string.trans_login_base));
        startActivity(i, transitionActivityOptions.toBundle());
//        finish();
    }

    private void actTransListener() {
        Transition transition = getWindow().getSharedElementEnterTransition();
        transition.addListener(new Transition.TransitionListener() {

            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                viewLogin.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

    }
}
