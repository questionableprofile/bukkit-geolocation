package com.myplugintest.geoloc;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GeoData {
    public User user;
    public Position position;

    public GeoData (Player player) {
        this.user = new User(player);
        this.position = new Position(player.getLocation());
    }

    static class Position {
        public double x, y, z;

        public Position (Location loc) {
            x = loc.getX();
            y = loc.getY();
            z = loc.getZ();
        }
    }

    static class User {
        public String name;
        public int level;

        public User (Player player) {
            name = player.getDisplayName();
            level = player.getLevel();
        }
    }
}