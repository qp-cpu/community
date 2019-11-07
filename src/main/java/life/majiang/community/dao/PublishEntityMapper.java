package life.majiang.community.dao;

import life.majiang.community.entity.PublishEntity;

public interface PublishEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PublishEntity record);

    int insertSelective(PublishEntity record);

    PublishEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PublishEntity record);

    int updateByPrimaryKey(PublishEntity record);
}