package com.example.borjaangulo.rssreader.utils;

import com.example.borjaangulo.rssreader.model.RssNotice;

import java.util.Comparator;
import java.util.Locale;

/**
 * Created by borja.angulo on 21/06/2017.
 */

public class ComparatorTime implements Comparator<RssNotice> {

    @Override
    public int compare(RssNotice o1, RssNotice o2) {
        return ((Long)o2.getDate()).compareTo((Long)o1.getDate());
    }
}
