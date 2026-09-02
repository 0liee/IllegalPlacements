package pl.olie.illegalPlacements;

import org.bukkit.Material;
import pl.olie.illegalPlacements.config.Config;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private final Config config;
    private final List<Material> doubleBlocksMaterials = new ArrayList<>();
    private final List<Material> illegalBlocksMaterials = new ArrayList<>();
    public Utils(IllegalPlacements plugin){
        this.config = plugin.getConfigValues();
        loadDoubleBlocks();
        loadIllegalBLocks();
    }
    List<String> doubleBlocks = List.of(
            "TALL_GRASS",
            "LARGE_FERN",
            "SUNFLOWER",
            "LILAC",
            "ROSE_BUSH",
            "PEONY",
            "PITCHER_PLANT",
            "TALL_SEAGRASS"
    );

    private void loadDoubleBlocks(){
        for(String name : doubleBlocks){
            Material material = Material.getMaterial(name);
            if(material != null){
                doubleBlocksMaterials.add(material);
            }
        }
    }
    private void loadIllegalBLocks(){
        for (String name : config.getBlocks()){
            Material material = Material.getMaterial(name);
            if(material != null){
                illegalBlocksMaterials.add(material);
            }
        }
    }

    public boolean isIllegalBlock(Material type) {
        if (type == null || type.isAir()) return false;
        return illegalBlocksMaterials.contains(type);
    }
    public boolean isDoubleBlock(Material type) {
        return doubleBlocksMaterials.contains(type);
    }
    public void reload(){
        illegalBlocksMaterials.clear();
        loadIllegalBLocks();
    }
}
