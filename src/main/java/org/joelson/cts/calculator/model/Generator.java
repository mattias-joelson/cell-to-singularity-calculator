package org.joelson.cts.calculator.model;

public interface Generator extends Unlockable {

    String name();

    Amount baseCost();

    float compoundingCost();

    Amount baseProduction();

    default Amount getCost(int level) {
        return new Amount(baseCost().currency(), (float) (baseCost().amount() * Math.pow(compoundingCost(), level)));
    }

    default CurrencyMapping getMapping() {
        return new CurrencyMapping(baseCost().currency(), baseProduction().currency());
    }

    @Override
    default String getName() {
        return name();
    }
}
