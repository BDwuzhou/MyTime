package top.bdwuzhou.mytime;

import android.app.Application;

import io.reactivex.schedulers.Schedulers;
import top.bdwuzhou.mytime.data.db.AppDataBase;
import top.bdwuzhou.mytime.data.net.APIManager;

/**
 * @author wuzhou
 * @date 2017/12/7
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        APIManager.getInstance().with(this)
                .getCities()
                .subscribeOn(Schedulers.io())
                .subscribe(cities -> {
                    AppDataBase db = AppDataBase.getInstance(this);
                    db.beginTransaction();
                    try {
                        db.cityDao().insertAll(cities.getCities());
                        db.setTransactionSuccessful();
                    } finally {
                        db.endTransaction();
                        db.close();
                    }
                });
    }
}
