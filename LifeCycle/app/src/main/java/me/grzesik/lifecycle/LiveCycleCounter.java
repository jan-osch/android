package me.grzesik.lifecycle;

import android.content.SharedPreferences;

public class LiveCycleCounter {

    private static SharedPreferences settings;

    public static void initialize(SharedPreferences settings) {
        LiveCycleCounter.settings = settings;
    }

    public static boolean isInitialized() {
        return LiveCycleCounter.settings != null;
    }

    public static void increment(String activityName, String liveCycleKey) {
        String counterKey = generateHashKey(activityName, liveCycleKey);

        int value = settings.getInt(counterKey, 0);

        SharedPreferences.Editor edit = settings.edit();
        edit.putInt(counterKey, value + 1);

        edit.apply();
    }

    public static int get(String activityName, String liveCycleKey) {
        String counterKey = generateHashKey(activityName, liveCycleKey);

        return settings.getInt(counterKey, 0);
    }

    private static String generateHashKey(String activityName, String liveCycleKey) {
        return activityName + liveCycleKey;
    }

}
