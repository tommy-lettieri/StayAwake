package com.lettieri.thomas.stayawake.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class WidgetHelper {
    public static final String STAY_AWAKE_WIDGET_ID ="STAY_AWAKE_WIDGET_ID";

    /**
     * This intent is used for click events
     * @param context
     * @param action
     * @param appWidgetId
     * @return A Pending Intent Broadcast
     */
    public static PendingIntent getPendingSelfIntent(Class clazz, Context context, String action, int appWidgetId) {
//        https://stackoverflow.com/questions/8304387/android-how-do-i-force-the-update-of-all-widgets-of-a-particular-kind
//        https://stackoverflow.com/questions/34324656/android-widget-change-background-color
//        https://stackoverflow.com/questions/23220757/android-widget-onclick-listener-for-several-buttons
//        https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/content/unit-1-expand-the-user-experience/lesson-2-app-widgets/2-1-p-app-widgets/2-1-p-app-widgets.html
        Intent intent = new Intent(context, clazz);
        intent.setAction(action);
        intent.putExtra(STAY_AWAKE_WIDGET_ID, appWidgetId);
//        return PendingIntent.getBroadcast(context, 0, intent, 0);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
