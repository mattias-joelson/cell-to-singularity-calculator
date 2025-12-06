package org.joelson.cts.calculator.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.joelson.cts.calculator.util.DurationToolkit.durationString;

public class ImprovementCalculator {

    private ImprovementCalculator() throws InstantiationException {
        throw new InstantiationException("Should not be instantiated!");
    }

    public static Map<String, Float> calculateProduction(Garden garden) {
        Map<String, Float> totalProduction = new HashMap<>();
        for (Generator generator : garden.getGenerators().reversed()) {
            int count = generator.getCount();
            String currencyName = generator.getBaseProduction().currency();
            float baseProduction = generator.getBaseProduction().amount();
            float efficiency = generator.getEfficiency();
            float production = baseProduction * efficiency * count;
            System.out.printf("Generator %s: count %d (next %.2e), base %.2e %s, each %.2e %s, total %.3e %s%n",
                    generator.getName(), count, generator.getCost(count).amount(), baseProduction, currencyName,
                    baseProduction * efficiency, currencyName, production, currencyName);
            totalProduction.put(currencyName, totalProduction.getOrDefault(currencyName, 0f) + production);
        }
        for (String currency : garden.getCurrencies()) {
            System.out.printf("%s production: %.2e%n", currency, totalProduction.get(currency));
        }
        System.out.println();
        return totalProduction;
    }

    public static void calculateImprovement(Garden garden) {
        Map<String, Float> totalProduction = calculateProduction(garden);

        Map<CurrencyMapping, List<Improvement>> improvements = new HashMap<>();
        for (Generator generator : garden.getGenerators().reversed()) {
            improvements.computeIfAbsent(generator.getMapping(), _ -> new ArrayList<>()).add(generator);
        }
        for (Upgrade upgrade : garden.getUpgrades()) {
            if (!upgrade.isBought()) {
                for (UpgradeEffect effect : upgrade.getEffects()) {
                    Improvement improvement = new UpgradeImprovement(upgrade, effect);
                    improvements.computeIfAbsent(improvement.getMapping(), _ -> new ArrayList<>()).add(improvement);
                }
            }
        }

        for (Map.Entry<CurrencyMapping, List<Improvement>> improvementsEntry : improvements.entrySet()) {
            calculateMappingImprovement(improvementsEntry, totalProduction, improvements);
        }
    }

    private static void calculateMappingImprovement(
            Map.Entry<CurrencyMapping, List<Improvement>> improvementsEntry, Map<String, Float> totalProduction,
            Map<CurrencyMapping, List<Improvement>> improvements) {
        System.out.printf("Improvements from %s to %s:%n", improvementsEntry.getKey().from(),
                improvementsEntry.getKey().to());
        List<Improvement> mappingImprovements = improvementsEntry.getValue();
        mappingImprovements.sort(Comparator.comparing(Improvement::getRatio));
        for (Improvement improvement : mappingImprovements.reversed()) {
            Amount cost = improvement.getCost();
            float ratio = improvement.getRatio();
            Amount increase = improvement.getIncrease();
            float time = cost.amount() / totalProduction.get(cost.currency());
            System.out.printf("%s: cost %s, increase %s, ratio %.7f, time %s%n",
                    improvement.getName(), cost.asString(), increase.asString(), ratio, durationString(time));
        }
        Improvement best = mappingImprovements.getLast();
        System.out.printf("Best: %s, (%s)%n", best.getName(), best.getMapping());
        List<Improvement> candidates = new ArrayList<>();
        for (Map.Entry<CurrencyMapping, List<Improvement>> candidateEntry : improvements.entrySet()) {
            if (!candidateEntry.getKey().to().equals(best.getCost().currency())) {
                continue;
            }
            for (Improvement candidate : candidateEntry.getValue()) {
                if (candidate == best) {
                    continue;
                }
                candidates.add(candidate);
                System.out.printf("  candidate %s%n", candidate.getName());
            }
        }
        Amount bestCost = best.getCost();
        float bestTime = bestCost.amount() / totalProduction.get(bestCost.currency());
        float shortestTime = bestTime;
        Improvement bestImprovement = null;
        System.out.printf("%s: time %s%n", best.getName(), durationString(bestTime));
        for (Improvement candidate : candidates) {
            Amount candidateCost = candidate.getCost();
            float candidateTime = candidateCost.amount() / totalProduction.get(candidateCost.currency());
            Amount candidateIncrease = candidate.getIncrease();
            float bestImprovedTime = bestCost.amount() / (totalProduction.get(candidateIncrease.currency())
                    + candidateIncrease.amount());
            float totalTime = candidateTime + bestImprovedTime;
            System.out.printf("%s and %s: %s and %s = %s%n", candidate.getName(), best.getName(),
                    durationString(candidateTime), durationString(bestImprovedTime), durationString(totalTime));
            if (totalTime < shortestTime) {
                shortestTime = totalTime;
                bestImprovement = candidate;
            }
        }
        if (bestImprovement != null) {
            System.out.printf("Do %s before %s%n", bestImprovement.getName(), best.getName());

        }

        System.out.println();
    }
}
