package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.FungusAmongUs;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FungusAmongUsController {

    private static final String GARDEN_GET = "/fungusamongus";
    private static final String GARDEN_GENERATOR_UPDATE = "/fungusamongus-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/fungusamongus-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/fungusamongus-generator-decrement";
    private static final String GARDEN_UPGRADE = "/fungusamongus-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public FungusAmongUsController() {
        garden = FungusAmongUs.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.clearUpgradesBought();
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Mind-Altering Fungi", 0);
        setGeneratorCount("Unwelcome Fungi", 0);
        setGeneratorCount("Tasty Fungi", 0);
        setGeneratorCount("Domesticated Fungi", 0);
        setGeneratorCount("Fungi of the Forest", 0);
        setGeneratorCount("Fungal Cleaners", 0);
        setGeneratorCount("Fungal Living", 0);
        setGeneratorCount("Fungi", 1);

        String[] boughtUpgrades = {
//                "Mycology",
//                "Mushrooms",
//                "Yeast",
//                "Mold",

//                "Diet",
//                "Reproduction",
//                "Hydrolytic Enzymes",
//                "Spores",
//                "Symbiosis",
//                "Growth",

//                "Bioremediation",
//                "Radiotrophic Fungi",
//                "Decomposition",
//                "Fungal Burial",

//                "Mycorrhiza",
//                "Mycelial Network",
//                "Ghost Orchid",
//                "Defense Alert",

//                "Building Material",
//                "Pesticides",
//                "Medicine",
//                "Antibiotics",

//                "Edible Mushrooms",
//                "Truffles",
//                "Bread",
//                "Fermentation",
//                "Cheese Ripening",
//                "Blue Cheese",
//                "Alcohol",

//                "Moldy Food",
//                "Fungal Infections",
//                "Plant Blight",
//                "Black Mold",
//                "Human Illness",
//                "Poisonous Mushrooms",
//                "Amanita",
//                "Parasitic Fungi",

//                "Scent & Taste",
//                "Psychedelic Mushrooms",
//                "Cordyceps",
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
    public String fungusAmongUs(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String fungusAmongUsGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String fungusAmongUsGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String fungusAmongUsGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String fungusAmongUsUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
