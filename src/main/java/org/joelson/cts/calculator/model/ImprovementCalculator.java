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

    public static Map<String, Float> calculateProduction(Garden garden, GardenState state) {
        Map<String, Float> totalProduction = new HashMap<>();
        for (Generator generator : garden.getUnlockedGenerators(state).reversed()) {
            GeneratorState generatorState = state.getGeneratorState(generator);
            int count = generatorState.count();
            String currencyName = generator.getBaseProduction().currency();
            float baseProduction = generator.getBaseProduction().amount();
            float efficiency = generatorState.efficiency();
            float production = baseProduction * efficiency * count;
            System.out.printf("Generator %s: count %d (next %s), base %s, each %s, total %s%n",
                    generator.getName(), count, generator.getCost(count).asString(),
                    new Amount(currencyName, baseProduction).asString(),
                    new Amount(currencyName, baseProduction * efficiency).asString(),
                    new Amount(currencyName, production).asString());
            totalProduction.put(currencyName, totalProduction.getOrDefault(currencyName, 0f) + production);
        }
        for (String currency : garden.getCurrencies()) {
            System.out.printf("%s production: %.2e%n", currency, totalProduction.get(currency));
        }
        System.out.println();
        return totalProduction;
    }

    public static Map<CurrencyMapping, Improvement> calculateImprovement(Garden garden, GardenState state) {
        Map<String, Float> totalProduction = calculateProduction(garden, state);

        Map<CurrencyMapping, List<Improvement>> improvements = new HashMap<>();
        for (Generator generator : garden.getUnlockedGenerators(state).reversed()) {
            improvements.computeIfAbsent(generator.getMapping(), _ -> new ArrayList<>()).add(
                    GeneratorImprovement.create(generator, state));
        }
        for (Upgrade upgrade : garden.getUnlockedUpgrades(state)) {
            if (!state.isUpgradeBought(upgrade)) {
                for (UpgradeEffect effect : upgrade.getEffects()) {
                    Improvement improvement = UpgradeImprovement.create(upgrade, effect, state);
                    improvements.computeIfAbsent(improvement.getMapping(), _ -> new ArrayList<>()).add(improvement);
                }
            }
        }

        Map<CurrencyMapping, Improvement> improvementMap = new HashMap<>();
        for (Map.Entry<CurrencyMapping, List<Improvement>> improvementsEntry : improvements.entrySet()) {
            Improvement improvement = calculateMappingImprovement(improvementsEntry, totalProduction, improvements);
            improvementMap.put(improvementsEntry.getKey(), improvement);
            System.out.println();
        }
        return improvementMap;
    }

    private static Improvement calculateMappingImprovement(
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
        return calculateSpecificImprovement(totalProduction, improvements, best);
    }

    private static Improvement calculateSpecificImprovement(
            Map<String, Float> totalProduction, Map<CurrencyMapping, List<Improvement>> improvements,
            Improvement best) {
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
            System.out.printf(">>> Do %s before %s%n", bestImprovement.getName(), best.getName());
            return calculateSpecificImprovement(totalProduction, improvements, bestImprovement);
        }

        System.out.printf("*** Do %s by itself%n", best.getName());
        return best;
    }

    public static void calculateImprovementNew(Garden garden, GardenState state) {
        Map<String, Float> totalProduction = calculateProduction(garden, state);

        for (String currency : garden.getCurrencies()) {
            System.out.printf("Optimize for %s:%n", currency);

            List<Improvement> currencyImprovements = new ArrayList<>();
            List<Improvement> otherImprovements = new ArrayList<>();
            for (Generator generator : garden.getGenerators().reversed()) {
                if (generator.getBaseProduction().currency().equals(currency)) {
                    GeneratorImprovement improvement = GeneratorImprovement.create(generator, state);
                    if (generator.getBaseCost().currency().equals(currency)) {
                        currencyImprovements.add(improvement);
                    } else {
                        otherImprovements.add(improvement);
                    }
                }
            }
            for (Upgrade upgrade : garden.getUpgrades()) {
                if (!state.isUpgradeBought(upgrade)) {
                    for (UpgradeEffect effect : upgrade.getEffects()) {
                        if (effect.generator().getBaseProduction().currency().equals(currency)) {
                            Improvement improvement = UpgradeImprovement.create(upgrade, effect, state);
                            if (improvement.getCost().currency().equals(currency)) {
                                currencyImprovements.add(improvement);
                            } else {
                                otherImprovements.add(improvement);
                            }
                        }
                    }
                }
            }
            currencyImprovements.sort(Comparator.comparing(Improvement::getRatio));
            otherImprovements.sort(Comparator.comparing(Improvement::getRatio));

            for (Improvement improvement : currencyImprovements.reversed()) {
                Amount cost = improvement.getCost();
                float time = cost.amount() / totalProduction.get(cost.currency());
                System.out.printf("%s: cost %s, increase %s, ratio %.7f, time %s%n",
                        improvement.getName(), cost.asString(), improvement.getIncrease().asString(),
                        improvement.getRatio(), durationString(time));
            }
            for (Improvement improvement : otherImprovements.reversed()) {
                Amount cost = improvement.getCost();
                float time = cost.amount() / totalProduction.get(cost.currency());
                System.out.printf("%s: cost %s, increase %s, ratio %.7f, time %s%n",
                        improvement.getName(), cost.asString(), improvement.getIncrease().asString(),
                        improvement.getRatio(), durationString(time));
            }

            Improvement bestImprovement;
            if (currencyImprovements.isEmpty()) {
                continue;
            } else if (currencyImprovements.size() == 1) {
                bestImprovement = currencyImprovements.getLast();
                System.out.printf("Only %s to %s improvement is %s.%n", currency, currency,
                        bestImprovement.getName());
            } else {
                bestImprovement = currencyImprovements.getLast();
                System.out.printf("Multiple candidates, choosing %s%n", bestImprovement.getName());

                Amount bestCost = bestImprovement.getCost();
                float bestTime = bestCost.amount() / totalProduction.get(bestCost.currency());
                float shortestTime = bestTime;
                Improvement otherImprovement = null;
                System.out.printf("%s: time %s%n", bestImprovement.getName(), durationString(bestTime));

                for (Improvement candidate : currencyImprovements) {
                    if (candidate == bestImprovement) {
                        continue;
                    }
                    Amount candidateCost = candidate.getCost();
                    float candidateTime = candidateCost.amount() / totalProduction.get(candidateCost.currency());
                    Amount candidateIncrease = candidate.getIncrease();
                    float bestImprovedTime = bestCost.amount() / (totalProduction.get(candidateIncrease.currency())
                            + candidateIncrease.amount());
                    float totalTime = candidateTime + bestImprovedTime;
                    System.out.printf("%s and %s: %s and %s = %s%n", candidate.getName(), bestImprovement.getName(),
                            durationString(candidateTime), durationString(bestImprovedTime), durationString(totalTime));
                    if (totalTime < shortestTime) {
                        shortestTime = totalTime;
                        otherImprovement = candidate;
                    }
                }
                if (otherImprovement != null) {
                    System.out.printf("Do %s before %s%n", otherImprovement.getName(), bestImprovement.getName());

                }
            }
            float bestTime = timeUntil(bestImprovement, totalProduction);
            System.out.printf("Time for %s: %s%n", bestImprovement.getName(), durationString(bestTime));

            float timeLeft = bestTime;
            List<Improvement> addedImprovements = new ArrayList<>();
            for (Improvement improvement : otherImprovements.reversed()) {
                float time = timeUntil(improvement, totalProduction);
                System.out.printf("Time for %s: %s%n", improvement.getName(), durationString(time));
                if (time < bestTime) {
                    float timedProduction = time * totalProduction.get(currency);
                    float improvedBestTime =
                            time + (bestImprovement.getCost().amount() - timedProduction) / (totalProduction.get(
                                    currency) + improvement.getIncrease().amount());
                    System.out.printf("Improved time for %s: %s when %s after %s.%n", bestImprovement.getName(),
                            durationString(improvedBestTime), improvement.getName(), durationString(time));
                }
            }

            System.out.println();
        }
    }

    private static float timeUntil(Improvement improvement, Map<String, Float> totalProduction) {
        return improvement.getCost().amount() / totalProduction.get(improvement.getCost().currency());
    }
}
