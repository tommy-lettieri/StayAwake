package com.lettieri.thomas.stayawake;

import android.content.Context;
import android.os.PowerManager;

public class StayAwakeManager {
//    https://developer.android.com/reference/android/appwidget/AppWidgetProvider.html#onUpdate(android.content.Context,%20android.appwidget.AppWidgetManager,%20int[])

    public static PowerManager.WakeLock wakeLock = null;

    public static boolean turnOnKeepAwake(Context context) {
        // TODO there should be a lock that locks at the beginning and end of this method and the other method (same lock)
        if(wakeLock != null) {
            return false;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
                "StayAwake::WakeLockTag");
        wakeLock.acquire();
        return true;
    }

    public static boolean turnOffKeepAwake() {
        // TODO there should be a lock that locks at the beginning and end of this method and the other method (same lock)
        if(wakeLock == null) {
            return false;
        }
        wakeLock.release();
        wakeLock = null;
        return true;
    }

    public static boolean isWakeLocked() {
        return wakeLock != null;
    }

    public static void toggleWakeLock(Context context) {
        if(isWakeLocked()) {
            turnOffKeepAwake();
        } else {
            turnOnKeepAwake(context);
        }
    }
}
