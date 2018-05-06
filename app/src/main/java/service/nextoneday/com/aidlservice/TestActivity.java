package service.nextoneday.com.aidlservice;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TestActivity";

    private IBookManager mRemoteBookManager;

    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;
    public static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.d(TAG, "receive new  book" + msg.obj);
                    break;
            }
        }
    };
    private Uri mUri;
    private ContentResolver mContentResolver;
    private HMLContentObserver mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View btnStart = findViewById(R.id.btn_start);
        View btnProvider = findViewById(R.id.btn_provide);
        btnProvider.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        mUri = Uri.parse("content://service.nextoneday.com.aidlservice.hmd.provider/vartable");
        observer();

    }

    private void start() {
        Intent intent = new Intent();
        intent.setAction("com.nextoneday.pax");
        intent.setPackage("service.nextoneday.com.aidlservice");
        bindService(intent, new ServiceConnection() {


            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IBookManager manager = IBookManager.Stub.asInterface(service);
                try {
                    mRemoteBookManager = manager;
                    List<Book> bookList = manager.getBookList();

                    for (Book book : bookList) {
                        Log.d(TAG, book.toString());
                    }
                    manager.addBook(new Book(3, "Book3"));
                    manager.registerListener(mOnNewBookArrivedListener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

                //远程服务意外死亡，在这里重连服务。
                //这个方法在客户端的UI线程中回调

            }
        }, Context.BIND_AUTO_CREATE);
    }


    private INoNewBookArrivedListener mOnNewBookArrivedListener = new INoNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {

            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook).sendToTarget();
        }

    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_provide:
                queryContent();
                break;
            case R.id.btn_start:
                start();
                break;
        }
    }

    private void queryContent() {
        ContentResolver contentResolver = getContentResolver();


        ContentValues values = new ContentValues();


        values.put("time",System.currentTimeMillis());
        values.put("packageName",getPackageName());
        values.put("key","666");
        values.put("value","我是数据111");

        Uri insert = contentResolver.insert(mUri, values);
        Log.d(TAG,insert+" url地址");
//
//        Cursor query = contentResolver.query(uri, null, null, null, null);
//        while (query.moveToNext()) {
//            int anInt = query.getInt(0);
//            String string = query.getString(1);
//
//            query.close();
//
//            Log.d(TAG, "anint" + anInt + "string" + string);
//        }

    }

    private void observer() {
        mObserver = new HMLContentObserver(mHandler);
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

    @Override
    protected void onDestroy() {
        mContentResolver.unregisterContentObserver(mObserver);

        super.onDestroy();
        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                Log.d(TAG, "unregister listener" + mOnNewBookArrivedListener);
                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
//            unbindService(mConnection);
        }
    }
}
