package life.majiang.community.service;

import life.majiang.community.dao.UserDao;
import life.majiang.community.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserEntity getuser(int id){
     return  userDao.getuser(id);
    }

    public Integer add(UserEntity userEntity)
    {
        return userDao.add(userEntity);
    }

    public UserEntity selectBytoken(String token){
        return userDao.selectBytoken(token);
    }
}
