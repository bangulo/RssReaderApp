package com.example.borjaangulo.rssreader.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;

import com.example.borjaangulo.rssreader.R;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by borja.angulo on 21/06/2017.
 */

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    Context context;

    public ImageLoader(ImageView bmImage,Context context) {
        this.bmImage = bmImage;
        this.context = context;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        if (urldisplay != null) {
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                return mIcon11;
            } catch (Exception e) {
                Log.e("ErrorLoadingImage", "error loading image");
                return null;
            }
        } else {
            return null;
        }
    }

    protected void onPostExecute(Bitmap result) {
        if(result != null){
            bmImage.setImageBitmap(result);
        }
    }
}
