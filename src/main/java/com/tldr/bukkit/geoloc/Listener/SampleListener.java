package com.tldr.bukkit.geoloc.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/*
* Unused
* */
public class SampleListener implements Listener {
    JavaPlugin ctx;
    Logger log;

    public SampleListener(JavaPlugin ctx) {
        this.ctx = ctx;
        this.log = ctx.getLogger();
    }

    @EventHandler
    public void onLogin (PlayerLoginEvent event) {}

    @EventHandler
    public void onPlayerMove (PlayerMoveEvent event) {}
}

