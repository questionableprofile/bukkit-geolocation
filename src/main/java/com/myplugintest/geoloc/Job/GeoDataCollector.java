package com.myplugintest.geoloc.Job;

import com.myplugintest.geoloc.GeoData;
import com.myplugintest.geoloc.GeolocationPlugin;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;
import java.util.stream.Collectors;

public class GeoDataCollector implements Runnable {
    GeolocationPlugin context;

    public GeoDataCollector (GeolocationPlugin context) {
        this.context = context;
    }

    @Override
    public void run() {
//        context.getLogger().info("SYNC TASK");
        /*context.getServer().getOnlinePlayers().forEach(player -> {
            StringBuilder playerData = new StringBuilder();

            Location playerLoc = player.getLocation();

            playerData.append(String.format("%s (%d): World: %s X: %f Y: %f Z: %f",
                    player.getDisplayName(),
                    player.getLevel(),
                    playerLoc.getWorld().getName(),
                    playerLoc.getX(), playerLoc.getY(), playerLoc.getZ())
            );

            final String result = playerData.toString();

            context.getLogger().info(result);
            player.sendMessage(result);
        });*/
//        context.getLogger().info("starting Writer task");
        final List<GeoData> data = context.getServer().getOnlinePlayers().stream().map(GeoData::new).collect(Collectors.toList());
        new GeoDataWriter(context, data).runTaskAsynchronously(context);
    }
}
