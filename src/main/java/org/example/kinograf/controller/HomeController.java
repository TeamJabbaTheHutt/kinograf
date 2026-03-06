package org.example.kinograf.controller;

import org.example.kinograf.repository.MovieRepository;
import org.example.kinograf.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    // For fetching info on movies to show on startpage

    @GetMapping()
    public String showHome() {
        return "static/index";
    }

}
