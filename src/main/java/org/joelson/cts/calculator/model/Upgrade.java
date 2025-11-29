package org.joelson.cts.calculator.model;

public class Upgrade {

    private final Generator generator;
    private final String name;

    public Upgrade(Generator generator, String name) {
        this.generator = generator;
        this.name = name;
    }

    public Generator getGenerator() {
        return generator;
    }

    public String getName() {
        return name;
    }
}
