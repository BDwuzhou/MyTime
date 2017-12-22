package top.bdwuzhou.mytime.data.entity;

import java.util.List;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/8
 */

@Data
public class Comment {
    private String code;
    private DataBean data;
    private String msg;
    private String showMsg;

    @Data
    public static class DataBean {
        private Mini mini;
        private Plus plus;

        //短评
        @Data
        public static class Mini {
            private int total;
            private List<ListBean> list;

            @Data
            public static class ListBean {
                private int commentDate;
                private int commentId;
                private String content;
                private String headImg;
                private String img;
                private boolean isHot;
                private boolean isPraise;
                private String locationName;
                private String nickname;
                private int praiseCount;
                private int rating;
                private int replyCount;
            }
        }

        //精选影评
        @Data
        public static class Plus {
            private int total;
            private List<ListBeanX> list;

            @Data
            public static class ListBeanX {
                private int commentDate;
                private int commentId;
                private String content;
                private String headImg;
                private boolean isWantSee;
                private String locationName;
                private String nickname;
                private int rating;
                private int replyCount;
                private String title;
            }
        }
    }
}
