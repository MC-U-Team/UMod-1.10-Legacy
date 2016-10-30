package net.hycrafthd.umod.utils;

import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

public class WorldUtils {
	
	
	public static boolean isBlockover(World w, BlockPos p) {
		for (int i = 1; i < 250; i++) {
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
