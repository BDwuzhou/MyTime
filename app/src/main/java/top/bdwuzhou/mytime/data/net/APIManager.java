package top.bdwuzhou.mytime.data.net;

import android.content.Context;

import com.google.common.base.Preconditions;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;
import top.bdwuzhou.mytime.Constants;
import top.bdwuzhou.mytime.data.entity.CitiesWrapper;
import top.bdwuzhou.mytime.data.entity.City;
import top.bdwuzhou.mytime.data.entity.CityNameWrapper;
import top.bdwuzhou.mytime.data.entity.Comment;
import top.bdwuzhou.mytime.data.entity.ImageWrapper;
import top.bdwuzhou.mytime.data.entity.Member;
import top.bdwuzhou.mytime.data.entity.Movie;
import top.bdwuzhou.mytime.data.entity.OnComing;
import top.bdwuzhou.mytime.data.entity.OnSaling;
import top.bdwuzhou.mytime.data.entity.OnShowing;
import top.bdwuzhou.mytime.data.entity.Person;
import top.bdwuzhou.mytime.data.entity.VideoWrapper;
import top.bdwuzhou.mytime.data.exception.ExceptionEngine;

/**
 * @author wuzhou
 * @date 2017/12/8
 */

public class APIManager {
    private static final APIManager INSTANCE = new APIManager();
    private Context mContext;
    private Retrofit mRetrofit;
    private APIService mService;

    public static APIManager getInstance() {
        return INSTANCE;
    }

    public APIManager with(Context context) {
        mContext = context.getApplicationContext();
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(15000, TimeUnit.MILLISECONDS)
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpLoggingInterceptor())
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = mRetrofit.create(APIService.class);
        return this;
    }

    //统一处理observable
    private <T> ObservableTransformer<T, T> applyTransform() {
        return upstream -> upstream
                //处理各种异常
                .onErrorResumeNext(new ExceptionHandler<>())
                //io线程发出请求
                .subscribeOn(Schedulers.io())
                //主线程处理结果
                .observeOn(AndroidSchedulers.mainThread());
    }

    private APIService safeAPI() {
        return Preconditions.checkNotNull(mService, "Please call with() method first!");
    }

    /**
     * @param location 纬度，经度
     * @return 根据经纬度获取城市名字
     */
    public Observable<CityNameWrapper> getCityName(@Query("location") String location) {
        return safeAPI()
                .getCityName(location, "ReWIPfhZICmTXojT2P9awwDhTKj7lGE3",
                        "44:AC:01:3F:1D:7E:D1:85:BD:7B:30:7C:A7:10:37:7C:72:FD:B1:97;top.bdwuzhou.mytime", "json")
                .compose(applyTransform());
    }

    /**
     * @return 城市代码
     */
    public Observable<CitiesWrapper<List<City>>> getCities() {
        return safeAPI()
                .getCities()
                .compose(applyTransform());
    }

    /**
     * @param locationId 城市id
     * @return 正在售票(包括正在热映和即将上映)
     */
    public Observable<OnSaling> getOnSalings(int locationId) {
        return safeAPI()
                .getOnSalings(locationId)
                .compose(applyTransform());
    }

    /**
     * @param locationId 城市id
     * @return 正在热映
     */
    public Observable<OnShowing> getOnShowings(int locationId) {
        return safeAPI()
                .getOnShowings(locationId)
                .compose(applyTransform());
    }

    /**
     * @param locationId 城市id
     * @return 即将上映
     */
    public Observable<OnComing> getOnComings(int locationId) {
        return safeAPI()
                .getOnComings(locationId)
                .compose(applyTransform());
    }

    /**
     * @param locationId 城市id
     * @param movieId    影片id
     * @return 影片详情
     */
    public Observable<Movie> getMovieDetail(int locationId, int movieId) {
        return safeAPI()
                .getMovieDetail(locationId, movieId)
                .compose(applyTransform());
    }

    /**
     * @param movieId 影片id
     * @return 演职员表
     */
    public Observable<Member> getMembers(int movieId) {
        return safeAPI()
                .getMembers(movieId)
                .compose(applyTransform());
    }

    /**
     * @param personId 人员id
     * @param cityId   城市id
     * @return 人员详细信息
     */
    public Observable<Person> getPersonDetail(int personId, int cityId) {
        return safeAPI()
                .getPersonDetail(personId, cityId)
                .compose(applyTransform());
    }

    /**
     * @param movieId 影片id
     * @return 影片评论
     */
    public Observable<Comment> getComments(int movieId) {
        return safeAPI()
                .getComments(movieId)
                .compose(applyTransform());
    }

    /**
     * @param pageIndex 分页页码
     * @param movieId   影片id
     * @return 预告片&拍摄花絮
     */
    public Observable<VideoWrapper> getVideos(int pageIndex, int movieId) {
        return safeAPI()
                .getVideos(pageIndex, movieId)
                .compose(applyTransform());
    }

    /**
     * @param movieId 影片id
     * @return 剧照
     */
    public Observable<ImageWrapper> getImages(int movieId) {
        return safeAPI()
                .getImages(movieId)
                .compose(applyTransform());
    }

    public class ExceptionHandler<T> implements Function<Throwable, Observable<T>> {
        @Override
        public Observable<T> apply(Throwable throwable) throws Exception {
            return Observable.error(ExceptionEngine.handelException(throwable));
        }
    }
}
