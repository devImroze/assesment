package com.example.assesment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imageViewPort;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setTitle(getIntent().getStringExtra(getString(R.string.title)));

        imageViewPort = findViewById(R.id.imageViewPort);
        textView = findViewById(R.id.htmlContent);
        Picasso.get().load(getIntent().getStringExtra(getString(R.string.image))).fit().into(imageViewPort);

        String content = Html.fromHtml(getIntent().getStringExtra("content").replace("<![CDATA[", "")).toString();
        textView.setText(content.substring(content.indexOf('\n')+1));
    }
}