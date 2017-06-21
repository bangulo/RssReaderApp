package com.example.borjaangulo.rssreader.data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.borjaangulo.rssreader.app.mainactivity.IMainActivity;
import com.example.borjaangulo.rssreader.model.RssNotice;
import com.example.borjaangulo.rssreader.utils.XmlParser;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by borja.angulo on 21/06/2017.
 */

public class FeedsLoader extends AsyncTask<Void, Void, ArrayList<RssNotice>> {

    private final static String RSS_URL = "http://elpais.com/tag/rss/futbol/a/";
    private ArrayList<RssNotice> rssNoticesList;
    private IMainActivity.OnFeedsLoaded onFeedsLoaded;

    public FeedsLoader(IMainActivity.OnFeedsLoaded onFeedsLoaded) {
        this.onFeedsLoaded = onFeedsLoaded;
    }

    @Override
    protected ArrayList<RssNotice> doInBackground(Void... params) {
        try{
            URL url = new URL(RSS_URL);
            InputStream inputStream = url.openConnection().getInputStream();
            rssNoticesList = XmlParser.getListFromXml(inputStream);
            return rssNoticesList;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<RssNotice> rssNotices) {
        onFeedsLoaded.setList(rssNotices);
    }
}
