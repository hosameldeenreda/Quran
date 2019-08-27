package com.example.Quran.thehollyquran;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

/**
 * Created by Hosam on 10/26/2018.
 */

public class Quran extends AppCompatActivity {
    static ViewPager viewPager;
    public String fileName="Quran";
    public String fileName2="QuranSaved";

    public static ListView mDrawerList;
    public ArrayAdapter<String> mAdapter;
    public Settings settings=new Settings(this) ;
    protected void onCreate(Bundle savedInstanceState) {
        fileName= getFilesDir().getPath().toString() + "/Quran.txt";
        fileName2= getFilesDir().getPath().toString() + "/QuranSaved.txt";
        final GestureDetector tapGestureDetector = new GestureDetector(this, new TapGestureListener());


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
                file.write(604-viewPager.getCurrentItem()); //save last page
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                try {
                    RandomAccessFile file = new RandomAccessFile(fileName,"rw");
                    file.seek(0);
                    file.write(604-viewPager.getCurrentItem()); //save last page
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            public void onPageSelected(int position) {

                try {
                    RandomAccessFile file = new RandomAccessFile(fileName,"rw");
                    file.seek(0);
                    file.write(604-viewPager.getCurrentItem()); //save last page
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });


        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                tapGestureDetector.onTouchEvent(event);
                return false;
            }
        });

        String newString="1";
        try {
            RandomAccessFile file = new RandomAccessFile(fileName,"rw");
            file.seek(0);
            newString= String.valueOf(file.read());
                if(newString.equals("-1")) {
                    newString= "1";// first time
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent current = getIntent();
        if (current != null && current.getStringExtra("page") != null) {
            newString= current.getStringExtra("page");
        }
        viewPager.setCurrentItem(604-Integer.valueOf(newString));
        //notification in friday
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 100, intent, 0);
        long t = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT&& t <= calendar.getTimeInMillis()) {
            alarmManager.setExact(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        }
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                    goToViewPager();
                else if(position==1)
                    goToindexOfSora();
                else if(position==2)
                    goToindexOfGoz2();
                else if(position==3)
                    saveSign();
                else if(position==4) {
                    goToViewPager();
                    goToSign();
                }
                else if(position==5)
                    settings.sharePage(604-viewPager.getCurrentItem());
                else if(position==6)
                    settings.rate();
                else if(position==7)
                    settings.moreApps();
                else if(position==8)
                    settings.share();
            }
        });

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
            RandomAccessFile file = new RandomAccessFile(fileName2,"rw");
            file.seek(0);
            file.read();
            file.write(604-viewPager.getCurrentItem());
            Toast.makeText(this,"تم حفظ الوجه",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void goToSign() {
        try {
            RandomAccessFile file = new RandomAccessFile(fileName2,"rw");
            file.seek(0);
            file.read();
            viewPager.setCurrentItem(604-Integer.valueOf(file.read()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void goToViewPager(){
        Quran.viewPager.setVisibility(View.VISIBLE);
        Quran.mDrawerList.setVisibility(View.INVISIBLE);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );
        Quran.mDrawerList.setLayoutParams(param);
    }
    private void addDrawerItems() {
        String[] osArray = {"الانتقال الى المصحف", "فهرس السور", "فهرس الأجزاء", "حفظ كعلامة","الانتقال إلى العلامة","مشاركة الوجه","قيمنا","تطبيقات أخرى","مشاركة التطبيق", };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }

}
class TapGestureListener extends GestureDetector.SimpleOnGestureListener{

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                0.0f
        );
        if(Quran.viewPager.getVisibility()==(View.VISIBLE)) {
            Quran.mDrawerList.setLayoutParams(param);
            Quran.viewPager.setVisibility(View.INVISIBLE);
            Quran.mDrawerList.setVisibility(View.VISIBLE);
        }
        return  false;
        // Your Code here
    }
}
