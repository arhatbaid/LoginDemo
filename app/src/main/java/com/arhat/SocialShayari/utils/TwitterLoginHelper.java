package com.arhat.SocialShayari.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import io.fabric.sdk.android.Fabric;

/**
 * Created by babu on 12/12/2016.
 */

public class TwitterLoginHelper extends Callback<TwitterSession> {
    private AppCompatActivity activity = null;
    private OnTwitterLoginCompleted onTwitterLoginCompleted = null;

    private TwitterAuthClient client = null;

    private static final String TWITTER_KEY = "2Z3N97RViA3s12exEYeueim1P";
    private static final String TWITTER_SECRET = "J7dqwVBKtDynANGHCfHsyUEsNJBjnASm5WFYvkGRjRrVpYsIjL";

    public void initTwitter(AppCompatActivity appCompatActivity, OnTwitterLoginCompleted onTwitterLoginCompleted) {
        activity = appCompatActivity;
        this.onTwitterLoginCompleted = onTwitterLoginCompleted;

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(activity, new Twitter(authConfig));
        client = new TwitterAuthClient();

    }


    public void signInWithTwitter() {
        client.authorize(activity, this);
    }

    @Override
    public void success(final Result<TwitterSession> mainResult) {
        client.requestEmail(mainResult.data, new Callback<String>() {
            @Override
            public void success(Result<String> emailResult) {
                onTwitterLoginCompleted.onSuccess(mainResult, emailResult);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                onTwitterLoginCompleted.onSuccess(mainResult, null);
            }
        });
    }

    @Override
    public void failure(TwitterException exception) {
        onTwitterLoginCompleted.onError(exception);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        client.onActivityResult(requestCode, resultCode, data);
    }


    public interface OnTwitterLoginCompleted {
        void onSuccess(Result<TwitterSession> mainResult, Result<String> emailResult);

        void onError(TwitterException exception);
    }
}
