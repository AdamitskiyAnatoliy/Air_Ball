package com.anatoliyadamitskiy.airball;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Anatoliy on 2/25/15.
 */
public class DribbbleDownload2 extends AsyncTask<String, Void, ArrayList<Shot>> {

    Context mContext;
    String category;
    int page;

    public DribbbleDownload2(Context _context, String _category)
    {
        mContext = _context;
        category = _category;
        page = 1;
    }

    @Override
    protected ArrayList<Shot> doInBackground(String... params) {

        ArrayList<Shot> shotsArray = new ArrayList<>();
        URL url = null;
        try {
            url = new URL("http://www.google.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {

            switch (category) {
                case "Popular":
                    url = new URL("https://api.dribbble.com/v1/shots?access_token=115715a8e2ac13850d7562" +
                            "a30b0c792b4de51378a331412e155b525913c4ce8b&page=" + page);
                    break;
                case "Debuts":
                    url = new URL("https://api.dribbble.com/v1/shots?access_token=115715a8e2ac13850d7562" +
                            "a30b0c792b4de51378a331412e155b525913c4ce8b&page=" + page + "&list=debuts");
                    break;
                case "Rebounds":
                    url = new URL("https://api.dribbble.com/v1/shots?access_token=115715a8e2ac13850d7562" +
                            "a30b0c792b4de51378a331412e155b525913c4ce8b&page=" + page + "&list=rebounds");
                    break;
                case "Playoffs":
                    url = new URL("https://api.dribbble.com/v1/shots?access_token=115715a8e2ac13850d7562" +
                            "a30b0c792b4de51378a331412e155b525913c4ce8b&page=" + page + "&list=playoffs");
                    break;
                default:
                    break;
            }


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            String data = IOUtils.toString(is);
            is.close();

            JSONArray dataArray = new JSONArray(data);

            for (int i = 0; i < dataArray.length(); ++i) {

                JSONObject shot = dataArray.getJSONObject(i);
                String title = shot.getString("title");
                String name = shot.getJSONObject("user").getString("name");
                String avatarUrl = shot.getJSONObject("user").getString("avatar_url");
                String webUrl = shot.getString("html_url");
                String commentsUrl = shot.getString("comments_url");
                String imageUrl = shot.getJSONObject("images").getString("normal");

                shotsArray.add(new Shot(webUrl, imageUrl, avatarUrl, commentsUrl, name, title));
            }

            FileStorage.saveShots(mContext, category, shotsArray);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
        return shotsArray;
    }

    @Override
    protected void onPostExecute(ArrayList<Shot> recipes) {
        super.onPostExecute(recipes);

        Intent intent = new Intent(MainActivity.PlaceholderFragment.UPDATE_MAIN);
        intent.putExtra("Category", category);
        mContext.sendBroadcast(intent);
    }
}
