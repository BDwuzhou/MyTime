package top.bdwuzhou.mytime.data.entity;

import java.util.List;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/8
 */

@Data
public class ImageWrapper {
    private List<Image> images;
    private List<ImageType> imageTypes;

    @Data
    public static class Image {
        private int id;
        private String image;
        private int type;
    }

    @Data
    public static class ImageType {
        private int type;
        private String typeName;
    }
}
