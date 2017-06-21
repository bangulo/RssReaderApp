package com.example.borjaangulo.rssreader.app.detailactivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.borjaangulo.rssreader.R;
import com.example.borjaangulo.rssreader.utils.ImageUtils;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvTitleDetail;
    TextView tvDescriptionDetail;
    ImageView ivImageDetail;
    Button goToWeb;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();

    }

    private void initViews() {
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        url = b.getString("link");

        goToWeb = (Button) findViewById(R.id.goToWeb);
        goToWeb.setOnClickListener(this);

        tvTitleDetail = (TextView) findViewById(R.id.tvTitleDetail);
        tvTitleDetail.setText(b.getString("title"));
        tvDescriptionDetail = (TextView) findViewById(R.id.tvDescriptionDetail);
        tvDescriptionDetail.setText((b.getString("description")));
        ivImageDetail = (ImageView) findViewById(R.id.ivImageDetail);
        ivImageDetail.setImageBitmap(ImageUtils.getBitmapFromBase64(b.getString("image")));

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
