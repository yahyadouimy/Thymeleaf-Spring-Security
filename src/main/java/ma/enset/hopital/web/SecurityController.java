package ma.enset.hopital.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping(path = "/notAuth")
    public String notAuth(){
        return "notAuth";
    }

    @GetMapping(path = "/login")
    public String login(){
        return "login";
    }
}
