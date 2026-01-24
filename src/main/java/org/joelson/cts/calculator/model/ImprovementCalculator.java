package org.joelson.cts.calculator.model;

import org.joelson.cts.calculator.util.DurationToolkit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.joelson.cts.calculator.util.DurationToolkit.durationString;

public class ImprovementCalculator {

    private ImprovementCalculator() throws InstantiationException {
        throw new InstantiationException("Should not be instantiated!");
    }

    public static void singleCurrencyApproach(Garden garden, GardenState gardenState, List<String> actions) {
        GardenState state = gardenState.copy();
        String currency = garden.getCurrencies().getFirst();
        CurrencyMapping mapping = new CurrencyMapping(currency, currency);

        addUnlocked(garden, state, actions);
        for (int i = 0; i < 100; i += 1) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            ImprovementDescription improvementDescription =
                    ImprovementCalculator.calculateImprovement(garden, state).get(mapping);
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println();
            Improvement improvement = improvementDescription.improvement();
            if (improvement instanceof GeneratorImprovement(Generator generator, GeneratorState generatorState)) {
                int count = generatorState.count();
                actions.add(String.format("(%d) Generator %s: %d -> %d : %s",
                        i + 1, generator.getName(), count, count + 1, improvementDescription.description()));
                state.setGeneratorCount(generator, count + 1);
                if (count == 0) {
                    addUnlocked(garden, state, actions);
                    if (i >= 20) {
                        break;
                    }
                }
            } else if (improvement instanceof UpgradeImprovement upgradeImprovement) {
                Upgrade upgrade = upgradeImprovement.upgrade();
                UpgradeEffect effect = upgrade.getEffects().getFirst();
                actions.add(String.format("(%d) Upgrade %s (%s) : %s",
                        i + 1, upgrade.getName(), effect.generator().getName(), improvementDescription.description()));
                state.setUpgradeBought(upgrade);
                state.updateGeneratorStates(garden);
                addUnlocked(garden, state, actions);
                if (i >= 20) {
                    break;
                }
            } else {
                throw new NullPointerException();
            }
        }
    }

    public static void candidateApproach(Garden garden, GardenState state, List<String> actions) {
        addUnlocked(garden, state, actions);
        boolean possibleUnlock = false;
        for (int i = 0; i < 20 || !possibleUnlock; i += 1) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Map<CurrencyMapping, ImprovementDescription> improvementDescriptions =
                    ImprovementCalculator.calculateImprovement(garden, state);
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println();

            possibleUnlock = false;
            Map<Improvement, Set<Map.Entry<CurrencyMapping, ImprovementDescription>>> improvementMap = new HashMap<>();
            for (Map.Entry<CurrencyMapping, ImprovementDescription> entry : improvementDescriptions.entrySet()) {
                Improvement improvement = entry.getValue().improvement();
                Set<Map.Entry<CurrencyMapping, ImprovementDescription>> set = improvementMap.get(improvement);
                if (set == null) {
                    set = new HashSet<>();
                    set.add(entry);
                    improvementMap.put(improvement, set);
                } else {
                    set.add(entry);
                }
            }

            List<String> currencies = garden.getCurrencies();
            for (String fromCurrency : currencies) {
                for (String toCurrency : currencies) {
                    CurrencyMapping mapping = new CurrencyMapping(fromCurrency, toCurrency);
                    for (Improvement improvement : improvementMap.keySet()) {
                        if (improvement.getMapping().equals(mapping)) {
                            actions.add(String.format("%s : %s", mapping.asString(), improvement.getName()));
                        }
                    }
                }
            }

            for (Map.Entry<Improvement, Set<Map.Entry<CurrencyMapping, ImprovementDescription>>> entry :
                    improvementMap.entrySet()) {
                Improvement improvement = entry.getKey();
                if (improvement instanceof GeneratorImprovement(Generator generator, GeneratorState generatorState)) {
                    int count = generatorState.count();
                    for (Map.Entry<CurrencyMapping, ImprovementDescription> currencyEntry : entry.getValue()) {
                        CurrencyMapping currencyMapping = currencyEntry.getKey();
                        ImprovementDescription improvementDescription = currencyEntry.getValue();
                        actions.add(String.format("(%d - %s) Generator %s: %d -> %d : %s", i + 1,
                                currencyMapping.asString(), generator.getName(), count, count + 1,
                                improvementDescription.description()));

                    }
                    state.setGeneratorCount(generator, count + 1);
                    if (count == 0) {
                        addUnlocked(garden, state, actions);
                        possibleUnlock = true;
                    }
                } else if (improvement instanceof UpgradeImprovement upgradeImprovement) {
                    Upgrade upgrade = upgradeImprovement.upgrade();
                    for (Map.Entry<CurrencyMapping, ImprovementDescription> currencyEntry : entry.getValue()) {
                        UpgradeEffect effect = upgrade.getEffects().getFirst();
                        CurrencyMapping currencyMapping = currencyEntry.getKey();
                        ImprovementDescription improvementDescription = currencyEntry.getValue();
                        actions.add(String.format("(%d - %s) Upgrade %s (%s) : %s", i + 1, currencyMapping.asString(),
                                upgrade.getName(), effect.generator().getName(), improvementDescription.description()));
                    }
                    state.setUpgradeBought(upgrade);
                    state.updateGeneratorStates(garden);
                    addUnlocked(garden, state, actions);
                    possibleUnlock = true;
                } else {
                    throw new NullPointerException();
                }
                actions.add("");
            }
            if (improvementMap.size() > 1) {
                break;
            }
        }
    }

    private static void addUnlocked(Garden garden, GardenState state, List<String> actions) {
        for (Generator generator : garden.getUnlockedGenerators(state).reversed()) {
            if (state.getGeneratorState(generator).count() == 0) {
                actions.add(String.format(" *** unlocked generator %s: base cost %s, inc %.2f, base production %s",
                        generator.getName(), generator.getBaseCost().asString(), generator.getCompoundingCost(),
                        generator.getBaseProduction().multiplyBy(state.getBoost()).asString()));
            }
        }
        for (Upgrade upgrade : garden.getUnlockedUpgrades(state)) {
            if (!state.isUpgradeBought(upgrade)) {
                for (UpgradeEffect effect : upgrade.getEffects()) {
                    if (effect.speed() == 1) {
                        actions.add(String.format(" *** unlocked upgrade %s: %s efficiency %.2f, cost %s",
                                upgrade.getName(), effect.generator().getName(), effect.efficiency(),
                                upgrade.getCost().asString()));
                    } else if (effect.efficiency() == 1) {
                        actions.add(String.format(" *** unlocked upgrade %s: %s speed %.2f, cost %s",
                                upgrade.getName(), effect.generator().getName(), effect.speed(),
                                upgrade.getCost().asString()));
                    } else {
                        actions.add(String.format(" *** unlocked upgrade %s: %s efficiency %.2f, speed %.2f, cost %s",
                                upgrade.getName(), effect.generator().getName(), effect.efficiency(), effect.speed(),
                                upgrade.getCost().asString()));
                    }
                }
            }
        }
    }

    public static Map<String, Double> calculateProduction(Garden garden, GardenState state) {
        Map<String, Double> totalProduction = new HashMap<>();
        for (Generator generator : garden.getUnlockedGenerators(state).reversed()) {
            GeneratorState generatorState = state.getGeneratorState(generator);
            int count = generatorState.count();
            String currencyName = generator.getBaseProduction().currency();
            double baseProduction = generator.getBaseProduction().amount();
            float efficiency = generatorState.efficiency();
            double production;
            if (generator.isTimed()) {
                double productionPerCycle = baseProduction * efficiency * count;
                float speed = generatorState.speed();
                float cycleTime = generator.getBaseChargeTime() / speed;
                production = (!generator.isTimed() || generatorState.automated()) ? productionPerCycle / cycleTime : 0;
                System.out.printf("Generator %s: count %d (next %s), base %s, each %s, total per cycle %s in %s"
                                + ", total per second %s%n",
                        generator.getName(), count, generator.getCost(count).asString(),
                        new Amount(currencyName, baseProduction).asString(),
                        new Amount(currencyName, baseProduction * efficiency).asString(),
                        new Amount(currencyName, productionPerCycle).asString(),
                        DurationToolkit.durationString(cycleTime),
                        new Amount(currencyName, production).asString());
            } else {
                production = baseProduction * efficiency * count;
                System.out.printf("Generator %s: count %d (next %s), base %s, each %s, total %s%n",
                        generator.getName(), count, generator.getCost(count).asString(),
                        new Amount(currencyName, baseProduction).asString(),
                        new Amount(currencyName, baseProduction * efficiency).asString(),
                        new Amount(currencyName, production).asString());
            }
            totalProduction.put(currencyName, totalProduction.getOrDefault(currencyName, 0d) + production);
        }
        for (String currency : garden.getCurrencies()) {
            Double amount = totalProduction.get(currency);
            if (amount != null) {
                System.out.printf("%s production: %s / s%n", currency, new Amount(currency, amount).asString());
            }
        }
        System.out.println();
        return totalProduction;
    }

    public static Map<CurrencyMapping, ImprovementDescription> calculateImprovement(Garden garden, GardenState state) {
        Map<String, Double> totalProduction = calculateProduction(garden, state);

        Map<CurrencyMapping, List<Improvement>> improvements = new HashMap<>();
        for (Generator generator : garden.getUnlockedGenerators(state).reversed()) {
            improvements.computeIfAbsent(generator.getMapping(), _ -> new ArrayList<>()).add(
                    GeneratorImprovement.create(generator, state));
        }
        for (Upgrade upgrade : garden.getUnlockedUpgrades(state)) {
            if (!state.isUpgradeBought(upgrade)) {
                Improvement improvement = UpgradeImprovement.create(upgrade, state);
                improvements.computeIfAbsent(improvement.getMapping(), _ -> new ArrayList<>()).add(improvement);
            }
        }

        Map<CurrencyMapping, ImprovementDescription> improvementDescriptionMap = new HashMap<>();
        for (Map.Entry<CurrencyMapping, List<Improvement>> improvementsEntry : improvements.entrySet()) {
            ImprovementDescription improvementDescription =
                    calculateMappingImprovement(improvementsEntry, totalProduction, improvements);
            improvementDescriptionMap.put(improvementsEntry.getKey(), improvementDescription);
            System.out.println();
        }
        return improvementDescriptionMap;
    }

    private static ImprovementDescription calculateMappingImprovement(
            Map.Entry<CurrencyMapping, List<Improvement>> improvementsEntry, Map<String, Double> totalProduction,
            Map<CurrencyMapping, List<Improvement>> improvements) {
        System.out.printf("Improvements from %s to %s:%n", improvementsEntry.getKey().from(),
                improvementsEntry.getKey().to());
        List<Improvement> mappingImprovements = improvementsEntry.getValue();
        mappingImprovements.sort(Comparator.comparing(Improvement::getRatio));
        for (Improvement improvement : mappingImprovements.reversed()) {
            Amount cost = improvement.getCost();
            double ratio = improvement.getRatio();
            Amount increase = improvement.getIncrease();
            double time = cost.amount() / totalProduction.get(cost.currency());
            System.out.printf("%s: cost %s, increase %s, ratio %.7f, time %s%n",
                    improvement.getName(), cost.asString(), increase.asString(), ratio, durationString(time));
        }
        Improvement best = mappingImprovements.getLast();
        return calculateSpecificImprovement(totalProduction, improvements, best, "");
    }

    private static ImprovementDescription calculateSpecificImprovement(
            Map<String, Double> totalProduction, Map<CurrencyMapping, List<Improvement>> improvements,
            Improvement best, String description) {
        System.out.printf("Best: %s, (%s)%n", best.getName(), best.getMapping());
        String bestCostCurrency = best.getCost().currency();
        List<Improvement> sameCandidates = new ArrayList<>();
        List<Improvement> otherCandidates = new ArrayList<>();
        for (Map.Entry<CurrencyMapping, List<Improvement>> candidateEntry : improvements.entrySet()) {
            if (!candidateEntry.getKey().to().equals(bestCostCurrency)) {
                continue;
            }
            for (Improvement candidate : candidateEntry.getValue()) {
                if (candidate == best) {
                    continue;
                }
                if (candidate.getMapping().from().equals(bestCostCurrency)) {
                    sameCandidates.add(candidate);
                } else {
                    otherCandidates.add(candidate);
                }
                System.out.printf("  candidate %s%n", candidate.getName());
            }
        }
        Amount bestCost = best.getCost();
        double bestTime = bestCost.amount() / totalProduction.get(bestCost.currency());
        Improvement bestBefore = null;
        System.out.printf("%s: time %s%n", best.getName(), durationString(bestTime));
        for (Improvement candidate : sameCandidates.reversed()) {
            Amount candidateCost = candidate.getCost();
            double candidateTime = candidateCost.amount() / totalProduction.get(candidateCost.currency());
            Amount candidateIncrease = candidate.getIncrease();
            double bestImprovedTime = bestCost.amount() / (totalProduction.get(candidateIncrease.currency())
                    + candidateIncrease.amount());
            double totalTime = candidateTime + bestImprovedTime;
            System.out.printf("%s and %s: %s and %s = %s%n", candidate.getName(), best.getName(),
                    durationString(candidateTime), durationString(bestImprovedTime), durationString(totalTime));
            if (totalTime < bestTime) {
                bestBefore = candidate;
                break;
            }
        }
        for (Improvement improvement : otherCandidates) {
            Amount improvementCost = improvement.getCost();
            double improvementTime = improvementCost.amount() / totalProduction.get(improvementCost.currency());
            if (improvementTime < bestTime) {
                double timedProduction = improvementTime * totalProduction.get(bestCostCurrency);
                double improvedBestTime = improvementTime + (bestCost.amount() - timedProduction)
                        / (totalProduction.get(bestCostCurrency) + improvement.getIncrease().amount());
                System.out.printf("Improved time for %s: %s when %s after %s.%n", best.getName(),
                        durationString(improvedBestTime), improvement.getName(), durationString(improvementTime));
            }
        }
        if (bestBefore != null) {
            System.out.printf(">>> Do %s before %s%n", bestBefore.getName(), best.getName());
            String extendedDescription =
                    extendDescriptionWithBestBefore(totalProduction, best, description, bestBefore, bestCost, bestTime);
            return calculateSpecificImprovement(totalProduction, improvements, bestBefore, extendedDescription);
        }

        System.out.printf("*** Do %s by itself%n", best.getName());
        String endDescription = extendDescriptionWithBest(totalProduction, best, description, bestTime);
        return new ImprovementDescription(best, endDescription);
    }

    private static String extendDescriptionWithBest(
            Map<String, Double> totalProduction, Improvement best, String description, double bestTime) {
        String currency = best.getIncrease().currency();
        double productionBefore = totalProduction.get(currency);
        double productionAfter = productionBefore + best.getIncrease().amount();
        return description + String.format("%s (%s) : %s -> %s", best.getName(), durationString(bestTime),
                new Amount(currency, productionBefore).asString(), new Amount(currency, productionAfter).asString());
    }

    private static String extendDescriptionWithBestBefore(
            Map<String, Double> totalProduction, Improvement best, String description, Improvement bestBefore,
            Amount bestCost, double bestTime) {
        Amount bestBeforeCost = bestBefore.getCost();
        double bestBeforeTime = bestBeforeCost.amount() / totalProduction.get(bestBeforeCost.currency());
        Amount bestBeforeIncrease = bestBefore.getIncrease();
        double bestImprovedTime = bestCost.amount() / (totalProduction.get(bestBeforeIncrease.currency())
                + bestBeforeIncrease.amount());
        double totalTime = bestBeforeTime + bestImprovedTime;
        return description + String.format("%s (%s) -> %s (%s) + %s (%s) = (%s); ",
                best.getName(), durationString(bestTime), bestBefore.getName(), durationString(bestBeforeTime),
                best.getName(), durationString(bestImprovedTime), durationString(totalTime));
    }
}
