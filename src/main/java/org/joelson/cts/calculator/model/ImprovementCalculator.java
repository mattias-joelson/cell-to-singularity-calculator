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

    public static void singleCurrencyApproach(Garden garden, GardenState state, List<String> actions) {
        Set<String> unlocked = new HashSet<>();
        addUnlocked(garden, state, actions, unlocked);
        actions.add("");

        String currency = garden.getCurrencies().getFirst();
        CurrencyMapping mapping = new CurrencyMapping(currency, currency);

        multiSingleCurrencyApproach(garden, state.copy(), mapping, 100, 20, actions, unlocked);
    }

    public static void multiCurrencyApproach(Garden garden, GardenState state, List<String> actions) {
        Set<String> unlocked = new HashSet<>();
        addUnlocked(garden, state, actions, unlocked);
        actions.add("");

        List<String> currencies = garden.getCurrencies();
        for (String fromCurrency : currencies) {
            for (String toCurrency : currencies) {
                CurrencyMapping mapping = new CurrencyMapping(fromCurrency, toCurrency);
                List<String> mappingActions = new ArrayList<>();
                multiSingleCurrencyApproach(garden, state.copy(), mapping, 20, 15, mappingActions,
                        new HashSet<>(unlocked));
                if (!mappingActions.isEmpty()) {
                    actions.add(String.format(">>> from %s to %s <<<", fromCurrency, toCurrency));
                    actions.addAll(mappingActions);
                    actions.add(String.format(">>> from %s to %s <<<", fromCurrency, toCurrency));
                    actions.add("");
                }
            }
        }

    }

    private static void multiSingleCurrencyApproach(
            Garden garden, GardenState state, CurrencyMapping mapping, int rows, int unlockedRows, List<String> actions,
            Set<String> unlocked) {

        for (int i = 0; i < rows; i += 1) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            ImprovementDescription improvementDescription = calculateImprovement(garden, state, mapping);
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println();
            if (improvementDescription == null) {
                return;
            }
            Improvement improvement = improvementDescription.improvement();
            if (improvement instanceof GeneratorImprovement(Generator generator, GeneratorState generatorState)) {
                int count = generatorState.count();
                actions.add(String.format("(%d) Generator %s: %d -> %d : %s",
                        i + 1, generator.getName(), count, count + 1, improvementDescription.description()));
                state.setGeneratorCount(generator, count + 1);
                addUnlocked(garden, state, actions, unlocked);
                if (count == 0 && i >= unlockedRows) {
                    break;
                }
            } else if (improvement instanceof UpgradeImprovement upgradeImprovement) {
                Upgrade upgrade = upgradeImprovement.upgrade();
                UpgradeEffect effect = upgrade.getEffects().getFirst();
                actions.add(String.format("(%d) Upgrade %s (%s) : %s",
                        i + 1, upgrade.getName(), effect.generator().getName(), improvementDescription.description()));
                state.setUpgradeBought(upgrade);
                state.updateGeneratorStates(garden);
                addUnlocked(garden, state, actions, unlocked);
                if (i >= unlockedRows) {
                    break;
                }
            } else {
                throw new NullPointerException();
            }
        }
    }

    private static void addUnlocked(Garden garden, GardenState state, List<String> actions, Set<String> unlocked) {
        for (Generator generator : garden.getUnlockedGenerators(state).reversed()) {
            if (state.getGeneratorState(generator).count() == 0 && !unlocked.contains(generator.getName())) {
                actions.add(String.format(" *** unlocked generator %s: base cost %s, inc %.2f, base production %s",
                        generator.getName(), generator.getBaseCost().asString(), generator.getCompoundingCost(),
                        generator.getBaseProduction().multiplyBy(state.getBoost()).asString()));
                unlocked.add(generator.getName());
            }
        }
        for (Upgrade upgrade : garden.getUnlockedUpgrades(state)) {
            if (!state.isUpgradeBought(upgrade) && !unlocked.contains(upgrade.getName())) {
                for (UpgradeEffect effect : upgrade.getEffects()) {
                    String efficiency = (effect.efficiency() >= 1_000_000) ? String.format("%.2e",
                            effect.efficiency()) : String.format("%.2f", effect.efficiency());
                    if (effect.speed() == 1) {
                        actions.add(String.format(" *** unlocked upgrade %s: %s efficiency %s, cost %s",
                                upgrade.getName(), effect.generator().getName(), efficiency,
                                upgrade.getCost().asString()));
                    } else if (effect.efficiency() == 1) {
                        actions.add(String.format(" *** unlocked upgrade %s: %s speed %.2f, cost %s",
                                upgrade.getName(), effect.generator().getName(), effect.speed(),
                                upgrade.getCost().asString()));
                    } else {
                        actions.add(String.format(" *** unlocked upgrade %s: %s efficiency %s, speed %.2f, cost %s",
                                upgrade.getName(), effect.generator().getName(), efficiency, effect.speed(),
                                upgrade.getCost().asString()));
                    }
                }
                unlocked.add(upgrade.getName());
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

    private static ImprovementDescription calculateImprovement(
            Garden garden, GardenState state, CurrencyMapping mapping) {
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

        List<Improvement> improvementList = improvements.get(mapping);
        if (improvementList == null) {
            return null;
        }
        return calculateMappingImprovement(Map.entry(mapping, improvementList), totalProduction, improvements);
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
            Double totProd = totalProduction.get(cost.currency());
            if (totProd == null) {
                continue;
            }
            double time = cost.amount() / totProd;
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
        sameCandidates.sort(Comparator.comparing(Improvement::getRatio));
        otherCandidates.sort(Comparator.comparing(Improvement::getRatio));
        Amount bestCost = best.getCost();
        Double bestTotProd = totalProduction.get(bestCost.currency());
        if (bestTotProd == null) {
            return null;
        }
        double bestTime = bestCost.amount() / bestTotProd;
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
            Double totProd = totalProduction.get(improvementCost.currency());
            if (totProd == null) {
                continue;
            }
            double improvementTime = improvementCost.amount() / totProd;
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
