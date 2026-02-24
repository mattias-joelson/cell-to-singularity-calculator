package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.ThePriceOfThrust;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ThePriceOfThrustController {

    private static final String GARDEN_GET = "/priceofthrust";
    private static final String GARDEN_GENERATOR_UPDATE = "/priceofthrust-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/priceofthrust-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/priceofthrust-generator-decrement";
    private static final String GARDEN_UPGRADE = "/priceofthrust-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public ThePriceOfThrustController() {
        garden = ThePriceOfThrust.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.clearUpgradesBought();
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Future Money", 0);
        setGeneratorCount("Electronic Money", 0);
        setGeneratorCount("Paper Money", 0);
        setGeneratorCount("Coins", 0);
        setGeneratorCount("Commodity Currency", 1);

        String[] boughtUpgrades = {
//                "Debt",
//                "Tally Sticks",
//                "Bartering",
//                "Wampum",
//                "Recordkeeping",
//                "Intrinsic Value",
//                "Precious Metals",
//                "Metallurgy",
//                "Cowrie Shells",
//                "Bronze Shells",
//                "Government Mints",
//                "Shekels", // coinage
//                "Rai Stones", // coinage
//                "Quipu", // banknotes

//                "Lydian Coinage",
//                "Chinese Coins",
//                "Interest",
//                "Usury",
//                "Roman Coinage",
//                "Carolingian Coinage",
//                "Negotiable Instruments",
//                "Banking",
//                "Iconography",
//                "Deposit Lending",
//                "Counterfeiting",
//                "Credit", // banknotes
//                "Piece of Eight", // banknotes
//                "Taxation", // credit

//                "Flying Money",
//                "Central Bank",
//                "Gold Standard",
//                "Fiat Money",
//                "Stock Market",
//                "Speculative Bubbles",
//                "Inflation",
//                "ATM", // credit
//                "Euro", // cryptocurrency

//                "Information Security",
//                "Payment Cards",
//                "Online Banking",
//                "e-commerce",
//                "Wire Transfer",
//                "Mobile Payment",
//                "ETF",
//                "Bank Run", // cryptocurrency
//                "Algorithmic Trading", // cryptocurrency

//                "Darwinium Cube",
//                "Blockchain",
//                "Cryptocurrency",
//                "Virtual Currencies",
//                "What's next?",
//                "Unregulated",
//                "One world, One Currency?",
//                "Decentralized Utopia?",
//                "Post-scarcity Society?",
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
    public String priceOfThrust(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String priceOfThrustGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String priceOfThrustGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String priceOfThrustGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String priceOfThrustUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
