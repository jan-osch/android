package me.grzesik.livecycle;

import java.util.HashMap;
import java.util.Map;

public class LiveCycleCounter {

    private static Map<String, Integer> counters = new HashMap<>();

    public static void increment(String activityName, String liveCycleKey) {
        String counterKey = generateHashKey(activityName, liveCycleKey);

        Integer value = LiveCycleCounter.counters.getOrDefault(counterKey, 0);

        LiveCycleCounter.counters.put(counterKey, value + 1);
    }

    public static int get(String activityName, String liveCycleKey) {
        String counterKey = generateHashKey(activityName, liveCycleKey);

        return LiveCycleCounter.counters.getOrDefault(counterKey, 0);
    }

    private static String generateHashKey(String activityName, String liveCycleKey) {
        return activityName + liveCycleKey;
    }

}
