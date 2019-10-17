package life.majiang.community.dto;

import life.majiang.community.entity.UserEntity;
import lombok.Data;

@Data
public class PublishDto {
    private Integer id;
    private String title;
    private String descrition;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private String tag;
    private UserEntity userEntity;
}
