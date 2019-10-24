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
    public void createOrUpdate(UserEntity userEntity)
    {
        UserEntity dbuserEntity=userDao.findByAccountId(userEntity.getAccount_id());
        if(dbuserEntity==null)
        {
            //插入
            userEntity.setGmt_create(System.currentTimeMillis());
            userEntity.setGmt_modified(userEntity.getGmt_create());
            userDao.add(userEntity);
        }
        else {
            //更新
            dbuserEntity.setGmt_modified(System.currentTimeMillis());
            dbuserEntity.setAvatar_url(userEntity.getAvatar_url());
            dbuserEntity.setName(userEntity.getName());
            dbuserEntity.setToken(userEntity.getToken());
            userDao.updatetoken(dbuserEntity);
        }
    }
}
