package org.joelson.cts.calculator.model;

public record UpgradeEffect(Generator generator, float efficiency, float speed, boolean automated) {

    public UpgradeEffect(Generator generator, float efficiency) {
        this(generator, efficiency, 1, false);
    }

    public UpgradeEffect(Generator generator, float efficiency, float speed) {
        this(generator, efficiency, speed, false);
    }

    public UpgradeEffect(Generator generator, boolean automated) {
        this(generator, 1, 1, automated);
    }
}
