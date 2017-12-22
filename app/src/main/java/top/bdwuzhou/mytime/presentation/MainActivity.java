package top.bdwuzhou.mytime.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import top.bdwuzhou.mytime.R;
import top.bdwuzhou.mytime.data.db.AppDataBase;
import top.bdwuzhou.mytime.data.entity.CityInfo;
import top.bdwuzhou.mytime.data.net.APIManager;

/**
 * @author wuzhou
 */
public class MainActivity extends Activity {
    @BindView(R.id.btn_locate)
    Button mBtnLocate;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.lottie)
    LottieAnimationView mLottie;
    private AppDataBase db;
    private boolean isLocationAllowed = false;
    private LocationClient mLocationClient = new LocationClient(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        db = AppDataBase.getInstance(this);
        initView();
        initListener();
        getPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLottie.resumeAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLottie.cancelAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
    }

    private void initView() {
        mTvCity = findViewById(R.id.tv_city);
        mBtnLocate = findViewById(R.id.btn_locate);
        mLottie = findViewById(R.id.lottie);
        mLottie.playAnimation();
    }

    private void initListener() {
        Observable.interval(1000000, TimeUnit.MICROSECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> mTvCity.setText(String.valueOf(aLong)));
        mBtnLocate.setOnClickListener(view -> {
            if (isLocationAllowed) {
                LocationClientOption option = new LocationClientOption();
                //定位请求时间间隔
                option.setScanSpan(2000);
                option.setOpenGps(true);
                option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
                mLocationClient = new LocationClient(MainActivity.this);
                mLocationClient.setLocOption(option);
                mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
                    @Override
                    public void onReceiveLocation(BDLocation bdLocation) {
//                        APIManager.getInstance().with(MainActivity.this)
//                                .getCities()
//                                .flatMap(citiesWrapper -> {
//                                    db.beginTransaction();
//                                    try {
//                                        db.cityDao().insertAll(citiesWrapper.getCities());
//                                        db.setTransactionSuccessful();
//                                    } finally {
//                                        db.endTransaction();
//                                    }
//                                    return APIManager.getInstance().with(MainActivity.this)
//                                            .getCityName(bdLocation.getLatitude() + "," + bdLocation.getLongitude());
//                                })

                        APIManager.getInstance().with(MainActivity.this)
                                .getCityName(bdLocation.getLatitude() + "," + bdLocation.getLongitude())

                                .flatMap(cityNameWrapper -> {
                                    Observable<List<CityInfo>> observable;
                                    db.beginTransaction();
                                    try {
                                        observable = db.cityDao().getByName
                                                (cityNameWrapper.getResult()
                                                        .getAddressComponent()
                                                        .getCity())
                                                .toObservable();
                                        db.setTransactionSuccessful();
                                        return observable;
                                    } finally {
                                        db.endTransaction();
                                        db.close();
                                    }
                                })
                                .subscribe(cityInfos -> {
                                    if (cityInfos != null && cityInfos.size() > 0) {
                                        CityInfo city = cityInfos.get(0);
                                        mTvCity.setText(city.getName() + ":" + city.getId());
                                    }
                                }, throwable -> Log.e("TAG", throwable.getMessage()));
                    }
                });
                mLocationClient.start();
            } else {
                getPermission();
            }
        });
    }

    /**
     * 获取定位权限
     */
    private void getPermission() {
        AndPermission.with(this)
                .permission(Permission.LOCATION)
                .requestCode(100)
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        isLocationAllowed = true;
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        isLocationAllowed = false;
                    }
                })
                .rationale((requestCode, rationale) -> AndPermission.rationaleDialog(this, rationale).show())
                .start();
    }
}
