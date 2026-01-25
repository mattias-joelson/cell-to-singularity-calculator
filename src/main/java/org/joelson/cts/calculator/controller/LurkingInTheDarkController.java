package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.LurkingInTheDark;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LurkingInTheDarkController {

    private static final String GARDEN_GET = "/lurkinginthedark";
    private static final String GARDEN_GENERATOR_UPDATE = "/lurkinginthedark-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/lurkinginthedark-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/lurkinginthedark-generator-decrement";
    private static final String GARDEN_UPGRADE = "/lurkinginthedark-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final SingleCurrencyExplorationUpdater helper;

    public LurkingInTheDarkController() {
        garden = LurkingInTheDark.createGarden(1, 1, 0);
        state = new GardenState();
        helper = new SingleCurrencyExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE,
                GARDEN_GENERATOR_INCREMENT, GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("The Trenches", 0);
        setGeneratorCount("The Abyss", 0);
        setGeneratorCount("Midnight Zone", 0);
        setGeneratorCount("Twilight Zone", 0);
        setGeneratorCount("Sunlight Zone", 1);

        String[] boughtUpdates = {
//                "Microscopic Plants",
//                "Global Drifters",
//                "Speedy Swimmers",
//                "Whale Power",
//                "Sinking Detritus",
//                "Marine Snow",
//                "Snot Palace",
//                "Nightly Migrations",
//                "Slow Living",
//                "Whale Fall",
//                "Upwelling",
//                "Nutrient Express",
//                "Climate Control",
//                "Earth's Lifeline",

//                "Fish World",
//                "Making Light",
//                "Seeing Blue",
//                "Red is the New Black",
//                "See-Through Bodies",
//                "Oxygen Exploit",
//                "Light as a Lure",
//                "Lurking Champion",
//                "Hiding in Light",
//                "Alarms and Flash Bangs",

//                "Mammal Limit",
//                "Under Pressure",
//                "Giant Eyes",
//                "The Biggest Gulp",
//                "Giant Teeth",
//                "Extreme Mating",
//                "Collective Living",

//                "Smell and Touch",
//                "Electrical Sensors",
//                "Benthic Desert",
//                "A Gathering Herd",
//                "Extreme Species",
//                "Lonesome Predator",

//                "Hadal Extremes",
//                "Hydrothermal Vents",
//                "Chemical Ecosystem",
//                "Cold Seeps",
//                "Briny Death Traps",
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
    public String lurkingInTheDark(Model model) {
        initState();
        return helper.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String lurkingInTheDarkGeneratorUpdate(Model model, String target, String value) {
        return helper.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String lurkingInTheDarkGeneratorIncrement(Model model, String target) {
        return helper.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String lurkingInTheDarkGeneratorDecrement(Model model, String target) {
        return helper.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String lurkingInTheDarkUpgrade(Model model, String target, String value) {
        return helper.gardenUpgrade(model, target, value);
    }
}
