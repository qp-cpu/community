package life.majiang.community.service;

import life.majiang.community.dao.CommentEntityMapper;
import life.majiang.community.dao.PublishEntityMapper;
import life.majiang.community.entity.CommentEntity;
import life.majiang.community.entity.PublishEntity;
import life.majiang.community.enums.ContentTypeEnums;
import life.majiang.community.exception.CustmizeException;
import life.majiang.community.exception.CustomizeErrorcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentEntityMapper commentEntityMapper;

    @Autowired
    private PublishEntityMapper publishEntityMapper;

    @Transactional
    public void insert(CommentEntity record) {
        if(record.getParentId()==null || record.getParentId()==0)
        {
            throw new CustmizeException(CustomizeErrorcode.TARGET_PARAM_NOT_FIND);
        }
       if(record.getType()==null|| !ContentTypeEnums.isExist(record.getType()))
       {
           throw new CustmizeException(CustomizeErrorcode.TYPE_PARAM_ERROE);
       }
       if(record.getType()==ContentTypeEnums.COMMENT.getType())
       {
           //回复评论
            CommentEntity commentEntity = commentEntityMapper.selectByPrimaryKey(record.getParentId());
            if(commentEntity==null)
            {
                throw new CustmizeException(CustomizeErrorcode.COMMENT_NOT_FIND);
            }
            commentEntityMapper.insert(record);
       }
       else {
           //回复问题
           PublishEntity publishEntity = publishEntityMapper.selectByPrimaryKey(record.getParentId().intValue());
           if(publishEntity==null)
           {
               throw new CustmizeException(CustomizeErrorcode.QUESTION_NOT);
           }
           commentEntityMapper.insert(record);
           publishEntity.setCommentCount(1);
           publishEntityMapper.incCommentCount(publishEntity);
       }
    }
}
