package pl.olie.illegalPlacements.config;

import pl.olie.illegalPlacements.IllegalPlacements;

import java.util.List;

public class Config {
    public Config(IllegalPlacements plugin){
        this.plugin = plugin;
        loadConfig();
    }
    private final IllegalPlacements plugin;
    private List<String> blocks;
    private boolean physics;
    private List<String> disabledWorlds;
    private boolean requirePermission;
    public void loadConfig(){
        this.blocks = plugin.getConfig().getStringList("blocks");
        this.physics = plugin.getConfig().getBoolean("disable-physics");
        this.disabledWorlds = plugin.getConfig().getStringList("disabled-worlds");
        this.requirePermission = plugin.getConfig().getBoolean("require-permission");
    }
    public List<String> getBlocks(){return blocks;}
    public boolean getPhysics(){return physics;}
    public List<String> getDisabledWorlds(){return disabledWorlds;}
    public boolean isRequirePermission(){return requirePermission;}
}