package life.majiang.community.service;

import life.majiang.community.dao.PublishDao;
import life.majiang.community.dao.UserDao;
import life.majiang.community.dto.PageDto;
import life.majiang.community.dto.PublishDto;
import life.majiang.community.entity.PublishEntity;
import life.majiang.community.entity.UserEntity;
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

    public PageDto selectAll(Integer page, Integer size){
        Integer ofsize= size * (page-1);
        List<PublishEntity> publishEntityList=publishDao.selectAll(ofsize,size);
        List<PublishDto> publishDtos=new ArrayList<>();
        PageDto pageDto = new PageDto();
        for (PublishEntity publishEntity:publishEntityList)
        {
            UserEntity userEntity = userDao.getuser(publishEntity.getCreator());
            if(userEntity!=null) {
                PublishDto publishDto = new PublishDto();
                BeanUtils.copyProperties(publishEntity, publishDto);
                publishDto.setUserEntity(userEntity);
                publishDtos.add(publishDto);
            }
        }
        pageDto.setPublishDtos(publishDtos);
        Integer totalcount=publishDao.count();
        pageDto.setPagenation(totalcount,page,size);

        return pageDto;
    }

}