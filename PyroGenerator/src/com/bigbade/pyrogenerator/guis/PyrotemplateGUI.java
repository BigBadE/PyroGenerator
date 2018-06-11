package com.bigbade.pyrogenerator.guis;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;

import com.bigbade.pyrogenerator.PyroGenerator;

public class PyrotemplateGUI
{
  public static Inventory gui;

  public static int page = 0;
  public static String section = null;

  public static void setTemplate(int fpage) {
      page = fpage;
      Inventory edit = Bukkit.createInventory(null, 27, "Page " + fpage);
      for(int i = 0; i < 26; i++) {
          if (i == 18 && page != 1) {
              ItemStack back = new ItemStack(Material.PAPER, 1);
              ItemMeta meta = back.getItemMeta();
              meta.setDisplayName(ChatColor.GREEN + "Back a Page");
              back.setItemMeta(meta);
              edit.setItem(18, back);
          } else {
              PyroGenerator plugin = PyroGenerator.getInstance();
              int numb = i + (page*27);
              String slot = plugin.getConfig().getString(section + "." + numb);
              if (slot != null) {
                  ItemStack item = new ItemStack(Material.FIREWORK);
                  List<String> lore = new ArrayList<String>();
                  String time = plugin.getConfig().getString(
                          section + "." + numb + ".time");
                  if (time == null) {
                      lore.add(ChatColor.GRAY + "Lifetime: 1");
                  } else {
                      lore.add(ChatColor.GRAY + "Lifetime: " + time);
                  }
                  String explosion = plugin.getConfig().getString(
                          section + "." + numb + ".explosion");
                  if (explosion == null) {
                      lore.add(ChatColor.GRAY + "Explosion: Small Ball");
                  } else {
                      lore.add(ChatColor.GRAY + "Explosion: " + explosion);
                  }
                  String twinkle = plugin.getConfig().getString(
                          section + "." + numb + ".twinkle");
                  if (twinkle == null) {
                      lore.add(ChatColor.GRAY + "Twinkle: False");
                  } else {
                      lore.add(ChatColor.GRAY + "Twinkle: " + twinkle);
                  }
                  String trail = plugin.getConfig().getString(
                          section + "." + numb + ".trail");
                  if (trail == null) {
                      lore.add(ChatColor.GRAY + "Trail: False");
                  } else {
                      lore.add(ChatColor.GRAY + "Trail: " + trail);
                  }
                  String delay = plugin.getConfig().getString(
                          section + "." + numb + ".delay");
                  if (delay == null) {
                      lore.add(ChatColor.GRAY + "Delay: 0");
                  } else {
                      lore.add(ChatColor.GRAY + "Delay: " + delay);
                  }
                  String color = plugin.getConfig().getString(
                          section + "." + numb + ".color");
                  if (color == null) {
                      lore.add(ChatColor.GRAY + "Color: White");
                  } else {
                      String rcolor = null;
                      for (String scolor : color.split(",")) {
                          String strcolor = ColorEdit.colors.get(Integer.parseInt(scolor)).name().replace("_", " ").toLowerCase();
                          if (rcolor == null) {
                              rcolor = strcolor;
                          } else {
                              rcolor = rcolor + ", "
                                      + strcolor;
                          }
                      }
                      lore.add(ChatColor.GRAY + "Color(s): " + rcolor);
                  }
                  String fade = plugin.getConfig().getString(
                          section + "." + numb + ".fade");
                  if (fade == null) {
                      lore.add(ChatColor.GRAY + "Fade: White");
                  } else {
                      String rfade = null;
                      for (String sfade : fade.split(",")) {
                          String strfade = ColorEdit.colors.get(Integer.parseInt(sfade)).name().replace("_", " ").toLowerCase();
                          if (rfade == null) {
                              rfade = strfade;
                          } else {
                              rfade += ", " + strfade;
                          }
                      }
                      lore.add(ChatColor.GRAY + "Fade: " + rfade);
                  }
                  ItemMeta meta = item
                          .getItemMeta();
                  meta.setLore(lore);
                  item.setItemMeta(meta);
                  edit.setItem(i, item);
              } else {
                  edit.setItem(i, new ItemStack(Material.INK_SACK,
                          1, (short) 8));
              }
          }
          }
      ItemStack next = new ItemStack(Material.PAPER, 1);
      ItemMeta meta = next.getItemMeta();
      meta.setDisplayName(ChatColor.GREEN + "Next Page");
      next.setItemMeta(meta);
      edit.setItem(26, next);
      gui = edit;
  }
}
