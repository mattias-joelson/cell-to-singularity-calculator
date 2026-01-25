package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.MindMachines;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MindMachinesController {

    private static final String GARDEN_GET = "/mindmachines";
    private static final String GARDEN_GENERATOR_UPDATE = "/mindmachines-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/mindmachines-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/mindmachines-generator-decrement";
    private static final String GARDEN_UPGRADE = "/mindmachines-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public MindMachinesController() {
        garden = MindMachines.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Artificial Intelligence", 1);
        setGeneratorCount("Evils and Perils", 0);
        setGeneratorCount("AI Tools", 0);
        setGeneratorCount("Generative AI", 0);
        setGeneratorCount("Analytic AI", 0);
        setGeneratorCount("Mechanical Brain", 1);

        String[] boughtUpdates = {
//                "Future AI",
//                "Quantum Computing",
//                "Biocomputers",
//                "Uploaded Mind",
//                "Philosophical Zombies",
//                "Morality AI",
//                "Sentience",
//                "Artificial General Intelligence",

//                "Analytic Engine",
//                "Binary Code",
//                "Logic Switches",
//                "Input, Output",
//                "Algorithms",
//                "Learning Loops",
//                "Love Letters",
//                "Imitation Game",
//                "ELIZA Therapy Bot",
//                "Stochastic Parrot",

//                "Turochamp",
//                "Logical Thinking",
//                "Data Processing",
//                "Brute Force",
//                "Heuristics",
//                "Fuzzy Logic",
//                "Games, Mastered",
//                "Expert Systems",
//                "Context",
//                "Black Box",

//                "Electric Brain",
//                "Neural Network",
//                "Machine Learning",
//                "Training Sets",
//                "Big Data",
//                "Large Language Models",
//                "GPTs",
//                "Deep Learning",
//                "Natural Language",
//                "Hallucinations",
//                "AI Evolved",

//                "Personal Assistants",
//                "Customer Service",
//                "Therapists",
//                "Translators and Interpreters",
//                "AI Avatars",
//                "Self-Driving Vehicles",
//                "AI Drones and Carts",
//                "Space Explorers",
//                "Statistical Forecasting",
//                "Smarter Systems",
//                "Scientific Discoveries",
//                "Mind Reading",

//                "Bad Actors",
//                "Gullibility",
//                "Human Bias",
//                "Resistance",
//                "Weak Laws",
//                "AI Workforce",
//                "Cyberattacks and Warfare",
//                "Machine Unlearning",
//                "Red Teaming",
//                "Tough Decisions",
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
    public String mindMachines(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String mindMachinesGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String mindMachinesGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String mindMachinesGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String mindMachinesUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
