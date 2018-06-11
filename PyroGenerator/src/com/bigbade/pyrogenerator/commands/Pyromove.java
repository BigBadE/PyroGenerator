package com.bigbade.pyrogenerator.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.bigbade.pyrogenerator.PyroGenerator;

public class Pyromove implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender,
			Command cmd, String label, String[] args) {
			FileConfiguration config = PyroGenerator.getInstance().getConfig();
			Location location = ((Player) sender).getLocation();
			World world = Bukkit.getWorld(args[4]);
			if(world == null) {
			    sender.sendMessage(ChatColor.RED + "World not found!");
			    return true;
            }
			if(args.length == 5) {
				config.set(args[0] + ".position", args[1] + " " + args[2] + " " + args[3] + " " + world);
			} else if(args.length == 1) {
			    if(sender instanceof Player) {
                    config.set(args[0] + ".position", location.getX() + " " + location.getY() + " " + location.getZ() + " " + location.getWorld());
                }  else {
			        sender.sendMessage(ChatColor.RED + "/pyromove with only 1 argument can only be run by a player!");
                }
			} else {
				sender.sendMessage(ChatColor.RED + "/pyromove (name) (x) (y) (z) (world optional)\nIf no position is given, it will be set to your position.");
			}
			PyroGenerator.getInstance().saveConfig();
			sender.sendMessage(ChatColor.GREEN + args[0] + "was successfully moved to " + config.getString(args[0] + ".position").replace("CraftWorld{name=", "").replace("}", ""));
		return true;
	}

}
