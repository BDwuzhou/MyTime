package top.bdwuzhou.mytime.data.entity;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/27
 */

@Data
public class BaiduCoorResult {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
