package service.nextoneday.com.aidlservice;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import service.nextoneday.com.aidlservice.HMD.VarTableOpenDao;

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
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        mDbHelper.query(uri,projection,selection,selectionArgs,sortOrder);
      return null;
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
        mDbHelper.insert(uri,values);
        new VarTableOpenDao(getContext()).insert()
        getContext().getContentResolver().notifyChange(uri,null);

        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        mDbHelper.delete(uri,selection,selectionArgs);
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
