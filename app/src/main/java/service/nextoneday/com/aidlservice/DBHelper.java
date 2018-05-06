package service.nextoneday.com.aidlservice;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import org.greenrobot.greendao.annotation.Id;

import java.util.List;

import service.nextoneday.com.aidlservice.HMD.DaoMaster;
import service.nextoneday.com.aidlservice.HMD.DaoSession;
import service.nextoneday.com.aidlservice.HMD.LogTableDao;
import service.nextoneday.com.aidlservice.HMD.VarTable;
import service.nextoneday.com.aidlservice.HMD.VarTableDao;

/**
 * Created by nextonedaygg on 2018/5/5.
 */

public class DBHelper {


    private static DaoSession mDaoSession;

    public   DBHelper(Context context) {

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "dhm.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        mDaoSession = daoMaster.newSession();

    }

    public VarTableDao getVarTableDao() {
        return mDaoSession.getVarTableDao();

    }

    public LogTableDao getLogTabDao() {
        return mDaoSession.getLogTableDao();
    }



    public void insert(Uri uri, ContentValues values) {
        VarTable table = new VarTable();
        table.setKey(values.getAsString("key"));
        table.setValue(values.getAsString("value"));
        table.setTime(values.getAsLong("time"));
        table.setPackageName(values.getAsString("packageName"));
        getVarTableDao().insert(table);

    }



    public void query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

//        getVarTableDao().queryBuilder().
    }

    /**
     * 有根据很多种条件删除的，
     *
     */
    public void delete(Uri uri, String selection, String[] selectionArgs) {

        getVarTableDao().delete();
    }
}
