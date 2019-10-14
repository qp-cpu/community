package life.majiang.community.controller;

import life.majiang.community.dto.AccesstokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

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
                           @RequestParam("state") String state){

        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setCode(code);
        accesstokenDTO.setState(state);
        accesstokenDTO.setRedirect_uri(redirect_url);
        accesstokenDTO.setClient_id(client_Id);
        accesstokenDTO.setClient_secret(client_secrect);
        String accessToken = githubProvider.getAccessToken(accesstokenDTO);
        System.out.println(accessToken);
        GithubUser user = githubProvider.getuser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
