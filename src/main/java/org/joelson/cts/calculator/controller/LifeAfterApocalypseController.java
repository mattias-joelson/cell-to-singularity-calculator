package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.LifeAfterApocalypse;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LifeAfterApocalypseController {

    private static final String GARDEN_GET = "/lifeafterapocalypse";
    private static final String GARDEN_GENERATOR_UPDATE = "/lifeafterapocalypse-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/lifeafterapocalypse-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/lifeafterapocalypse-generator-decrement";
    private static final String GARDEN_UPGRADE = "/lifeafterapocalypse-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public LifeAfterApocalypseController() {
        garden = LifeAfterApocalypse.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.clearUpgradesBought();
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Cockroach World?", 0);
        setGeneratorCount("Age of Mammals", 0);
        setGeneratorCount("Reign of Dinosaurs", 0);
        setGeneratorCount("Mesozoic Dawn", 0);
        setGeneratorCount("Pangean Life", 0);
        setGeneratorCount("Age of Fishes", 0);
        setGeneratorCount("Metazoan Seas", 0);
        setGeneratorCount("L.U.C.A.", 1);

        String[] boughtUpgrades = {
//                "Cambrian Explosion",
//                "Trilobites",
//                "Placoderms",
//                "Tetrapods",
//                "Archosaurs",
//                "Tyrannosaurus Rex",
//                "Homo Sapiens",
//                "Unknown Species",
//                "Space Loophole",
//                "Life Beyond",
//                "Ordovician Extinction",
//                "Devonian Extinction",
//                "Permian Extinction",
//                "Triassic Extinction",
//                "Cretaceous Extinction",
//                "Anthropocene Extinction",
//                "Solar Apocalypse",

//                "Ordovician Extinction",
//                "Trilobite World",
//                "Asteroid Bombardment",
//                "Continental Collision",
//                "Carbon Tipping Point",
//                "Ice Age!",
//                "Anoxic Oceans",

//                "Devonian Extinction",
//                "Land Grab",
//                "Killer Trees",
//                "Killer Plankton",
//                "Extinction Pulses",

//                "Permian Extinction",
//                "Vertebrate World",
//                "Mega-Volcanoes",
//                "Toxic Ash",
//                "Ozone Destroyed",
//                "Acid Rain",
//                "Carbon Amok",
//                "Scorching Earth",

//                "Triassic Extinction",
//                "Crocodile World",
//                "Pangean Rift",
//                "Carbon Amok (Again)",

//                "Cretaceous Extinction",
//                "Deccan Death Traps",
//                "Killer Space Rock!",
//                "Shook and Boom",
//                "Quake and Slide",
//                "Meteorite Bombs",
//                "Broiled Earth",
//                "Day into Night",

//                "Anthropocene Extinction",
//                "Thermal Maximum",
//                "Ice Age (Again)",
//                "Anthropocene",
//                "Human Impact",
//                "Extinction Threats",
//                "Climate Tipping Point",
//                "Dead Oceans?",
//                "Nuclear Winter?",
//                "Killer Space Rock?",
//                "Galactic Hazards?",

//                "Solar Apocalypse",
//                "An Existential Question",
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
    public String lifeAfterApocalypse(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String lifeAfterApocalypseGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String lifeAfterApocalypseGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String lifeAfterApocalypseGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String lifeAfterApocalypseUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
