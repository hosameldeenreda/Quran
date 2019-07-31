package com.example.xxxx.thehollyquran;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;


public class Settings {
    public Context context;
    Settings(Context c){
        context=c;
    }
    public void moreApps(){
        Intent moreapps=new Intent(Intent.ACTION_VIEW);
        moreapps.setData(Uri.parse("https://play.google.com/store/apps/developer?id=Hosam Eldeen Reda"));
        context.startActivity(moreapps);
    }
    public void rate(){
            Intent rateapps = new Intent(Intent.ACTION_VIEW);
            rateapps.setData(Uri.parse("market://details?id=" + context.getPackageName()));
            context.startActivity(rateapps);
    }
    public void share(){
        Intent Share=new Intent(Intent.ACTION_SEND);
        Share.setType("text/plain");
        Share.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+ context.getPackageName());
        Intent.createChooser(Share,"shareapp");
        context.startActivity(Intent.createChooser(Share,"Share using "));
    }
    public void sharePage(int page){
        Uri imageUri = Uri.parse("android.resource://" + context.getPackageName()
                + "/drawable/" + "a"+String.valueOf(page));
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello");
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/jpeg");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(shareIntent, "send"));

    }

}
