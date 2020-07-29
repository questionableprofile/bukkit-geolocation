package com.myplugintest.geoloc.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Test implements Listener {
    JavaPlugin ctx;
    Logger log;

    public Test(JavaPlugin ctx) {
        this.ctx = ctx;
        this.log = ctx.getLogger();
    }

    @EventHandler
    public void onLogin (PlayerLoginEvent event) {
        /*log.info("received login event");
        log.info(String.format("source address: %s", event.getAddress().toString()));
        log.info("allowing event");*/
        event.allow();
    }

    @EventHandler
    public void onPlayerMove (PlayerMoveEvent event) {
        Player player = event.getPlayer();
        StringBuilder playerData = new StringBuilder();

        Location playerLoc = player.getLocation();

        playerData.append(String.format("%s (%d): World: %s X: %f Y: %f Z: %f",
                player.getDisplayName(),
                player.getLevel(),
                playerLoc.getWorld().getName(),
                playerLoc.getX(), playerLoc.getY(), playerLoc.getZ())
        );

        final String result = playerData.toString();

        log.info(result);
        player.sendMessage(result);
    }
}

