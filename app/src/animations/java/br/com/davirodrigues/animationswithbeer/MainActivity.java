package br.com.davirodrigues.animationswithbeer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mContainer;
    private LoginView mLoginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mLoginView = new LoginView(this);
        mContainer = (FrameLayout) findViewById(R.id.container);
        mContainer.addView(mLoginView);
    }

}
