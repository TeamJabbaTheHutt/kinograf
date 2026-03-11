package org.example.kinograf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/adminHome")
public class AdminHomeController {

    @GetMapping
    public String showAdminHomepage() {
        return "redirect:adminHomepage.html";
    }


}
