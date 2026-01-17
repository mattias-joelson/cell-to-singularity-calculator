package org.joelson.cts.calculator.model;

public record UpgradeEffect(Generator generator, float efficiency, float speed, boolean automated) {

    public static UpgradeEffect withEfficiency(Generator generator, float efficiency) {
        return new UpgradeEffect(generator, efficiency, 1, false);
    }
}
