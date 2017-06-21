package com.example.borjaangulo.rssreader.app.mainactivity;

import com.example.borjaangulo.rssreader.data.FeedsLoader;
import com.example.borjaangulo.rssreader.model.RssNotice;
import com.example.borjaangulo.rssreader.utils.ComparatorTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by borja.angulo on 21/06/2017.
 */

public class MainPresenter implements IMainPresenter {

    private IMainActivity mView;


    public MainPresenter(IMainActivity mView) {
        this.mView = mView;
    }

    @Override
    public void getFeeds(IMainActivity.OnFeedsLoaded onFeedsLoaded) {
        new FeedsLoader(onFeedsLoaded).execute();
    }

    @Override
    public void setList(ArrayList<RssNotice> feedList) {
        if(feedList != null){
            Collections.sort(feedList, new ComparatorTime());
            mView.initViews(feedList);
        }else{
            mView.initViews(new ArrayList<RssNotice>());
        }
    }
}
