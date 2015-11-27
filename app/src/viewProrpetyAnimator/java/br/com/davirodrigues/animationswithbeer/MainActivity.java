package br.com.davirodrigues.animationswithbeer;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    View square;
    View square2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toInflateView = (FrameLayout) findViewById(R.id.to_inflate_view);
        square = findViewById(R.id.square);
        square2 = findViewById(R.id.square2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        resetSquareStatus();
        square.animate().setStartDelay(500);

        if (id == R.id.nav_rotation_x) {
            animationRotationX().start();
        } else if (id == R.id.nav_rotation_y) {
            animationRotationY().start();
        } else if (id == R.id.nav_rotation_z) {
            animationRotationZ().start();
        } else if (id == R.id.nav_translation_x) {
            animationTranslationX().start();
        } else if (id == R.id.nav_translation_y) {
            animationTranslationY().start();
        } else if (id == R.id.nav_translation_z && isLollipop()) {
            animationTranslationZ().start();
        } else if (id == R.id.nav_scale_x) {
            animationScaleX().start();
        } else if (id == R.id.nav_scale_y) {
            animationScaleY().start();
        } else if (id == R.id.nav_alpha) {
            animationAlphaToZero().start();
        } else if (id == R.id.nav_combine_1 && isLollipop()) {
            animationCombine1().start();
        } else if (id == R.id.nav_combine_2 && isLollipop()) {
            animationCombine2().start();
        } else if (id == R.id.nav_combine_3 && isLollipop()) {
            animationCombine3().start();
        } else if (!isLollipop()) {
            Snackbar.make(square, "Not is lollipop", Snackbar.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void resetSquareStatus() {
        square.setRotation(0);
        square.setRotationX(0);
        square.setRotationY(0);
        square.setAlpha(1);
        square.setTranslationX(0);
        square.setTranslationY(0);
        square.setScaleX(1);
        square.setScaleY(1);
        square.clearAnimation();
        square.animate().setListener(null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            square.setTranslationZ(0);
        }

        square2.setVisibility(View.GONE);
    }


    public ViewPropertyAnimator animationTranslationX() {
        return square.animate().translationX(100)
                .setDuration(200);
    }

    public ViewPropertyAnimator animationTranslationY() {
        return square.animate().translationY(100)
                .setDuration(200);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ViewPropertyAnimator animationTranslationZ() {
        return square.animate().translationZ(50)
                .setDuration(200);
    }

    public ViewPropertyAnimator animationScaleX() {
        return square.animate().scaleX(2f)
                .setDuration(200);
    }

    public ViewPropertyAnimator animationScaleY() {
        return square.animate().scaleY(2f)
                .setDuration(200);
    }

    public ViewPropertyAnimator animationAlphaToZero() {
        return square.animate().alpha(0)
                .setDuration(200);
    }

    public ViewPropertyAnimator animationRotationX() {
        return square.animate().rotationX(180)
                .setDuration(300);
    }

    public ViewPropertyAnimator animationRotationY() {
        return square.animate().rotationY(180)
                .setDuration(300);
    }

    public ViewPropertyAnimator animationRotationZ() {
        return square.animate().rotation(180)
                .setDuration(500);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ViewPropertyAnimator animationCombine1() {
        return square.animate()
                .rotation(90)
                .scaleY(1.5f)
                .translationZ(50)
                .setDuration(400);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ViewPropertyAnimator animationCombine2() {
        return square.animate()
                .rotationX(180)
                .scaleX(1.5f)
                .setDuration(400)
                .setListener(new br.com.davirodrigues.animationswithbeer.AnimatorWithBeerListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        square.setTranslationZ(50);
                        animation.removeListener(this);
                    }
                });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ViewPropertyAnimator animationCombine3() {
        //prepare animation
        square2.setVisibility(View.VISIBLE);
        // animation
        return square.animate()
                .scaleY(1.5f)
                .translationY(150)
                .scaleX(1.5f)
                .translationX(150)
                .setDuration(500)
                .translationZ(50);
    }

    public boolean isLollipop() {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP;
    }

}
