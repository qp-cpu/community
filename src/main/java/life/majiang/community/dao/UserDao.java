package life.majiang.community.dao;

import life.majiang.community.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
  public UserEntity getuser(int id);
  public Integer add(UserEntity userEntity);
}
