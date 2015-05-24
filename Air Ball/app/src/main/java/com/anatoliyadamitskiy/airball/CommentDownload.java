package com.anatoliyadamitskiy.airball;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.anatoliyadamitskiy.airball.Shot;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Anatoliy on 3/14/15.
 */
public class CommentDownload extends AsyncTask<String, Void, ArrayList<Comment>> {

    Context mContext;
    String comments;

    public CommentDownload(Context _context, String url)
    {
        mContext = _context;
        comments = url + "?access_token=115715a8e2ac13850d7562a30b0c792b4de51378a331412e155b525913c4ce8b";
    }

    @Override
    protected ArrayList<Comment> doInBackground(String... params) {

        ArrayList<Comment> commentsArray = new ArrayList<>();

        try {

            URL url = new URL(comments);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            String data = IOUtils.toString(is);
            is.close();

            JSONArray dataArray = new JSONArray(data);

            for (int i = 0; i < dataArray.length(); ++i) {

                JSONObject shot = dataArray.getJSONObject(i);
                String com = shot.getString("body");
                String name = shot.getJSONObject("user").getString("name");
                String avatarUrl = shot.getJSONObject("user").getString("avatar_url");

                commentsArray.add(new Comment(com, avatarUrl, name));
            }

            FileStorage.saveComments(mContext, commentsArray);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
        return commentsArray;
    }

    @Override
    protected void onPostExecute(ArrayList<Comment> shots) {
        super.onPostExecute(shots);

        Intent intent = new Intent(MoreInfoActivity.UPDATE_COMMENTS);
        mContext.sendBroadcast(intent);
    }

}
