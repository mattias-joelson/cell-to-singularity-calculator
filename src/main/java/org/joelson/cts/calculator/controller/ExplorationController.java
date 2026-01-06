package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.controller.model.Production;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExplorationController {

    @Autowired
    SetInStoneController setInStoneController;

    @Autowired
    UnfoldTheUniverseController unfoldTheUniverseController;

    @GetMapping("/exploration/setinstone")
    public String explorationSetInStone(Model model) {
        Garden garden = setInStoneController.getGarden();
        GardenState state = setInStoneController.getState();
        return exploration(model, garden, state);
    }

    @GetMapping("/exploration/unfoldtheuniverse")
    public String explorationUnfoldTheUniverse(Model model) {
        Garden garden = unfoldTheUniverseController.getGarden();
        GardenState state = unfoldTheUniverseController.getState();
        return exploration(model, garden, state);
    }

    private String exploration(Model model, Garden garden, GardenState state) {
        Production production = Production.calculateProduction(garden, state);
        model.addAttribute("production", production);

        return "exploration";
    }
}
