package com.bigbade.pyrogenerator.commands;

import com.bigbade.pyrogenerator.PyroGenerator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class Pyrocreate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
                             String[] args) {
         if(args.length != 1) {
             sender.sendMessage(ChatColor.RED + "/pyrocreate (name)");
             return true;
         }
         FileConfiguration config = PyroGenerator.getInstance().getConfig();
         if(config.getConfigurationSection(args[0]) != null) {
             sender.sendMessage(ChatColor.GREEN + args[0] + " already exists!");
             return true;
         }
        config.set(args[0] + ".position", "none");
        sender.sendMessage(ChatColor.GREEN + args[0] + " was successfully created!");
        return true;
    }
}
