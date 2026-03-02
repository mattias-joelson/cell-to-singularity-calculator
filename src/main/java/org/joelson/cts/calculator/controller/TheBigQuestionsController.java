package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.TheBigQuestions;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TheBigQuestionsController {

    private static final String GARDEN_GET = "/bigquestions";
    private static final String GARDEN_GENERATOR_UPDATE = "/bigquestions-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/bigquestions-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/bigquestions-generator-decrement";
    private static final String GARDEN_UPGRADE = "/bigquestions-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public TheBigQuestionsController() {
        garden = TheBigQuestions.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.clearUpgradesBought();
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Meaning of Life", 0);
        setGeneratorCount("Spirit", 0);
        setGeneratorCount("Metaphysics", 0);
        setGeneratorCount("Epistemology", 0);
        setGeneratorCount("Political Philosophy", 0);
        setGeneratorCount("Ethics", 0);
        setGeneratorCount("Philosophy", 1);

        String[] boughtUpgrades = {
//                "What is Right?",
//                "What is Society?",
//                "What is True?",
//                "What is Real?",
//                "What am I?",
//                "Why?",
//                "The Answer Is...",

//                "What is Ethical?",
//                "Dharma",
//                "What is Moral?",
//                "Moral Skepticism",
//                "Utilitarianism",
//                "Humanism",
//                "Intuitive Ethics",
//                "Code of Law",
//                "Stoicism",

//                "Who Should Rule?",
//                "Autocracy",
//                "What is Power?",
//                "Divine Right",
//                "Machiavellianism",
//                "Human Rights",
//                "Individualism",
//                "Right to Revolt",
//                "Collectivism",
//                "Anarchism",
//                "Thinking...",

//                "What Do We Know?",
//                "Pramana",
//                "How Do We Know?",
//                "Socratic Method",
//                "Rationalism",
//                "Empiricism",
//                "Skepticism",
//                "What's the Answer?",

//                "How Did It Start?",
//                "Cosmology",
//                "Why Am I Aware?",
//                "Identity",
//                "Ship of Theseus",
//                "Space & Time",
//                "Theology",
//                "Mind & Matter",
//                "Ontology",
//                "Ascetism",
//                "Theism",

//                "What Is Spirit?",
//                "Theory of Forms",
//                "What Is Humanity?",
//                "Sapience",
//                "Soul",
//                "State of Nature",
//                "Nondualism",
//                "Fate & Free Will",
//                "Solipsism",
//                "Transcendence",

//                "What's the Good Life?",
//                "What's the Point?",
//                "Nihilism",
//                "Hedonism",
//                "Absurdism",
//                "Existentialism",
//                "42",
//                "What's the Question?",
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
    public String bigQuestions(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String bigQuestionsGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String bigQuestionsGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String bigQuestionsGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String bigQuestionsUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
