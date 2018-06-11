package com.bigbade.pyrogenerator;

import com.bigbade.pyrogenerator.commands.*;
import com.bigbade.pyrogenerator.guis.ColorEdit;
import com.bigbade.pyrogenerator.events.TemplateClick;

public class PyroGenerator extends org.bukkit.plugin.java.JavaPlugin {
	private static PyroGenerator instance;

	public PyroGenerator() {
		instance = this;
	}

	public static PyroGenerator getInstance() {
		return instance;
	}

	public void onEnable() {
	    ColorEdit.colorInit();
		saveDefaultConfig();
		getCommand("Pyrotemplate").setExecutor(new Pyrotemplate());
        getCommand("Pyrodelete").setExecutor(new Pyrodelete());
		getCommand("Pyrostart").setExecutor(new Pyrostart());
		getCommand("Pyromove").setExecutor(new Pyromove());
        getCommand("Pyrocreate").setExecutor(new Pyrocreate());
		getServer().getPluginManager()
				.registerEvents(new TemplateClick(), this);
	}

	public void onDisable() {
		this.saveConfig();
	}
}
