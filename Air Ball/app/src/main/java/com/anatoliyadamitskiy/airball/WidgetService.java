package com.anatoliyadamitskiy.airball;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Anatoliy on 2/27/15.
 */
public class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetViewFactory(getApplicationContext());
    }

    private class WidgetViewFactory implements RemoteViewsFactory {

        String userPref;
        private Context mContext;
        private ArrayList<Shot> mRecipes;

        public WidgetViewFactory(Context context) {
            mContext = context;
            mRecipes = new ArrayList<>();
        }

        @Override
        public void onCreate() {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
            userPref = sp.getString("3x2_PREF", "Popular");
            onDataSetChanged();
        }

        @Override
        public void onDataSetChanged() {

            mRecipes = FileStorage.getShots(mContext, userPref);

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return mRecipes.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            Shot recipe = mRecipes.get(position);

            RemoteViews itemView = new RemoteViews(mContext.getPackageName(), R.layout.widget_item_layout);
            itemView.setTextViewText(R.id.recipeNameLabel2, recipe.getShotTitle());
            itemView.setImageViewBitmap(R.id.smartImageView3x2, getImageBitmap(recipe.getImageUrl()));
            itemView.setTextViewText(R.id.widgetDesignersName, "by " +   recipe.getPlayerName());

            return itemView;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL Url = new URL(url);
            URLConnection conn = Url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("ERROR", "getImageBitmap");
        }
        return bm;
    }

}