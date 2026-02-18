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
        setGeneratorCount("Bribery and Deception", 0);
        setGeneratorCount("Food Banking", 0);
        setGeneratorCount("The Art of Attraction", 0);
        setGeneratorCount("Primitive Bees", 0);
        setGeneratorCount("Primitive Flowers", 0);
        setGeneratorCount("Bees", 0);
        setGeneratorCount("Flowers", 1);

        String[] boughtUpgrades = {
//                "Naked Seeds",
//                "Bloom Boom",
//                "Size and Structure",
//                "Pollen Tag",
//                "Pollination Pinnacle",
//                "A New Suitor",

//                "A New Suitor",
//                "Wasteful Wind",
//                "Flight",
//                "The Hungry Beetle",
//                "The Vegetarian Wasp",
//                "Solitary Nests",
//                "UV Vision",
//                "Happy Ending",
//                "Apex of Evolution",

//                "Self-Marriage",
//                "Self-Control",
//                "Stranger Marriage",
//                "The Showy Magnolia",

//                "Johnston's Organ",
//                "The Competition",
//                "Birds Not Bees",
//                "Bumblebee Exclusive",

//                "Come One or All?",
//                "Dandelion Welcome Mat",
//                "Snappy Snapdragons",
//                "Early Willows",
//                "Color and Pattern",

//                "Very Hairy Body",
//                "Pollen Brushes",
//                "Bristled Baskets",
//                "Buzz Pollination",
//                "Mechanical Mouthparts",
//                "Bandit Bees",
//                "All-Purpose Oils",
//                "Orchid Bees",

//                "Nectar Bribes",
//                "Nectar Safeguards",
//                "Kidnappers!",
//                "Murderers!",
//                "Floral Oils",
//                "Orchid Flowers",

//                "Baby Bees",
//                "Old Foragers",
//                "Waggle Dance",

//                "The Crops We Crave",
//                "Our Favorite Bee",
//                "Africanized Bees",
//                "Wild Decline",
//                "Habitat Destruction",
//                "Varroa Destructor",
//                "Colony Collapse Disorder",
//                "Till Death Do Us Part?",
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
