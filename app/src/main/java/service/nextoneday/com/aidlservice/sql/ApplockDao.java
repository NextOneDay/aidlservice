package service.nextoneday.com.aidlservice.sql;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.itheima.mobilesafe24.db.ApplockDBOpenHelper;

public class ApplockDao {
	
	ApplockDBOpenHelper helper;
	private Context context;
	public ApplockDao(Context context) {
		
		this.context = context;
		helper = new ApplockDBOpenHelper(context);
	}
	
	/**
	 *  添加到 程序锁的 表 
	 * 
	 * @param packagename
	 * @return
	 */
	public boolean add(String packagename){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		// packagename
		values.put("packagename", packagename);
		
		//lockinfo
		long insert = db.insert("lockinfo", null, values);
		
		db.close();
		
		
		Uri uri = Uri.parse("content://com.itheima.tainwanggaidihu/liangwanwu");
		
		
		// 当数据库的数据发生改变的时候, notify (通知 )一下
		context.getContentResolver().notifyChange(uri, null);
		
		return insert!=-1;
		
	}
	
	public boolean delete(String packagename){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		int result = db.delete("lockinfo", "packagename=?", new String[]{packagename});
		
//		db.execSQL("delete lockinfo where packagename=?", new String[]{packagename});
		
		db.close();
		
		
		Uri uri = Uri.parse("content://com.itheima.tainwanggaidihu/liangwanwu");
		
		// 当数据库的数据发生改变的时候, notify (通知 )一下
		context.getContentResolver().notifyChange(uri, null);
		
		
		return result!=0;
		
	}
	
	public boolean find(String packagename){
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery("select * from lockinfo where packagename=?", new String[]{packagename});
		
		boolean result = false;
		
		if(cursor!=null&&cursor.moveToNext()){
			result =true;
		}
		cursor.close();
		db.close();
		return result;
		
	}
	
	//返回所有的  已加锁 的 list 集合 
	public List<String> findAll(){
		
		List<String> list = new ArrayList<String>();
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery("select packagename from lockinfo", null);
		
		if(cursor!=null){
			
			while(cursor.moveToNext()){
				String packagename = cursor.getString(0);
				list.add(packagename);
			}
			
			cursor.close();
		}
		db.close();
		
		return list;
	}
		
}
