package me.grzesik.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AbstractTrackedClass {

    @Override
    protected String getTag() {
        return "First Activity";
    }

    @Override
    protected TextView getCreateTv() {
        return (TextView) findViewById(R.id.firstCreateTV);
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
        initializeView();
        this.updateOnCreate();
    }

    private void initializeView() {
        Button launchActivityTwoButton = (Button) findViewById(R.id.switchToActivityTwoButton);
        launchActivityTwoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
