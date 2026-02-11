package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.AJourneyOfSerenity;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AJourneyOfSerenityController {

    private static final String GARDEN_GET = "/journeyofserenity";
    private static final String GARDEN_GENERATOR_UPDATE = "/journeyofserenity-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/journeyofserenity-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/journeyofserenity-generator-decrement";
    private static final String GARDEN_UPGRADE = "/journeyofserenity-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public AJourneyOfSerenityController() {
        garden = AJourneyOfSerenity.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.clearUpgradesBought();
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Virtual Tea", 0);
        setGeneratorCount("Unconventional Tea", 0);
        setGeneratorCount("Infused Tea", 0);
        setGeneratorCount("Loose-Leaf Tea", 0);
        setGeneratorCount("Matcha", 0);
        setGeneratorCount("Tea Evolution", 0);
        setGeneratorCount("Tea Plantation", 0);
        setGeneratorCount("Domesticated Tea Plant", 0);
        setGeneratorCount("Wild Tea Plant", 1);

        String[] boughtUpgrades = {
//                "Cultivation", // check
//                "Health Benefits", // check
//                "Defense Response", // check
//                "Tea Meals", // check
//                "Chagayu", // check
//                "Herbal Medicine", // check
//                "Ochazuke", // check

//                "Origin Myth", // check
//                "Calm Body and Mind", // check
//                "Digestion", // check
//                "Anti-inflammatory", // check
//                "Weight Management", // check

//                "Pruning", // check
//                "Harvesting", // check
//                "Scissors", // check
//                "Harvesting Machinery", // check
//                "Soil Acidity", // check
//                "Fertilizer", // check
//                "Pest and Disease Control", // check
//                "Vertical Farming", // check
//                "Irrigation System", // check
//                "Mechanical Plucking", // check
//                "Drone Technology", // check
//                "Monitoring System", // check
//                "Storing", // check
//                "Drying", // check
//                "Roasting", // check
//                "Fermentation", // check

//                "Tea Contest", // check
//                "Silk Road Trade", // check
//                "Arabic Shai", // check
//                "Moroccan Atai", // check
//                "AI Automation", // tea leaves // check

//                "Whisking", // check
//                "Foam Art", // check
//                "Chanoyu", // check
//                "Grinding", // tea leaves // check

//                "Steeping", // check
//                "Darye", // check
//                "Trade to Europe", // check
//                "Yunnan Pu-erh Tea", // tea leaves // check
//                "Tea Brick", // tea leaves // check

//                "British Tea", // check
//                "Masala Chai", // check
//                "Boiling", // check
//                "High Tea", // check
//                "Tea House", // check
//                "Assam Tea", // tea leaves // check
//                "Storage Jar", // tea leaves // check

//                "Iced Tea", // check
//                "Cold Brew", // check
//                "Herbal Tea", // check
//                "Tea Latte", // check
//                "Tea Cocktail", // check
//                "Bubble Tea", // check
//                "Tea Bag", // tea leaves // check
//                "Vacuum Sealer", // tea leaves // check

//                "Tea Simulator", // check
//                "Online Tea Ceremony", // check
//                "Shared Serenity", // check
        };

        for (String upgradeName : boughtUpgrades) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
        state.updateGeneratorStates(garden);
    }

    private void setGeneratorCount(String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    @GetMapping(GARDEN_GET)
    public String journeyOfSerenity(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String journeyOfSerenityGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String journeyOfSerenityGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String journeyOfSerenityGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String journeyOfSerenityUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
