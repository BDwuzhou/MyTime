package top.bdwuzhou.mytime.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/8
 */

@Data
public class VideoWrapper {
    private int totalPageCount;
    private int totalCount;
    private List<Video> videoList;

    @Data
    public static class Video {
        //预告片 id
        private int id;
        //普通质量预告片链接
        private String url;
        //高质量预告片链接
        @SerializedName("hightUrl")
        private String highUrl;
        //预告片图片
        private String image;
        //预告片名称
        private String title;
        private int type;
        //预告片时长
        private int length;
    }
}
