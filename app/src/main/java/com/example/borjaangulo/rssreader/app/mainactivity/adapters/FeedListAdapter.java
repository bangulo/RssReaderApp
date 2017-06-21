package com.example.borjaangulo.rssreader.app.mainactivity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.borjaangulo.rssreader.R;
import com.example.borjaangulo.rssreader.data.ImageLoader;
import com.example.borjaangulo.rssreader.model.RssNotice;

import java.util.ArrayList;

/**
 * Created by borja.angulo on 21/06/2017.
 */

public class FeedListAdapter extends ArrayAdapter<RssNotice> {

    private ArrayList<RssNotice> rssNotices;

    public FeedListAdapter(Context context, ArrayList<RssNotice> rssNotices) {
        super(context, 0, rssNotices);
        this.rssNotices = rssNotices;
    }

    static class ViewHolder {
        ImageView ivFeedImage;
        TextView ivFeedTitle;
        TextView ivFeedDescription;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_rss_notice, parent, false);
            holder = new ViewHolder();
            holder.ivFeedImage = (ImageView) convertView.findViewById(R.id.ivFeedImage);
            holder.ivFeedTitle = (TextView) convertView.findViewById(R.id.tvFeedTitle);
            holder.ivFeedDescription = (TextView) convertView.findViewById(R.id.tvFeedDescription);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        new ImageLoader(holder.ivFeedImage, getContext().getApplicationContext()).execute(rssNotices.get(position).getImageUrl());
        holder.ivFeedTitle.setText(rssNotices.get(position).getTitle());
        holder.ivFeedDescription.setText(rssNotices.get(position).getDescription());

        return convertView;
    }
}
