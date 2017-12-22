package top.bdwuzhou.mytime.data.entity;

import java.util.List;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/7
 * 正在售票
 */

@Data
public class OnSaling {
    private int count;
    private int totalCinemaCount;
    private int totalComingMovie;
    private int totalHotMovie;
    private List<Movie> movies;

    @Data
    public static class Movie {
        private String actorName1;
        private String actorName2;
        private String btnText;
        private String commonSpecial;
        private String directorName;
        private String img;
        private boolean is3D;
        private boolean isDMAX;
        private boolean isFilter;
        private boolean isHot;
        private boolean isIMAX;
        private boolean isIMAX3D;
        private boolean isNew;
        private int length;
        private int movieId;
        private NearestShowtime nearestShowtime;
        private int rDay;
        private int rMonth;
        private int rYear;
        private double ratingFinal;
        private String titleCn;
        private String titleEn;
        private String type;
        private int wantedCount;

        @Data
        public static class NearestShowtime {
            private boolean isTicket;
            private int nearestCinemaCount;
            private int nearestShowDay;
            private int nearestShowtimeCount;

        }
    }
}
