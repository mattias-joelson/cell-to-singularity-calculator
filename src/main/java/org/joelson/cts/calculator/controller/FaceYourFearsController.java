package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.FaceYourFears;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FaceYourFearsController {

    private static final String GARDEN_GET = "/faceyourfears";
    private static final String GARDEN_GENERATOR_UPDATE = "/faceyourfears-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/faceyourfears-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/faceyourfears-generator-decrement";
    private static final String GARDEN_UPGRADE = "/faceyourfears-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public FaceYourFearsController() {
        garden = FaceYourFears.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.clearUpgradesBought();
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("The Unfathomable", 0);
        setGeneratorCount("The Uncanny", 0);
        setGeneratorCount("The Insidious", 1);

        String[] boughtUpgrades = {
//                "Kobold",
//                "Bannik",
//                "Kappa",
//                "Bunyip",
//                "Adze",
//                "Crocotta",

//                "Baba Yaga",
//                "Mermaid",
//                "Hal",
//                "Changeling",
//                "Chupacabra",
//                "Capelobo",
//                "Yeti",
//                "Mothman",
//                "Jersey Devil",
//                "Bigfoot",

//                "Yacumama",
//                "Raiju",
//                "Dingonek",
//                "Tatzelwurm",
//                "Mongolian Death Worm",
//                "Jinn",
//                "Loch Ness Monster",
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
    public String faceYourFears(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String faceYourFearsGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String faceYourFearsGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String faceYourFearsGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String faceYourFearsUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
