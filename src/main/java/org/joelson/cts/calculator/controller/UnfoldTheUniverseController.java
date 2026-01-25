package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.UnfoldTheUniverse;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UnfoldTheUniverseController {

    private static final String GARDEN_GET = "/unfoldtheuniverse";
    private static final String GARDEN_GENERATOR_UPDATE = "/unfoldtheuniverse-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/unfoldtheuniverse-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/unfoldtheuniverse-generator-decrement";
    private static final String GARDEN_UPGRADE = "/unfoldtheuniverse-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public UnfoldTheUniverseController() {
        garden = UnfoldTheUniverse.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Launch", 0);
        setGeneratorCount("Construction", 0);
        setGeneratorCount("Development", 0);
        setGeneratorCount("James Webb Telescope", 0);
        setGeneratorCount("Hubble Telescope", 0);
        setGeneratorCount("Ground Telescope", 1);

        String[] boughtUpdates = {
//                "Space Telescope", // check
//                "Origins", // check

//                "Repair Mission", // check
//                "Landmark Discoveries", // check
//                "Hubble's Successor", // check
//                "Distance from Earth", // check
//                "Size Comparison", // check

//                "James E. Webb", // check
//                "Naming", // check
//                "Mission Objectives", // check
//                "Mission Length", // check
//                "Cleared Name", // check
//                "International Collaboration", // check
//                "Budget", // check
//                "Ground Support", // check

//                "Black Holes", // check
//                "Galactic Birth", // check
//                "Funding", // check
//                "Seeking Exoplanets", // check
//                "Averted Cancellation", // check
//                "Delayed Launch", // check

//                "Infrared Visibility", // check
//                "Micro Shutters", // check
//                "Dangerous Heat", // check
//                "Sunshield", // check
//                "Hexagonal Mirrors", // check

//                "Christmas Launch", // check
//                "Journey to L2", // check
//                "Sunshield Unfolding", // check
//                "Mirrors Unfolding", // check
//                "Secondary Mirrors", // check
//                "Primary Mirrors", // check
//                "Warm-Up Period", // check
//                "First Images", // check
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
    public String unfoldTheUniverse(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String unfoldTheUniverseGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String unfoldTheUniverseGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String unfoldTheUniverseGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String unfoldTheUniverseUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
