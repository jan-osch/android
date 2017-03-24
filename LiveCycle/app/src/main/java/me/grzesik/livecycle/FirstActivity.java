package me.grzesik.livecycle;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FirstActivity extends AbstractTrackedClass {

    @Override
    protected String getTag() {
        return "First Activity";
    }

    @Override
    protected TextView getCreateTv() {
        View viewById = findViewById(R.id.firstCreateTV);
        return (TextView) viewById;
    }

    @Override
    protected TextView getStartTv() {
        return (TextView) findViewById(R.id.firstStartTV);
    }

    @Override
    protected TextView getResumeTv() {
        return (TextView) findViewById(R.id.firstResumeTV);
    }

    @Override
    protected TextView getRestartTv() {
        return (TextView) findViewById(R.id.firstRestartTV);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        this.updateOnCreate();
    }
}
