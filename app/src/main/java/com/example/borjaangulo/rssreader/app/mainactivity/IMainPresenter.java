package com.example.borjaangulo.rssreader.app.mainactivity;

import com.example.borjaangulo.rssreader.model.RssNotice;

import java.util.ArrayList;

/**
 * Created by borja.angulo on 21/06/2017.
 */

public interface IMainPresenter {


    void getFeeds(IMainActivity.OnFeedsLoaded onFeedsLoaded);

    void setList(ArrayList<RssNotice> feedList);
}
