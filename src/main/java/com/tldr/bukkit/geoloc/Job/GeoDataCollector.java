package com.tldr.bukkit.geoloc.Job;

import com.tldr.bukkit.geoloc.GeoData;
import com.tldr.bukkit.geoloc.GeolocationPlugin;

import java.util.List;
import java.util.stream.Collectors;

public class GeoDataCollector implements Runnable {
    private GeolocationPlugin context;

    public GeoDataCollector (GeolocationPlugin context) {
        this.context = context;
    }

    @Override
    public void run() {
        final List<GeoData> data = context.getServer().getOnlinePlayers().stream().map(GeoData::new).collect(Collectors.toList());
        new GeoDataWriter(context, data).runTaskAsynchronously(context);
    }
}
