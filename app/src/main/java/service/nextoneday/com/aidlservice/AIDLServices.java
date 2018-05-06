package service.nextoneday.com.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by nextonedaygg on 2018/5/3.
 */

public class AIDLServices extends Service {

    public static final String TAG = "AIDLServices";
    CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    RemoteCallbackList<INoNewBookArrivedListener> mListener = new RemoteCallbackList<>();

    AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);
    Binder mBinder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Log.d(TAG, book.toString());
            mBookList.add(book);
        }

        @Override
        public void registerListener(INoNewBookArrivedListener listener) throws RemoteException {

            mListener.register(listener);
            Log.d(TAG, "registerListener");
        }

        @Override
        public void unregisterListener(INoNewBookArrivedListener listener) throws RemoteException {
            mListener.unregister(listener);
            Log.d(TAG, "unregisterListener");
        }
    };

    private void onNewBookArrived(Book book) throws RemoteException{
        mBookList.add(book);
        Log.d(TAG, "add book" + book.toString());
        int count = mListener.beginBroadcast();
        for (int i = 0; i <count ; i++) {
            INoNewBookArrivedListener broadcastItem = mListener.getBroadcastItem(i);
            if(broadcastItem!=null){

                broadcastItem.onNewBookArrived(book);
                Log.d(TAG, "notify onNewBookArrived" + broadcastItem);
            }
        }
        mListener.finishBroadcast();

    }

    @Override
    public void onCreate() {
        super.onCreate();

        mBookList.add(new Book(1, "book1"));
        mBookList.add(new Book(2, "book2"));

        new  Thread(new ServiceWorker()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //可以在这里进行权限认证，比如permission

        return mBinder;
    }

    public class  ServiceWorker implements Runnable {

        @Override
        public void run() {
            while(! mIsServiceDestoryed.get()){
                try {
                    Thread.sleep(5000);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                int bookId= mBookList.size()+1;
                Book newBook = new Book(bookId, "new Book#" + bookId);
                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsServiceDestoryed.set(true);
    }
}
