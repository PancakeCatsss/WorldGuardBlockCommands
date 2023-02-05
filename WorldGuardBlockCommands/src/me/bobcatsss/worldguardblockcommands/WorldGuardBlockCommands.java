package me.bobcatsss.worldguardblockcommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.bobcatsss.worldguardblockcommands.commands.ReloadCommand;
import me.bobcatsss.worldguardblockcommands.handlers.PlayerMoveHandler;
import me.bobcatsss.worldguardblockcommands.utils.WorldGuardUtils;

public class WorldGuardBlockCommands extends JavaPlugin {
	
	private WorldGuardUtils worldGuardUtils;
	private Map<Material, List<String>> commands = new HashMap<>();
	private String worldGuardRegionName = null;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		worldGuardUtils = new WorldGuardUtils();
		reloadConfig();
		getCommand("worldguardblockcommands").setExecutor(new ReloadCommand(this));
		registerHandlers();
	}
	
	private void registerHandlers() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerMoveHandler(this), this);
	}
	
    public void reloadConfig() {
    	commands.clear();
		worldGuardRegionName = getConfig().getString("Worldguard-Region", null);
		if(getConfig().isConfigurationSection("Blocks")) {
			for(String key : getConfig().getConfigurationSection("Blocks").getKeys(false)) {
				if(!isValidMaterial(key)) {
					getLogger().log(Level.WARNING, "Unknown Material: " + key);
					continue;
				}
				Material material = Material.matchMaterial(key);
				List<String> blockCommands = getConfig().getStringList("Blocks." + key);
				commands.put(material, blockCommands);
			}
		}
	}
	
	public WorldGuardUtils getWorldGuardUtils() {
		return worldGuardUtils;
	}
	
	public String getWorldGuardRegionName() {
		return worldGuardRegionName;
	}
	
	public boolean hasCommands(Material material) {
		return commands.containsKey(material);
	}
	
	public List<String> getCommands(Material material) {
		return commands.get(material);
	}
	
	private boolean isValidMaterial(String material) {
		for(Material materials : Material.values()) {
			if(material.equalsIgnoreCase(materials.name())) return true;
		}
		return false;
	}
}
