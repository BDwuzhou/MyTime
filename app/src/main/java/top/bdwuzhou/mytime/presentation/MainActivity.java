package top.bdwuzhou.mytime.presentation;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.baidu.location.LocationClient;
import com.jakewharton.rxbinding2.view.RxView;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.solodovnikov.rx2locationmanager.IgnoreErrorTransformer;
import ru.solodovnikov.rx2locationmanager.LocationRequestBuilder;
import ru.solodovnikov.rx2locationmanager.LocationTime;
import top.bdwuzhou.mytime.MyApplication;
import top.bdwuzhou.mytime.R;
import top.bdwuzhou.mytime.data.db.AppDataBase;
import top.bdwuzhou.mytime.data.entity.CityInfo;
import top.bdwuzhou.mytime.data.net.APIManager;

/**
 * @author wuzhou
 */
public class MainActivity extends Activity {
    private static final String NO_PERMISSIONS_MSG = "get location permissions first!";
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
//        db = AppDataBase.getInstance(this);
        db = ((MyApplication) getApplication()).getDataBase();
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
        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> mTvCity.setText(String.valueOf(aLong)));
//        RxView.clicks(mBtnLocate)
//                .map(o -> isLocationAllowed)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(aBoolean -> {
//                    if (!aBoolean) {
//                        throw new IllegalStateException("get location permissions first!");
//                    }
//                    Preconditions.checkNotNull((LocationManager) getSystemService(Context.LOCATION_SERVICE))
//                            .requestSingleUpdate(LocationManager.NETWORK_PROVIDER,
//                                    new LocationListener() {
//                                        @Override
//                                        public void onLocationChanged(Location location) {
//                                            Log.e("my time", "location changed:" + location.getLatitude() + "," +
//                                                    location.getLongitude());
//                                        }
//
//                                        @Override
//                                        public void onStatusChanged(String provider, int status, Bundle extras) {
//                                            Log.e("my time", "status changed,status:" + status);
//                                        }
//
//                                        @Override
//                                        public void onProviderEnabled(String provider) {
//                                            Log.e("my time", "provider enabled,provider:" + provider);
//                                        }
//
//                                        @Override
//                                        public void onProviderDisabled(String provider) {
//                                            Log.e("my time", "provider disabled,provider:" + provider);
//                                        }
//                                    }, getMainLooper());
//                });

        RxView.clicks(mBtnLocate)
                //判断是否有定位权限
                .map(o -> isLocationAllowed)
                //根据权限情况决定是否请求定位
                .flatMap(aBoolean -> {
                    if (!aBoolean) {
                        throw new IllegalStateException(NO_PERMISSIONS_MSG);
                    }
                    return new LocationRequestBuilder(this).addLastLocation(LocationManager.NETWORK_PROVIDER,
                            new LocationTime(30, TimeUnit.SECONDS), new IgnoreErrorTransformer(null))
                            .addRequestLocation(LocationManager.NETWORK_PROVIDER, new LocationTime(10,
                                    TimeUnit.SECONDS))
                            .setDefaultLocation(new Location(LocationManager.PASSIVE_PROVIDER))
                            .create()
                            .toObservable();
                })
                //转换地图坐标系统
//                .flatMap(location -> APIManager.getInstance().with(MainActivity.this)
//                        .convertCoor(location.getLatitude() + "," + location.getLongitude()))
                //根据定位结果经纬度请求当前所在地址
                .flatMap(location -> APIManager.getInstance().with(MainActivity.this)
                        .getCityName(location.getLatitude() + "," + location.getLongitude()))
                //从数据库查找符合当前城市名城市代码
//                .subscribe(baiduAPIWrapper -> {
//                    db.beginTransaction();
//                    try {
//                        db.cityDao()
//                                .getByName(baiduAPIWrapper.getResult().getAddressComponent().getCity())
//                                .subscribe(cityInfos -> {
//                                    if (cityInfos != null && cityInfos.size() > 0) {
//                                        CityInfo city = cityInfos.get(0);
//                                        mTvCity.setText(String.format(Locale.CHINA, "%s:%d", city.getName(),
//                                                city.getId()));
//                                    }
//                                }, throwable -> {
//                                    Log.e("TAG", throwable.getMessage());
//                                    if (throwable instanceof IllegalStateException && throwable.getMessage().equals
//                                            (NO_PERMISSIONS_MSG)) {
//                                        getPermission();
//                                    }
//                                });
//                        db.setTransactionSuccessful();
//                    } finally {
//                        db.endTransaction();
//                    }
//                });
                .flatMap(baiduAPIWrapper -> {
                    Observable<List<CityInfo>> observable;
                    db.beginTransaction();
                    try {
                        observable = db.cityDao()
                                .getByName(baiduAPIWrapper.getResult().getAddressComponent().getCity())
                                .toObservable();
                        db.setTransactionSuccessful();
                        return observable;
                    } finally {
                        db.endTransaction();
                    }
                })
                .subscribe(cityInfos -> {
                    if (cityInfos != null && cityInfos.size() > 0) {
                        CityInfo city = cityInfos.get(0);
                        mTvCity.setText(String.format(Locale.CHINA, "%s:%d", city.getName(),
                                city.getId()));
                    }
                }, throwable -> {
                    Log.e("TAG", throwable.getMessage());
                    if (throwable instanceof IllegalStateException && throwable.getMessage().equals
                            (NO_PERMISSIONS_MSG)) {
                        getPermission();
                    }
                });

//        mBtnLocate.setOnClickListener(view -> {
//            if (isLocationAllowed) {
//                LocationClientOption option = new LocationClientOption();
//                //定位请求时间间隔
//                option.setScanSpan(2000);
//                option.setOpenGps(true);
//                option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//                mLocationClient = new LocationClient(MainActivity.this);
//                mLocationClient.setLocOption(option);
//                mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
//                    @Override
//                    public void onReceiveLocation(BDLocation bdLocation) {
//                        APIManager.getInstance().with(MainActivity.this)
//                                .getCityName(bdLocation.getLatitude() + "," + bdLocation.getLongitude())
//                                .flatMap(cityNameWrapper -> {
//                                    Observable<List<CityInfo>> observable;
//                                    db.beginTransaction();
//                                    try {
//                                        observable = db.cityDao().getByName
//                                                (cityNameWrapper.getResult()
//                                                        .getAddressComponent()
//                                                        .getCity())
//                                                .toObservable();
//                                        db.setTransactionSuccessful();
//                                        return observable;
//                                    } finally {
//                                        db.endTransaction();
//                                        db.close();
//                                    }
//                                })
//                                .subscribe(cityInfos -> {
//                                    if (cityInfos != null && cityInfos.size() > 0) {
//                                        CityInfo city = cityInfos.get(0);
//                                        mTvCity.setText(String.format(Locale.CHINA, "%s:%d", city.getName(),
//                                                city.getId()));
//                                    }
//                                }, throwable -> Log.e("TAG", throwable.getMessage()));
//                    }
//                });
//                mLocationClient.start();
//            } else {
//                getPermission();
//            }
//        });
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
