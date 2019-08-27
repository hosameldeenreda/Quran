package com.example.Quran.thehollyquran;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


/**
 * Created by Hosam on 10/15/2018.
 */

public class IndexOfSora extends AppCompatActivity {
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
        setContentView(R.layout.index_of_sora);
        mProgressBar = new ProgressDialog(this);
        mTableLayout = (TableLayout) findViewById(R.id.main_table);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mTableLayout.setStretchAllColumns(true);
        loadData();
    }
    public void loadData() {
        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/mcs_3.ttf");
        int textSize = 45,paddingDp = 25;
        float density = this.getResources().getDisplayMetrics().density;
        int paddingPixel = (int) (paddingDp * density / 2);
        ListSora listSora = new ListSora();
        Sora[] data = listSora.getList();
        int rows = data.length;
        getSupportActionBar().setTitle("فهرس أسماء السور");
        mTableLayout.removeAllViews();
        // -1 means heading row
        for (int i = -1; i < rows; i++) {
            Sora row = null;
            if (i > -1)
                row = data[i];
            // data columns
            TextView tv = new TextView(this);
            TextView tv2 = new TextView(this);
            TextView tv3 = new TextView(this);
            TextView tv4 = new TextView(this);

            tv.setGravity(Gravity.CENTER);
            tv2.setGravity(Gravity.CENTER);
            tv3.setGravity(Gravity.CENTER);
            tv4.setGravity(Gravity.CENTER);

            tv.setPadding(5, paddingPixel, 0, paddingPixel);
            tv2.setPadding(5, paddingPixel, 0, paddingPixel);
            tv3.setPadding(5, paddingPixel, 0, paddingPixel);
            tv4.setPadding(5, paddingPixel, 0, paddingPixel);


            if (i == -1) {
                tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv2.setBackgroundColor(Color.parseColor("#f7f7f7"));
                tv3.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv4.setBackgroundColor(Color.parseColor("#f7f7f7"));

                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

                tv.setText("البيان");
                tv2.setText("الصفحة");
                tv3.setText("رقمها");
                tv4.setText("السورة");

                tv.setTypeface(myFont);
                tv2.setTypeface(myFont);
                tv3.setTypeface(myFont);
                tv4.setTypeface(myFont);

            }
            else {
                tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                tv3.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv4.setBackgroundColor(Color.parseColor("#ffffff"));

                if (row.isStatement() == true)
                    tv.setText("مكية");
                else
                    tv.setText("مدنية");
                tv2.setText(replace(String.valueOf(row.getFirst())));
                tv3.setText(replace(String.valueOf(row.getNumber())));
                tv4.setText(row.getName());

                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

                tv.setTextColor(Color.parseColor("#000000"));
                tv2.setTextColor(Color.parseColor("#000000"));
                tv3.setTextColor(Color.parseColor("#000000"));
                tv4.setTextColor(Color.parseColor("#000000"));

            }
            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            tr.addView(tv);
            tr.addView(tv2);
            tr.addView(tv3);
            tr.addView(tv4);
            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView sample = (TextView) tr.getChildAt(1);
                    if (!sample.getText().toString().equals("الصفحة")) {
                        Intent intent = new Intent(IndexOfSora.this, Quran.class);
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