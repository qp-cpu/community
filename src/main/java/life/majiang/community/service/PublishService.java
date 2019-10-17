package life.majiang.community.service;

import life.majiang.community.dao.PublishDao;
import life.majiang.community.dao.UserDao;
import life.majiang.community.dto.PublishDto;
import life.majiang.community.entity.PublishEntity;
import life.majiang.community.entity.UserEntity;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublishService {
    @Autowired
    PublishDao publishDao;
    @Autowired
    UserDao userDao;

    public Integer insertpublish(PublishEntity publishEntity){
        return publishDao.insertpublish(publishEntity);
    }

    public List<PublishDto> selectAll(){
        List<PublishEntity> publishEntityList=publishDao.selectAll();
        List<PublishDto> publishDtos=new ArrayList<>();
        for (PublishEntity publishEntity:publishEntityList)
        {
            UserEntity userEntity = userDao.getuser(publishEntity.getCreator());
            PublishDto publishDto = new PublishDto();
            BeanUtils.copyProperties(publishEntity,publishDto);
            publishDto.setUserEntity(userEntity);
            publishDtos.add(publishDto);
        }

        return publishDtos;
    }
}
