package xyz.tomszir.urpg.player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerHealth {

    // TODO: Add JavaDoc.

    private static HashMap<UUID, Double> health = new HashMap<>();

    public static void setHealth(UUID uuid, double value) {
        health.put(uuid, value);
    }

    public static double getHealth(UUID uuid) {
        return health.get(uuid);
    }

    public static void remove(UUID uuid) {
        health.remove(uuid);
    }
}
