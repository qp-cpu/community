package life.majiang.community.controller;

import life.majiang.community.entity.PublishEntity;
import life.majiang.community.entity.UserEntity;
import life.majiang.community.service.PublishService;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class PublishController {

    @Autowired
  private PublishService publishService;
    @Autowired
  private UserService userService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "tag",required = false) String tag,
                            HttpServletRequest request,
                            Model model)
    {
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        UserEntity userEntity = null;

        if(description == null || description == "")
        {
            model.addAttribute("error","问题描述不能为空");
            return "publish";
        }
        if(title == null || title == "")
        {
            model.addAttribute("error","问题标题不能为空");
            return "publish";
        }
        if(tag == null || tag == "")
        {
            model.addAttribute("error","标签不能为空");
            return "publish";
        }


        Cookie[] cookies=request.getCookies();
        if(cookies!=null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    userEntity = userService.selectBytoken(token);
                    if (userEntity != null) {
                        request.getSession().setAttribute("user", userEntity);
                    }
                    break;
                }
            }
        }
        else
        {
            return "index";
        }
        if(userEntity == null)
        {
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        PublishEntity publishEntity = new PublishEntity();
        publishEntity.setTitle(title);
        publishEntity.setDescrition(description);
        publishEntity.setTag(tag);
        publishEntity.setCreator(userEntity.getId());
        publishEntity.setGmt_create(System.currentTimeMillis());
        publishEntity.setGmt_modified(publishEntity.getGmt_create());
        publishService.insertpublish(publishEntity);
        return "redirect:/";
    }
}

