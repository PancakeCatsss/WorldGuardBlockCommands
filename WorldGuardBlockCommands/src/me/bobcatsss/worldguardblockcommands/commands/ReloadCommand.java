package me.bobcatsss.worldguardblockcommands.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.bobcatsss.worldguardblockcommands.WorldGuardBlockCommands;
import net.md_5.bungee.api.ChatColor;

public class ReloadCommand implements CommandExecutor {

	private final WorldGuardBlockCommands plugin;
	public ReloadCommand(WorldGuardBlockCommands pl) {
		this.plugin = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("worldguardblockcommands.command.reload")) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
			return true;
		}
		if(args.length == 0) {
			if(args[0].equalsIgnoreCase("reload")) {
				plugin.reloadConfig();
				sender.sendMessage(ChatColor.GREEN + "Config has been reloaded.");
				return true;
			}
		}
		sender.sendMessage(ChatColor.GRAY + "Usage: /worldguardblockcommands reload");
		return true;
	}
}
