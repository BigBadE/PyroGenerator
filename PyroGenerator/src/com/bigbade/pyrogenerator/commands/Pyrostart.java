package com.bigbade.pyrogenerator.commands;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.lang.NumberFormatException;
import java.util.Collections;

import com.bigbade.pyrogenerator.guis.PyrotemplateGUI;
import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import com.bigbade.pyrogenerator.PyroGenerator;
import com.bigbade.pyrogenerator.guis.ColorEdit;

public class Pyrostart implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
	    if(args.length != 1) {
	        sender.sendMessage(ChatColor.RED + "/pyrostart (name)");
	        return true;
        }
	    ConfigurationSection section = PyroGenerator.getInstance().getConfig().getConfigurationSection(args[0]);
	    if(section == null) {
	        sender.sendMessage(ChatColor.RED + args[0] + " not found!");
	        return true;
        }
		PyroGenerator plugin = PyroGenerator.getInstance();
		FileConfiguration config = plugin.getConfig();
		ConfigurationSection slots = config.getConfigurationSection(args[0]);
		if(slots == null) {
		    sender.sendMessage(ChatColor.RED + args[0] + " not found!");
		    return true;
        }
        System.out.println();
		if (slots.getString("position").equals("none")) {
			Location location = ((Player) sender).getLocation();
			config.set(section.getName() + ".position", location.getX() + " " + location.getY()
					+ " " + location.getZ() + " " + location.getWorld());
			plugin.saveConfig();
		}
		String[] data = config.getString(section.getName() + ".position").split(" ");
		double x = Double.parseDouble(data[0]);
		double y = Double.parseDouble(data[1]);
		double z = Double.parseDouble(data[2]);
		World world = Bukkit.getWorld(data[3].replace("CraftWorld{name=", "").replace("}", ""));
		ArrayList<Integer> sections = new ArrayList<Integer>();
		for (String ksection : slots.getKeys(false)) {
            try {
                if(!ksection.equals("position")) {
                    sections.add(Integer.parseInt(ksection));
                }
            } catch(NumberFormatException e) {

            }
        }
        Collections.sort(sections);
		for(Integer numb : sections) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){   
                @Override
                public void run() {
                    ConfigurationSection ifirework = slots
                            .getConfigurationSection(String.valueOf(numb));
                        Firework f = (Firework) world.spawn(new Location(world, x, y, z),
                                Firework.class);
                        FireworkMeta meta = f.getFireworkMeta();
                        Type ftype = null;
                        for (Type type : Type.values()) {
                            String stype = ifirework.getString("explosion");
                            if (stype.equals("Small Ball")) {
                                stype = "Ball";
                            }
                            if (stype.equals("Large Ball")) {
                                stype = "Ball Large";
                            }
                            if (stype.replace(" ", "_")
                                    .equalsIgnoreCase(type.toString())) {
                                ftype = type;
                            }
                        }
                        List<Color> colors = new ArrayList<Color>();
                    String scolor = ifirework.getString("color");
                    for (String string : scolor.split(",")) {
                        DyeColor color = ColorEdit.colors.get(Integer.parseInt(string));
                        colors.add(color.getColor());
                    }
                        List<Color> fades = new ArrayList<Color>();
                        String sfade = ifirework.getString("fade");
                        for (String string : sfade.split(",")) {
                            DyeColor fade = ColorEdit.colors.get(Integer.parseInt(string));
                            fades.add(fade.getColor());
                        }
                        meta.addEffect(FireworkEffect
                                .builder()
                                .flicker(
                                        Boolean.parseBoolean(ifirework.getString("twinkle")
                                                .toLowerCase()))
                                .trail(Boolean.parseBoolean(ifirework.getString("trail")
                                        .toLowerCase())).with(ftype)
                                .withColor(colors)
                                .withFade(fades)
                                .build());
                        meta.setPower(config.getInt(section + ".lifetime"));
                        f.setFireworkMeta(meta);
                    }
            }, config.getLong(section.getName() + "." + numb + ".delay"));
		}
		sender.sendMessage(ChatColor.GREEN + "Pyro successfully started!");
		return true;
	}
}