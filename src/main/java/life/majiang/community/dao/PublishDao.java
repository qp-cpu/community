package life.majiang.community.dao;

import life.majiang.community.entity.PublishEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PublishDao {
     public Integer insertpublish(PublishEntity publishEntity);
}
