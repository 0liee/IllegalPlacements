package pl.olie.illegalPlacements.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import pl.olie.illegalPlacements.IllegalPlacements;
import pl.olie.illegalPlacements.Utils;
import pl.olie.illegalPlacements.config.Config;

public class PhysicListener implements Listener {
    private final Utils utils;
    private final Config config;
    public PhysicListener(IllegalPlacements plugin, Utils utils){
        this.utils = utils;
        this.config = plugin.getConfigValues();
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPhysics(BlockPhysicsEvent event) {
        if(config.getPhysics()){
            Material type = event.getBlock().getType();
            if (utils.isIllegalBlock(type)) {
                event.setCancelled(true);
            }
        }
    }
}
