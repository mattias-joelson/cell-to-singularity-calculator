import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.CurrencyMapping;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.Improvement;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.joelson.cts.calculator.model.UpgradeImprovement;

static String MILK = "Milk";
static String CHEESE = "Cheese";

private static Amount milk(float amount) {
    return new Amount(MILK, amount);
}

private static Amount cheese(float amount) {
    return new Amount(CHEESE, amount);
}

private static Upgrade createUpgrade(Garden garden, Generator generator, String name, float cost, float efficiency) {
    Upgrade upgrade = new Upgrade(name, cheese(cost), true);
    upgrade.addEffect(new UpgradeEffect(generator, efficiency));
    garden.addUpgrade(upgrade);
    return upgrade;
}

void main() {
    Garden dairyDelights = new Garden("Dairy Delights");
    dairyDelights.addCurrency(MILK);
    dairyDelights.addCurrency(CHEESE);

    Generator milkGenerator = new Generator("Milk", milk(50), 1.4f, milk(1));
    dairyDelights.addGenerator(milkGenerator);
    Upgrade lactose = new Upgrade("Lactose", milk(250), true);
    lactose.addEffect(new UpgradeEffect(milkGenerator, 1.5f));
    dairyDelights.addUpgrade(lactose);
    createUpgrade(dairyDelights, milkGenerator, "Mozzarella", 2_000, 3);
    createUpgrade(dairyDelights, milkGenerator, "Cultures", 10_000, 1.5f);
    createUpgrade(dairyDelights, milkGenerator, "Camembert", 300_000, 1.5f);
    createUpgrade(dairyDelights, milkGenerator, "Rennet", 8e6f, 3);
    createUpgrade(dairyDelights, milkGenerator, "Muenster", 8e7f, 6);
    createUpgrade(dairyDelights, milkGenerator, "Danish", 3e9f, 6);
    createUpgrade(dairyDelights, milkGenerator, "Curds and Whey", 5e9f, 2);
    createUpgrade(dairyDelights, milkGenerator, "Salt", 6e13f, 6).setBought(false);

    Generator freshCheese = new Generator("Fresh Cheese", milk(1_000), 1.15f, cheese(1));
    dairyDelights.addGenerator(freshCheese);
    createUpgrade(dairyDelights, freshCheese, "Cottage Cheese", 150, 3);
    createUpgrade(dairyDelights, freshCheese, "Cream Cheese", 500_000, 5);
    createUpgrade(dairyDelights, freshCheese, "Kefir", 2e7f, 6);
    createUpgrade(dairyDelights, freshCheese, "Feta", 2e10f, 301).setBought(false);

    Generator softRipenedCheese = new Generator("Soft-Ripened Cheese", milk(50_000), 1.15f, cheese(100));
    dairyDelights.addGenerator(softRipenedCheese);
    createUpgrade(dairyDelights, softRipenedCheese, "Brie", 150_000, 1.5f);
    createUpgrade(dairyDelights, softRipenedCheese, "Chévre", 6e10f, 1.6f).setBought(false);

    Generator semiSoftCheese = new Generator("Semi-Soft Cheese", milk(1.2e6f), 1.25f, cheese(1000));
    dairyDelights.addGenerator(semiSoftCheese);
    createUpgrade(dairyDelights, semiSoftCheese, "Morbier", 7e6f, 1.5f);

    Generator blueCheese = new Generator("Blue Cheese", milk(1.5e7f), 1.15f, cheese(10_000));
    dairyDelights.addGenerator(blueCheese);
    createUpgrade(dairyDelights, blueCheese, "Roquefort", 2e8f, 1.5f);
    createUpgrade(dairyDelights, blueCheese, "Stilton", 7e8f, 2);
    createUpgrade(dairyDelights, blueCheese, "Gorgonzola", 1.25e9f, 1.5f);

    Generator semiFirmCheese = new Generator("Semi-Firm Cheese", milk(3e8f), 1.15f, cheese(1e6f));
    dairyDelights.addGenerator(semiFirmCheese);
    createUpgrade(dairyDelights, semiFirmCheese, "Halloumi", 1.5e10f, 1.5f).setBought(false);

    semiFirmCheese.setCount(1);
    blueCheese.setCount(13);
    semiSoftCheese.setCount(5);
    softRipenedCheese.setCount(14);
    freshCheese.setCount(38);
    milkGenerator.setCount(42);

    produceNext(dairyDelights);
}

private void produceNext(Garden garden) {
    Map<String, Float> totalProduction = new HashMap<>();
    for (String currency : garden.getCurrencies()) {
        totalProduction.put(currency, 0f);
    }
    for (Generator generator : garden.getGenerators()) {
        generator.updateEfficiency(garden);
    }
    for (Generator generator : garden.getGenerators().reversed()) {
        int count = generator.getCount();
        String currencyName = generator.getBaseProduction().currency();
        float baseProduction = generator.getBaseProduction().amount();
        float efficiency = generator.getEfficiency();
        float production = baseProduction * efficiency * count;
        System.out.printf("Generator %s: count %d (next %.2e), base %.2e %s, each %.2e %s, total %.3e %s%n",
                generator.getName(), count, generator.getCost(count).amount(), baseProduction, currencyName,
                baseProduction * efficiency, currencyName, production, currencyName);
        totalProduction.put(currencyName, totalProduction.get(currencyName) + production);
    }
    for (String currency : garden.getCurrencies()) {
        System.out.printf("%s production: %.2e%n", currency, totalProduction.get(currency));
    }
    System.out.println();

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
        System.out.printf("Improvements from %s to %s:%n", improvementsEntry.getKey().from(),
                improvementsEntry.getKey().to());
        List<Improvement> imp = improvementsEntry.getValue();
        imp.sort(Comparator.comparing(Improvement::getRatio));
        for (Improvement improvement : imp.reversed()) {
            Amount cost = improvement.getCost();
            float ratio = improvement.getRatio();
            Amount increase = improvement.getIncrease();
            float time = cost.amount() / totalProduction.get(cost.currency());
            System.out.printf("%s: cost %s, increase %s, ratio %.7f, time %s%n",
                    improvement.getName(), cost.asString(), increase.asString(), ratio, toString(time));
        }
        Improvement best = imp.getLast();
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
        System.out.printf("%s: time %s%n", best.getName(), toString(bestTime));
        for (Improvement candidate : candidates) {
            if (candidate == best) {
                continue;
            }
            Amount candidateCost = candidate.getCost();
            float candidateTime = candidateCost.amount() / totalProduction.get(candidateCost.currency());
            Amount candidateIncrease = candidate.getIncrease();
            float bestImprovedTime = bestCost.amount() / (totalProduction.get(candidateIncrease.currency())
                    + candidateIncrease.amount());
            float totalTime = candidateTime + bestImprovedTime;
            System.out.printf("%s and %s: %s and %s = %s%n", candidate.getName(), best.getName(),
                    toString(candidateTime), toString(bestImprovedTime), toString(totalTime));
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

private static String toString(float seconds) {
    Duration duration = Duration.ofSeconds(Math.round(seconds));
    if (duration.toDays() > 1) {
        return String.format("%d days %d:%02d:%02d", duration.toDays(), duration.toHoursPart(),
                duration.toMinutesPart(), duration.toSecondsPart());
    } else if (duration.toDays() > 0) {
        return String.format("1 day %d:%02d:%02d", duration.toHoursPart(), duration.toMinutesPart(),
                duration.toSecondsPart());
    } else {
        return String.format("%d:%02d:%02d", duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
    }
}
