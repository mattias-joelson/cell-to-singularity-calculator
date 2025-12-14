package org.joelson.cts.calculator.model;

public class UpgradeEffect {

    private final Generator generator;
    private final float efficiency;

    public UpgradeEffect(Generator generator, float efficiency) {
        this.generator = generator;
        this.efficiency = efficiency;
    }

    public Generator getGenerator() {
        return generator;
    }

    public float getEfficiency() {
        return efficiency;
    }
}
