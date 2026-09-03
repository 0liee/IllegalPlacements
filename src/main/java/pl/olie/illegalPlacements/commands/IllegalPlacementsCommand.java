package pl.olie.illegalPlacements.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import pl.olie.illegalPlacements.IllegalPlacements;
import pl.olie.illegalPlacements.Utils;
import pl.olie.illegalPlacements.config.Config;

import java.util.ArrayList;
import java.util.List;

public class IllegalPlacementsCommand implements CommandExecutor, TabCompleter {

    private final IllegalPlacements plugin;
    private final Config config;
    private final Utils utils;
    public IllegalPlacementsCommand(IllegalPlacements plugin, Utils utils){
        this.plugin = plugin;
        this.config = plugin.getConfigValues();
        this.utils = utils;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(args.length == 1 ){
            if(args[0].equalsIgnoreCase("reload")){
                if(!sender.hasPermission("illegalplacement.reload") || !sender.hasPermission("illegalplacements.*")){
                    return true;
                }
                plugin.reloadConfig();
                config.loadConfig();
                utils.reload();
                sender.sendMessage("§aReloaded config!");
            }else if (args[0].equalsIgnoreCase("toggle") || sender.hasPermission("illegalplacements.*")){
                boolean current = config.isEnabledPlacements();
                config.setBoolean("enable-illegalplacements", !current);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender,Command command,String label, String[] args){
        if(args.length == 1){
            if(!sender.hasPermission("illegalplacements.reload") || !sender.hasPermission("illegalplacements.*")){
                return List.of();
            }
            List<String> tab = new ArrayList<>();
            tab.add("reload");
            tab.add("toggle");
            return tab;
        }
        return List.of();
    }
}
