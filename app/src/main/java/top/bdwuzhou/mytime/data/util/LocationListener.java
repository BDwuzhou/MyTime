package top.bdwuzhou.mytime.data.util;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

/**
 * @author wuzhou
 * @date 2017/12/14
 */

public class LocationListener extends BDAbstractLocationListener {
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
        //以下只列举部分获取经纬度相关（常用）的结果信息
        //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

        //获取纬度信息
        double latitude = bdLocation.getLatitude();
        //获取经度信息
        double longitude = bdLocation.getLongitude();
        //获取定位精度，默认值为0.0f
        float radius = bdLocation.getRadius();

        //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
        String coorType = bdLocation.getCoorType();

        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        int errorCode = bdLocation.getLocType();
    }
}
