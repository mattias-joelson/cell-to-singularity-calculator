package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.SetInStone;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SetInStoneController {

    private static final String GARDEN_GET = "/setinstone";
    private static final String GARDEN_GENERATOR_UPDATE = "/setinstone-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/setinstone-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/setinstone-generator-decrement";
    private static final String GARDEN_UPGRADE = "/setinstone-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public SetInStoneController() {
        garden = SetInStone.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.clearUpgradesBought();
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Gem", 0);
        setGeneratorCount("Crystal", 0);
        setGeneratorCount("Metamorphic Rock", 0);
        setGeneratorCount("Sedimentary Rock", 0);
        setGeneratorCount("Igneous Rock", 0);
        setGeneratorCount("Mineral", 1);

        String[] boughtUpdates = {
//                "Olivine",
//                "Quartz",
//                "Feldspars",
//                "Magma",
//                "Tuff", // rocks
//                "Clay",
//                "Rock Cycle",
//                "Sand",
//                "Sandstone", // rocks
//                "Calcite",
//                "Granite", // rocks
//                "Gneiss", // rocks
//                "Fluorite",
//                "Pegmatite", // rocks
//                "Coal", // rocks

//                "Dunite",
//                "Basalt",
//                "Andesite",
//                "Diorite",
//                "Scoria",
//                "Obsidian",

//                "Limestone",
//                "Siltstone",
//                "Coquina",
//                "Shale",
//                "Chalk",
//                "Flint",
//                "Natural Beauty",

//                "Marble",
//                "Slate",
//                "Schist",
//                "Anthracite",
//                "Jade", // crystal

//                "Amethyst",
//                "Jasper",
//                "Fulgurite", // rocks
//                "Topaz",
//                "Lapis Lazuli", // rocks
//                "Diamond",
//                "Pyrite",
//                "Lab-Grown Diamonds",

//                "Emerald",
//                "Aquamarine",
//                "Opal",
//                "Garnet",
//                "Sapphire",
//                "Ruby",
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
    public String setInStone(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String setInStoneGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String setInStoneGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String setInStoneGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String setInStoneUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
