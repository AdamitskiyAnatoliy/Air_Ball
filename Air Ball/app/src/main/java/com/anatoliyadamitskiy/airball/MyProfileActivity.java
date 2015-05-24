package com.anatoliyadamitskiy.airball;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

/**
 * Created by Anatoliy on 3/19/15.
 */
public class MyProfileActivity extends ActionBarActivity {

    User user;
    ListView listView;

    ArrayList<Shot> arrayList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        user = FileStorage.getUser(this).get(0);

        listView = (ListView) findViewById(R.id.userShotsListView);

        RoundedCornersSmartImageView avatar = (RoundedCornersSmartImageView) findViewById(R.id.profileAvatarImage);
        avatar.setImageUrl(user.getAvatarUrl());
        avatar.setRadius(300);

        arrayList.add(new Shot("","http://freebiesbug.com/wp-content/uploads/2014/03/dribbble-app-440x330.jpg",
                "", "", "Anatoliy Adamitskiy", "Shottts"));
        arrayList.add(new Shot("","http://www.dtelepathy.com/wp-content/uploads/2013/06/iOS-7-Dribbble-Image-2.png",
                "", "", "Anatoliy Adamitskiy", "Flat UI Concept"));

        TextView followers = (TextView) findViewById(R.id.followers);
        followers.setText(user.getFollowers());
        TextView following = (TextView) findViewById(R.id.following);
        following.setText(user.getFollowing());
        TextView shots = (TextView) findViewById(R.id.shots);
        shots.setText("2");

        ProfileAdapter profileAdapter = new ProfileAdapter(this, arrayList);
        listView.setAdapter(profileAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_log_out) {

            finish();
            Toast.makeText(this, "You have logged out", Toast.LENGTH_LONG).show();

            return true;
        } else if (id == R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
