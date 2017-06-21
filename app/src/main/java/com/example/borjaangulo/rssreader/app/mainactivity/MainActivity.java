package com.example.borjaangulo.rssreader.app.mainactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.borjaangulo.rssreader.R;
import com.example.borjaangulo.rssreader.app.detailactivity.DetailActivity;
import com.example.borjaangulo.rssreader.app.mainactivity.adapters.FeedListAdapter;
import com.example.borjaangulo.rssreader.model.RssNotice;
import com.example.borjaangulo.rssreader.utils.ImageUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainActivity,IMainActivity.OnFeedsLoaded {

    ListView lvFeeds;
    FeedListAdapter noticeArrayAdapter;
    IMainPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
        mPresenter.getFeeds(this);
    }

    @Override
    public void initViews(ArrayList<RssNotice> feedList) {
        lvFeeds = (ListView) findViewById(R.id.lvFeeds);
        noticeArrayAdapter = new FeedListAdapter(this,feedList);
        lvFeeds.setAdapter(noticeArrayAdapter);
        initListeners();
    }

    @Override
    public void setList(ArrayList<RssNotice> feedList) {
        mPresenter.setList(feedList);
    }

    private void initListeners() {
        lvFeeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(view.getContext(), DetailActivity.class);
                Bundle b = new Bundle();

                Bitmap bitmap = ((BitmapDrawable)((ImageView)view.findViewById(R.id.ivFeedImage)).getDrawable()).getBitmap();


                b.putString("title",((RssNotice)parent.getItemAtPosition(position)).getTitle());
                b.putString("description",((RssNotice)parent.getItemAtPosition(position)).getDescription());
                b.putString("image", ImageUtils.encodeToBase64(bitmap,Bitmap.CompressFormat.PNG,100));
                b.putString("link",((RssNotice)parent.getItemAtPosition(position)).getLink());
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}