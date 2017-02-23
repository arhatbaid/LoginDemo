package com.arhat.SocialShayari.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by babu on 12/11/2016.
 */

public class FbLoginHelper implements
        FacebookCallback<LoginResult>,
        GraphRequest.GraphJSONObjectCallback {


    private OnFbLoginCompleted onFbLoginCompleted = null;

    private CallbackManager callbackManager = null;

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    public void initLogin(Context context, OnFbLoginCompleted onFbLoginCompleted) {
        this.onFbLoginCompleted = onFbLoginCompleted;
        FacebookSdk.sdkInitialize(context);
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, this);
        AppEventsLogger.activateApp(context);
    }

    public void signInWithFacebook(Activity context) {
        LoginManager.getInstance().logInWithReadPermissions(context, Arrays.asList("email", "public_profile"));
    }

    public void logout() {
        LoginManager.getInstance().logOut();
    }


    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.d("Success", "Login");
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(), this);

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onCancel() {
        Log.d("Cancel", "Login");
        onFbLoginCompleted.onError(null);
    }

    @Override
    public void onError(FacebookException error) {
        Log.d("Error", "Login");
        onFbLoginCompleted.onError(error);
    }

    @Override
    public void onCompleted(JSONObject object, GraphResponse response) {
        onFbLoginCompleted.onSucess(object, response);
    }

    public interface OnFbLoginCompleted {
        void onSucess(JSONObject object, GraphResponse response);

        void onError(FacebookException error);
    }
}
