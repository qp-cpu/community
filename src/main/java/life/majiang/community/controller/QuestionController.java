package life.majiang.community.controller;

import life.majiang.community.dto.PublishDto;
import life.majiang.community.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private PublishService publishService;
    @GetMapping("/question/{creator}")
    public  String question(@PathVariable("creator") Integer creator,
                            Model model){
        PublishDto publishdto = publishService.getBYid(creator);
        model.addAttribute("question",publishdto);
        return "question";
    }
}
