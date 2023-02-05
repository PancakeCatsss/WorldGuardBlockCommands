package me.bobcatsss.worldguardblockcommands.handlers;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.bobcatsss.worldguardblockcommands.WorldGuardBlockCommands;

public class PlayerMoveHandler implements Listener {
	
	private final WorldGuardBlockCommands plugin;
	public PlayerMoveHandler(WorldGuardBlockCommands pl) {
		this.plugin = pl;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Location to = event.getTo();
		Location from = event.getFrom();
		//prevent spamming the commands if the player doesn't actually move rather moves their mouse
		//not sure if you want this or not, it wasn't mentioned in your post
		if(to.getBlockX() == from.getBlockX() && to.getBlockY() == from.getBlockY() && to.getBlockZ() == from.getBlockZ()) return;
		//check if the region loaded from the config is not null
		if(plugin.getWorldGuardRegionName() == null) return;
		//check if the player is about to stand on one of the blocks loaded from the config
		Material block = to.getBlock().getRelative(BlockFace.DOWN).getType();
		if(!plugin.hasCommands(block)) return;
		//check they are in the correct region
		if(!plugin.getWorldGuardUtils().isPlayerInRegion(plugin.getWorldGuardRegionName(), player)) return;
		//get the commands to run
		List<String> commands = plugin.getCommands(block);
		//run the commands
		for(String command : commands) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
		}
	}
}
