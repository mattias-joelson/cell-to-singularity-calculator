package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.DairyDelights;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DairyDelightsController {

    private static final String GARDEN_GET = "/dairydelights";
    private static final String GARDEN_GENERATOR_UPDATE = "/dairydelights-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/dairydelights-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/dairydelights-generator-decrement";
    private static final String GARDEN_UPGRADE = "/dairydelights-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public DairyDelightsController() {
        garden = DairyDelights.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.clearUpgradesBought();
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Hard Cheese", 0);
        setGeneratorCount("Not-Quite-Cheese", 0);
        setGeneratorCount("Semi-Firm Cheese", 0);
        setGeneratorCount("Blue Cheese", 0);
        setGeneratorCount("Semi-Soft Cheese", 0);
        setGeneratorCount("Soft-Ripened Cheese", 0);
        setGeneratorCount("Fresh Cheese", 0);
        setGeneratorCount("Milk", 1);

        String[] boughtUpdates = {
//                "Lactose",
//                "Mozzarella", // cheese
//                "Cultures", // cheese
//                "Camembert", // cheese
//                "Rennet", // cheese
//                "Muenster", // cheese
//                "Danish", // cheese
//                "Curds and Whey", // cheese
//                "Edam", // cheese
//                "Salt", // cheese
//                "Ricotta", // cheese
//                "Parmigiano-Reggiano", // cheese
//                "The Big Cheese", // cheese

//                "Cottage Cheese",
//                "Cream Cheese",
//                "Kefir",
//                "Feta",

//                "Brie",
//                "Chèvre",
//                "Paneer",

//                "Morbier",
//                "Havarti",

//                "Roquefort",
//                "Stilton",
//                "Gorgonzola",

//                "Halloumi",
//                "Emmental",
//                "Cheddar",
//                "Gloucester",
//                "Provolone",
//                "Limburger",
//                "Gouda",

//                "Non-Dairy",
//                "Processed",
//                "American",
//                "Canned Cheese",

//                "Pecorino",
//                "Manchego",
//                "Cotija",
//                "Casu Martzu",
        };

        for (String upgradeName : boughtUpdates) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
        state.updateGeneratorStates(garden);
    }

    private void setGeneratorCount(String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    @GetMapping(GARDEN_GET)
    public String dairyDelights(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String dairyDelightsGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String dairyDelightsGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String dairyDelightsGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String dairyDelightsUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
