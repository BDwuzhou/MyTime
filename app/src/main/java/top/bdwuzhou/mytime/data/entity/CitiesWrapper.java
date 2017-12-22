package top.bdwuzhou.mytime.data.entity;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/15
 */

@Data
public class CitiesWrapper<T> {
    @SerializedName("p")
    private T cities;

    public T getCities() {
        return cities;
    }

    public void setCities(T cities) {
        this.cities = cities;
    }
}
