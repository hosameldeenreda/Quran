package com.example.xxxx.thehollyquran;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Hosam on 10/15/2018.
 */

public class IndexOfGoz2 extends AppCompatActivity {
    Settings settings =new Settings(this);
    private TableLayout mTableLayout;
    ProgressDialog mProgressBar;
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.MoreApps)
            settings.moreApps();
        else if(item.getItemId()==R.id.RateMe) {
            try {
                settings.rate();
            }
            catch (ActivityNotFoundException a){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" +getPackageName())));
            }
        }
        else if(item.getItemId()==R.id.Share) {
            settings.share();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_of_goz2);
        /*TextView textView=(TextView)findViewById(R.id.textview4);
        Typeface myFont=Typeface.createFromAsset(getAssets(),"fonts/mcs_3.ttf");
        textView.setTypeface(myFont);
        textView=(TextView)findViewById(R.id.textview3);
        textView.setTypeface(myFont);
        textView=(TextView)findViewById(R.id.textview2);
        textView.setTypeface(myFont);
        textView=(TextView)findViewById(R.id.textview);
        textView.setTypeface(myFont);*/
        mProgressBar = new ProgressDialog(this);
        mTableLayout = (TableLayout) findViewById(R.id.main2_table);
        mTableLayout.setStretchAllColumns(true);
        startLoadData();
    }
    public void startLoadData() {
       /* mProgressBar.setCancelable(false);
        mProgressBar.setMessage("Fetching Invoices..");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();*/
        loadData();
    }
    public void loadData() {
        Typeface myFont=Typeface.createFromAsset(getAssets(),"fonts/mcs_3.ttf");

        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;
        int textSize = 0, smallTextSize =0, mediumTextSize = 0;

        textSize = 45;
        smallTextSize = 45;

        ListGoz2 listGoz2 = new ListGoz2();
        Goz2[] data = listGoz2.getList();


        int rows = data.length;
        getSupportActionBar().setTitle("فهرس الأجزاء");
        TextView textSpacer = null;

        mTableLayout.removeAllViews();

        // -1 means heading row
        for(int i = -1; i < rows; i ++) {
            Goz2 row = null;
            if (i > -1)
                row = data[i];
            else {
                textSpacer = new TextView(this);
                textSpacer.setText("");
            }
            // data columns
            final TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            tv.setGravity(Gravity.CENTER);

            tv.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv.setText("رقم الصفحة");
                tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                tv.setTypeface(myFont);

            } else {
                tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv.setText(replace(String.valueOf(row.getFirst())));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv.setTypeface(myFont);
            }
            final TextView tv2 = new TextView(this);
            if (i == -1) {
                tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            tv2.setGravity(Gravity.CENTER);

            tv2.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv2.setText("الجزء");
                tv2.setBackgroundColor(Color.parseColor("#f7f7f7"));
                tv2.setTypeface(myFont);
            }else {
                tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                tv2.setTextColor(Color.parseColor("#000000"));
                tv2.setText(row.getName());
            }


            final LinearLayout layAmounts = new LinearLayout(this);
            layAmounts.setOrientation(LinearLayout.VERTICAL);
            layAmounts.setGravity(Gravity.RIGHT);
            layAmounts.setPadding(0, 10, 0, 10);
            layAmounts.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT));

            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setPadding(0,0,0,0);
            tr.setLayoutParams(trParams);
            tr.addView(tv);
            tr.addView(tv2);
            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView sample = (TextView) tr.getChildAt(0);
                    Intent intent=new Intent(IndexOfGoz2.this,Quran.class);
                    intent.putExtra("page",sample.getText().toString());
                    startActivity(intent);
                }
            });

            mTableLayout.addView(tr);

        }
    }
    String replace(String string){
        string=string.replace("1","١")
                .replace("2","٢")
                .replace("3","٣")
                .replace("4","٤")
                .replace("5","٥")
                .replace("6","٦")
                .replace("7","٧")
                .replace("8","٨")
                .replace("9","٩")
                .replace("0","٠");
        return string;
    }


    //////////////////////////////////////////////////////////////////////////////

    //
    // The params are dummy and not used
    //
    /*class LoadDataTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {

            try {
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Task Completed.";
        }
       /* @Override
        protected void onPostExecute(String result) {
            mProgressBar.hide();
            loadData();
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Integer... values) {

        }
    }*/


}