package top.bdwuzhou.mytime.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/7
 * 正在热映
 */

@Data
public class OnShowing {
    private String bImg;
    private String date;
    private boolean hasPromo;
    private int lid;
    private int newActivitiesTime;
    private PromoBean promo;
    private int totalComingMovie;
    private String voucherMsg;
    //具体正在热映电影信息
    @SerializedName("ms")
    private List<MsBean> detail;

    @Data
    public static class PromoBean {
    }

    @Data
    public static class MsBean {
        private int NearestCinemaCount;
        private int NearestDay;
        private int NearestShowtimeCount;
        //主演1
        @SerializedName("aN1")
        private String actor1;
        //主演2
        @SerializedName("aN2")
        private String actor2;
        private String actors;
        //今日上映该电影的影院数量，同NearestCinemaCount
        @SerializedName("cC")
        private int cinemaCount;
        private String commonSpecial;
        //影片时长
        @SerializedName("d")
        private String duration;
        //导演名字
        @SerializedName("dN")
        private String director;
        private int def;
        private int id;
        private String img;
        private boolean is3D;
        private boolean isDMAX;
        private boolean isFilter;
        private boolean isHasTrailer;
        private boolean isHot;
        private boolean isIMAX;
        private boolean isIMAX3D;
        private boolean isNew;
        private boolean isTicket;
        private String m;
        private String movieType;
        private boolean preferentialFlag;
        //影片评分
        @SerializedName("r")
        private int rating;
        private int rc;
        //影片上映时间
        @SerializedName("rd")
        private String rd;
        private int rsC;
        private int sC;
        //影片名称
        @SerializedName("t")
        private String title;
        //影片中文名
        @SerializedName("tCn")
        private String titleCn;
        //影片英文名
        @SerializedName("tEn")
        private String titleEn;
        private int ua;
        private int wantedCount;
        private List<String> p;
        //影片观影类型，如 3D、IMAX等
        private List<Version> versions;

        @Data
        public static class Version {
            @SerializedName("enum")
            private int enumX;
            private String version;
        }
    }
}
