package com.anatoliyadamitskiy.airball;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by Anatoliy on 2/26/15.
 */

public class WidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        int widgetId;

        for(int i = 0; i < appWidgetIds.length; i++) {

            widgetId = appWidgetIds[i];

            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            Intent intent = new Intent(context, WidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
            rv.setRemoteAdapter(R.id.viewFlipper2, intent);

            appWidgetManager.updateAppWidget(widgetId, rv);
        }

    }

}
