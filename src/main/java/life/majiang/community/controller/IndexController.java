package life.majiang.community.controller;


import life.majiang.community.entity.UserEntity;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if(cookies!=null) {
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
        }
        else {
            return "index";
        }
        return "index";
    }
}
