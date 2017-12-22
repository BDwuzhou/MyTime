package top.bdwuzhou.mytime.data.entity;

import android.arch.persistence.room.ColumnInfo;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/7
 * 查询更少的字段，使得查询速度更快
 */

@Data
public class CityInfo {
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;

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
}
