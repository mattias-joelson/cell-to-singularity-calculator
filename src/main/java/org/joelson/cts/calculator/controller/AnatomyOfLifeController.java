package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.AnatomyOfLife;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AnatomyOfLifeController {

    private static final String GARDEN_CHALLENGE_LEVEL = "/anatomyoflife-challenge-level";
    private static final String GARDEN_BOOST = "/anatomyoflife-boost";
    private static final String GARDEN_PRODUCTION_INCREASE = "/anatomyoflife-production-increase";
    private static final String GARDEN_GET = "/anatomyoflife";
    private static final String GARDEN_GENERATOR_UPDATE = "/anatomyoflife-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/anatomyoflife-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/anatomyoflife-generator-decrement";
    private static final String GARDEN_UPGRADE = "/anatomyoflife-upgrade";

    private final ExplorationUpdater updater;

    public AnatomyOfLifeController() {
        updater = new ExplorationUpdater(AnatomyOfLife::createGarden, GARDEN_CHALLENGE_LEVEL, GARDEN_BOOST,
                GARDEN_PRODUCTION_INCREASE, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        Garden garden = updater.getGarden();
        GardenState state = updater.getState();
        state.clearUpgradesBought();
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Liver", 0);
        setGeneratorCount("Kidneys", 0);
        setGeneratorCount("Stomach", 0);
        setGeneratorCount("Skin", 0);
        setGeneratorCount("Lungs", 0);
        setGeneratorCount("Muscles", 0);
        setGeneratorCount("Skeleton", 0);
        setGeneratorCount("Endocrine System", 0);
        setGeneratorCount("Brain", 0);
        setGeneratorCount("Heart", 1);

        String[] boughtUpgrades = {
//                "Spinal Cord",
//                "Nerves",
//                "Brain Stem",
//                "Right Atrium", // deoxygenated blood
//                "Right Ventricle", // deoxygenated blood
//                "Digestion", // deoxygenated blood
//                "Left Atrium", // deoxygenated blood
//                "Left Ventricle", // deoxygenated blood

//                "Frontal Lobe",
//                "Occipital Lobe",
//                "Eyes",
//                "Temporal Lobe",
//                "Parietal Lobe",
//                "Nose",
//                "Ears",
//                "Mouth",

//                "Hypothalamus",
//                "Pituitary",
//                "Adrenal",
//                "Gonads",
//                "Pineal",
//                "Thyroid",

//                "Bones",
//                "Bone Marrow",
//                "Cartilage",
//                "Joints",

//                "Skeletal Muscles",
//                "Tendons",
//                "Smooth Muscles",

//                "Bronchi", // deoxygenated blood
//                "Aveoli", // deoxygenated blood

//                "Epidermis",
//                "Dermis",
//                "Hypodermis",

//                "Small Intestine",
//                "Large Intestine",

//                "Ureters",
//                "Bladder",

//                "Gallbladder",
//                "Pancreas",
//                "Aorta",
        };

        for (String upgradeName : boughtUpgrades) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
        state.updateGeneratorStates(garden);
    }

    private void setGeneratorCount(String generatorName, int count) {
        Generator generator = updater.getGarden().getGenerator(generatorName);
        updater.getState().setGeneratorCount(generator, count);
    }

    @PostMapping(GARDEN_CHALLENGE_LEVEL)
    public String anatomyOfLifeChallengeLevel(Model model, String value) {
        return updater.updateChallengeLevel(model, value);
    }

    @PostMapping(GARDEN_BOOST)
    public String anatomyOfLifeBoost(Model model, String value) {
        return updater.updateBoost(model, value);
    }

    @PostMapping(GARDEN_PRODUCTION_INCREASE)
    public String anatomyOfLifeProductionIncrease(Model model, String value) {
        return updater.updateProductionIncrease(model, value);
    }

    @GetMapping(GARDEN_GET)
    public String anatomyOfLife(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String anatomyOfLifeGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String anatomyOfLifeGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String anatomyOfLifeGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String anatomyOfLifeUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
