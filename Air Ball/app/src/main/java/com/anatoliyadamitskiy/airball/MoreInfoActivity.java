package com.anatoliyadamitskiy.airball;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

/**
 * Created by Anatoliy on 3/14/15.
 */
public class MoreInfoActivity extends ActionBarActivity {

    public static final String UPDATE_COMMENTS = "com.anatoliyadamitskiy.airball.UPDATE_COMMENTS";

    ArrayList<Comment> commentArrayList = new ArrayList<>();

    SmartImageView mainImage;
    Shot shot;
    String commentUrl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        mainImage = (SmartImageView) findViewById(R.id.moreInfoImageView);

        Intent intent = getIntent();
        shot = (Shot) intent.getSerializableExtra("Shot");
        commentUrl = shot.getCommentsUrl();
        mainImage.setImageUrl(shot.getImageUrl());

        CommentDownload commentDownload = new CommentDownload(this, commentUrl);
        commentDownload.execute();

        FloatingActionButton fabButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_fav_more_info))
                .withButtonColor(Color.rgb(33, 150, 243))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 300)
                .create();

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Saved to Favorites", Toast.LENGTH_LONG).show();
                FileStorage.saveFavorites(getApplicationContext(), "Favorites", shot);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(UPDATE_COMMENTS);
        this.registerReceiver(downloadOkayReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.unregisterReceiver(downloadOkayReceiver);
    }

    BroadcastReceiver downloadOkayReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(UPDATE_COMMENTS)) {


                ListView commentListView = (ListView) findViewById(R.id.commentsListView);
                commentArrayList = FileStorage.getComments(getApplicationContext());
                CommentAdapter mainAdapter = new CommentAdapter(getApplicationContext(), commentArrayList);
                commentListView.setAdapter(mainAdapter);

            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.more_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(shot.getWebUrl()));
            this.startActivity(intent);

            return true;
        } else if (id == R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
