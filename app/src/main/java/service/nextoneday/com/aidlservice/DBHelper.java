package service.nextoneday.com.aidlservice;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import service.nextoneday.com.aidlservice.DHM.DaoMaster;
import service.nextoneday.com.aidlservice.DHM.DaoSession;
import service.nextoneday.com.aidlservice.DHM.VarTable;
import service.nextoneday.com.aidlservice.DHM.VarTableDao;

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

    public VarTableDao getLogTabDao() {
        return mDaoSession.getVarTableDao();
    }



    public void insert(Uri uri, ContentValues values) {
     

    }



    public void query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

//        getVarTableDao().queryBuilder().
    }

    /**
     * 有根据很多种条件删除的，
     *
     */
    public void delete(Uri uri, String selection, String[] selectionArgs) {

    }
}
