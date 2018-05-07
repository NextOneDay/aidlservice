package service.nextoneday.com.aidlservice;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by nextonedaygg on 2018/5/6.
 */

public class HDMService {

    private ContentResolver mResolver;
    private Uri mUri;

    public void getInstance(Context context){

        mResolver = context.getContentResolver();
        mUri = Uri.parse("");

    }

    public void  registerObserver(HDMObserver observer){

    }

    public void unRegisterOberver(HDMObserver observer){

    }

    public void DHMIntSet(String key,int value) throws  Exception{
        ContentValues values = new ContentValues();
        values.put("key",key);
        values.put("value",value);
        mResolver.insert(mUri,values);
    }


    public void DHMStrGet(String key) throws  Exception{

        Cursor query = mResolver.query(mUri, new String[]{"key,value"}, null, new String[]{key}, null);
        while (query!=null && query.moveToNext()){
            query.getString(0); //拿到key的值
            query.getString(1); // 拿到value的值
        }
    }


    public void DHMLogAdd(String logLevel,String log) throws  Exception{
        ContentValues values = new ContentValues();
        values.put("logLevel",logLevel);
        values.put("log",log);
        mResolver.insert(mUri,values);
    }

    public void DHMClearLog(String logLevel, long startTime ,long endTime, String packageName){

        mResolver.delete(mUri,"packageName=?",new String []{packageName});
    }
}
