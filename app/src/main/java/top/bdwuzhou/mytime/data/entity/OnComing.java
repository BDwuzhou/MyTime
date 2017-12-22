package top.bdwuzhou.mytime.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/7
 * 即将上映
 */

@Data
public class OnComing {
    private List<Attention> attention;
    @SerializedName("moviecomings")
    private List<MovieComing> movieComings;

    //最受关注
    @Data
    public static class Attention {
        private String actor1;
        private String actor2;
        private String director;
        private int id;
        private String image;
        private boolean isFilter;
        private boolean isTicket;
        private boolean isVideo;
        private String locationName;
        private int rDay;
        private int rMonth;
        private int rYear;
        private String releaseDate;
        private String title;
        private String type;
        private int videoCount;
        private int wantedCount;
        private List<Video> videos;
    }

    //即将上映
    @Data
    public static class MovieComing {
        private String actor1;
        private String actor2;
        private String director;
        private int id;
        private String image;
        private boolean isFilter;
        private boolean isTicket;
        private boolean isVideo;
        private String locationName;
        private int rDay;
        private int rMonth;
        private int rYear;
        private String releaseDate;
        private String title;
        private String type;
        private int videoCount;
        private int wantedCount;
        private List<Video> videos;
    }

    //预告片，目前无权访问
    @Data
    public static class Video {
        @SerializedName("hightUrl")
        private String highUrl;
        private String image;
        private int length;
        private String title;
        private String url;
        private int videoId;
    }
}
