package life.majiang.community.controller;

import life.majiang.community.dto.AccesstokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.entity.UserEntity;
import life.majiang.community.provider.GithubProvider;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private UserService userService;



    @Value("${github.client.id}")
    private String client_Id;

    @Value("${github.client.secrect}")
    private String client_secrect;

    @Value("${github.redirect.url}")
    private String redirect_url;

    @Autowired
   private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam("state") String state,
                           HttpServletRequest request){

        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setCode(code);
        accesstokenDTO.setState(state);
        accesstokenDTO.setRedirect_uri(redirect_url);
        accesstokenDTO.setClient_id(client_Id);
        accesstokenDTO.setClient_secret(client_secrect);
        String accessToken = githubProvider.getAccessToken(accesstokenDTO);
        System.out.println(accessToken);
        GithubUser user = githubProvider.getuser(accessToken);
        if(user!=null)
       {
           UserEntity userEntity = new UserEntity();
           userEntity.setToken(UUID.randomUUID().toString());
           userEntity.setName(user.getName());
           userEntity.setAccount_id(String.valueOf(user.getId()));
           userEntity.setGmt_create(System.currentTimeMillis());
           userEntity.setGmt_modified(userEntity.getGmt_create());
           //登录成功
           Integer add = userService.add(userEntity);
           request.getSession().setAttribute("user",user);
           return "redirect:/";
       }
       else
       {
           //登录失败，重新登录
           return "redirect:/";
       }
    }
}
