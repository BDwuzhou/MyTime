package top.bdwuzhou.mytime.data.entity;

import java.util.List;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/7
 */

@Data
public class Person {
    private String code;
    private DataBean data;
    private String msg;
    private String showMsg;

    @Data
    public static class DataBean {

        private AdvertisementBean advertisement;
        private BackgroundBean background;

        @Data
        public static class AdvertisementBean {
            private int count;
            private String error;
            private boolean success;
            private List<AdvListBean> advList;

            @Data
            public static class AdvListBean {
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

        @Data
        public static class BackgroundBean {
            private String address;
            private int birthDay;
            private int birthMonth;
            private int birthYear;
            private CommunityBean community;
            private String content;
            private HotMovieBean hotMovie;
            private String image;
            private int movieCount;
            private String nameCn;
            private String nameEn;
            private String profession;
            private QuizGameBean quizGame;
            private String rating;
            private String ratingFinal;
            private StyleBean style;
            private int totalNominateAward;
            private int totalWinAward;
            private String url;
            private List<AwardsBean> awards;
            private List<ExpriencesBean> expriences;
            private List<FestivalsBean> festivals;
            private List<ImagesBean> images;
            private List<OtherHonorBean> otherHonor;
            private List<RelationPersonsBean> relationPersons;

            @Data
            public static class CommunityBean {
            }

            @Data
            public static class HotMovieBean {
            }

            @Data
            public static class QuizGameBean {
            }

            @Data
            public static class StyleBean {
                private int isLeadPage;
                private String leadImg;
                private String leadUrl;
            }

            @Data
            public static class AwardsBean {
                private int festivalId;
                private int nominateCount;
                private int winCount;
                private List<?> nominateAwards;
                private List<WinAwardsBean> winAwards;

                @Data
                public static class WinAwardsBean {
                    private String awardName;
                    private String festivalEventYear;
                    private String image;
                    private int movieId;
                    private String movieTitle;
                    private String movieTitleEn;
                    private String movieYear;
                    private String roleName;
                    private int sequenceNumber;
                }
            }

            @Data
            public static class ExpriencesBean {
                private String content;
                private String img;
                private String title;
                private int year;

            }

            @Data
            public static class FestivalsBean {
                private int festivalId;
                private String img;
                private String nameCn;
                private String nameEn;
                private String shortName;

            }

            @Data
            public static class ImagesBean {
                private String image;
                private int imageId;
                private int type;
            }

            @Data
            public static class OtherHonorBean {
                private String honor;
            }

            @Data
            public static class RelationPersonsBean {
                private String rCover;
                private String rNameCn;
                private String rNameEn;
                private int rPersonId;
                private String relation;

            }
        }
    }
}
