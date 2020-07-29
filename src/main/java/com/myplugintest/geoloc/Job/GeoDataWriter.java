package com.myplugintest.geoloc.Job;

import com.google.gson.Gson;
import com.myplugintest.geoloc.GeoData;
import com.myplugintest.geoloc.GeolocationPlugin;
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
//        File outFile = new File(context.outputPath);
//        context.getLogger().info(String.format("writing geo data to: %s", outFile.getAbsoluteFile()));
        try {
            FileWriter writer = new FileWriter(context.outputFile);
            writer.write(gson.toJson(data));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
