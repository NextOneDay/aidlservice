package service.nextoneday.com.aidlservice;

/**
 * Created by nextonedaygg on 2018/5/6.
 */

abstract class  HDMObserver {

    public  final String[] keys;

    public  HDMObserver(String [] key){
        this.keys=key;
    }

    public void update(String key,String value){

    }
}
