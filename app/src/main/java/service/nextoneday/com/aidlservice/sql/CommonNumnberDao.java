package service.nextoneday.com.aidlservice.sql;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.mobilesafe24.bean.ChildBean;
import com.itheima.mobilesafe24.bean.GroupBean;


public class CommonNumnberDao {

	public static List<GroupBean> commnumerQuery(Context context,String dbname) {
		
		List<GroupBean> list = new ArrayList<GroupBean>();
		
		File file = new File(context.getFilesDir(), dbname);
		
		SQLiteDatabase db = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
		
		//查询 数据库 了 
		//首先去查询  classlist表,然后 获得 name和 idx , 然后 再 根据 idx 去 tablex表查询,  这里的 x 就是 具体 的idx 的值 
		Cursor classCursor = db.rawQuery("select name,idx from classlist", null);
		
		
		//查询  表中的数据, 并且 封装到 groupBean, groupBean中又 有对应的  children的信息. 最后都添加到 list 中. 
		// list 中有所有的数据 
		
		if(classCursor!=null){
			
			while(classCursor.moveToNext()){
				
				String groupName = classCursor.getString(0);
				String groupIdx = classCursor.getString(1);
				
				GroupBean groupBean = new GroupBean();
				groupBean.groupName = groupName;
				groupBean.childrenList = new ArrayList<ChildBean>();
				
				// 去 到 tablex表查询 
				String sql = "select name,number from table"+groupIdx;
				
				Cursor tableCursor = db.rawQuery(sql, null);
				
				if(tableCursor!=null){
					
					while(tableCursor.moveToNext()){
						ChildBean childBean = new ChildBean();

						String childName = tableCursor.getString(0);
						String childNumber = tableCursor.getString(1);
						
						childBean.childName = childName;
						childBean.childNumber = childNumber;
						groupBean.childrenList.add(childBean);
					}
					tableCursor.close();
				}
				
				
				list.add(groupBean);
			}
		
			classCursor.close();
		}
		
		return list;
	}

}
