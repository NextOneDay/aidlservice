package service.nextoneday.com.aidlservice.HMD;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by nextonedaygg on 2018/5/5.
 */

@Entity
public class LogTable {
    @Id
    private Long id;

    private String tag;

    private String LOGLevel;

    private String LOG;

    private long time;

    private String packageName;

    @Generated(hash = 1469299014)
    public LogTable(Long id, String tag, String LOGLevel, String LOG, long time,
            String packageName) {
        this.id = id;
        this.tag = tag;
        this.LOGLevel = LOGLevel;
        this.LOG = LOG;
        this.time = time;
        this.packageName = packageName;
    }

    @Generated(hash = 1975800615)
    public LogTable() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLOGLevel() {
        return this.LOGLevel;
    }

    public void setLOGLevel(String LOGLevel) {
        this.LOGLevel = LOGLevel;
    }

    public String getLOG() {
        return this.LOG;
    }

    public void setLOG(String LOG) {
        this.LOG = LOG;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
