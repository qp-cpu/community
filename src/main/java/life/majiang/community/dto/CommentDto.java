package life.majiang.community.dto;

import lombok.Data;

@Data
public class CommentDto {
    private  Long parentId;
    private String comtent;
    private Integer type;
}
