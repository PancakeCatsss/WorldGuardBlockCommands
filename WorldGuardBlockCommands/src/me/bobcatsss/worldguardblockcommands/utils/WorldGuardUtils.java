package me.bobcatsss.worldguardblockcommands.utils;

import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class WorldGuardUtils {

	public boolean isPlayerInRegion(String regionName, Player player) {
		org.bukkit.Location location = player.getLocation();
		ApplicableRegionSet regions = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(player.getWorld())).getApplicableRegions(BlockVector3.at(location.getX(), location.getY(), location.getZ()));
		for(ProtectedRegion region : regions.getRegions()) {
			if(region.getId().equals(regionName)) return true;
		}
		return false;
	}
}
