package pl.olie.illegalPlacements;

import org.bukkit.plugin.java.JavaPlugin;
import pl.olie.illegalPlacements.commands.Reload;
import pl.olie.illegalPlacements.config.Config;
import pl.olie.illegalPlacements.listeners.InteractListener;
import pl.olie.illegalPlacements.listeners.PhysicListener;

import java.util.Objects;

public final class IllegalPlacements extends JavaPlugin {
    private Config config;
    public Config getConfigValues(){return config;}
    @Override
    public void onEnable() {
        config = new Config(this);
        Utils utils = new Utils(this);
        Objects.requireNonNull(getCommand("illegalplacements")).setExecutor(new Reload(this));
        getServer().getPluginManager().registerEvents(new InteractListener(utils), this);
        getServer().getPluginManager().registerEvents(new PhysicListener(this, utils), this);
        saveDefaultConfig();
    }
    @Override
    public void onDisable() {
        saveConfig();
    }
}
