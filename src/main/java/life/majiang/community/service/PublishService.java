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
        PageDto pageDto = new PageDto();
        Integer totalcount=publishDao.count();
        pageDto.setPagenation(totalcount,page,size);

        //对违规值进行处理
        if(page<1)
        {
            page=1;
        }
        if(page>pageDto.getTotalpage())
        {
            page=pageDto.getTotalpage();
        }

        Integer ofsize= size * (page-1);
        List<PublishEntity> publishEntityList=publishDao.selectAll(ofsize,size);
        List<PublishDto> publishDtos=new ArrayList<>();

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
        return pageDto;
    }

    public PageDto list(Integer id, Integer page, Integer size) {
        PageDto pageDto = new PageDto();
        Integer totalcount=publishDao.count1(id);
        pageDto.setPagenation(totalcount,page,size);

        //对违规值进行处理
        if(page<1)
        {
            page=1;
        }
        if(page>pageDto.getTotalpage())
        {
            page=pageDto.getTotalpage();
        }

        Integer ofsize= size * (page-1);
        List<PublishEntity> publishEntityList=publishDao.list(id,ofsize,size);
        List<PublishDto> publishDtos=new ArrayList<>();

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

        return  pageDto;
    }

    public PublishDto getBYid(Integer id) {
        PublishEntity publishentity = publishDao.getBYid(id);
        PublishDto publishDto=new PublishDto();
        BeanUtils.copyProperties(publishentity,publishDto);
        UserEntity userEntity = userDao.getuser(publishentity.getCreator());
        publishDto.setUserEntity(userEntity);
        return publishDto;
    }
}
