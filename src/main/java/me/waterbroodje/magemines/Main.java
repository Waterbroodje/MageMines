package me.waterbroodje.magemines;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;
import java.util.TimeZone;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        TimeZone.setDefault( TimeZone.getTimeZone("GMT+1"));

        new BukkitRunnable() {
            @Override
            public void run() {
                Date now = new Date();

                if ((now.getHours() + ":" + now.getMinutes()).equalsIgnoreCase(getConfig().getString("reset-time"))) {
                    getConfig().getConfigurationSection("mines").getKeys(false).forEach(key -> {

                    });
                }
            }
        }.runTaskTimer(this, 0L, 1200L);
    }
}
