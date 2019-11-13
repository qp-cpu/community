package life.majiang.community.controller;

import life.majiang.community.dto.CommentDto;
import life.majiang.community.dto.ResultDto;
import life.majiang.community.entity.CommentEntity;
import life.majiang.community.entity.UserEntity;
import life.majiang.community.exception.CustmizeException;
import life.majiang.community.exception.CustomizeErrorcode;
import life.majiang.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object post(@RequestBody CommentDto commentDto,
                       HttpServletRequest request){

        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (user==null)
        {
            return new CustmizeException(CustomizeErrorcode.NO_LOGIN);
        }
        CommentEntity record = new CommentEntity();
        record.setParentId(commentDto.getParentId());
        record.setContent(commentDto.getComtent());
        record.setType(commentDto.getType());
        record.setGmtCreate(System.currentTimeMillis());
        record.setGmtModified(System.currentTimeMillis());
        record.setLikeCount(0L);
        record.setCommentor(user.getId());
        commentService.insert(record);
        return ResultDto.okOf();
    }
}
