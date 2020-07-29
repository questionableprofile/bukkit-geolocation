package com.myplugintest.geoloc;

import com.myplugintest.geoloc.Job.GeoDataCollector;
import com.myplugintest.geoloc.Listener.Test;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class GeolocationPlugin extends JavaPlugin {
    public File outputFile;

    @Override
    public void onEnable() {
        String outDir = getConfig().getString("outputDirectory");
        if (outDir == null) {
            getLogger().warning("Configuration didn't provide 'outputDirectory' property. Plugin won't work.");
            saveDefaultConfig();
            return;
        }

        File outDirFile = new File(outDir);
        if (outDirFile.isDirectory()) {
            outputFile = new File(outDirFile.getAbsoluteFile() + File.separator + "geodata.json");
            registerJobs();
            registerListeners();
            getLogger().info(String.format("Geolocation plugin loaded. Data output file: %s", outputFile.getAbsoluteFile()));
        }
        else {
            String error = "Incorrect configuration. ";
            if (!outDirFile.exists())
                error = error.concat("Directory doesn't exist");
            else if (!outDirFile.isDirectory())
                error = error.concat("Path have to be an existing directory");
            else
                error = error.concat("Unknown error");
            getLogger().warning(error);
        }
    }

    private void registerListeners () {
//        getServer().getPluginManager().registerEvents(new Test(this), this);
    }

    private void registerJobs () {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new GeoDataCollector(this), 0L, 100L);
    }
}