package io.github.mc_umod.api;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IBlockInformation {
	
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tip, boolean advanced);
	
}
