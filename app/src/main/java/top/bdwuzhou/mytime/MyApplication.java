package top.bdwuzhou.mytime;

import android.app.Application;
import android.util.Log;

import io.reactivex.schedulers.Schedulers;
import top.bdwuzhou.mytime.data.db.AppDataBase;
import top.bdwuzhou.mytime.data.net.APIManager;

/**
 * @author wuzhou
 * @date 2017/12/7
 */
public class MyApplication extends Application {
    private AppDataBase mDataBase;

    public AppDataBase getDataBase() {
        return mDataBase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDataBase = AppDataBase.getInstance(this);
        APIManager.getInstance().with(this)
                .getCities()
                .subscribeOn(Schedulers.io())
                .subscribe(cities -> {
                    mDataBase.beginTransaction();
                    try {
                        mDataBase.cityDao().insertAll(cities.getCities());
                        mDataBase.setTransactionSuccessful();
                    } finally {
                        mDataBase.endTransaction();
                        mDataBase.close();
                    }
                }, throwable -> Log.e("application-my-time", throwable.getMessage()));
    }
}
