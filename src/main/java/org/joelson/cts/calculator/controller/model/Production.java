package org.joelson.cts.calculator.controller.model;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.CurrencyMapping;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Production {

    private final Garden garden;
    private final GardenState state;
    private final Map<String, Amount> totalProduction;
    private final Map<String, GeneratorProduction> generatorProductions;

    private Production(
            Garden garden, GardenState state, Map<String, Amount> totalProduction,
            Map<String, GeneratorProduction> generatorProductions) {
        this.garden = garden;
        this.state = state;
        this.totalProduction = totalProduction;
        this.generatorProductions = generatorProductions;
    }

    public String getName() {
        return garden.getName();
    }

    public List<String> getTotalProductions() {
        List<String> totalProductionList = new ArrayList<>();
        for (String currency : garden.getCurrencies()) {
            totalProductionList.add(totalProduction.get(currency).asString());
        }
        return totalProductionList;
    }

    public Amount getTotalProduction(String currency) {
        return totalProduction.get(currency);
    }

    public List<GeneratorProduction> getGeneratorProductions() {
        List<GeneratorProduction> generatorProductionList = new ArrayList<>();
        List<Generator> unlockedGenerators = garden.getUnlockedGenerators(state);
        for (String fromCurrency : garden.getCurrencies()) {
            for (String toCurrency : garden.getCurrencies()) {
                CurrencyMapping currencyMapping = new CurrencyMapping(fromCurrency, toCurrency);
                for (Generator generator : unlockedGenerators) {
                    if (generator.getMapping().equals(currencyMapping)) {
                        generatorProductionList.add(generatorProductions.get(generator.getName()));
                    }
                }
            }
        }
        return generatorProductionList.reversed();
    }

    public static Production calculateProduction(Garden garden, GardenState state) {
        Map<String, GeneratorProduction> generatorProductions = new HashMap<>();
        Map<String, Amount> totalProductions = new HashMap<>();
        Production that = new Production(garden, state, totalProductions, generatorProductions);
        for (Generator generator : garden.getUnlockedGenerators(state).reversed()) {
            GeneratorState generatorState = state.getGeneratorState(generator);
            GeneratorProduction generatorProduction = GeneratorProduction.calculate(that, generator, generatorState);
            generatorProductions.put(generator.getName(), generatorProduction);
            Amount production = generatorProduction.getProductionAmount();
            String currency = production.currency();
            totalProductions.put(currency,
                    totalProductions.getOrDefault(currency, new Amount(currency, 0)).plus(production));
        }
        return that;
    }
}
