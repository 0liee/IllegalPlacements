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
    public void loadConfig(){
        this.blocks = plugin.getConfig().getStringList("blocks");
        this.physics = plugin.getConfig().getBoolean("disable-physics");
    }
    public List<String> getBlocks(){return blocks;}
    public boolean getPhysics(){return physics;}
}