package com.arhat.SocialShayari.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by babu on 12/13/2016.
 */

public class AppHelper {

    public static void hidekeyboard(Context context, View view){
        // Check if no view has focus:
        try {
            if (view != null) {
                view.clearFocus();
                InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }


//            if(view != null){
//                view.clearFocus();
//                InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY|InputMethodManager.HIDE_NOT_ALWAYS|InputMethodManager.RESULT_HIDDEN, 0);
//
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
