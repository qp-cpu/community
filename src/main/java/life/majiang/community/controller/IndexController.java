package life.majiang.community.controller;


import life.majiang.community.dto.PublishDto;
import life.majiang.community.entity.PublishEntity;
import life.majiang.community.entity.UserEntity;
import life.majiang.community.service.PublishService;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private PublishService publishService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
        Cookie[] cookies=request.getCookies();
        if(cookies!=null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    UserEntity userEntity = userService.selectBytoken(token);
                    if (userEntity != null) {
                        request.getSession().setAttribute("user", userEntity);
                    }
                    break;
                }
            }
             List<PublishDto> publishEntityList =publishService.selectAll();

                model.addAttribute("publishs",publishEntityList);

        }
        else {
            return "index";
        }
        return "index";
    }
}
