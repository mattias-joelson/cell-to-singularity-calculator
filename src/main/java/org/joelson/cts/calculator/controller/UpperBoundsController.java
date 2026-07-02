package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.UpperBounds;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpperBoundsController {

    private static final String GARDEN_GET = "/upperbounds";
    private static final String GARDEN_GENERATOR_UPDATE = "/upperbounds-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/upperbounds-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/upperbounds-generator-decrement";
    private static final String GARDEN_UPGRADE = "/upperbounds-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public UpperBoundsController() {
        garden = UpperBounds.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.clearUpgradesBought();
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Exosphere", 0);
        setGeneratorCount("Thermosphere", 0);
        setGeneratorCount("Mesosphere", 0);
        setGeneratorCount("Stratosphere", 0);
        setGeneratorCount("Troposhpere", 1);

        String[] boughtUpgrades = {
//                "Air", // check
//                "Nitrogen", // check
//                "Oxygen", // check
//                "Water", // check
//                "Sunlight", // check
//                "Infrared Radiation", // check
//                "Vapor", // check
//                "Clouds", // check
//                "Cumulus", // check
//                "Pressure", // check
//                "Wind", // check
//                "Cirrus", // check
//                "Fronts", // check
//                "Storm", // check
//                "Cumlonimbus", // check
//                "Tornado", // check
//                "Jet Stream", // check
//                "Stratus", // check
//                "Rain", // check
//                "Lightning", // check
//                "Thunder", // check
//                "Nimbostratus", // check
//                "Visible Light", // check
//                "Rainbow", // check
//                "Mammatus", // check
//                "UV Radiation", // check
//                "Lenticular Clouds", // check
//                "Asperitas", // check
//                "Ozone", // check

//                "Ozone Layer", // check
//                "Blue Jet Lightning", // check
//                "Polar Vortes", // check
//                "Nacreous Clouds", // check
//                "Blue Sky", // check
//                "Black Sky", // check

//                "Meteoric Smoke Particles", // check
//                "Shooting Stars", // check
//                "Sprites", // check
//                "Noctilucent Clouds", // check
//                "ELVES", // check
//                "Turbopause", // check

//                "Karman Line", // check
//                "Ionized Gas", // check
//                "Electron", // check
//                "Positive Ion", // check
//                "Airglow", // check
//                "Cosmic Rays", // check
//                "Plasma Bubbles", // check
//                "Solar Winds", // check
//                "Aurora Borealis", // check
//                "Magnetosphere", // check
//                "Ionosphere", // check
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
    public String upperBounds(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String upperBoundsGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String upperBoundsGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String upperBoundsGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String upperBoundsUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
