package org.joelson.cts.calculator.model.builder;

import org.joelson.cts.calculator.model.Unlockable;

record UnresolvedUpgradeRequirement(Unlockable unlockable, String upgradeName) implements UnresolvedRequirement {

}
