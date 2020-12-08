package by.sergel.nonlinear.controller;

import by.sergel.nonlinear.dto.Calculation;
import by.sergel.nonlinear.dto.Result;
import by.sergel.nonlinear.service.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MainServiceImpl mainService;

    @GetMapping
    public String getMain(Model model){
        model.addAttribute("entity", mainService.getData());
        model.addAttribute("entity", mainService.getData());
        return "main";
    }

    @PostMapping("/calculate")
    public String init(@RequestParam Map<String, String> form, Model model){
        mainService.setData(form);
        model.addAttribute("entity", mainService.getData());
        List<Calculation> calculations = mainService.getCalculations();
        model.addAttribute("calc", calculations);
        List<Result> results = mainService.getResultsList(calculations);
        model.addAttribute("results", results);
        model.addAttribute("totalCost", mainService.getResultNumber(results));
        return "calculations";
    }
}
