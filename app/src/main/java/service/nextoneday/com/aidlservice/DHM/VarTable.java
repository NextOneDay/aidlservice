package service.nextoneday.com.aidlservice.DHM;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by nextonedaygg on 2018/5/7.
 */

@Entity
public class VarTable  {

    @Id
    private Long id;
    private String key;
    private String value;
    private String time;
    @Generated(hash = 1664040557)
    public VarTable(Long id, String key, String value, String time) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.time = time;
    }
    @Generated(hash = 746788040)
    public VarTable() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
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
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }

}
