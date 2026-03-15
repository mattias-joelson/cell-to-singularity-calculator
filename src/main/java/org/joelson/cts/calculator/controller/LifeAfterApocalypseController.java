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
//                "Cambrian Explosion", // check
//                "Trilobites", // check
//                "Placoderms", // check
//                "Tetrapods", // check
//                "Archosaurs", // check
//                "Tyrannosaurus Rex", // check
//                "Homo Sapiens", // check
//                "Unknown Species", // check
//                "Space Loophole", // check
//                "Life Beyond", // check
//                "Ordovician Extinction", // check
//                "Devonian Extinction", // check
//                "Permian Extinction", // check
//                "Triassic Extinction", // check
//                "Cretaceous Extinction", // check
//                "Anthropocene Extinction", // check
//                "Solar Apocalypse", // check

//                "Ordovician Extinction", // check
//                "Trilobite World", // check
//                "Asteroid Bombardment", // check
//                "Continental Collision", // check
//                "Carbon Tipping Point", // check
//                "Ice Age!", // check
//                "Anoxic Oceans", // check

//                "Devonian Extinction", // check
//                "Land Grab", // check
//                "Killer Trees", // check
//                "Killer Plankton", // check
//                "Extinction Pulses", // check

//                "Permian Extinction", // check
//                "Vertebrate World", // check
//                "Mega-Volcanoes", // check
//                "Toxic Ash", // check
//                "Ozone Destroyed", // check
//                "Acid Rain", // check
//                "Carbon Amok", // check
//                "Scorching Earth", // check

//                "Triassic Extinction", // check
//                "Crocodile World", // check
//                "Pangean Rift", // check
//                "Carbon Amok (Again)", // check

//                "Cretaceous Extinction", // check
//                "Deccan Death Traps", // check
//                "Killer Space Rock!", // check
//                "Shook and Boom", // check
//                "Quake and Slide", // check
//                "Meteorite Bombs", // check
//                "Broiled Earth", // check
//                "Day into Night", // check

//                "Anthropocene Extinction", // check
//                "Thermal Maximum", // check
//                "Ice Age (Again)", // check
//                "Anthropocene", // check
//                "Human Impact", // check
//                "Extinction Threats", // check
//                "Climate Tipping Point", // check
//                "Dead Oceans?", // check
//                "Nuclear Winter?", // check
//                "Killer Space Rock?", // check
//                "Galactic Hazards?", // check

//                "Solar Apocalypse", // check
//                "An Existential Question", // check
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
