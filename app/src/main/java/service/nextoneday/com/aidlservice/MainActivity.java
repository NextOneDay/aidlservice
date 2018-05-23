package service.nextoneday.com.aidlservice;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        ConcurrentHashMap

        //获取ContentResolver
        ContentResolver contentResolver = getContentResolver();
        //查询数据，返回Cursor
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();


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
