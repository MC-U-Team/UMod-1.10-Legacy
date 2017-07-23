package io.github.mc_umod.api.render;

import net.minecraft.util.EnumFacing;

public interface IIOMode {
	
	public EnumFacing[] getFacingFromMode(int mode);
	
	public int getModeFromFacing(EnumFacing face);
	
	public void setModeToFace(EnumFacing face,int mode);
	
	public boolean hasModeForFaceing(EnumFacing face);
	
	public int getModeCount();
	
}
