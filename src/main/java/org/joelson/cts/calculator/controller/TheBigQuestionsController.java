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

        String[] boughtUpdates = {
//                "What is Right?", // check
//                "What is Society?", // check
//                "What is True?", // check
//                "What is Real?", // check
//                "What am I?", // check
//                "Why?", // check
//                "The Answer Is...", // check

//                "What is Ethical?", // check
//                "Dharma", // check
//                "What is Moral?", // check
//                "Moral Skepticism", // check
//                "Utilitarianism", // check
//                "Humanism", // check
//                "Intuitive Ethics", // check
//                "Code of Law", // check
//                "Stoicism", // check

//                "Who Should Rule?", // check
//                "Autocracy", // check
//                "What is Power?", // check
//                "Divine Right", // check
//                "Machiavellianism", // check
//                "Human Rights", // check
//                "Individualism", // check
//                "Right to Revolt", // check
//                "Collectivism", // check
//                "Anarchism", // check
//                "Thinking...", // check

//                "What Do We Know?", // check
//                "Pramana", // check
//                "How Do We Know?", // check
//                "Socratic Method", // check
//                "Rationalism", // check
//                "Empiricism", // check
//                "Skepticism", // check
//                "What's the Answer?", // check

//                "How Did It Start?", // check
//                "Cosmology", // check
//                "Why Am I Aware?", // check
//                "Identity", // check
//                "Ship of Theseus", // check
//                "Space & Time", // check
//                "Theology", // check
//                "Mind & Matter", // check
//                "Ontology", // check
//                "Ascetism", // check
//                "Theism", // check

//                "What Is Spirit?", // check
//                "Theory of Forms", // check
//                "What Is Humanity?", // check
//                "Sapience", // check
//                "Soul", // check
//                "State of Nature", // check
//                "Nondualism", // check
//                "Fate & Free Will", // check
//                "Solipsism", // check
//                "Transcendence", // check

//                "What's the Good Life?", // check
//                "What's the Point?", // check
//                "Nihilism", // check
//                "Hedonism", // check
//                "Absurdism", // check
//                "Existentialism", // check
//                "42", // check
//                "What's the Question?", // check
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
