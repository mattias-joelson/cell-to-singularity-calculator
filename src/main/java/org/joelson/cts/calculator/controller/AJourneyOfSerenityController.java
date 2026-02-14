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
//                "Cultivation",
//                "Health Benefits",
//                "Defense Response",
//                "Tea Meals",
//                "Chagayu",
//                "Herbal Medicine",
//                "Ochazuke",

//                "Origin Myth",
//                "Calm Body and Mind",
//                "Digestion",
//                "Anti-inflammatory",
//                "Weight Management",

//                "Pruning",
//                "Harvesting",
//                "Scissors",
//                "Harvesting Machinery",
//                "Soil Acidity",
//                "Fertilizer",
//                "Pest and Disease Control",
//                "Vertical Farming",
//                "Irrigation System",
//                "Mechanical Plucking",
//                "Drone Technology",
//                "Monitoring System",
//                "Storing",
//                "Drying",
//                "Roasting",
//                "Fermentation",

//                "Tea Contest",
//                "Silk Road Trade",
//                "Arabic Shai",
//                "Moroccan Atai",
//                "AI Automation", // tea leaves

//                "Whisking",
//                "Foam Art",
//                "Chanoyu",
//                "Grinding", // tea leaves

//                "Steeping",
//                "Darye",
//                "Trade to Europe",
//                "Yunnan Pu-erh Tea", // tea leaves
//                "Tea Brick", // tea leaves

//                "British Tea",
//                "Masala Chai",
//                "Boiling",
//                "High Tea",
//                "Tea House",
//                "Assam Tea", // tea leaves
//                "Storage Jar", // tea leaves

//                "Iced Tea",
//                "Cold Brew",
//                "Herbal Tea",
//                "Tea Latte",
//                "Tea Cocktail",
//                "Bubble Tea",
//                "Tea Bag", // tea leaves
//                "Vacuum Sealer", // tea leaves

//                "Tea Simulator",
//                "Online Tea Ceremony",
//                "Shared Serenity",
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
