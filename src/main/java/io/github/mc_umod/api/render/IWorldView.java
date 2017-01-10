package io.github.mc_umod.api.render;

import net.minecraft.util.math.*;
import net.minecraft.world.*;

public interface IWorldView {
	
	public boolean showPower();
	
	public String[] textToAdd();
	
	public BlockPos getPos();
	
	public World getWorld();
}
