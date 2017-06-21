package com.example.borjaangulo.rssreader.app.mainactivity;

import com.example.borjaangulo.rssreader.model.RssNotice;

import java.util.ArrayList;

/**
 * Created by borja.angulo on 21/06/2017.
 */

public interface IMainActivity {


    void initViews(ArrayList<RssNotice> feedList);

    interface OnFeedsLoaded {
        void setList(ArrayList<RssNotice> feedList);
    }

}
