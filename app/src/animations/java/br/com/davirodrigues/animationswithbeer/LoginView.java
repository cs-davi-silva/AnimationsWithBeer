package br.com.davirodrigues.animationswithbeer;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginView extends FrameLayout {

    private View mView;
    private TextView mEmail;
    private TextView mName;
    private TextView mPhoneNumer;
    private TextView mPassword;
    private Button mEnter;
    private Button mLoginOrRegister;
    private FrameLayout rootForm;
    private LinearLayout buttonsPanel;
    private boolean flagLogin = true;

    public LoginView(Context context) {
        this(context, null);
    }

    public LoginView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeLayout();
    }

    private void initializeLayout() {
        mView = inflate(getContext(), R.layout.login_view_layout, this);

        rootForm = (FrameLayout) mView.findViewById(R.id.root_form);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            rootForm.setTranslationZ(20);
        }
        buttonsPanel = (LinearLayout) mView.findViewById(R.id.buttons_panel);
        mEmail = (TextView) mView.findViewById(R.id.email);
        mName = (TextView) mView.findViewById(R.id.name);
        mPhoneNumer = (TextView) mView.findViewById(R.id.phone_number);
        mPassword = (TextView) mView.findViewById(R.id.password);
        mEnter = (Button) mView.findViewById(R.id.enter);
        mLoginOrRegister = (Button) mView.findViewById(R.id.register_login);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            rootForm.setTranslationZ(20);
            buttonsPanel.setTranslationZ(20);
            mEmail.setTranslationZ(20);
            mName.setTranslationZ(20);
            mPhoneNumer.setTranslationZ(20);
            mPassword.setTranslationZ(20);
            mEnter.setTranslationZ(20);
            mLoginOrRegister.setTranslationZ(20);
        }

        mEnter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEnter(v);
            }
        });

        mLoginOrRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                flagLogin = !flagLogin;
                clickLoginOrRegister(flagLogin, v);
            }
        });
    }

    public void clickEnter(@NonNull View v) {

        Snackbar.make(this, "Enter", Snackbar.LENGTH_LONG).show();
    }

    public void clickLoginOrRegister(boolean isLogin, @NonNull View v) {
        if (isLogin) {
            closeRegisterAnimation();
            mLoginOrRegister.setText(R.string.register);

        } else {
            openRegisterAnimation();
            mLoginOrRegister.setText(R.string.login);
        }
    }

    public void openRegisterAnimation() {
        rootForm.animate().scaleY(1.5f).start();
        mEmail.animate().translationY(-90).start();
        mName.animate().setListener(new AnimatorWithBeerListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                LoginView.this.mName.setAlpha(0);
                LoginView.this.mName.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }
        }).alpha(1);

        mPhoneNumer.animate().setListener(new AnimatorWithBeerListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                LoginView.this.mPhoneNumer.setAlpha(0);
                LoginView.this.mPhoneNumer.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }
        }).alpha(1);

        mPassword.animate().translationY(90).start();
        buttonsPanel.animate().translationY(90).start();
    }

    public void closeRegisterAnimation() {
        rootForm.animate().scaleY(1f).start();
        mEmail.animate().translationY(5).start();
        mName.animate().setListener(new AnimatorWithBeerListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                LoginView.this.mName.setVisibility(GONE);
            }
        }).alpha(0);

        mPhoneNumer.animate().setListener(new AnimatorWithBeerListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                LoginView.this.mPhoneNumer.setVisibility(GONE);
            }
        }).alpha(0);

        mPassword.animate().translationY(-5).start();
        buttonsPanel.animate().translationY(-5).start();
    }

}
