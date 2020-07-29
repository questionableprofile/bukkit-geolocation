package com.tldr.bukkit.geoloc.Job;

import com.google.gson.Gson;
import com.tldr.bukkit.geoloc.GeoData;
import com.tldr.bukkit.geoloc.GeolocationPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeoDataWriter extends BukkitRunnable {
    private static Gson gson = new Gson();

    private final GeolocationPlugin context;
    private final List<GeoData> data;

    public GeoDataWriter (GeolocationPlugin context, List<GeoData> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public void run () {
        try {
            FileWriter writer = new FileWriter(context.outputFile);
            writer.write(gson.toJson(data));
            writer.close();
        } catch (IOException e) {
            /*StringWriter exceptionString = new StringWriter();
            exceptionString
                    .append("Couldn't write to an output file: ")
                    .append(e.getLocalizedMessage())
                    .append("\n");*/

            context.getLogger().warning(String.format("Couldn't write to an output file: %s", e.getLocalizedMessage()));
            e.printStackTrace();
        }
    }
}
