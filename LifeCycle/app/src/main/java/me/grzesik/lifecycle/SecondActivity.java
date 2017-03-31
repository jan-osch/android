package me.grzesik.lifecycle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AbstractTrackedClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initializeView();
        updateOnCreate();
    }

    @Override
    protected String getTag() {
        return "Second activity";
    }

    @Override
    protected TextView getCreateTv() {
        return (TextView) findViewById(R.id.secondCreateTV);
    }


    @Override
    protected TextView getStartTv() {
        return (TextView) findViewById(R.id.secondStartTV);
    }

    @Override
    protected TextView getResumeTv() {
        return (TextView) findViewById(R.id.secondResumeTV);
    }

    @Override
    protected TextView getRestartTv() {
        return (TextView) findViewById(R.id.secondRestartTV);
    }

    private void initializeView() {
        Button launchActivityTwoButton = (Button) findViewById(R.id.buttonEndActivity);
        launchActivityTwoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
