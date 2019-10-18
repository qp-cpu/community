package life.majiang.community.dao;

import life.majiang.community.entity.PublishEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PublishDao {
     public Integer insertpublish(PublishEntity publishEntity);

     public List<PublishEntity> selectAll(@Param("ofszie") Integer ofszie, @Param("size") Integer size);
     public  Integer count();
}
