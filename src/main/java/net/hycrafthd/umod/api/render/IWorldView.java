package net.hycrafthd.umod.api.render;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IWorldView {
	
	public boolean showPower();
	
	public String[] textToAdd();
	
	public BlockPos getPos();
	
	public World getWorld();
}
