package com.example.Quran.thehollyquran;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

/**
 * Created by Hosam on 10/15/2018.
 */

public class IndexOfGoz2 extends AppCompatActivity {
    Settings settings = new Settings(this);
    private TableLayout mTableLayout;
    ProgressDialog mProgressBar;
    private AdView mAdView;

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.MoreApps)
            settings.moreApps();
        else if (item.getItemId() == R.id.RateMe) {
            try {
                settings.rate();
            } catch (ActivityNotFoundException a) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
            }
        } else if (item.getItemId() == R.id.Share) {
            settings.share();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_of_goz2);
        mProgressBar = new ProgressDialog(this);
        mTableLayout = (TableLayout) findViewById(R.id.main2_table);
        mTableLayout.setStretchAllColumns(true);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        loadData();
    }
    public void loadData() {
        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/mcs_3.ttf");

        int textSize = 45,paddingDp = 25;
        float density = this.getResources().getDisplayMetrics().density;
        int paddingPixel = (int) (paddingDp * density/2);
        ListGoz2 listGoz2 = new ListGoz2();
        Goz2[] data = listGoz2.getList();
        int rows = data.length;
        getSupportActionBar().setTitle("فهرس الأجزاء");
        mTableLayout.removeAllViews();
        mTableLayout.bringToFront();
        // -1 means heading row
        for (int i = -1; i < rows; i++) {
            Goz2 row = null;
            if (i > -1)
                row = data[i];

            TextView tv = new TextView(this);
            TextView tv2 = new TextView(this);

            tv.setGravity(Gravity.CENTER);
            tv2.setGravity(Gravity.CENTER);

            tv.setPadding(5, paddingPixel, 0, paddingPixel);
            tv2.setPadding(5, paddingPixel, 0, paddingPixel);


            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

            if (i == -1) {
                tv.setText("رقم الصفحة");
                tv2.setText("الجزء");

                tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv2.setBackgroundColor(Color.parseColor("#f7f7f7"));

                tv.setTypeface(myFont);
                tv2.setTypeface(myFont);

            }
            else {
                tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv2.setBackgroundColor(Color.parseColor("#ffffff"));

                tv.setTextColor(Color.parseColor("#000000"));
                tv2.setTextColor(Color.parseColor("#000000"));

                tv.setText(replace(String.valueOf(row.getFirst())));
                tv2.setText(row.getName());
            }
            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            tr.setPadding(0, 0, 0, 0);
            tr.addView(tv);
            tr.addView(tv2);
            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView sample = (TextView) tr.getChildAt(0);
                    if(!sample.getText().toString().equals("رقم الصفحة")) {
                        Intent intent = new Intent(IndexOfGoz2.this, Quran.class);
                        intent.putExtra("page", sample.getText().toString());
                        startActivity(intent);
                    }
                }
            });

            mTableLayout.addView(tr);
        }
    }

    String replace(String string) {
        string = string.replace("1", "١")
                .replace("2", "٢")
                .replace("3", "٣")
                .replace("4", "٤")
                .replace("5", "٥")
                .replace("6", "٦")
                .replace("7", "٧")
                .replace("8", "٨")
                .replace("9", "٩")
                .replace("0", "٠");
        return string;
    }
}