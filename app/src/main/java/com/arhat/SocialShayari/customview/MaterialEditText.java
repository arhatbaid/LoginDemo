package com.arhat.SocialShayari.customview;

import android.animation.Animator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.arhat.SocialShayari.R;

public class MaterialEditText extends RelativeLayout implements EditText.OnFocusChangeListener {

    private boolean isAlreadyIinfalted = false;
    private View view = null;


    private ImageView imgTextIcon = null;
    private EditText txtLogin = null;
    private View viewActive = null;
    private View viewInactive = null;
    private TextInputLayout ipText = null;
    private int imgTextIconFocused;


    public MaterialEditText(Context context) {
        super(context);
        if (!isAlreadyIinfalted)
            inflatelayout(context);
    }

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isAlreadyIinfalted)
            inflatelayout(context);

    }

    public MaterialEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isAlreadyIinfalted)
            inflatelayout(context);
    }

    public MaterialEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!isAlreadyIinfalted)
            inflatelayout(context);
    }

    private void inflatelayout(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.material_edittext, this, true);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        isAlreadyIinfalted = true;

        ipText = (TextInputLayout) view.findViewById(R.id.ipText);
        imgTextIcon = (ImageView) view.findViewById(R.id.imgTextIcon);
        txtLogin = (EditText) view.findViewById(R.id.txtLogin);
        viewActive = view.findViewById(R.id.viewActive);
        viewInactive = view.findViewById(R.id.viewInactive);

        viewActive.setVisibility(GONE);
        imgTextIcon.setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_ATOP);
        txtLogin.setOnFocusChangeListener(this);
    }

    public MaterialEditText changeFieldText(String text) {
        txtLogin.setText(text);
        return this;
    }

    public MaterialEditText changeFieldHintText(String hintText) {
        ipText.setHint(hintText);
        return this;
    }

    public MaterialEditText changeFieldImage(int imgResource) {
        imgTextIcon.setImageResource(imgResource);
        return this;
    }

    public MaterialEditText changeInputType(int inputType) {
        txtLogin.setInputType(inputType);
        return this;
    }


    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            animateOnFocus(viewActive, viewInactive);
        } else {
            animateOffFocus(viewInactive, viewActive);
        }
    }


    private void animateOnFocus(final View toBeVisible, final View toBeInvisible) {

        int cx = (int) (toBeVisible.getX() + toBeVisible.getWidth() / 2);
        int cy = (int) (toBeVisible.getY() + toBeVisible.getHeight() / 2);
        float finalRadius = (float) Math.hypot(toBeVisible.getWidth(), toBeVisible.getHeight());
        Animator anim =
                ViewAnimationUtils.createCircularReveal(toBeVisible, cx, cy, 0, finalRadius);
//        anim.setDuration(300);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                toBeVisible.setVisibility(View.VISIBLE);
                imgTextIcon.setColorFilter(getResources().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.SRC_ATOP);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                toBeVisible.setVisibility(View.VISIBLE);
//                toBeInvisible.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration(700);
        anim.start();
    }

    private void animateOffFocus(final View toBeVisible, final View toBeInvisible) {

        int cx = (int) (toBeInvisible.getX() + toBeInvisible.getWidth() / 2);
        int cy = (int) (toBeInvisible.getY() + toBeInvisible.getHeight() / 2);
        float finalRadius = (float) Math.hypot(toBeInvisible.getWidth(), toBeInvisible.getHeight());
        Animator anim =
                ViewAnimationUtils.createCircularReveal(toBeInvisible, cx, cy, finalRadius, 0);
//        anim.setDuration(300);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                toBeInvisible.setVisibility(View.VISIBLE);
                imgTextIcon.setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_ATOP);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
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
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration(700);
        anim.start();
    }
}
