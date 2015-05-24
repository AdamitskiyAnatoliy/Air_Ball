package com.anatoliyadamitskiy.airball;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
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

/**
 * Created by Anatoliy on 3/14/15.
 */
public class CommentAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Comment> mObjects;
    private static final int ID_CONSTANT = 0x1000000;

    public CommentAdapter(Context c, ArrayList<Comment> objects) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.comment_layout, parent, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Comment item = (Comment) getItem(position);

        holder.nameTextView.setText(item.getName());
        Spanned newComment = Html.fromHtml(item.getComment());
        holder.commentTextView.setText(newComment);
        holder.mainImageView.setRadius(75);
        holder.mainImageView.setImageUrl(item.getAvatarUrl());

        return convertView;
    }


    static class ViewHolder {

        public TextView nameTextView;
        public TextView commentTextView;
        public RoundedCornersSmartImageView mainImageView;

        public ViewHolder (View v) {

            nameTextView = (TextView)v.findViewById(R.id.commentName);
            commentTextView = (TextView)v.findViewById(R.id.comment);
            mainImageView = (RoundedCornersSmartImageView)v.findViewById(R.id.commentAvatarImageView);
        }
    }
}
