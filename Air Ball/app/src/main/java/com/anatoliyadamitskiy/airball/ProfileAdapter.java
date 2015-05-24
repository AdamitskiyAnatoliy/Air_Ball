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


public class ProfileAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Shot> mObjects;
    private static final int ID_CONSTANT = 0x1000000;

    public ProfileAdapter(Context c, ArrayList<Shot> objects) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.profile_card_layout, parent, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Shot item = (Shot) getItem(position);

        holder.titleTextView.setText(item.getShotTitle());
        holder.authorTextView.setText("by " + item.getPlayerName());
        holder.mainImageView.setImageUrl(item.getImageUrl());

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
        public Button shareButtonMain;
        public SmartImageView mainImageView;

        public ViewHolder (View v) {

            titleTextView = (TextView)v.findViewById(R.id.profileTitleTextView);
            authorTextView = (TextView)v.findViewById(R.id.profileNameTextView);
            mainImageView = (SmartImageView)v.findViewById(R.id.profileImageView);
            shareButtonMain = (Button)v.findViewById(R.id.shareButtonProfile);

        }

    }

}
