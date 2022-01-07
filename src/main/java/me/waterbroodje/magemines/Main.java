package me.waterbroodje.magemines;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
                        Location loc1 = new Location(Bukkit.getWorld(getConfig().getString("mines." + key + ".location-1.world")), getConfig().getInt("mines." + key + ".location-1.x"), getConfig().getInt("mines." + key + ".location-1.y"), getConfig().getInt("mines." + key + ".location-1.z"));
                        Location loc2 = new Location(Bukkit.getWorld(getConfig().getString("mines." + key + ".location-2.world")), getConfig().getInt("mines." + key + ".location-2.x"), getConfig().getInt("mines." + key + ".location-2.y"), getConfig().getInt("mines." + key + ".location-2.z"));
                        Cuboid cuboid = new Cuboid(loc1, loc2);
                        System.out.println(cuboid);
                        System.out.println(loc1);
                        System.out.println(loc2);
                        cuboid.blockList().forEach(block -> {
                            int random = (int)(Math.random() * 10 + 1);
                            if (random < 4) {
                                block.setType(Material.IRON_ORE);
                            } else if (random < 8) {
                                block.setType(Material.COAL_ORE);
                            } else {
                                block.setType(Material.DIAMOND);
                            }
                        });
                    });
                }
            }
        }.runTaskTimer(this, 0L, 1200L);
    }
}
