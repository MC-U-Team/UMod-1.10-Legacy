package io.github.mc_umod.api.energy;

import net.minecraft.item.ItemStack;

public interface IBatteryProvider extends IPowerProvieder{
	
	public ItemStack getBattery();
	
    public boolean setBattery(ItemStack st);	
}