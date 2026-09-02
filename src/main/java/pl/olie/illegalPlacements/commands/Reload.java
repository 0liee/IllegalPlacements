package pl.olie.illegalPlacements.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import pl.olie.illegalPlacements.IllegalPlacements;
import pl.olie.illegalPlacements.config.Config;

import java.util.ArrayList;
import java.util.List;

public class Reload implements CommandExecutor, TabCompleter {
    private final IllegalPlacements plugin;
    private final Config config;
    public Reload(IllegalPlacements plugin){
        this.plugin = plugin;
        this.config = plugin.getConfigValues();
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(args.length == 1){
            if(!sender.hasPermission("illegalplacement.reload")){
                return true;
            }
            plugin.reloadConfig();
            config.loadConfig();
            sender.sendMessage("§aReloaded config!");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender,Command command,String label, String[] args){
        if(args.length == 1){
            if(!sender.hasPermission("illegalplacement.reload")){
                return List.of();
            }
            List<String> tab = new ArrayList<>();
            tab.add("reload");
            return tab;
        }
        return List.of();
    }
}
