package com.arhat.SocialShayari;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.arhat.SocialShayari.customview.RoundedImageView;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;

/**
 * Created by babu on 12/9/2016.
 */

public class ActProfile extends AppCompatActivity {

    private DiagonalLayout diagonalLayout = null;
    private KenBurnsView imgCover = null;
    private TextView lblUserName = null, lblTagLing = null, lblDescription = null;
    private RoundedImageView imgUser = null;
    private Toolbar toolbar = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_profile);
        findViews();
        setUpStatusBar();
    }

    private void findViews() {
        diagonalLayout = (DiagonalLayout) findViewById(R.id.diagonalLayout);
        imgCover = (KenBurnsView) findViewById(R.id.imgCover);
        lblUserName = (TextView) findViewById(R.id.lblUserName);
        lblTagLing = (TextView) findViewById(R.id.lblTagLing);
        imgUser = (RoundedImageView) findViewById(R.id.imgUser);
        lblDescription = (TextView) findViewById(R.id.lblDescription);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }


    private void setUpStatusBar() {
        BitmapDrawable drawable = (BitmapDrawable) imgCover.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Palette palette = Palette.generate(bitmap);
        Palette.Swatch swatch = palette.getDominantSwatch();
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(swatch.getRgb());
    }

}
