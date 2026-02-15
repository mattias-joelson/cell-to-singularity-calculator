package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.ACoevolutionLoveStory;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ACoevolutionLoveStoryController {

    private static final String GARDEN_GET = "/coevolutionlovestory";
    private static final String GARDEN_GENERATOR_UPDATE = "/coevolutionlovestory-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/coevolutionlovestory-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/coevolutionlovestory-generator-decrement";
    private static final String GARDEN_UPGRADE = "/coevolutionlovestory-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public ACoevolutionLoveStoryController() {
        garden = ACoevolutionLoveStory.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.clearUpgradesBought();
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Human", 0);
        setGeneratorCount("The Hive Life", 0);
        setGeneratorCount("Food Banking", 0);
        setGeneratorCount("Primitive Bees", 0);
        setGeneratorCount("Bees", 0);
        setGeneratorCount("Bribery and Deception", 0);
        setGeneratorCount("The Art of Attraction", 0);
        setGeneratorCount("Primitive Flowers", 0);
        setGeneratorCount("Flowers", 1);

        String[] boughtUpgrades = {
//                "Naked Seeds", // check
//                "Bloom Boom", // check
//                "Size and Structure", // check
//                "Pollen Tag", // check
//                "Pollination Pinnacle", // check
//                "A New Suitor", // check

//                "Self-Marriage", // check
//                "Self-Control", // check
//                "Stranger Marriage", // check
//                "The Showy Magnolia", // check

//                "Come One or All?", // check
//                "Dandelion Welcome Mat", // check
//                "Snappy Snapdragons", // check
//                "Early Willows", // check
//                "Color and Pattern", // check

//                "Nectar Bribes", // check
//                "Nectar Safeguards", // check
//                "Kidnappers!", // check
//                "Murderers!", // check
//                "Floral Oils", // check
//                "Orchid Flowers", // check

//                "A New Suitor", // check
//                "Wasteful Wind", // check
//                "Flight", // check
//                "The Hungry Beetle", // check
//                "The Vegetarian Wasp", // check
//                "Solitary Nests", // check
//                "UV Vision", // check
//                "Happy Ending", // check
//                "Apex of Evolution", // check

//                "Johnston's Organ", // check
//                "The Competition", // check
//                "Birds Not Bees", // check
//                "Bumblebee Exclusive", // check

//                "Very Hairy Body", // check
//                "Pollen Brushes", // check
//                "Bristled Baskets", // check
//                "Buzz Pollination", // check
//                "Mechanical Mouthparts", // check
//                "Bandit Bees", // check
//                "All-Purpose Oils", // check
//                "Orchid Bees", // check

//                "Baby Bees", // check
//                "Old Foragers", // check
//                "Waggle Dance", // check

//                "The Crops We Crave", // check
//                "Our Favorite Bee", // check
//                "Africanized Bees", // check
//                "Wild Decline", // check
//                "Habitat Destruction", // check
//                "Varroa Destructor", // check
//                "Colony Collapse Disorder", // check
//                "Till Death Do Us Part?", // check
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
    public String aCoevolutionLoveStory(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String aCoevolutionLoveStoryGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String aCoevolutionLoveStoryGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String aCoevolutionLoveStoryGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String aCoevolutionLoveStoryUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
