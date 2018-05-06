package service.nextoneday.com.aidlservice.HMD;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LOG_TABLE".
*/
public class LogTableDao extends AbstractDao<LogTable, Long> {

    public static final String TABLENAME = "LOG_TABLE";

    /**
     * Properties of entity LogTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Tag = new Property(1, String.class, "tag", false, "TAG");
        public final static Property LOGLevel = new Property(2, String.class, "LOGLevel", false, "LOGLEVEL");
        public final static Property LOG = new Property(3, String.class, "LOG", false, "LOG");
        public final static Property Time = new Property(4, long.class, "time", false, "TIME");
        public final static Property PackageName = new Property(5, String.class, "packageName", false, "PACKAGE_NAME");
    }


    public LogTableDao(DaoConfig config) {
        super(config);
    }
    
    public LogTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LOG_TABLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TAG\" TEXT," + // 1: tag
                "\"LOGLEVEL\" TEXT," + // 2: LOGLevel
                "\"LOG\" TEXT," + // 3: LOG
                "\"TIME\" INTEGER NOT NULL ," + // 4: time
                "\"PACKAGE_NAME\" TEXT);"); // 5: packageName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LOG_TABLE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LogTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String tag = entity.getTag();
        if (tag != null) {
            stmt.bindString(2, tag);
        }
 
        String LOGLevel = entity.getLOGLevel();
        if (LOGLevel != null) {
            stmt.bindString(3, LOGLevel);
        }
 
        String LOG = entity.getLOG();
        if (LOG != null) {
            stmt.bindString(4, LOG);
        }
        stmt.bindLong(5, entity.getTime());
 
        String packageName = entity.getPackageName();
        if (packageName != null) {
            stmt.bindString(6, packageName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LogTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String tag = entity.getTag();
        if (tag != null) {
            stmt.bindString(2, tag);
        }
 
        String LOGLevel = entity.getLOGLevel();
        if (LOGLevel != null) {
            stmt.bindString(3, LOGLevel);
        }
 
        String LOG = entity.getLOG();
        if (LOG != null) {
            stmt.bindString(4, LOG);
        }
        stmt.bindLong(5, entity.getTime());
 
        String packageName = entity.getPackageName();
        if (packageName != null) {
            stmt.bindString(6, packageName);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LogTable readEntity(Cursor cursor, int offset) {
        LogTable entity = new LogTable( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // tag
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // LOGLevel
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // LOG
            cursor.getLong(offset + 4), // time
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // packageName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LogTable entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTag(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLOGLevel(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLOG(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTime(cursor.getLong(offset + 4));
        entity.setPackageName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LogTable entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LogTable entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LogTable entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}