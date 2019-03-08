package com.lettieri.thomas.stayawake;

import android.content.Context;
import android.os.PowerManager;

/**
 * This is meant for handling a static wake lock
 */
public class StayAwakeManager {
//    https://developer.android.com/reference/android/appwidget/AppWidgetProvider.html#onUpdate(android.content.Context,%20android.appwidget.AppWidgetManager,%20int[])

    public static PowerManager.WakeLock wakeLock = null;

    /**
     * If lock awake does not exist create and acquire the lock
     * @param context The context of the app (needed for getting the wake lock)
     * @return false if wakeLock exists, true if wakeLock does not exist
     */
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

    /**
     * If lock awake exists release and nullify the lock
     * @return false if there was no wakeLock, true if the wakeLock was released
     */
    public static boolean turnOffKeepAwake() {
        // TODO there should be a lock that locks at the beginning and end of this method and the other method (same lock)
        if(wakeLock == null) {
            return false;
        }
        wakeLock.release();
        wakeLock = null;
        return true;
    }

    /**
     * determine if there is a wake lock
     * @return whether or not the wake lock exists (and thus is acquired)
     */
    public static boolean isWakeLocked() {
        return wakeLock != null;
    }

    /**
     * If wake locked unlock, if not wake locked then lock
     * @param context is the context of the app (need for making the lock)
     */
    public static void toggleWakeLock(Context context) {
        if(isWakeLocked()) {
            turnOffKeepAwake();
        } else {
            turnOnKeepAwake(context);
        }
    }
}
