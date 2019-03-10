package com.lettieri.thomas.stayawake.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;

import com.lettieri.thomas.stayawake.R;
import com.lettieri.thomas.stayawake.utils.StayAwakeManager;
import com.lettieri.thomas.stayawake.utils.WidgetHelper;

/**
 * Implementation of App Widget functionality.
 */
public class StayAwakeTestWidget extends AppWidgetProvider {
    public final static String STAY_AWAKE_UPDATE_CLICK_EVENT = "STAY_AWAKE_UPDATE_CLICK_EVENT";
    public final static String STAY_AWAKE_CLICK_EVENT = "STAY_AWAKE_CLICK_EVENT";
    public final static String LOG_TAG = "StayAwakeTestWidget";

    private static int updateCount = 0;
    /**
     * Update the UI for a widget
     * @param context of the app
     * @param appWidgetManager Manager to update the widget
     * @param appWidgetId Id of the appWidget
     */
    public void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // THIS IS THE SAME AS SUPERS
        // TODO find a way to put this into a method (they layout is different...)
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.stay_awake_test_widget);
        // Determine UI updates based on state
        int backgroundColor;
        if(StayAwakeManager.isWakeLocked()) {
            backgroundColor = Color.GREEN;
        } else {
            backgroundColor = Color.RED;
        }
        // Update UI
        views.setInt(R.id.layoutStayAwake, "setBackgroundColor", backgroundColor);
        views.setOnClickPendingIntent(R.id.layoutStayAwake, WidgetHelper.getPendingSelfIntent(StayAwakeTestWidget.class, context, STAY_AWAKE_CLICK_EVENT, appWidgetId));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

        // THIS IS THE TEST LOGIC
        updateCount++;
        views.setOnClickPendingIntent(R.id.btnUpdate, WidgetHelper.getPendingSelfIntent(StayAwakeTestWidget.class, context, STAY_AWAKE_UPDATE_CLICK_EVENT, appWidgetId));
        views.setTextViewText(R.id.txtUpdateCount, "Updated: " + updateCount);
        appWidgetManager.updateAppWidget(appWidgetId, views);
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
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        super.onDisabled(context);
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

        if(intent.hasExtra(WidgetHelper.STAY_AWAKE_WIDGET_ID)) {
            int widgetId = intent.getIntExtra(WidgetHelper.STAY_AWAKE_WIDGET_ID, -1);
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

