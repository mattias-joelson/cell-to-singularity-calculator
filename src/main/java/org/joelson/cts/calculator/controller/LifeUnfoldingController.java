package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.LifeUnfolding;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LifeUnfoldingController {

    private static final String GARDEN_CHALLENGE_LEVEL = "/lifeunfolding-challenge-level";
    private static final String GARDEN_BOOST = "/lifeunfolding-boost";
    private static final String GARDEN_PRODUCTION_INCREASE = "/lifeunfolding-production-increase";
    private static final String GARDEN_GET = "/lifeunfolding";
    private static final String GARDEN_GENERATOR_UPDATE = "/lifeunfolding-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/lifeunfolding-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/lifeunfolding-generator-decrement";
    private static final String GARDEN_UPGRADE = "/lifeunfolding-upgrade";

    private final ExplorationUpdater updater;

    public LifeUnfoldingController() {
        updater = new ExplorationUpdater(LifeUnfolding::createGarden, GARDEN_CHALLENGE_LEVEL, GARDEN_BOOST,
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

        setGeneratorCount("Mother", 1);
        setGeneratorCount("Month Nine", 0);
        setGeneratorCount("Month Eight", 0);
        setGeneratorCount("Month Seven", 0);
        setGeneratorCount("Month Six", 0);
        setGeneratorCount("Month Five", 0);
        setGeneratorCount("Month Four", 0);
        setGeneratorCount("Month Three", 0);
        setGeneratorCount("Month Two", 0);
        setGeneratorCount("Month One", 0);

        String[] boughtUpgrades = {
//                "Zygote",
//                "Multiples",
//                "Blastocyst",
//                "Embryo",
//                "Placenta",
//                "Amniotic Sac",

//                "Neural Tube",
//                "Heart Tube",
//                "The Body Forms",
//                "Umbilical Cord",

//                "Fetus",
//                "Amniotic Fluid",

//                "Reproductive Organs",
//                "Organ Function",
//                "Rudimentary Senses",

//                "Fetal Skin",
//                "Kicks and Punches",

//                "Bone Marrow",
//                "Hearing",
//                "Lungs",
//                "Pre-Term Birth",

//                "Opaque Skin",
//                "Blinking",

//                "Brain",
//                "Independence",

//                "Fluid Balance",
//                "Cranial Plate",
//                "Hair",
//                "Immune System",
//                "Lightening",

//                "Reproductive System",
//                "Egg",
//                "Sperm",
//                "Fertilization",
//                "Diet Limits",
//                "Confirmation",
//                "Morning Sickness",
//                "Cravings",
//                "Milk Glands",
//                "False Labor",
//                "Labor",
//                "Water Breaking",
//                "Dilation",
//                "Expulsion",
//                "Birth", // push!
//                "Afterbirth", // push!
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
    public String lifeUnfoldingChallengeLevel(Model model, String value) {
        return updater.updateChallengeLevel(model, value);
    }

    @PostMapping(GARDEN_BOOST)
    public String lifeUnfoldingBoost(Model model, String value) {
        return updater.updateBoost(model, value);
    }

    @PostMapping(GARDEN_PRODUCTION_INCREASE)
    public String lifeUnfoldingProductionIncrease(Model model, String value) {
        return updater.updateProductionIncrease(model, value);
    }

    @GetMapping(GARDEN_GET)
    public String lifeUnfolding(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String lifeUnfoldingGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String lifeUnfoldingGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String lifeUnfoldingGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String lifeUnfoldingUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
