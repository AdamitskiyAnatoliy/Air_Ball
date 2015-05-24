package com.anatoliyadamitskiy.airball;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Anatoliy on 3/19/15.
 */
public class ProfileDownload extends AsyncTask<String, Void, ArrayList<User>> {

    Context mContext;
    String profile;

    public ProfileDownload(Context _context, String username)
    {
        mContext = _context;
        profile = "https://api.dribbble.com/v1/users/" + username +
                "?access_token=115715a8e2ac13850d7562a30b0c792b4de51378a331412e155b525913c4ce8b";
    }

    @Override
    protected ArrayList<User> doInBackground(String... params) {

        ArrayList<User> profileArray = new ArrayList<>();

        try {

            URL url = new URL(profile);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            String data = IOUtils.toString(is);
            is.close();

            JSONObject user = new JSONObject(data);

            String avatarUrl = user.getString("avatar_url");
            String followers = user.getString("followers_count");
            String following = user.getString("followings_count");
            String shots = user.getString("shots_count");
            String shotsUrl = user.getString("shots_url");

            profileArray.add(new User(avatarUrl, followers, following, shots, shotsUrl));

            FileStorage.saveUser(mContext, profileArray);

        } catch (IOException | JSONException e) {
            e.printStackTrace();

            //  Toast.makeText(mContext, "Invalid Login", Toast.LENGTH_LONG ).show();

            return null;
        }
        return profileArray;
    }

    @Override
    protected void onPostExecute(ArrayList<User> shots) {
        super.onPostExecute(shots);

        if (shots == null) {
            Toast.makeText(mContext, "Invalid Login", Toast.LENGTH_LONG ).show();
        } else {
                Intent intent = new Intent(NavigationDrawerFragment.UPDATE_MY_PROFILE);
            mContext.sendBroadcast(intent);
        }
    }
}
