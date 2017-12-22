package top.bdwuzhou.mytime.data.entity;

import java.util.List;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/7
 */

@Data
public class Member {
    private List<Type> types;

    @Data
    public static class Type {
        //已知取值有导演、演员、编剧等
        private String typeName;
        //typeName 字段对应的英文
        private String typeNameEn;
        //具体人员信息
        private List<Person> persons;

        @Data
        public static class Person {
            //人员 id
            private int id;
            //人员姓名
            private String name;
            //人员英文名
            private String nameEn;
            private String image;

        }
    }
}
