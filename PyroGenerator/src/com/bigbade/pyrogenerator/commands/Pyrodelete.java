package com.bigbade.pyrogenerator.commands;

import com.bigbade.pyrogenerator.PyroGenerator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class Pyrodelete implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
                             String[] args) {
        if(args.length != 1) {
            sender.sendMessage(ChatColor.RED + "/pyrodelete (name)");
        }
        FileConfiguration config = PyroGenerator.getInstance().getConfig();
        ConfigurationSection section = config.getConfigurationSection(args[0]);
        if(section == null) {
            sender.sendMessage(ChatColor.RED + args[0] + " not found!");
        } else {
            config.set(args[0], null);
        }
        sender.sendMessage(ChatColor.GREEN + args[0] + "was successfully deleted!");
        return true;
    }
}
