package life.majiang.community.controller;

import life.majiang.community.dto.NotificationDto;
import life.majiang.community.dto.PageDto;
import life.majiang.community.dto.PageNtifcationdto;
import life.majiang.community.entity.UserEntity;
import life.majiang.community.service.NtificationEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NtificationController {
    @Autowired
    private NtificationEntityService ntiService;

    @GetMapping("notification/{id}")
    public String notification(@PathVariable("id") Long id,
                          HttpServletRequest request) {
        UserEntity userEntity=(UserEntity) request.getSession().getAttribute("user");
        if(userEntity==null)
        {
            return "redirect:/";
        }

      NotificationDto notificationDto= ntiService.read(id,userEntity);

        return "profile";
    }

}
