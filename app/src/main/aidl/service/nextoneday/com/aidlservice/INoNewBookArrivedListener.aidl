// INoNewBookArrivedListener.aidl
package service.nextoneday.com.aidlservice;

// Declare any non-default types here with import statements
import service.nextoneday.com.aidlservice.Book;
interface INoNewBookArrivedListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
  void onNewBookArrived(in Book newBook);
}
