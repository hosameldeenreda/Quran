package com.example.xxxx.thehollyquran;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.CaptivePortal;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Hosam on 10/26/2018.
 */

public class Quran extends AppCompatActivity {
    ViewPager viewPager;
    public String fileName="";
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private Settings settings=new Settings(this) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fileName= getFilesDir().getPath().toString() + "/Name.txt";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quran);
        viewPager = findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
                try {
                RandomAccessFile file = new RandomAccessFile(fileName,"rw");
                file.seek(0);
                file.write(604-viewPager.getCurrentItem());
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                /*try {
                RandomAccessFile file = new RandomAccessFile(fileName,"rw");
                file.seek(0);
                file.write(604-viewPager.getCurrentItem());
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            }
            public void onPageSelected(int position) {
               /* try {
                RandomAccessFile file = new RandomAccessFile(fileName,"rw");
                    file.seek(0);
                    file.write(604-viewPager.getCurrentItem());
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            }
        });


        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                    goToindexOfSora();
                else if(position==1)
                    goToindexOfGoz2();
                else if(position==2)
                    saveSign();
                else if(position==3)
                    goToSign();
                else if(position==4)
                    settings.sharePage(604-viewPager.getCurrentItem());
                else if(position==5)
                    settings.rate();
                else if(position==6)
                    settings.moreApps();
                else if(position==7)
                    settings.share();


            }
        });/*
        try {
            viewPager.setOnClickListener(
                    new View.OnClickListener() {

                        @SuppressLint("WrongConstant")
                        @Override
                        public void onClick(View view) {
                           /* Button b1 = findViewById(R.id.button11);
                            Button b2 = findViewById(R.id.button10);
                            Button b3 = findViewById(R.id.button9);
                            Button b4 = findViewById(R.id.button12);

                            if (mDrawerList.getVisibility() == View.VISIBLE) {
                                mDrawerList.setVisibility(View.GONE);
                            } else {
                                mDrawerList.setVisibility(View.VISIBLE);
                            }

                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        String newString="1";
        try {
            RandomAccessFile file = new RandomAccessFile(fileName,"rw");
            file.seek(0);
            newString= String.valueOf(file.read());
            if(newString.equals("-1"))
                newString= "1";
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent current = getIntent();
        if (current != null && current.getStringExtra("page") != null) {
            newString= current.getStringExtra("page");
        }
        viewPager.setCurrentItem(604-Integer.valueOf(newString));
    }
    public void goToindexOfSora() {
        Intent intent=new Intent(this,IndexOfSora.class);
        startActivity(intent);
    }
    public void goToindexOfGoz2() {
        Intent intent=new Intent(this,IndexOfGoz2.class);
        startActivity(intent);
    }

    public void saveSign() {
        try {
            RandomAccessFile file = new RandomAccessFile(fileName,"rw");
            file.seek(0);
            file.read();
            file.write(604-viewPager.getCurrentItem());
            Toast.makeText(this,"تم حفظ الصفحة كعلامة",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void goToSign() {
        try {
            RandomAccessFile file = new RandomAccessFile(fileName,"rw");
            file.seek(0);
            file.read();
            viewPager.setCurrentItem(604-Integer.valueOf(file.read()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void addDrawerItems() {
        String[] osArray = { "فهرس السور", "فهرس الأجزاء", "حفظ كعلامة","الانتقال إلى العلامة","مشاركة الصفحة","قيمنا","تطبيقات أخرى","مشاركة التطبيق" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);

        mDrawerList.setAdapter(mAdapter);
    }

}
