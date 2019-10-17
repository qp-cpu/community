package life.majiang.community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishEntity {
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
}
