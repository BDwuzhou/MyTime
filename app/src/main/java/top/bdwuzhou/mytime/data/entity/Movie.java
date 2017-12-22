package top.bdwuzhou.mytime.data.entity;

import java.util.List;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/7
 */

@Data
public class Movie {
    private String code;
    private DataBean data;
    private String msg;
    private String showMsg;

    @Data
    public static class DataBean {
        private Advertisement advertisement;
        private Basic basic;
        private BoxOffice boxOffice;
        private Live live;
        private Related related;

        @Data
        public static class Advertisement {
            private int count;
            private String error;
            private boolean success;
            private List<AdvList> advList;

            @Data
            public static class AdvList {
                private String advTag;
                private int endDate;
                private boolean isHorizontalScreen;
                private boolean isOpenH5;
                private int startDate;
                private String tag;
                private String type;
                private String typeName;
                private String url;
            }
        }

        //具体详情
        @Data
        public static class Basic {
            private Award award;
            //一句话总结该电影
            private String commentSpecial;
            private Community community;
            private Director director;
            //热映排行榜
            private int hotRanking;
            //剧照
            private String img;
            private boolean is3D;
            private boolean isDMAX;
            private boolean isEggHunt;
            private boolean isFilter;
            private boolean isIMAX;
            private boolean isIMAX3D;
            private boolean isTicket;
            private String message;
            private String mins;
            private int movieId;
            private String name;
            private String nameEn;
            private int overallRating;
            private int personCount;
            private QuizGame quizGame;
            //上映地区
            private String releaseArea;
            private String releaseDate;
            private int showCinemaCount;
            private int showDay;
            private int showtimeCount;
            //影片剧照
            private StageImg stageImg;
            //剧情简介
            private String story;
            private Style style;
            private int totalNominateAward;
            private int totalWinAward;
            private String url;
            private Video video;
            private List<Actors> actors;
            private List<?> festivals;
            private List<String> type;

            //获得的奖项
            @Data
            public static class Award {
            }

            @Data
            public static class Community {
            }

            //导演信息
            @Data
            public static class Director {
            }

            @Data
            public static class QuizGame {
            }

            @Data
            public static class StageImg {
                private int count;
                private List<ListBean> list;

                @Data
                public static class ListBean {
                    private int imgId;
                    private String imgUrl;
                }
            }

            @Data
            public static class Style {
                private int isLeadPage;
                private String leadImg;
                private String leadUrl;
            }

            //预告片
            @Data
            public static class Video {
                private int count;
                private String hightUrl;
                private String img;
                private String title;
                private String url;
                private int videoId;
            }

            //演员信息
            @Data
            public static class Actors {
                //演员 id
                private int actorId;
                //演员照片
                private String img;
                //演员名
                private String name;
                //演员英文名
                private String nameEn;
                //影片中饰演角色图片
                private String roleImg;
                //影片中饰演角色名字
                private String roleName;
            }
        }

        //专业解读内容
        @Data
        public static class BoxOffice {
            private int movieId;
            //票房排名
            private int ranking;
            //今日实时票房量
            private int todayBox;
            //今日实时票房量
            private String todayBoxDes;
            //今日实时票房量单位
            private String todayBoxDesUnit;
            private int totalBox;
            //累计票房量
            private String totalBoxDes;
            //累计票房量单位
            private String totalBoxUnit;
        }

        @Data
        public static class Live {
        }

        @Data
        public static class Related {
            private int goodsCount;
            private int relateId;
            private String relatedUrl;
            private int type;
            private List<?> goodsList;
        }
    }
}
