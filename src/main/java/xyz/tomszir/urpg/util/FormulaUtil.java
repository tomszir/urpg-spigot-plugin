package xyz.tomszir.urpg.util;

public class FormulaUtil {
    public static double getDamageReductionFromDefense(double defense) {
        return (100 -
            (defense <= 200
                ? defense * 0.25
                : 50.0 + (defense - 200.0) * (0.2495 / (1 + (defense - 200.0) / 200.0))))
            / 100;
    }
}
