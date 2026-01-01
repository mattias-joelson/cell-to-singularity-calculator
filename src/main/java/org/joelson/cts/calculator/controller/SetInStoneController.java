package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.SetInStone;
import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorState;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.util.DurationToolkit;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SetInStoneController {

    private final Garden garden = SetInStone.createGarden(1, 1);
    private final GardenState state = new GardenState();

    private void initState() {
        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Gem", 0);
        setGeneratorCount("Crystal", 0);
        setGeneratorCount("Metamorphic Rock", 0);
        setGeneratorCount("Sedimentary Rock", 0);
        setGeneratorCount("Igneous Rock", 0);
        setGeneratorCount("Mineral", 1);

        String[] boughtUpdates = {
//                "Olivine", // check
//                "Quartz", // check
//                "Feldspars", // check
//                "Magma", // check
//                "Tuff", // rocks // check
//                "Clay", // check
//                "Rock Cycle", // check
//                "Sand", // check
//                "Sandstone", // rocks // check
//                "Calcite", // check
//                "Granite", // rocks // check
//                "Gneiss", // rocks // check
//                "Fluorite", // check
//                "Pegmatite", // rocks // check
//                "Coal", // rocks // check

//                "Dunite", // check
//                "Basalt", // check
//                "Andesite", // check
//                "Diorite", // check
//                "Scoria", // check
//                "Obsidian", // check

//                "Limestone", // check
//                "Siltstone", // check
//                "Coquina", // check
//                "Shale", // check
//                "Chalk", // check
//                "Flint", // check
//                "Natural Beauty", // check

//                "Marble", // check
//                "Slate", // check
//                "Schist", // check
//                "Anthracite", // check
//                "Jade", // crystal // check

//                "Amethyst", // check
//                "Jasper", // check
//                "Fulgurite", // rocks // check
//                "Topaz", // check
//                "Lapis Lazuli", // rocks // check
//                "Diamond", // check
//                "Pyrite", // check
//                "Lab-Grown Diamonds", // check

//                "Emerald", // check
//                "Aquamarine", // check
//                "Opal", // check
//                "Garnet", // check
//                "Sapphire", // check
//                "Ruby", // check
        };

        for (String upgradeName : boughtUpdates) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
    }

    private void setGeneratorCount(String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    @GetMapping("/setinstone")
    public String setInStone(Model model) {
        initState();
        return updateModel(model);
    }

    @PostMapping("/setinstone")
    public String updateSetInStone(Model model) {
        return updateModel(model);
    }

    private @NonNull String updateModel(Model model) {
        state.updateGeneratorStates(garden);
        GardenState initialState = state.copy();

        model.addAttribute("garden", garden);
        model.addAttribute("state", state);
        List<GeneratorProduction> generatorProductions = calculateGeneratorProduction(garden, state);
        model.addAttribute("generatorProductions", generatorProductions);
        List<String> totalProductions = calculateTotalProductions(garden, state);
        model.addAttribute("totalProductions", totalProductions);

        // all generators (unlocked)
        // all upgrades (unlocked)


        // actions

        return "setinstone";
    }

    public record GeneratorProduction(String name, int count, String next, String each, String total) {

    }

    public static List<GeneratorProduction> calculateGeneratorProduction(Garden garden, GardenState state) {
        List<GeneratorProduction> generatorProductions = new ArrayList<>();
        for (Generator generator : garden.getUnlockedGenerators(state).reversed()) {
            GeneratorState generatorState = state.getGeneratorState(generator);
            int count = generatorState.count();
            String currencyName = generator.getBaseProduction().currency();
            double baseProduction = generator.getBaseProduction().amount();
            float efficiency = generatorState.efficiency();
            if (generator.isTimed()) {
                double productionPerCycle = baseProduction * efficiency * count;
                float speed = generatorState.speed();
                float cycleTime = generator.getBaseChargeTime() / speed;
                double production = productionPerCycle / cycleTime;
                String productionString = String.format("%s in %s s, %s per second",
                        new Amount(currencyName, productionPerCycle).asString(),
                        DurationToolkit.durationString(cycleTime),
                        new Amount(currencyName, production).asString());
                generatorProductions.add(new GeneratorProduction(generator.getName(), count,
                        generator.getCost(count).asString(),
                        generator.getBaseProduction().multiplyBy(efficiency).asString(), productionString));
            } else {
                double production = baseProduction * efficiency * count;
                String productionString = String.format("%s", new Amount(currencyName, production).asString());
                generatorProductions.add(new GeneratorProduction(generator.getName(), count,
                        generator.getCost(count).asString(),
                        generator.getBaseProduction().multiplyBy(efficiency).asString(), productionString));
            }
        }
        return generatorProductions;
    }

    private List<String> calculateTotalProductions(Garden garden, GardenState state) {
        List<String> productionAmounts = new ArrayList<>();
        Map<String, Double> production = ImprovementCalculator.calculateProduction(garden, state);
        for (String currency : garden.getCurrencies()) {
            if (production.containsKey(currency)) {
                productionAmounts.add(new Amount(currency, production.get(currency)).asString());
            }
        }
        return productionAmounts;
    }

}
