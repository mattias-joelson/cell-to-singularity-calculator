package org.joelson.cts.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Upgrade {

    private final String name;
    private final Amount cost;
    private final List<UpgradeEffect> effects;
    private boolean bought = false;

    public Upgrade(String name, Amount cost, boolean bought) {
        this.name = name;
        this.cost = cost;
        this.effects = new ArrayList<>();
        this.bought = bought;
    }

    public Upgrade(String name, Amount cost) {
        this(name, cost, false);
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

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
