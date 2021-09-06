package coview.coview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(HttpServletRequest request, Model model){
//        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
//        Auth memberAuth = null;
//
//        if (flashMap != null){
//            memberAuth = (Auth) flashMap.get("memberAuth");
//        }
//
//        if (memberAuth != null){
//            model.addAttribute("href_dashboard", "Dashboard");
//            model.addAttribute("href_logout", "Logout");
//        }
//        else{
//            model.addAttribute("href_dashboard", null);
//            model.addAttribute("href_logout", null);
//        }

        return "index";
    }
}