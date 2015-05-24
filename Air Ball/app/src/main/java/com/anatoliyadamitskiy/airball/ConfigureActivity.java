package com.anatoliyadamitskiy.airball;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Anatoliy on 2/26/15.
 */
public class ConfigureActivity extends ActionBarActivity {

    int widgetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_activity_layout);

        ConfigureFragment configFrag = new ConfigureFragment();
        getFragmentManager().beginTransaction().add(R.id.configLayout3x2, configFrag, ConfigureFragment.TAG).commit();

        Intent intent = getIntent();
        if(intent.hasExtra(AppWidgetManager.EXTRA_APPWIDGET_ID)) {
            widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0);
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_config, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            String userPref = sp.getString("3x2_PREF", "Popular");

            DribbbleDownload2 dribbbleDownload = new DribbbleDownload2(this, userPref);
            dribbbleDownload.execute();

            Intent resultIntent = new Intent();
            resultIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
            setResult(RESULT_OK, resultIntent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class ConfigureFragment extends PreferenceFragment {

        public static final String TAG = "ConfigureFragment.TAG";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.config);
        }

    }

}
