package me.bobcatsss.worldguardblockcommands.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandsTab implements TabCompleter {
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if(!sender.hasPermission("worldguardblockcommands.command.reload")) {
			return new ArrayList<>();
		}
		if(args.length == 0) {
			return Arrays.asList("reload");
		}
		return new ArrayList<>();
	}
}
