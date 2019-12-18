package ga.tumgaming.tumine.tuminemoney;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import ga.tumgaming.tumine.tuminemoney.listeners.BlockBreakListener;
import ga.tumgaming.tumine.tuminemoney.listeners.ClickListener;
import ga.tumgaming.tumine.tuminemoney.listeners.JoinListener;
import ga.tumgaming.tumine.tuminemoney.listeners.BankCommand;
import ga.tumgaming.tumine.tuminemoney.util.*;

public class TUMineMoney extends JavaPlugin {

	private static Config config;
	private static Plugin plugin;

	@Override
	public void onEnable() {
		TUMineMoney.plugin = this;

		config = new Config(plugin, "skills");
		
	    // Register our command "createInbox" (set an instance of your command class as executor)
	    this.getCommand("bank").setExecutor(new BankCommand());
		
		registerEvents();

		log("Plugin erfolgreich geladen");
	}

	/**
	 * logs a String in the console
	 *
	 * @param str logged String
	 */
	public void log(String str) {
		Logger.getLogger(str);
	}

	private static void registerEvents() {
		PluginManager pluginManager = Bukkit.getPluginManager();

		pluginManager.registerEvents(new JoinListener(), plugin);
		pluginManager.registerEvents(new ClickListener(), plugin);
		pluginManager.registerEvents(new BlockBreakListener(), plugin);
	}

}
