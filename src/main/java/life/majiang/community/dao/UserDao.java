package life.majiang.community.dao;

import life.majiang.community.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
  public UserEntity getuser(int id);
  public Integer add(UserEntity userEntity);
  public UserEntity selectBytoken(@Param("token") String token);
}
