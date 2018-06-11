package com.bigbade.pyrogenerator.events;

import com.bigbade.pyrogenerator.PyroGenerator;
import com.bigbade.pyrogenerator.guis.ColorEdit;
import com.bigbade.pyrogenerator.guis.FireworkEdit;
import com.bigbade.pyrogenerator.guis.PyrotemplateGUI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TemplateClick implements Listener {
	public static int change = 1;
	public static int change2 = 1;

    private String openInventory = null;
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
	    String section = PyrotemplateGUI.section;
		FileConfiguration config = PyroGenerator.getInstance().getConfig();
		if (event.getInventory().getName().equals("Placeholder")) {
			int slot = event.getSlot();
			int page = PyrotemplateGUI.page;
			if ((slot == 18) && (page != 1)) {
				PyrotemplateGUI.page = page - 1;
			} else if (slot == 26) {
				PyrotemplateGUI.page = page + 1;
			} else {
				FireworkEdit.fireworkEdit(event.getSlot() * page);
				event.getWhoClicked().openInventory(FireworkEdit.gui);
				openInventory = "Edit";
			}
			event.setCancelled(true);
		}
		if (event.getInventory().getName()
				.equals("Page " + PyrotemplateGUI.page)) {
			int numb = FireworkEdit.selected;
			if (event.getSlot() == 29) {
				int time = Integer.parseInt(config.getString(section + "." + numb
						+ ".time"));
				if (event.isShiftClick()) {
					if (change2 == 1000) {
						change2 = 1;
					} else {
						change2 *= 10;
					}
				} else {
					if (time + change2 >= 129) {
						config.set(section + "." + numb + ".time", 0);
					} else {
						config.set(section + "." + numb + ".time", time + change2);
					}
				}
			}
			if (event.getSlot() == 30) {
				String exp = config.getString(section + "." + numb + ".explosion");
				if (exp.equals("Small Ball")) {
					config.set(section + "." + numb + ".explosion", "Large Ball");
				}
				if (exp.equals("Large Ball")) {
					config.set(section + "." + numb + ".explosion", "Star");
				}
				if (exp.equals("Star")) {
					config.set(section + "." + numb + ".explosion", "Creeper");
				}
				if (exp.equals("Creeper")) {
					config.set(section + "." + numb + ".explosion", "Burst");
				}
				if (exp.equals("Burst")) {
					config.set(section + "." + numb + ".explosion", "Small Ball");
				}
			}
			if (event.getSlot() == 31) {
				String twinkle = config.getString(section + "." + numb + ".twinkle");
				if (twinkle.equals("True")) {
					config.set(section + "." + numb + ".twinkle", "False");
				} else {
					config.set(section + "." + numb + ".twinkle", "True");
				}
			}
			if (event.getSlot() == 32) {
				String trail = config.getString(section + "." + numb + ".trail");
				if (trail.equals("True")) {
					config.set(section + "." + numb + ".trail", "False");
				} else {
					config.set(section + "." + numb + ".trail", "True");
				}
			}
			if (event.getSlot() == 33) {
				int delay = Integer.parseInt(config.getString(section + "." + numb
						+ ".delay"));
				if (event.isShiftClick()) {
					if (change == 1000000) {
						change = 1;
					} else {
						change *= 10;
					}
				} else {
					if (delay + change >= 9999999) {
						config.set(section + "." + numb + ".delay", 0);
					} else {
						config.set(section + "." + numb + ".delay", delay + change);
					}
				}
			}
			if (event.getSlot() == 39) {
				ColorEdit.colorEdit(false, FireworkEdit.selected);
				event.getWhoClicked().openInventory(ColorEdit.gui);
                openInventory = "Color";
			}
			if (event.getSlot() == 41) {
				ColorEdit.colorEdit(true, FireworkEdit.selected);
				event.getWhoClicked().openInventory(ColorEdit.gui);
                openInventory = "Fade";
			}
			PyroGenerator.getInstance().saveConfig();
			FireworkEdit.fireworkEdit(numb);
			if(openInventory.equals("Edit")) event.getWhoClicked().openInventory(FireworkEdit.gui);
			event.setCancelled(true);
		}
		if(event.getInventory().getName().equals("Color Editor")) {
			Boolean fade = ColorEdit.fadebool;
			int numb = FireworkEdit.selected;
			for (int i = 1; i < 18; i++) {
				if (i == 8) {
					continue;
				}
				if (event.getSlot() == i + 27) {
					int colorno = i - 1;
					if (i > 8) {
						colorno -= 1;
					}
					String colors;
					String fades;
					if (fade) {
						fades = config.getString(section + "." + numb + ".fade");
						if(fades == null) {
                            config.set(section + "." + numb + ".fade", 15);
                        } else {
                            Boolean skip = false;
                            for(String string : fades.split(",")) {
                                if (Integer.parseInt(string) == colorno) {
                                    skip = true;
                                }
                            }
                            if(skip == false) {
                                config.set(section + "." + numb + ".fade", fades + "," + colorno);
                            } else {
                                String nfade = "";
                                for(String string : fades.split(",")) {
                                    if (Integer.parseInt(string) != colorno) {
                                        if(nfade == "" || nfade == null) {
                                            nfade = string;
                                        } else {
                                            nfade = nfade + "," + string;
                                        }
                                    }
                                }
								if(nfade.equals("")) {
									config.set(section + "." + numb + ".fade", "15");
								} else {
									config.set(section + "." + numb + ".fade", nfade);
								}
                            }
                        }
					} else {
                        colors = config.getString(section + "." + numb + ".color");
                        if(colors == null) {
                            config.set(section + "." + numb + ".color", 15);
                        } else {
                            Boolean skip = false;
                            for(String string : colors.split(",")) {
                                if (Integer.parseInt(string) == colorno) {
                                    skip = true;
                                }
                            }
                            if(skip == false) {
                                config.set(section + "." + numb + ".color", colors + "," + colorno);
                            } else {
                                String ncolors = "";
                                for(String string : colors.split(",")) {
                                    if (Integer.parseInt(string) != colorno) {
                                        if(ncolors == "" || ncolors == null) {
                                            ncolors = string;
                                        } else {
                                            ncolors = ncolors + "," + string;
                                        }
                                    }
                                }
                                if(ncolors.equals("")) {
									config.set(section + "." + numb + ".color", "15");
								} else {
									config.set(section + "." + numb + ".color", ncolors);
								}
                            }
                        }
					}
				}
			}
			PyroGenerator.getInstance().saveConfig();
			ColorEdit.colorEdit(fade, FireworkEdit.selected);
			event.getWhoClicked().openInventory(ColorEdit.gui);
			event.setCancelled(true);
		}
	}
}