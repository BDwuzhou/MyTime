package top.bdwuzhou.mytime.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/7
 */

@Entity(tableName = "cities")
@Data
public class City {
    private int count;
    @PrimaryKey
    private int id;
    @SerializedName("n")
    private String name;
    @ColumnInfo(name = "pinyin_full")
    private String pinyinFull;
    @ColumnInfo(name = "pinyin_short")
    private String pinyinShort;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyinFull() {
        return pinyinFull;
    }

    public void setPinyinFull(String pinyinFull) {
        this.pinyinFull = pinyinFull;
    }

    public String getPinyinShort() {
        return pinyinShort;
    }

    public void setPinyinShort(String pinyinShort) {
        this.pinyinShort = pinyinShort;
    }
}