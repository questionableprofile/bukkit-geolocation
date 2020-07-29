package com.tldr.bukkit.geoloc;

import com.tldr.bukkit.geoloc.Job.GeoDataCollector;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class GeolocationPlugin extends JavaPlugin {
    public File outputFile;
    public long updateTicks;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        String outDir = getConfig().getString("outputDirectory");
        if (outDir == null) {
            getLogger().warning("Configuration didn't provide 'outputDirectory' property. Plugin won't work.");
            return;
        }

        updateTicks = getConfig().getLong("updateTicks");

        if (updateTicks <= 0) {
            getLogger().warning("Update ticks must be more than zero. Using default value 100L");
            updateTicks = 100L;
            return;
        }

        File outDirFile = new File(outDir);
        if (outDirFile.isDirectory()) {
            outputFile = new File(outDirFile.getAbsoluteFile() + File.separator + "geodata.json");
            registerJobs();
            registerListeners();
            getLogger().info(String.format("Geolocation plugin loaded. Data output: %s", outputFile.getAbsoluteFile()));
        }
        else {
            String error = "Incorrect configuration. ";
            if (!outDirFile.exists())
                error = error.concat(String.format("Directory '%s' doesn't exist", outDirFile.getAbsolutePath()));
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
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new GeoDataCollector(this), 0L, updateTicks);
    }
}