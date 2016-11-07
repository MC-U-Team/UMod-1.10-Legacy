package net.hycrafthd.umod.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class WorldSavedDataInfectedStructure extends WorldSavedData {
	
	public WorldSavedDataInfectedStructure(String name) {
		super(name);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		
		return nbt;
	}
	
}
