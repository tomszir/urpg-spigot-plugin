package xyz.tomszir.urpg.___old.util;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleUtil {
    public static void scatterParticlesOnPlayer(Player player, Particle particle) {
        scatterParticlesOnPlayer(player, particle, 6, 4);
    }

    public static void scatterParticlesOnPlayer(Player player, Particle particle, int amount) {
        scatterParticlesOnPlayer(player, particle, amount, 4);
    }

    public static void scatterParticlesOnPlayer(Player player, Particle particle, int amount, int randomness) {
        for (int i = 0; i < amount + Math.floor(Math.random() * randomness); i++) {
            Location location = player.getLocation();

            double x = location.getX() + Math.random() * 2 - 1;
            double y = location.getY() + 0.15 + (Math.random() / 2);
            double z = location.getZ() + Math.random() * 2 - 1;

            player.spawnParticle(particle, x, y, z, 1);
        }
    }
}
