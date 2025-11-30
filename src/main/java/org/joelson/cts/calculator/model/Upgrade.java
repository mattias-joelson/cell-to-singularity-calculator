package org.joelson.cts.calculator.model;

public class Upgrade {

    private final Generator generator;
    private final String name;
    private boolean bought = false;

    public Upgrade(Generator generator, String name, boolean bought) {
        this.generator = generator;
        this.name = name;
        this.bought = bought;
    }

    public Upgrade(Generator generator, String name) {
        this(generator, name, false);
    }

    public Generator getGenerator() {
        return generator;
    }

    public String getName() {
        return name;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
