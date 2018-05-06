package service.nextoneday.com.aidlservice.HMD;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;


/**
 * Created by nextonedaygg on 2018/5/5.
 */

@Entity
public class VarTable {

    @Id
    private Long id;

    @Property(nameInDb = "key")
    private String key;

    @Property(nameInDb = "value")
    private String value;

    @Property(nameInDb = "packageName")
    private String packageName;

    @Property(nameInDb = "time")
    private long time;

    @Generated(hash = 47064108)
    public VarTable(Long id, String key, String value, String packageName,
            long time) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.packageName = packageName;
        this.time = time;
    }

    @Generated(hash = 746788040)
    public VarTable() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
