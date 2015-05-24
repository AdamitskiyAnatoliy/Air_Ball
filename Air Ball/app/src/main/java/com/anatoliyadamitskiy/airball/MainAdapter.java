package com.anatoliyadamitskiy.airball;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class MainAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Shot> mObjects;
    private static final int ID_CONSTANT = 0x1000000;

    public MainAdapter(Context c, ArrayList<Shot> objects) {
        mContext = c;
        mObjects = objects;
    }

    @Override
    public int getCount() {
        if(mObjects != null) {
            return mObjects.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if(mObjects != null && position < mObjects.size() && position >= 0) {
            return mObjects.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.main_card_layout, parent, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Shot item = (Shot) getItem(position);

        holder.titleTextView.setText(item.getShotTitle());
        holder.authorTextView.setText("by " + item.getPlayerName());
        holder.mainImageView.setImageUrl(item.getImageUrl());
        holder.avatarImageView.setRadius(150);
        holder.avatarImageView.setImageUrl(item.getAvatarUrl());

        holder.mainImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MoreInfoActivity.class);
                intent.putExtra("Shot", item);
                mContext.startActivity(intent);

            }
        });

        holder.likeButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Saved to Favorites", Toast.LENGTH_LONG).show();
                FileStorage.saveFavorites(mContext, "Favorites", item);
            }
        });

        holder.shareButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mObjects.get(position).getImageUrl()));
                mContext.startActivity(intent);

            }
        });

        return convertView;
    }


    static class ViewHolder {

        public TextView titleTextView;
        public TextView authorTextView;
        public Button likeButtonMain;
        public Button shareButtonMain;
        public SmartImageView mainImageView;
        public RoundedCornersSmartImageView avatarImageView;

        public ViewHolder (View v) {

            titleTextView = (TextView)v.findViewById(R.id.mainTitleTextView);
            authorTextView = (TextView)v.findViewById(R.id.mainNameTextView);
            mainImageView = (SmartImageView)v.findViewById(R.id.mainImageView);
            avatarImageView = (RoundedCornersSmartImageView)v.findViewById(R.id.avatarImageView);
            likeButtonMain = (Button)v.findViewById(R.id.likeButtonMain);
            shareButtonMain = (Button)v.findViewById(R.id.shareButtonMain);

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
