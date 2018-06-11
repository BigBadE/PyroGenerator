package com.bigbade.pyrogenerator.guis;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bigbade.pyrogenerator.PyroGenerator;

public class ColorEdit {
	public static Inventory gui;
	public static List<DyeColor> colors = new ArrayList<DyeColor>();
	public static Boolean fadebool;

	public static void colorInit() {
		colors.add(DyeColor.BLACK);
		colors.add(DyeColor.RED);
		colors.add(DyeColor.GREEN);
		colors.add(DyeColor.BROWN);
		colors.add(DyeColor.BLUE);
		colors.add(DyeColor.PURPLE);
		colors.add(DyeColor.CYAN);
		colors.add(DyeColor.SILVER);
		colors.add(DyeColor.GRAY);
		colors.add(DyeColor.PINK);
		colors.add(DyeColor.LIME);
		colors.add(DyeColor.YELLOW);
		colors.add(DyeColor.LIGHT_BLUE);
		colors.add(DyeColor.MAGENTA);
		colors.add(DyeColor.ORANGE);
		colors.add(DyeColor.WHITE);
	}
	public static void colorEdit(Boolean fade, int numb) {
	    String section = PyrotemplateGUI.section;
		fadebool = fade;
		String configSection;
		if (fade == true) {
			configSection = section + "." + numb + ".fade";
		} else {
			configSection = section + "." + numb + ".color";
		}
		Inventory edit = Bukkit.createInventory(null, 45, "Color Editor");
		FileConfiguration config = PyroGenerator.getInstance().getConfig();
		ItemStack firework = new ItemStack(Material.FIREWORK, 1);
		ItemMeta meta = firework.getItemMeta();
		List<String> lore = new ArrayList<String>();
		String heading;
		if (fade == true) {
			heading = "Fade: ";
		} else {
			heading = "Color(s): ";
		}
        String color = config.getString(
                configSection);
        if (color == null) {
            lore.add(ChatColor.GRAY + heading + "White");
        } else {
            String rcolor = null;
            for (String scolor : color.split(",")) {
                String strcolor = ColorEdit.colors.get(Integer.parseInt(scolor)).name().replace("_", "").toLowerCase();
                if (rcolor == null) {
                    rcolor = strcolor;
                } else {
                    rcolor = rcolor + ", "
                            + strcolor;
                }
            }
            lore.add(ChatColor.GRAY + heading + rcolor);
        }
		meta.setLore(lore);
		firework.setItemMeta(meta);
		for (int i = 0; i < 17; i++) {
			int colorno = i;
			if (i > 7) {
				colorno -= 1;
			}
			ItemStack item = new ItemStack(Material.INK_SACK, 1, (short) colorno);
			List<String> clore = new ArrayList<String>();
			String scolor = colors.get(colorno).name().replace("_", "").toLowerCase();
			Boolean contains = false;
			for(String string : color.split(",")) {
				if (Integer.parseInt(string) == colorno) {
					clore.add(ChatColor.GRAY + "Remove color " + scolor);
					contains = true;
				}
			}
			if(contains == false) {
				clore.add(ChatColor.GRAY + "Add color " + scolor);
			}
			ItemMeta cmeta = item.getItemMeta();
			cmeta.setLore(clore);
			item.setItemMeta(cmeta);
			if(i != 7) {
                edit.setItem(28 + i, item);
            }
		}
		gui = edit;
	}
}
