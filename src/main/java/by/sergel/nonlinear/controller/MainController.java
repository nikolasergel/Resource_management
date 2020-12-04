package by.sergel.nonlinear.controller;

import by.sergel.nonlinear.service.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private MainServiceImpl mainService;

    @GetMapping
    public String getMain(){
        return "main";
    }

}
