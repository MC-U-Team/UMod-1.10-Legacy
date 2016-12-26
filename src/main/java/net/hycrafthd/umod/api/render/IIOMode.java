package net.hycrafthd.umod.api.render;

import net.minecraft.util.EnumFacing;

public interface IIOMode {
	
	public EnumFacing getFacing(int i);
	
	public void setFacing(int i, EnumFacing face);
	
	public int hasSomefacing(EnumFacing i);
	
	public int getModeCount();
	
}
