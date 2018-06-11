package com.bigbade.pyrogenerator.guis;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bigbade.pyrogenerator.PyroGenerator;
import com.bigbade.pyrogenerator.events.TemplateClick;

public class FireworkEdit {
	public static Inventory gui;
	public static int selected = 0;

	public static void fireworkEdit(int numb) {
	    String section = PyrotemplateGUI.section;
		Inventory edit = Bukkit.createInventory(null, 45, "Page "
				+ PyrotemplateGUI.page);
		selected = numb;
		List<String> lore = new ArrayList<String>();
		FileConfiguration config = PyroGenerator.getInstance().getConfig();
		String time = config.getString(section + "." + numb + ".time");
		String explosion = config.getString(section + "." + numb + ".explosion");
		String twinkle = config.getString(section + "." + numb + ".twinkle");
		String trail = config.getString(section + "." + numb + ".trail");
		String delay = config.getString(section + "." + numb + ".delay");
		String color = config.getString(section + "." + numb + ".color");
		String fade = config.getString(section + "." + numb + ".fade");
		if (time == null) {
			config.set(section + "." + numb + ".time", "1");
			time = "1";
		}
		if (explosion == null) {
			config.set(section + "." + numb + ".explosion", "Small Ball");
			explosion = "Small Ball";
		}
		if (twinkle == null) {
			config.set(section + "." + numb + ".twinkle", "False");
			twinkle = "Flase";
		}
		if (trail == null) {
			config.set(section + "." + numb + ".trail", "False");
			trail = "False";
		}
		if (delay == null) {
			config.set(section + "." + numb + ".delay", "0");
			delay = "1";
		}
		if (color == null) {
			config.set(section + "." + numb + ".color", "15");
			color = "15";
		}
		if (fade == null) {
			config.set(section + "." + numb + ".fade", "15");
			fade = "15";
		}
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
		String rfade = null;
		for (String sfade : fade.split(",")) {
			String strfade = ColorEdit.colors.get(Integer.parseInt(sfade)).name().replace("_", "").toLowerCase();
			if (rfade == null) {
				rfade = strfade;
			} else {
				rfade = rfade + ", "
						+ strfade;
			}
		}
		lore.add(ChatColor.GRAY + "Lifetime: " + time);
		lore.add(ChatColor.GRAY + "Explosion: " + explosion);
		lore.add(ChatColor.GRAY + "Twinkle: " + twinkle);
		lore.add(ChatColor.GRAY + "Trail: " + trail);
		lore.add(ChatColor.GRAY + "Delay: " + delay);
		lore.add(ChatColor.GRAY + "Color(s): " + rfade);
		lore.add(ChatColor.GRAY + "Fade: " + rcolor);
		ItemStack firework = new ItemStack(Material.FIREWORK, 1);
		ItemMeta meta = firework.getItemMeta();
		meta.setDisplayName(ChatColor.GRAY + "Firework");
		meta.setLore(lore);
		firework.setItemMeta(meta);
		ItemStack itime = new ItemStack(Material.INK_SACK, 1, (short) 8);
		List<String> tlore = new ArrayList<String>();
		tlore.add(ChatColor.GRAY + "Increase firework lifetime by half a second.");
		tlore.add(ChatColor.GRAY + "Shift click to the change in delay every click");
		tlore.add(ChatColor.GRAY + "Change: " + TemplateClick.change2);
		tlore.add(ChatColor.GRAY + "Lifetime: " + time);
		ItemMeta tmeta = itime.getItemMeta();
		tmeta.setDisplayName(ChatColor.GRAY + "Lifetime");
		tmeta.setLore(tlore);
		itime.setItemMeta(tmeta);
		ItemStack exp = new ItemStack(Material.INK_SACK, 1, (short) 8);
		List<String> elore = new ArrayList<String>();
		elore.add(ChatColor.GRAY + "Sets the explosion shape.");
		elore.add(ChatColor.GRAY + "Explosion: " + explosion);
		ItemMeta emeta = exp.getItemMeta();
		emeta.setDisplayName(ChatColor.GRAY + "Explosion");
		emeta.setLore(elore);
		exp.setItemMeta(emeta);
		ItemStack itwinkle = new ItemStack(Material.INK_SACK, 1, (short) 8);
		List<String> twlore = new ArrayList<String>();
		twlore.add(ChatColor.GRAY + "Makes firework twinkle after exploding.");
		twlore.add(ChatColor.GRAY + "Twinkle: " + twinkle);
		ItemMeta twmeta = itwinkle.getItemMeta();
		twmeta.setDisplayName(ChatColor.GRAY + "Twinkle");
		twmeta.setLore(twlore);
		itwinkle.setItemMeta(twmeta);
		ItemStack itrail = new ItemStack(Material.INK_SACK, 1, (short) 8);
		List<String> trlore = new ArrayList<String>();
		trlore.add(ChatColor.GRAY + "Adds trail to firework explosion.");
		trlore.add(ChatColor.GRAY + "Trail: " + trail);
		ItemMeta trmeta = itrail.getItemMeta();
		trmeta.setDisplayName(ChatColor.GRAY + "Trail");
		trmeta.setLore(trlore);
		itrail.setItemMeta(trmeta);
		ItemStack idelay = new ItemStack(Material.INK_SACK, 1, (short) 8);
		List<String> dlore = new ArrayList<String>();
		dlore.add(ChatColor.GRAY + "Increase delay before the firework launch (In ticks).");
		dlore.add(ChatColor.GRAY + "Shift click to the change in delay every click");
		dlore.add(ChatColor.GRAY + "Change: " + TemplateClick.change);
		dlore.add(ChatColor.GRAY + "Delay: " + delay);
		ItemMeta dmeta = idelay.getItemMeta();
		dmeta.setDisplayName(ChatColor.GRAY + "Delay");
		dmeta.setLore(dlore);
		idelay.setItemMeta(dmeta);
		ItemStack icolor = new ItemStack(Material.INK_SACK, 1, (short) 8);
		List<String> clore = new ArrayList<String>();
		clore.add(ChatColor.GRAY + "Set the colors of the firework.");
		clore.add(ChatColor.GRAY + "Color(s): " + rcolor);
		ItemMeta cmeta = icolor.getItemMeta();
		cmeta.setDisplayName(ChatColor.GRAY + "Color");
		cmeta.setLore(clore);
		icolor.setItemMeta(cmeta);
		ItemStack ifade = new ItemStack(Material.INK_SACK, 1, (short) 8);
		List<String> flore = new ArrayList<String>();
		flore.add(ChatColor.GRAY + "Set the fades of the firework.");
		flore.add(ChatColor.GRAY + "Fade(s): " + rfade);
		ItemMeta fmeta = ifade.getItemMeta();
		fmeta.setDisplayName(ChatColor.GRAY + "Fade");
		fmeta.setLore(flore);
		ifade.setItemMeta(fmeta);
		edit.setItem(39, icolor);
		edit.setItem(41, ifade);
		edit.setItem(13, firework);
		edit.setItem(29, itime);
		edit.setItem(30, exp);
		edit.setItem(31, itwinkle);
		edit.setItem(32, itrail);
		edit.setItem(33, idelay);
		gui = edit;
	}
}