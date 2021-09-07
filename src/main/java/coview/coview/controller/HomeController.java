package coview.coview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/coview")
    public String home(){
        return "main";
    }
}