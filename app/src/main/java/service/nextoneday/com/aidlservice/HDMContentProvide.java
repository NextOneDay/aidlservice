package service.nextoneday.com.aidlservice;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static java.util.Calendar.getInstance;


/**
 * Created by nextonedaygg on 2018/5/5.
 *
 * crud 均运行在contentProvider的进程中，在binder线程池中
 */

public class HDMContentProvide extends ContentProvider {


    public static final String AUTHORITY = "service.nextoneday.com.aidlservice.hmd.provider";
    public static final Uri VARTABLE_URI= Uri.parse("content://"+AUTHORITY+"/vartable");
    public static final Uri LOGTABLE_URI= Uri.parse("content://"+AUTHORITY+"/logtable");

    public static final int VARTABLE_URI_CODE= 0;
    public static final int LOGTABLE_URI_CODE= 1;
    private  static  final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        sUriMatcher.addURI(AUTHORITY,"vartable",VARTABLE_URI_CODE);
        sUriMatcher.addURI(AUTHORITY,"logtable",LOGTABLE_URI_CODE);
    }

    private DBHelper mDbHelper;
    private MyOpenHelper mMyOpenHelper;


    private String getTableName(Uri uri){
        String tableName =null;
        switch (sUriMatcher.match(uri)){
            case VARTABLE_URI_CODE:
                tableName= "数据库表";
                break;
            case LOGTABLE_URI_CODE:
                break;
        }
        return tableName;
    }

    /**
     * 初始化
     * @return
     */
    @Override
    public boolean onCreate() {

        mDbHelper = new DBHelper(getContext());
        mMyOpenHelper = new MyOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        String tableName = getTableName(uri);
        if(tableName==null){
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        mDbHelper.query(uri,projection,selection,selectionArgs,sortOrder);
        return  mMyOpenHelper.getReadableDatabase().query(tableName,projection,selection,selectionArgs,null,null,sortOrder);

    }

    /**
     * 用来返回一个uri请求所对应的MIME类型。 比如图片，视频等
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String tableName = getTableName(uri);
        if(tableName==null){
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        mDbHelper.insert(uri,values);
        mMyOpenHelper.getWritableDatabase().insert(tableName,null,values);
        getContext().getContentResolver().notifyChange(uri,null);

        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        String tableName = getTableName(uri);
        if(tableName==null){
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        mDbHelper.delete(uri,selection,selectionArgs);
        int count = mMyOpenHelper.getWritableDatabase().delete(tableName, selection, selectionArgs);
        if(count>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getTableName(uri);
        if(tableName==null){
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        int update = mMyOpenHelper.getWritableDatabase().update(tableName, values, selection, selectionArgs);
        if(update>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }

        return update;
    }
}
