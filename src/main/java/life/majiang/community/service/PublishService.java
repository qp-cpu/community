package life.majiang.community.service;

import life.majiang.community.dao.PublishDao;
import life.majiang.community.dao.UserDao;
import life.majiang.community.entity.PublishEntity;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishService {
    @Autowired
    PublishDao publishDao;

    public Integer insertpublish(PublishEntity publishEntity){
        return publishDao.insertpublish(publishEntity);
    }
}
