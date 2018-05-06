// IBookManager.aidl
package service.nextoneday.com.aidlservice;

// Declare any non-default types here with import statements
import service.nextoneday.com.aidlservice.Book;
import service.nextoneday.com.aidlservice.INoNewBookArrivedListener;

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

   List<Book> getBookList();
   void addBook(in Book book);

   void registerListener(INoNewBookArrivedListener listener);
   void unregisterListener(INoNewBookArrivedListener listener);
}
