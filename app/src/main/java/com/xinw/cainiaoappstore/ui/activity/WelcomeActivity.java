package com.xinw.cainiaoappstore.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;
import com.xinw.cainiaoappstore.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.pathView)
    PathView pathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        pathView.getPathAnimator().delay(10).duration(5000).listenerStart(new PathView.AnimatorBuilder.ListenerStart() {
            @Override
            public void onAnimationStart() {
                Log.v("AppStore", "start");
            }
        }).listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
            @Override
            public void onAnimationEnd() {
                Log.v("AppStroe", "end");
                jumpToMain();
            }
        }).interpolator(new AccelerateDecelerateInterpolator()).start();
    }

    private void jumpToMain() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }
}
