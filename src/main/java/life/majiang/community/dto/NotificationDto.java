package life.majiang.community.dto;

import life.majiang.community.entity.UserEntity;
import lombok.Data;

@Data
public class NotificationDto {
    private Integer id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private String type;
    private Long outerid;
}
