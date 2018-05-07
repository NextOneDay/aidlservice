package service.nextoneday.com.aidlservice.sql;

import java.io.File;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NumberQueryDao {

	//查询 号码的 归属地  
	public static String numberAddressQuery(Context context, String queryNumber) {
		
		// addres.db --- /data/data/包名/files/address.db
		File file = new File(context.getFilesDir(), "address.db");
		
		// /data/data/包名/files/address.db
		String absolutePath = file.getAbsolutePath();
		
		//查询 
		SQLiteDatabase db = SQLiteDatabase.openDatabase(absolutePath, null, SQLiteDatabase.OPEN_READONLY);
		
		//接下来的代码就跟之前类似了, 可以直接去查询 号码的归属地了 
		// d -- digit 
		//判断 number是 手机号码还是  座机号码 
		String regex = "^1[34578]\\d{9}$";
		
		boolean matches = queryNumber.matches(regex);
		
		String location="";
		
		if(matches){
			
			//说明是 手机号码 
			// 查询 比较麻烦, 需要去查询 两张表 
			// sql 语句 
// select outkey from data1 where id='1821111';
//select location from data2 where id='90';
// 第一种: select location from data2 where id=(select outkey from data1 where id='1869999');
			
// 表的级联查询 -- 同时从多张表中查询 
// 第二种: select data2.location from data1,data2 where data1.outkey=data2.id and data1.id='1869999';
			String sql ="select data2.location from data1,data2 where data1.outkey=data2.id and data1.id=?";
			Cursor cursor = db.rawQuery(sql, new String[]{queryNumber.substring(0, 7)});
			
			if(cursor!=null){
				
				if(cursor.moveToNext()){
					
					location = cursor.getString(0);
					
//					location = location.substring(0, location.length()-2);
					
					System.out.println("location : " + location);
				}
				
				cursor.close();
			}
			db.close();
			
		}else{
			
			//说明是非 手机号码 
			
			switch (queryNumber.length()) {
				case 4:
					location="模拟器";
					break;
				case 5:   // 95533   // 12345  95338
					location="商业机构电话";
					break;
				case 7:
				case 8:
					location="本地号码";
					break;
				case 9:
					location="热线电话";
					break;
				default:
					
					if(queryNumber.length()>10&&queryNumber.startsWith("0")){
						
						// 01084546666
						// 075584546666
						// 07107878988
						String sql="select location from data2 where area=?";
						Cursor cursor = db.rawQuery(sql, new String[]{queryNumber.substring(1, 3)});
						
						if(cursor!=null){
							
							if(cursor.moveToNext()){
								
								location = cursor.getString(0);
								
//								location = location.substring(0, location.length()-2);
								
								System.out.println("location : " + location);
							}
							
							cursor.close();
						}
						
						Cursor cursor2 = db.rawQuery(sql, new String[]{queryNumber.substring(1, 4)});
						
						if(cursor2!=null){
							
							if(cursor2.moveToNext()){
								
								location = cursor2.getString(0);
								
//								location = location.substring(0, location.length()-2);
								
								System.out.println("location : " + location);
							}
							
							cursor2.close();
						}
						
						db.close();
						
					}
					break;
			}
			
		}
		
		return location;
	}

}
