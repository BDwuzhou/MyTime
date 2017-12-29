package top.bdwuzhou.mytime.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import top.bdwuzhou.mytime.data.entity.City;

/**
 * @author wuzhou
 * @date 2017/12/7
 */
@Database(entities = {City.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "cities";
    private static AppDataBase sInstance;

    public static AppDataBase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
            Log.e("database", sInstance.toString());
        }
        return sInstance;
    }

    public static void onDestroy() {
        sInstance = null;
    }

    public abstract CityDao cityDao();
}
