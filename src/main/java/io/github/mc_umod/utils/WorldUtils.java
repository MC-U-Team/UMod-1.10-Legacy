package io.github.mc_umod.utils;

import net.minecraft.block.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class WorldUtils {
	
	public static boolean isBlockover(World w, BlockPos p) {
		for (int i = 1; i < 256; i++) {
			if (!(w.getBlockState(p.up(i)).getBlock() instanceof BlockAir)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isNight(World w) {
		WorldServer server = Minecraft.getMinecraft().getIntegratedServer().worldServers[0];
		return !server.isDaytime();
	}
}
