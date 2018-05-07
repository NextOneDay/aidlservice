package service.nextoneday.com.aidlservice.sql;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;

import com.itheima.mobilesafe24.bean.BlackNumberInfo;
import com.itheima.mobilesafe24.db.BlackNumberOpenHelper;

/**
 *  操作黑名单 号码的 dao 程序 
 * 
 *
 */
public class BlackNumberDao {
	
	BlackNumberOpenHelper helper;
	
	public BlackNumberDao(Context context) {

		helper = new BlackNumberOpenHelper(context);
	}
	
	/**
	 *   插入 黑名单号码  
	 * 
	 * 	number : 号码 
	 *  type: 拦截的类型 
	 * 
	 */
	public boolean insert(String number, String type){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("number", number);
		values.put("type", type);
		long result = db.insert("blacknumbers", null, values);
		
		db.close();
		/*if(result!=-1){
			return true;
		}else{
			return false;
		}
		*/
		
		return result!=-1 ?true : false;
	}
	
	/**
	 *    删除 黑名单号码 
	 * 
	 * @param number  
	 * @return
	 */
	public boolean delete(String number){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		int result = db.delete("blacknumbers", "number=?", new String[]{number});
		
		db.close();
		
		return result!=0?true:false;
		
	}
	
	public boolean update(String number, String newType){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("type", newType);
		
		int result = db.update("blacknumbers", values, "number=?", new String[]{number});
		
		db.close();
		return result!=-1 ?true : false;
	}
	/**
	 *   根据 传进来的 号码去查询 是否是 黑名单中的 , 以及返回 具体的拦截的 类型 
	 * @param number
	 * @return
	 */
	public String findType(String number){
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.query("blacknumbers", new String[]{"type"}, "number=?", new String[]{number}, null, null, null);
		
		String type="";
		if(cursor!=null){
			
			if(cursor.moveToNext()){
				type = cursor.getString(0);
			}
			cursor.close();
		}
		db.close();
		
		return type;
	}
	
	/**
	 *  查询 所有的  黑名单号码 
	 * 
	 * @return
	 */
	public List<BlackNumberInfo> getAllBlackNumbers(){
		
		List<BlackNumberInfo> list = new ArrayList<BlackNumberInfo>();
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		//查询 所有的 黑名单号码
		Cursor cursor = db.query("blacknumbers", new String[]{"number","type"}, null, null, null, null, null);
		
		if(cursor!=null){
			
			while(cursor.moveToNext()){
				
				String number = cursor.getString(0);
				String type = cursor.getString(1);
				
				BlackNumberInfo info = new BlackNumberInfo(number, type);
				
				list.add(info);
			}
			
			//释放资源
			cursor.close();
		}
		
		db.close();
		
		return list;
	}

	
	/**
	 *    分页查询 : 每次 查询  20 条 , 从 index 位置开始查询 
	 *  
	 * @param index :  从哪儿开始查询 
	 * @param pageSize : 每页 显示 多少条 
	 * @return
	 */
	public List<BlackNumberInfo> getPartBlackNumbers(int index, int pageSize) {
		
		SystemClock.sleep(300);
		
		List<BlackNumberInfo> list = new ArrayList<BlackNumberInfo>();
		
		// select number, type from blacknumbers;  ----查询 所有 
		
		//从 最开始的 位置查询, 查询 20 条 返回 
		// select number, type from blacknumbers limit 20 offset 0;
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		//这里 组拼 sql 语句要注意  前后的   空格 
		String sql ="select number,type from blacknumbers limit "+pageSize+" offset "+index;
		
		Cursor cursor = db.rawQuery(sql, null);
		

		if(cursor!=null){
			
			while(cursor.moveToNext()){
				
				String number = cursor.getString(0);
				String type = cursor.getString(1);
				
				BlackNumberInfo info = new BlackNumberInfo(number, type);
				
				list.add(info);
			}
			
			//释放资源
			cursor.close();
		}
		
		db.close();
		
		return list;
	}
	
	
}
