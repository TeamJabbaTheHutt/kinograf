package org.example.kinograf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/adminHome")
public class AdminHomeController {

    @GetMapping
    public RedirectView showAdminHomepage() {
        return new RedirectView("/html/adminHomepage.html");
    }


}
