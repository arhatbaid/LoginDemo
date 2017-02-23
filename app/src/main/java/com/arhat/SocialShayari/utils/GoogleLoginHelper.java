package com.arhat.SocialShayari.utils;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.arhat.SocialShayari.activity.ActLoginSignUp;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Created by babu on 12/11/2016.
 */

public class GoogleLoginHelper implements
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient = null;
    private int RC_SIGN_IN = 100;
    private AppCompatActivity activity = null;
    private OnGPlusLoginComplete onGPlusLoginComplete = null;

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void initGooglePlus(ActLoginSignUp actLoginSignUp, OnGPlusLoginComplete onGPlusLoginComplete) {
        activity = actLoginSignUp;
        this.onGPlusLoginComplete = onGPlusLoginComplete;

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    public void signInWithGplus() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("LoginStatus", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            System.out.println("!--Name - " + acct.getDisplayName());
            onGPlusLoginComplete.onSuccess();
        } else {
            onGPlusLoginComplete.onFailed();
            System.out.println("!--Failed - ");
        }
    }


    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        mGoogleApiClient = null;
                    }
                });
    }

    public interface OnGPlusLoginComplete {
        void onSuccess();

        void onFailed();
    }
}
