package service.nextoneday.com.aidlservice;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {

    private Uri mUri;

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


    }


}
