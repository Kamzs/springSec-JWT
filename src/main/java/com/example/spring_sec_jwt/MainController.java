package com.example.spring_sec_jwt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    @ResponseBody
    public String getMainPage ()
    {
        return "main page";
    }

    //pod tym url (tj. kazdym innym niz powyzszy) dostęp ma tylko admin - tj. dostajemy error 403
    @GetMapping("/a")
    @ResponseBody
    public String getOther ()
    {
        return "this page can only be seen by admin";
    }

}
