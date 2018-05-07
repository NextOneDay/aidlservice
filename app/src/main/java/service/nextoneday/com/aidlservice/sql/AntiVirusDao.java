package service.nextoneday.com.aidlservice.sql;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AntiVirusDao {
	
	public static boolean isVirus(Context context,String md5){
		
		
		File file = new File(context.getFilesDir(),"antivirus.db");
		
		//操作数据库 
		SQLiteDatabase db = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
		
		// select name from datable where md5='sdafdsa'
		Cursor cursor = db.rawQuery("select name from datable where md5=?", new String[]{md5});
	
		boolean isVirus = false;
		
		if(cursor!=null){
			
			if(cursor.moveToNext()){
				isVirus = true;
			}
			//释放资源
			cursor.close();
		}
		db.close();
		return isVirus;
	}
	
	public static void insert(Context context,String md5){
		
		
		File file = new File(context.getFilesDir(),"antivirus.db");
		
		//操作数据库 
		SQLiteDatabase db = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, 
				SQLiteDatabase.OPEN_READWRITE);
		
		ContentValues values = new ContentValues();
		values.put("md5", md5);
		values.put("type", "6");
		values.put("name", "XX神器");
		values.put("desc", "恶意的在后台发短信给好朋友...");
		
		db.insert("datable", null, values);


	}
	
	
}
