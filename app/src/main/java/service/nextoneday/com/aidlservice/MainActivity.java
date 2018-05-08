package service.nextoneday.com.aidlservice;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUri = Uri.parse("content://service.nextoneday.com.aidlservice.hmd.provider/vartable");
        ContentValues values = new ContentValues();


        values.put("time",System.currentTimeMillis());
        values.put("packageName",getPackageName());
        values.put("key","444");
        values.put("value","我是数据");

        getContentResolver().insert(mUri,values);

        observer();
    }

    private Uri mUri;
    private ContentResolver mContentResolver;
    private HMLContentObserver mObserver;
    private void observer() {
        mObserver = new HMLContentObserver(null);
        mContentResolver = getContentResolver();
        mContentResolver.registerContentObserver(mUri, true, mObserver);
    }

    private  class HMLContentObserver extends  ContentObserver{

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public HMLContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Log.d(TAG,"插入数据更新通知");
        }
    }

}
