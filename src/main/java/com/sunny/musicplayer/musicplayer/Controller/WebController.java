package com.sunny.musicplayer.musicplayer.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login.html";
    }

    @GetMapping("/home")
    public String home() {
        return "redirect:/index.html";
    }

}
