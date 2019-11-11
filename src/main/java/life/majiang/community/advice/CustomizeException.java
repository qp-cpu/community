package life.majiang.community.advice;

import life.majiang.community.exception.CustmizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
@ControllerAdvice
public class CustomizeException {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model)
    {
        HttpStatus status=getStatus(request);
        if(ex instanceof CustmizeException)
        {
            model.addAttribute("message",ex.getMessage());
        }
        else {
            model.addAttribute("message","服务失败了");
        }
        return  new ModelAndView("error");
    }


    private  HttpStatus getStatus(HttpServletRequest request)
    {
        Integer statuCode= (Integer) request.getAttribute("javax.servlet.erroe.status.status_code");
        if(statuCode==null)
        {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statuCode);
    }
}
