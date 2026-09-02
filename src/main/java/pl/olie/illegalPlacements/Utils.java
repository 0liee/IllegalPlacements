package pl.olie.illegalPlacements;

import org.bukkit.Material;
import pl.olie.illegalPlacements.config.Config;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private final Config config;
    public Utils(IllegalPlacements plugin){
        this.config = plugin.getConfigValues();
    }
    List<Material> doubleBlocks = List.of(
            Material.TALL_GRASS,
            Material.LARGE_FERN,
            Material.SUNFLOWER,
            Material.LILAC,
            Material.ROSE_BUSH,
            Material.PEONY,
            Material.PITCHER_PLANT,
            Material.TALL_SEAGRASS
    );

    public boolean isIllegalBlock(Material type) {
        if (type == null || type.isAir()) return false;
        List<String> blocks = config.getBlocks();
        List<Material> materialList = new ArrayList<>();
        for(String block : blocks){
            materialList.add(Material.getMaterial(block.toUpperCase()));
        }
        return materialList.contains(type);
    }
    public boolean isDoubleBlock(Material type) {
        return doubleBlocks.contains(type);
    }
}
