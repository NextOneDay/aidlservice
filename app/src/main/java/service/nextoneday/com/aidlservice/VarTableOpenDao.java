package service.nextoneday.com.aidlservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import service.nextoneday.com.aidlservice.MyOpenHelper;

/**
 * Created by nextonedaygg on 2018/5/6.
 */

public class VarTableOpenDao {

    private final MyOpenHelper mMyOpenHelper;

    public VarTableOpenDao(Context context){

        mMyOpenHelper = new MyOpenHelper(context);
    }

    //插入 操作
    public boolean insert(Uri uri, ContentValues values ){

        //拿到一个可写的 数据库
        SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();

        //看源代码 , 发现这个代码内部 是通过 组拼sql语句来实现的,
        long result = db.insert("students", null, values);

        //记得释放资源
        db.close();

        if(result!=-1){
            return true;
        }else{
            return false;
        }

    }

    //删除  操作
    public boolean delete(String name ){

        SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();

        // 删除操作
//		String sql ="delete from students where name=?";
//		db.execSQL(sql,new Object[]{name});

        // 组拼sql语句来实现的
        int result = db.delete("students", "name=?", new String[]{name});

        //释放资源
        db.close();
        if(result!=0){
            return true;
        }else{
            return false;
        }

    }

    //更新  操作
    public void update(String name, String newSex ){

        SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();

//		String sql ="update students set sex=? where name=?";
//		db.execSQL(sql,new Object[]{newSex,name});
//
        ContentValues values = new ContentValues();
        values.put("sex", newSex);
        db.update("students", values, "name=?", new String[]{name});

        //释放资源
        db.close();

    }

    //查询 操作
    public void queryAll(){

        SQLiteDatabase db = mMyOpenHelper.getReadableDatabase();
        // select sex from students where name='honghong';
//		String sql ="select * from students";   //查询所有的

        //注意:这里的cursor对象 就一个封装了查询的对象,
        // 查询的返回的数据就封装在这个对象中.
        //需要 搞清楚 这个 cursor对象的内部结构,从而就知道 调用 对应的api 来获得 数据了 .

        //注意:这里的实际上就是一个二维表的结构, 与直接查询的时候 返回的 结构是一样的.
        // cursor提供了 moveToNext 方法来 将指针 往下一行挪动,  并且 返回一个布尔值, 用于判断是否挪动成功.
        // 并且 提供对应 getXxx的方法 来获得 对应 每一行中的每一列的值.
//		Cursor cursor = db.rawQuery(sql, null);

        Cursor cursor = db.query("students", new String[]{"_id","name","sex"}, null, null, null, null, null);

        while(cursor.moveToNext()){

            //  获得对应的这一行的数据
            // 传 列的索引

            //动态的 根据列的名称来获得列的索引
//			int idIndex = cursor.getColumnIndex("_id");
//			int id = cursor.getInt(idIndex);   //获得id
            int id = cursor.getInt(cursor.getColumnIndex("_id"));

            int nameIndex = cursor.getColumnIndex("name");
            String name = cursor.getString(nameIndex);  // 获得 name

            int sexIndex = cursor.getColumnIndex("sex");
            String sex = cursor.getString(sexIndex);  // 获得 name

            System.out.println(" id : " + id +", name : " + name +" , sex :" + sex);

        }

        //释放资源 ,查询的时候, cursor同样也要close
        cursor.close();
        db.close();
    }

    public String querySex(String name){

        SQLiteDatabase db = mMyOpenHelper.getReadableDatabase();
//		String sql ="select sex from students where name=?";
//		Cursor cursor = db.rawQuery(sql, new String[]{name});

        Cursor cursor = db.query("students", new String[]{"sex"}, "name=?", new String[]{name}, null, null, null);

        String sex="";
        if(cursor.moveToNext()){

            sex = cursor.getString(0);
        }

        //释放资源
        cursor.close();
        db.close();

        return sex;

    }
}
