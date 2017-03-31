package me.grzesik.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public abstract class AbstractTrackedClass extends AppCompatActivity {
    private static final String RESTART_KEY = "restart";
    private static final String RESUME_KEY = "resume";
    private static final String START_KEY = "start";
    private static final String CREATE_KEY = "create";

    private static final String PREFERENCES_KEY = "lifecycle_tracking";

    protected abstract String getTag();

    abstract protected TextView getCreateTv();

    abstract protected TextView getStartTv();

    abstract protected TextView getResumeTv();

    abstract protected TextView getRestartTv();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!LiveCycleCounter.isInitialized()) {
            LiveCycleCounter.initialize(getSharedPreferences(PREFERENCES_KEY, 0));
        }
    }

    protected void displayCounts() {
        int mCreate = LiveCycleCounter.get(this.getTag(), CREATE_KEY);
        int mStart = LiveCycleCounter.get(this.getTag(), START_KEY);
        int mResume = LiveCycleCounter.get(this.getTag(), RESUME_KEY);
        int mRestart = LiveCycleCounter.get(this.getTag(), RESTART_KEY);

        this.getCreateTv().setText(new StringBuilder().append("onCreate() calls: ").append(mCreate).toString());
        this.getStartTv().setText(new StringBuilder().append("onStart() calls: ").append(mStart).toString());
        this.getResumeTv().setText(new StringBuilder().append("onResume() calls: ").append(mResume).toString());
        this.getRestartTv().setText(new StringBuilder().append("onRestart() calls: ").append(mRestart).toString());
    }

    protected void updateOnCreate() {
        Log.i(this.getTag(), "Entered the onCreate() method");

        LiveCycleCounter.increment(this.getTag(), CREATE_KEY);
        displayCounts();
    }

    @Override
    public void onStart() {
        super.onStart();

        LiveCycleCounter.increment(this.getTag(), START_KEY);
        displayCounts();
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(this.getTag(), "Entered the onResume() method");

        LiveCycleCounter.increment(this.getTag(), RESUME_KEY);
        displayCounts();
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i(this.getTag(), "Entered the onPause() method");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.i(this.getTag(), "Entered the onStop() method");
    }

    @Override
    public void onRestart() {
        super.onRestart();

        Log.i(this.getTag(), "Entered the onRestart() method");

        LiveCycleCounter.increment(this.getTag(), RESTART_KEY);
        displayCounts();
    }
}
