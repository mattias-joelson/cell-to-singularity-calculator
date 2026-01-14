package org.joelson.cts.calculator.model;

public record UpgradeEffect(Generator generator, float efficiency, float speed) {

    public UpgradeEffect(Generator generator, float efficiency) {
        this(generator, efficiency, 1);
    }
}
