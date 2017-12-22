package top.bdwuzhou.mytime.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import top.bdwuzhou.mytime.data.entity.City;
import top.bdwuzhou.mytime.data.entity.CityInfo;

/**
 * @author wuzhou
 * @date 2017/12/7
 */

@Dao
public interface CityDao {
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM cities")
    Flowable<List<CityInfo>> getAll();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM cities WHERE name LIKE :name")
    Flowable<List<CityInfo>> getByName(String name);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM cities WHERE id LIKE :id")
    Flowable<List<CityInfo>> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<City> cities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(City... city);

    @Delete
    void DeleteAll(City... city);
}
