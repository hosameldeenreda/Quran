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

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Hosam on 10/15/2018.
 */

public class IndexOfSora extends AppCompatActivity {
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
        setContentView(R.layout.index_of_sora);
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
        mTableLayout = (TableLayout) findViewById(R.id.main_table);
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

        ListSora listSora = new ListSora();
        Sora[] data = listSora.getList();


        int rows = data.length;
        getSupportActionBar().setTitle("فهرس أسماء السور");

        TextView textSpacer = null;

        mTableLayout.removeAllViews();

        // -1 means heading row
        for(int i = -1; i < rows; i ++) {
            Sora row = null;
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

            tv.setGravity(Gravity.LEFT);

            tv.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv.setText("البيان");
                tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                 tv.setTypeface(myFont);

            } else {
                tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
                if(row.isStatement()==true)
                    tv.setText("مكية");
                else
                    tv.setText("مدنية");

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

            tv2.setGravity(Gravity.LEFT);

            tv2.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv2.setText("الصفحة");
                tv2.setBackgroundColor(Color.parseColor("#f7f7f7"));
                tv2.setTypeface(myFont);
            }else {
                tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                tv2.setTextColor(Color.parseColor("#000000"));
                tv2.setText(replace(String.valueOf(row.getFirst())));
            }


            final LinearLayout layCustomer = new LinearLayout(this);
            layCustomer.setOrientation(LinearLayout.VERTICAL);
            layCustomer.setPadding(0, 10, 0, 10);
            layCustomer.setBackgroundColor(Color.parseColor("#f8f8f8"));

            final TextView tv3 = new TextView(this);
            if (i == -1) {
                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv3.setPadding(5, 5, 0, 5);
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv3.setPadding(5, 0, 0, 5);
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            tv3.setGravity(Gravity.TOP);


            if (i == -1) {
                tv3.setText("رقمها");
                tv3.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv3.setTypeface(myFont);

            } else {
                tv3.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv3.setTextColor(Color.parseColor("#000000"));
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                tv3.setText(replace(String.valueOf(row.getNumber())));
            }
            layCustomer.addView(tv3);



            final LinearLayout layAmounts = new LinearLayout(this);
            layAmounts.setOrientation(LinearLayout.VERTICAL);
            layAmounts.setGravity(Gravity.RIGHT);
            layAmounts.setPadding(0, 10, 0, 10);
            layAmounts.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT));



            final TextView tv4 = new TextView(this);
            if (i == -1) {
                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv4.setPadding(5, 5, 1, 5);
                layAmounts.setBackgroundColor(Color.parseColor("#f7f7f7"));
            } else {
                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv4.setPadding(5, 0, 1, 5);
                layAmounts.setBackgroundColor(Color.parseColor("#ffffff"));
            }

            tv4.setGravity(Gravity.RIGHT);

            if (i == -1) {
                tv4.setText("السورة");
                tv4.setBackgroundColor(Color.parseColor("#f7f7f7"));
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                tv4.setTypeface(myFont);
            } else {
                tv4.setBackgroundColor(Color.parseColor("#ffffff"));
                tv4.setTextColor(Color.parseColor("#000000"));
                tv4.setText(row.getName());
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv4.setTypeface(myFont);
            }

            layAmounts.addView(tv4);




            // add table row
            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setPadding(0,0,0,0);
            tr.setLayoutParams(trParams);



            tr.addView(tv);
            tr.addView(tv2);
            tr.addView(layCustomer);
            tr.addView(layAmounts);

            if (i > -1) {

                tr.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        TableRow tr = (TableRow) v;
                        //do whatever action is needed

                    }
                });


            }
            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView sample = (TextView) tr.getChildAt(1);
                    Intent intent=new Intent(IndexOfSora.this,Quran.class);
                    intent.putExtra("page",sample.getText().toString());
                    startActivity(intent);
                }
            });

            mTableLayout.addView(tr, trParams);
            if (i > -1) {
                // add separator row
                final TableRow trSep = new TableRow(this);
                TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

                trSep.setLayoutParams(trParamsSep);
                TextView tvSep = new TextView(this);
                TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tvSepLay.span = 4;
                tvSep.setLayoutParams(tvSepLay);
                tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                tvSep.setHeight(1);
                trSep.addView(tvSep);



                mTableLayout.addView(trSep, trParamsSep);
            }

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