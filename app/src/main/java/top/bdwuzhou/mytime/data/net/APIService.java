package top.bdwuzhou.mytime.data.net;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
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

/**
 * @author wuzhou
 * @date 2017/12/7
 * 时光网部分api
 */

public interface APIService {

    @GET("http://api.map.baidu.com/geocoder/v2/")
    Observable<CityNameWrapper> getCityName(@Query("location") String location, @Query("ak") String ak, @Query
            ("mcode") String mCode, @Query("output") String json);

    /**
     * @return 城市代码
     */
    @GET("Showtime/HotCitiesByCinema.api")
    Observable<CitiesWrapper<List<City>>> getCities();

    /**
     * @param locationId 城市id
     * @return 正在售票(包括正在热映和即将上映)
     */
    @GET("PageSubArea/HotPlayMovies.api")
    Observable<OnSaling> getOnSalings(@Query("locationId") int locationId);

    /**
     * @param locationId 城市id
     * @return 正在热映
     */
    @GET("Showtime/LocationMovies.api")
    Observable<OnShowing> getOnShowings(@Query("locationId") int locationId);

    /**
     * @param locationId 城市id
     * @return 即将上映
     */
    @GET("Movie/MovieComingNew.api")
    Observable<OnComing> getOnComings(@Query("locationId") int locationId);

    /**
     * @param locationId 城市id
     * @param movieId    影片id
     * @return 影片详情
     */
    @GET("movie/detail.api")
    Observable<Movie> getMovieDetail(@Query("locationId") int locationId, @Query("movieId") int movieId);

    /**
     * @param movieId 影片id
     * @return 演职员表
     */
    @GET("Movie/MovieCreditsWithTypes.api")
    Observable<Member> getMembers(@Query("movieId") int movieId);

    /**
     * @param personId 人员id
     * @param cityId   城市id
     * @return 人员详细信息
     */
    @GET("person/detail.api")
    Observable<Person> getPersonDetail(@Query("personId") int personId, @Query("cityId") int cityId);

    /**
     * @param movieId 影片id
     * @return 影片评论
     */
    @GET("movie/hotComment.api")
    Observable<Comment> getComments(@Query("movieId") int movieId);

    /**
     * @param pageIndex 分页页码
     * @param movieId   影片id
     * @return 预告片&拍摄花絮
     */
    @GET("Movie/VideoWrapper.api")
    Observable<VideoWrapper> getVideos(@Query("pageIndex") int pageIndex, @Query("movieId") int movieId);

    /**
     * @param movieId 影片id
     * @return 剧照
     */
    @GET("Movie/ImageAll.api?movieId=?")
    Observable<ImageWrapper> getImages(@Query("movieId") int movieId);
}
