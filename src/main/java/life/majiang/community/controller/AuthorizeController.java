package life.majiang.community.controller;

import life.majiang.community.dto.AccesstokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
   private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam("state") String state){

        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setCode(code);
        accesstokenDTO.setState(state);
        accesstokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accesstokenDTO.setClient_id("079686b431e42084be6c");
        accesstokenDTO.setClient_secret("c7afb2650726839d8f8156a9fa18493827152e70");
        String accessToken = githubProvider.getAccessToken(accesstokenDTO);
        System.out.println(accessToken);
        GithubUser user = githubProvider.getuser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
