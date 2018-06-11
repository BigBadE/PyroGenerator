package com.bigbade.pyrogenerator.commands;

import com.bigbade.pyrogenerator.PyroGenerator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import com.bigbade.pyrogenerator.guis.PyrotemplateGUI;

public class Pyrotemplate implements CommandExecutor {
	public boolean onCommand(CommandSender sender,
			Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
		    if(args.length != 1) {
		        sender.sendMessage(ChatColor.RED + "Incorrect amount of arguments!");
                return true;
		    }
            ConfigurationSection section = PyroGenerator.getInstance().getConfig().getConfigurationSection(args[0]);
			if(section == null) {
			    sender.sendMessage(ChatColor.RED + "No pyro by the name " + args[0] + " found!");
			    return true;
            }
		    PyrotemplateGUI.page = 1;
			PyrotemplateGUI.setTemplate(1);
			PyrotemplateGUI.section = section.getName();
			((Player) sender).openInventory(PyrotemplateGUI.gui);
			sender.sendMessage(ChatColor.GRAY + "Opening Firework Template.");
		}
		return true;
	}
}
