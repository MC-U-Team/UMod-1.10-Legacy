package io.github.mc_umod.api;

import java.util.*;

import net.minecraft.entity.player.*;
import net.minecraft.item.*;

public interface IBlockInformation {
	
	@SuppressWarnings("rawtypes")
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tip, boolean advanced);
	
}
