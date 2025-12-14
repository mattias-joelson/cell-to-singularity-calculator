package org.joelson.cts.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Upgrade implements Unlockable {

    private final String name;
    private final Amount cost;
    private final List<UpgradeEffect> effects;

    public Upgrade(String name, Amount cost) {
        this.name = name;
        this.cost = cost;
        this.effects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Amount getCost() {
        return cost;
    }

    public void addEffect(UpgradeEffect effect) {
        effects.add(effect);
    }

    public List<UpgradeEffect> getEffects() {
        return effects;
    }
}
