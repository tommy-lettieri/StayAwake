package com.lettieri.thomas.stayawake;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class StayAwakeWidget extends AppWidgetProvider {
    private final static String STAY_AWAKE_CLICK_EVENT = "STAY_AWAKE_CLICK_EVENT";
    public static final String STAY_AWAKE_WIDGET_ID ="STAY_AWAKE_WIDGET_ID";
    private final static String LOG_TAG = "StayAwakeWidget";

    private static void updateUI() {}

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.stay_awake_widget);
//        CharSequence widgetText = context.getString(R.string.appwidget_text);
//        views.setTextViewText(R.id.appwidget_text, widgetText);

        int backgroundColor;
        if(StayAwakeManager.isWakeLocked()) {
            backgroundColor = Color.GREEN;
        } else {
            backgroundColor = Color.RED;
        }
        views.setInt(R.id.layoutStayAwake, "setBackgroundColor", backgroundColor);
        views.setOnClickPendingIntent(R.id.layoutStayAwake, getPendingSelfIntent(context, STAY_AWAKE_CLICK_EVENT, appWidgetId));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    static protected PendingIntent getPendingSelfIntent(Context context, String action, int appWidgetId) {
//        https://stackoverflow.com/questions/8304387/android-how-do-i-force-the-update-of-all-widgets-of-a-particular-kind
//        https://stackoverflow.com/questions/34324656/android-widget-change-background-color
//        https://stackoverflow.com/questions/23220757/android-widget-onclick-listener-for-several-buttons
//        https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/content/unit-1-expand-the-user-experience/lesson-2-app-widgets/2-1-p-app-widgets/2-1-p-app-widgets.html

        Intent intent = new Intent(context, StayAwakeWidget.class);
        intent.setAction(action);
        intent.putExtra(STAY_AWAKE_WIDGET_ID, appWidgetId);
//        return PendingIntent.getBroadcast(context, 0, intent, 0);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        switch (intent.getAction()) {
            case STAY_AWAKE_CLICK_EVENT:
                Log.d(LOG_TAG, "onReceive: Stay awake event");
                StayAwakeManager.toggleWakeLock(context);
                break;
            default:
                Log.e(LOG_TAG, "onReceive: Incorrect action: " + intent.getAction());
        }

        if(intent.hasExtra(STAY_AWAKE_WIDGET_ID)) {
            int widgetId = intent.getIntExtra(STAY_AWAKE_WIDGET_ID, -1);
            if(widgetId >= 0) {
                this.updateAppWidget(context, AppWidgetManager.getInstance(context), widgetId);
            } else {
                Log.e(LOG_TAG, "onReceive: widgetId was less than 0, it was: " + widgetId);
            }
        } else {
            Log.e(LOG_TAG, "onReceive: NO STAY_AWAKE_WIDGET_ID");
        }
    }
}

