package com.example.borjaangulo.rssreader.utils;

import android.util.Log;
import android.util.Xml;

import com.example.borjaangulo.rssreader.model.RssNotice;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by borja.angulo on 21/06/2017.
 */

public class XmlParser {
    public static ArrayList<RssNotice> getListFromXml(InputStream inputStream){

        String title = null;
        String description = null;
        String time = null;
        String link = null;
        boolean isItem = false;

        RssNotice item ;

        ArrayList<RssNotice> items = new ArrayList<>();

        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);

            xmlPullParser.nextTag();
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                item = new RssNotice();
                int eventType = xmlPullParser.getEventType();

                String name = xmlPullParser.getName();
                if (name == null)
                    continue;

                if (eventType == XmlPullParser.END_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItem = false;
                    }
                    continue;
                }

                if (eventType == XmlPullParser.START_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItem = true;
                        continue;
                    }
                }

                Log.d("MyXmlParser", "Parsing name ==> " + name);
                String result = "";
                String url = null;
                if(xmlPullParser.getAttributeCount() > 0){
                    url = xmlPullParser.getAttributeValue(null,"url");
                }
                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    result = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                }


                if (name.equalsIgnoreCase("title")) {
                    title = result;
                } else if (name.equalsIgnoreCase("description")) {
                    description = result;
                }  else if (name.equalsIgnoreCase("pubDate")) {
                    time = result;
                }else if (name.equalsIgnoreCase("link")) {
                    link = result;
                }

                if (title != null && description != null && url != null && time != null && link != null) {
                    if(isItem) {
                        item = new RssNotice();
                        item.setTitle(title);
                        item.setDescription(description);
                        item.setDate(DateUtils.getTimeInMillis(time.trim()));
                        item.setLink(link);
                        if(url != null) {
                            item.setImageUrl(url);
                        }else{
                            item.setImageUrl(null);
                        }
                        items.add(item);
                    }

                    title = null;
                    description = null;
                    time = null;
                    link = null;
                    isItem = false;
                }
            }

            return items;

        }catch (Exception e){
            return null;
        }
    }
}
