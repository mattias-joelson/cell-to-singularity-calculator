package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @GetMapping("/calculator")
    public String calculator(Model model) {
        model.addAttribute("calculator", calculatorService.getCalculator());

        return "calculator";
    }
}
