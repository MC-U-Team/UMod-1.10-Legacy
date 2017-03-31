package io.github.mc_umod.item.block;

import java.util.*;

import io.github.mc_umod.*;
import io.github.mc_umod.api.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

public class ItemBlockBase extends ItemBlock {
	
	public ItemBlockBase(Block block) {
		super(block);
		this.setFull3D();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		if (this.block instanceof IBlockInformation) {
			((IBlockInformation) this.block).addInformation(stack, player, tooltip, advanced);
			return;
		}
	}
	
}
